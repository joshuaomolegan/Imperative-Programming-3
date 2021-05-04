import org.scalatest.FunSuite

class Rectangle(var width:Int, var height:Int) {
    // The given == function simply overloaded the default == function because its input type was different
    override def hashCode = (width, height).##
    override def equals(other : Any) : Boolean = other match {
        case that: Rectangle => this.width == that.width && this.height == that.height
        case _ => false
    }
}

class Q7 extends FunSuite{
    var r1 = new Rectangle(20,30)
    var r2 = new Rectangle(20,30)
    var r3 = new Rectangle(50,60)

    var r1a : Any = r1

    var rect_set = scala.collection.mutable.HashSet(r1)

    test("Standard equality tests"){assert(r1 === r2); assert(r2 !== r3); assert(r1 !== r3)}

    // if r1 == r2 then r2 == r1a should be true as it is identical to r1 but is a refernece of type Any. The given == function fails this part of the test
    test("Equality test between Rectangle and Any"){assert(r1 === r1a); assert((r1 === r2) && (r2 === r1a))} 

    // If r1 is in the hashset and r1 == r2, rect_set.contains(r2) should be true. Hashcode was also not changed. The given == function fails this test.
    test("Hashset contains test"){assert(rect_set.contains(r1)); assert(rect_set.contains(r2))}

    // Defining == in terms of mutable fields also causes issues in hash sets if one of the fields is updated. It causes the hashcode to change and so we won't find the object
    // If we wish to have a hash set of rectangles we should make these fields immutable
    test("Hashset contains after update"){r1.width += 10; assert(rect_set.contains(r1))}
}