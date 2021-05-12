trait PriorityQueue {
    def isEmpty: Boolean // Determine if queue is empty
    def insert(e:Int): Unit // Place the element e in the queue
    def remove(e:Int): Unit // Remove (one copy of) element e (if present)
    // ...(this operation is needed to undo insert)
    def delMin(): Int // Remove and return the smallest element
}

trait Change {
    def undo(): Unit
}

trait Command[T] {
    def execute(target: T): Option[Change]
}

class InsertCommand(e: Int) extends Command[PriorityQueue] {
    override def execute(target: PriorityQueue): Option[Change] = {
        target.insert(e)
        Some(new Change{override def undo() = target.remove(e)})
    }
}

class DelMinCommand() extends Command[PriorityQueue] {
    override def execute(target: PriorityQueue): Option[Change] = {
        if (target.isEmpty) None
        else{
            val min = target.delMin()
            Some(new Change {override def undo() = target.insert(min)})
        }
    }
}