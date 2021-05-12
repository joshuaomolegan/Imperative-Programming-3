object Q5 {
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

    class DelMinCommand() extends Command[PriorityQueue] {
        override def execute(target: PriorityQueue): Option[Change] = {
            if (target.isEmpty) None
            else{
                val min = target.delMin()
                Some(new Change {override def undo() = target.insert(min)})
            }
        }
    }

    class WhileCommand[T](test: T => Boolean, cmd: Command[T]) extends Command[T]{
        private var history = new scala.collection.mutable.ArrayBuffer[Change]() // Array buffer to keep track of the changes made
        private var count = 0 // Keep track of how many times we've run the command
        private var res : Option[Change] = None

        override def execute(target: T): Option[Change] = {
            while (test(target)){
                res = cmd.execute(target)
                if (res == None){for(i <- count-1 to 0 by -1) history(i).undo(); None}
                else{
                    history.append(res.get)
                    count += 1
                }
            }
            Some(new Change {override def undo() = {for(i <- count-1 to 0 by -1) history(i).undo()}})
        }
    }

    def threshold(limit: Int, target: PriorityQueue): Boolean = {
        if (target.isEmpty) false
        else {
            var min = target.delMin()
            target.insert(min)
            return min < limit
        }
    }
}