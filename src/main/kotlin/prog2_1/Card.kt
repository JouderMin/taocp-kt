package prog2_1

import java.util.Objects.isNull

/**
 * 扑克牌花色枚举类
 * 定义了四种标准扑克牌花色
 *
 * @param value 花色的简写表示
 */
enum class Suit(val value: String) {
    HEARTS("H"),   // 红桃
    DIAMONDS("D"), // 方块
    CLUBS("C"),    // 梅花
    SPADES("S");   // 黑桃

    /**
     * 返回花色的简写表示
     *
     * @return 花色的字符串表示
     */
    override fun toString(): String {
        return value
    }
}

/**
 * 扑克牌面值枚举类
 * 定义了标准扑克牌的13种面值
 * 实现了 Comparable 接口，可用于比较牌面大小
 *
 * @param value 牌面值的数值表示
 */
enum class Rank(val value: Int) : Comparable<Rank> {
    ACE(1),     // A
    TWO(2),     // 2
    THREE(3),   // 3
    FOUR(4),    // 4
    FIVE(5),    // 5
    SIX(6),     // 6
    SEVEN(7),   // 7
    EIGHT(8),   // 8
    NINE(9),    // 9
    TEN(10),    // 10
    JACK(11),   // J
    QUEEN(12),  // Q
    KING(13);   // K

    /**
     * 返回牌面值的字符串表示
     *
     * @return 牌面值的字符串形式
     */
    override fun toString(): String {
        return value.toString()
    }
}


/**
 * 扑克牌卡片数据类
 * 表示一张扑克牌，包含标签、花色、面值和指向下一张卡片的引用
 *
 * @param suit 卡片花色
 * @param rank 卡片面值
 */
data class Card(val suit: Suit, val rank: Rank) {
    /**
     * 卡片状态, true 表示正面放置状态, false 表示反面放置状态，null 表示未放置状态
     */
    var tag: Boolean? = null

    /**
     * 返回卡片的字符串表示
     * 根据标签状态决定是否在卡片表示外添加括号
     *
     * @return 卡片的字符串表示
     */
    override fun toString(): String {
        return if (isNull(tag) || tag == true) {
            "${suit.value}${rank.value}"  // 正常状态的卡片表示
        } else {
            "(${suit.value}${rank.value})"  // 特殊状态的卡片表示（带括号）
        }
    }
}

/**
 * 扑克牌组迭代器
 * 用于遍历 CardDeck 中的卡片
 *
 * @param current 当前迭代位置的卡片引用
 */
internal class CardDeckIterator(private var current: CardDeckNode?) : Iterator<Card> {
    /**
     * 检查是否有下一张卡片
     *
     * @return 如果当前卡片不为空，则返回 true，否则返回 false
     */
    override fun hasNext(): Boolean = current != null

    /**
     * 获取下一张卡片并移动迭代位置
     *
     * @return 当前迭代位置的卡片
     */
    override fun next(): Card {
        val next = current!!
        current = next.next
        return next.card
    }
}

internal class CardDeckNode(val card: Card) {
    internal var next: CardDeckNode? = null
}

/**
 * 扑克牌组类
 * 表示一个扑克牌的集合，使用链表结构存储卡片
 * 实现了 Iterable 接口，支持迭代遍历
 */
class CardDeck : Iterable<Card> {
    /**
     * 牌组的头部卡片引用
     */
    private var head: CardDeckNode? = null

    /**
     * 获取牌组的迭代器
     *
     * @return 用于遍历牌组的迭代器
     */
    override fun iterator(): Iterator<Card> {
        return CardDeckIterator(head)
    }

    /**
     * 算法 A
     * 在牌组顶部添加卡片
     * 将新卡片添加到牌组的末尾（顶部位置）
     *
     * @param card 要添加的卡片
     * @param tag 卡片的放置状态
     */
    fun addCardAtTop(card: Card, tag: Boolean) {
        card.tag = tag
        val newHead = CardDeckNode(card)
        newHead.next = head
        head = newHead
    }

    /**
     * 算法 B
     * 统计一叠牌的张数
     *
     * @return 牌组中卡片的数量
     */
    fun countCards(): Int {
        var count = 0
        var current = head
        while (current != null) {
            count++
            current = current.next
        }
        return count
    }

    /**
     * 习题 3
     * 从一叠纸牌中取走顶部的牌
     *
     * @return 从牌组顶部移除的卡片
     */
    fun popTopCard(): Card? {
        if (isNull(head)) {
            return null
        }
        val topCard = head!!.card
        head = head!!.next
        return topCard
    }

    /**
     * 习题 4
     * 将新牌面朝下添加到牌组底部
     *
     * @param card 要添加的卡片
     */
    fun addCardAtBottom(card: Card) {
        card.tag = false
        val newBottom = CardDeckNode(card)
        if (isNull(head)) {
            head = newBottom
            return
        }
        var current = head!!
        while (current.next != null) {
            current = current.next!!
        }
        current.next = newBottom
    }

    /**
     * 习题 5
     * 抽走牌组底部的牌
     *
     * @return 从牌组底部移除的卡片，若牌组为空则返回 null
     */
    fun popBottomCard(): Card? {
        if (isNull(head)) {
            return null
        }
        if (isNull(head!!.next)) {
            val current = head!!.card
            head = null
            return current
        }

        var current = head!!
        while (current.next!!.next != null) {
            current = current.next!!
        }
        val bottomCard = current.next!!.card
        current.next = null
        return bottomCard
    }
}