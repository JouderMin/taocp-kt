package prog2_1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Card 相关类的单元测试s
 */
class CardTest {

    /**
     * 测试 Suit 枚举类的功能
     */
    @Test
    fun testSuit() {
        // 测试枚举值数量
        assertThat(Suit.entries.size).isEqualTo(4)

        // 测试每个枚举值的 value 属性和 toString 方法
        val expectedValues = listOf("H", "D", "C", "S")
        Suit.entries.forEachIndexed { index, suit ->
            assertThat(suit.value).isEqualTo(expectedValues[index])
            assertThat(suit.toString()).isEqualTo(expectedValues[index])
        }
    }

    /**
     * 测试 Rank 枚举类的功能
     */
    @Test
    fun testRank() {
        // 测试枚举值数量
        assertThat(Rank.entries.size).isEqualTo(13)

        // 测试每个枚举值的 value 属性和 toString 方法
        val expectedValues = (1..13).toList()
        Rank.entries.toTypedArray().forEachIndexed { index, rank ->
            assertThat(rank.value).isEqualTo(expectedValues[index])
            assertThat(rank.toString()).isEqualTo(expectedValues[index].toString())
        }

        // 测试 Comparable 接口实现
        val ranks = Rank.entries.toTypedArray()
        for (i in (0..<ranks.size - 1)) {
            assertThat(ranks[i] < ranks[i + 1]).isTrue()
        }
    }

    /**
     * 测试 Card 数据类的功能
     */
    @Test
    fun testCard() {
        // 创建卡片实例
        val card1 = Card(Suit.HEARTS, Rank.ACE)

        // 测试属性
        card1.also {
            assertThat(it.tag).isNull()
            assertThat(it.suit).isEqualTo(Suit.HEARTS)
            assertThat(it.rank).isEqualTo(Rank.ACE)
            assertThat(it.next).isNull()
        }

        // 测试 toString 方法
        assertThat(card1.toString()).isEqualTo("H1")

        // 测试 tag 为 false 的情况
        val card2 = Card(Suit.CLUBS, Rank.TEN)
        card2.tag = false
        assertThat(card2.toString()).isEqualTo("(C10)")
    }

    @Test
    fun testCardDeck() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 测试 addCardAtTop 方法
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.KING))

        for (card in cards) {
            cardDeck.addCardAtTop(card, true)
        }

        cardDeck.forEachIndexed { index, card ->
            assertThat(card.tag).isEqualTo(true)
            assertThat(card.suit).isEqualTo(cards[index].suit)
            assertThat(card.rank).isEqualTo(cards[index].rank)
        }
    }
}