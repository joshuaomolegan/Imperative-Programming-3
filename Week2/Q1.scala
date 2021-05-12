import org.scalatest.FunSuite

class FilterIterator[T] (test: T => Boolean, it: Iterator[T]) extends Iterator[T]{
    var iter = it.filter(test)
    override def hasNext : Boolean = iter.hasNext
    override def next() : T = iter.next()
}

class Q1 extends FunSuite{
    var vowels = scala.collection.mutable.HashSet('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')

    var f1 = new FilterIterator({c : Char => c.isUpper}, "bcDfGhjKLMn".iterator)
    var f2 = new FilterIterator({c : Char => c.isUpper}, "aeiftgahoU".iterator)

    def isVowel(c: Char) : Boolean = vowels.contains(c)
    var f3 = new FilterIterator(isVowel, "bcDfGhjKLMn".iterator)
    var f4 = new FilterIterator(isVowel, "aeiftgahoU".iterator)
 
    test("string 1: Filter upper case chars"){assert(f1.hasNext); assert(f1.next() === 'D'); assert(f1.hasNext); assert(f1.next() === 'G')}
    test("string 2: Filter upper case chars"){assert(f2.hasNext); assert(f2.next() === 'U'); assert(!f2.hasNext)}
    test("string 1: Filter vowels"){assert(!f3.hasNext)}
    test("string 2: Filter vowels"){assert(f4.hasNext); assert(f4.next === 'a'); assert(f4.hasNext); assert(f4.next === 'e')}
}