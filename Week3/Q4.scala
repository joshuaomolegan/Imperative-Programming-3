class Bag[-T](f: (T => Int)) {
    // Add an element to the bag
    def add(x:T): Bag[T] = new Bag((y: T) => if (x == y) f(x)+1 else f(x))

    // Remove an element from the bag
    def remove(x:T): Bag[T] = new Bag((y: T) => if (x == y) (0).max(f(x)-1) else f(x))

    // How many times does x occur
    def count(x:T): Int = f(x)

    // Union of two bags
    def union [X <: T](that : Bag[X]) : Bag[X] = new Bag[X]((y: X) => f(y) + that.count(y))
}

/* Adding a "-" before the T in the class definition makes the class contravariant. We also have h=to modify the
   type signature of union so it only accepts bags containing type X such that X <: T (otherwise we can force items
   of a superclass of T into a bag of type T)
   
   b3 union b2 contains all the integer elements of both b3 and b2*/ 
