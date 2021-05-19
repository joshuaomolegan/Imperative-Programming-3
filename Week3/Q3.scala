trait PartialOrder[T] {
    def <=(that: T): Boolean // checks this <= that. Partial order on T.
    def lub(that: T): T // returns the least upper bound of this and that.
}

class MySet[T](elements: Set[T]) extends Set[T] with PartialOrder[MySet[T]]{
    def contains(key: T): Boolean = elements.contains(key)
    def iterator: Iterator[T] = elements.iterator
    override def empty: MySet[T] = new MySet[T](elements.empty)

    // + and - are both final so I overrode incl and excl instead
    override def incl(elem: T): MySet[T] = new MySet[T](elements + elem)
    override def excl(elem: T): MySet[T] = new MySet[T](elements - elem)

    def <=(that: MySet[T]): Boolean = this.forall(that.contains(_))

    // The lub of two sets ordered with <= is their union
    def lub(that: MySet[T]): MySet[T] = new MySet[T](elements.union(that.toSet))
}

class UpSet[T <: PartialOrder[T]](elements: Set[T]) extends PartialOrder[UpSet[T]]{
    private var elem = minimal(elements)
    def getElem = elem

    private def minimal(set: Set[T]): Set[T] = {
        var result : Set[T] = set
        for (x <- set) {
            var flag = true
            for (y <- set ; if ((x != y) && (y <= x))) flag = false
            if (!flag) result = result - x
        }
        result
    }

    def contains(x: T): Boolean = {
        for (y <- elements) if (y <= x) return true
        false
    }

    def intersection(that: UpSet[T]): UpSet[T] = {
        var result = Set[T] ()
        for (a <- elem ; b <- that.getElem) result = result + (a lub b)
        new UpSet(result)
    }

    def <=(that: UpSet[T]): Boolean = {
        for (e <- elem) if (!that.contains(e)) return false
        true
    }

    def lub(that: UpSet[T]): UpSet[T] = new UpSet[T](elem.union(that.getElem))
}
