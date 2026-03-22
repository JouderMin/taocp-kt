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

        var iterTimes = 0
        Suit.entries.forEachIndexed { index, suit ->
            iterTimes++
            assertThat(suit.value).isEqualTo(expectedValues[index])
            assertThat(suit.toString()).isEqualTo(expectedValues[index])
        }
        assertThat(iterTimes).isEqualTo(4)
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
        var iterTimes = 0
        Rank.entries.toTypedArray().forEachIndexed { index, rank ->
            iterTimes++
            assertThat(rank.value).isEqualTo(expectedValues[index])
            assertThat(rank.toString()).isEqualTo(expectedValues[index].toString())
        }
        assertThat(iterTimes).isEqualTo(13)

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
        }

        // 测试 toString 方法
        assertThat(card1.toString()).isEqualTo("H1")

        // 测试 tag 为 false 的情况
        val card2 = Card(Suit.CLUBS, Rank.TEN)
        card2.tag = false
        assertThat(card2.toString()).isEqualTo("(C10)")

        // 测试 tag 为 true 的情况
        val card3 = Card(Suit.CLUBS, Rank.TEN)
        card3.tag = true
        assertThat(card3.toString()).isEqualTo("C10")
    }

    @Test
    fun testCardDeck() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 测试 addCardAtTop 方法
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.KING))
        val cardsRev = cards.reversed()

        cards.forEach { card ->
            cardDeck.addCardAtTop(card, true)
        }

        // 测试 forEach 方法
        var iterTimes = 0
        cardDeck.forEachIndexed { index, card ->
            iterTimes++
            assertThat(card.tag).isEqualTo(true)
            assertThat(card.suit).isEqualTo(cardsRev[index].suit)
            assertThat(card.rank).isEqualTo(cardsRev[index].rank)
        }
        assertThat(iterTimes).isEqualTo(cards.size)
    }

    @Test
    fun testCountCards() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 测试 countCards 方法
        assertThat(cardDeck.countCards()).isEqualTo(0)

        // 测试添加卡片后 countCards 方法
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.KING))
        cards.forEach { card ->
            cardDeck.addCardAtTop(card, true)
        }
        assertThat(cardDeck.countCards()).isEqualTo(cards.size)
    }

    /**
     * 测试 popTopCard 方法
     * 牌组为空
     */
    @Test
    fun testPopTopCard_Empty() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 测试 popTopCard 方法，牌组为空
        val card = cardDeck.popTopCard()

        // 断言返回值为 null
        assertThat(card).isNull()
    }

    /**
     * 测试 popTopCard 方法
     * 牌组不为空
     */
    @Test
    fun testPopTopCard_NotEmpty() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 初始化牌组，添加三张卡片
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.KING))
        cards.forEach { card ->
            cardDeck.addCardAtTop(card, true)
        }

        // 测试 popTopCard 方法
        val topCard = cardDeck.popTopCard()

        // 断言返回值为顶部卡片
        assertThat(topCard).isEqualTo(cards[2])

        // 断言牌组中卡片数量为 2
        assertThat(cardDeck.countCards()).isEqualTo(2)
    }

    /**
     * 测试 addCardAtBottom 方法
     * 牌组为空
     */
    @Test
    fun testAddCardAtBottom_Empty() {
        // 创建牌组实例
        val cardDeck = CardDeck()
        val card = Card(Suit.HEARTS, Rank.ACE)

        // 测试 addCardAtBottom 方法，牌组为空
        cardDeck.addCardAtBottom(card)

        // 断言牌组中卡片数量为 1
        assertThat(cardDeck.countCards()).isEqualTo(1)
        cardDeck.forEach { card ->
            assertThat(card.toString()).isEqualTo("(H1)")
        }
    }

    /**
     * 测试 addCardAtBottom 方法
     * 牌组不为空
     */
    @Test
    fun testAddCardAtBottom_NotEmpty() {
        // 创建牌组实例
        val cardDeck = CardDeck()
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN))
        val exceptedCards = listOf("(H1)", "(C10)")

        // 测试 addCardAtBottom 方法，牌组不为空
        cards.forEach { card ->
            cardDeck.addCardAtBottom(card)
        }

        // 断言牌组中卡片数量为 2
        assertThat(cardDeck.countCards()).isEqualTo(2)
        cardDeck.forEachIndexed { index, card ->
            assertThat(card.toString()).isEqualTo(exceptedCards[index])
        }
    }

    /**
     * 测试 popBottomCard 方法
     * 牌组为空
     */
    @Test
    fun testPopBottomCard_Empty() {
        // 创建牌组实例
        val cardDeck = CardDeck()

        // 测试 popBottomCard 方法，牌组为空
        val card = cardDeck.popBottomCard()

        // 断言返回值为 null
        assertThat(card).isNull()
    }

    /**
     * 测试 popBottomCard 方法
     * 牌组长度为 1
     */
    @Test
    fun testPopBottomCard_OneCard() {
        // 创建牌组实例
        val cardDeck = CardDeck()
        val card = Card(Suit.HEARTS, Rank.ACE)

        // 测试 popBottomCard 方法，牌组长度为 1
        cardDeck.addCardAtBottom(card)

        // 断言返回值为顶部卡片
        val bottomCard = cardDeck.popBottomCard()
        assertThat(bottomCard).isEqualTo(card)

        // 断言牌组中卡片数量为 0
        assertThat(cardDeck.countCards()).isEqualTo(0)
    }

    /**
     * 测试 popBottomCard 方法
     * 牌组长度更长
     */
    @Test
    fun testPopBottomCard_MultipleCards() {
        // 创建牌组实例
        val cardDeck = CardDeck()
        val cards = listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.KING))

        // 初始化牌组，添加三张卡片
        cards.forEach { card ->
            cardDeck.addCardAtBottom(card)
        }

        // 断言牌组中卡片数量为 3
        assertThat(cardDeck.countCards()).isEqualTo(3)
        val card = cardDeck.popBottomCard()
        assertThat(card).isEqualTo(cards[2])

        // 断言牌组中卡片数量为 2
        assertThat(cardDeck.countCards()).isEqualTo(2)
    }
}
