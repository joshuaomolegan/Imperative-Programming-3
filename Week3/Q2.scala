class MySet[T](elements: Set[T]) extends Set[T]{
    type This = MySet[T]

    def contains(key: T): Boolean = elements.contains(key)

    def iterator: Iterator[T] = elements.iterator

    // + and - are both final so i overrode incl and excl instead
    def incl(elem: T): This = new MySet[T](elements + elem)
    def excl(elem: T): This = new MySet[T](elements - elem)

    override def empty: This = new MySet[T](elements.empty)
}