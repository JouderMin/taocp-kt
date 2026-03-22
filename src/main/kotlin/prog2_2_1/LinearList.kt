package prog2_2_1

interface LinearList<T> {
    fun at(index: Int): T?

    fun insertBefore(index: Int, item: T)

    fun insertAfter(index: Int, item: T)

    fun remove(index: Int): T?

    fun extend(list: LinearList<T>)

    fun split(index: Int): LinearList<T>

    fun duplicate(): LinearList<T>

    fun size(): Int

    fun sort()

    fun find(item: T): Int
}

interface Stack<T> {
    fun push(item: T)

    fun pop(): T?

    fun top(): T?
}

interface Queue<T> {
    fun push(item: T)

    fun pop(): T?

    fun front(): T?
}

interface Deque<T> {
    fun pushFront(item: T)

    fun pushBack(item: T)

    fun popFront(): T?

    fun popBack(): T?

    fun front(): T?

    fun back(): T?
}
