object Q4{
    trait Change {
        def undo(): Unit
    }

    trait Command[T] {
        def execute(target: T): Option[Change]
    }

    class AndThenCommand[T](first: Command[T], second: Command[T]) extends Command[T]{
        override def execute(target: T): Option[Change] = { 
            // Execute the first command and return None if it fails
            var res1 = first.execute(target)
            if (res1 == None) None
            else {
                // If it succeeds, execute the second. If this fails, undo the first and return None
                var res2 = second.execute(target)
                if (res2 == None){res1.get.undo(); None}
                else{
                    // Otherwise return a the change object
                    Some(new Change{override def undo() = {res2.get.undo(); res1.get.undo()}})
                }
            }
        }
    }

    def makeTransaction[T](commands: List[Command[T]]): Command[T] = new Command[T] {
        override def execute(target: T): Option[Change] = {
            // If we have only a single command, execute it
            if (commands.size == 1) commands.head.execute(target)

            // Otherwise, recursively call makeTransaction on the tail of the list and merge the result with the first command in the list
            else new AndThenCommand[T](commands.head, makeTransaction(commands.tail)).execute(target)
        }
    }


    // Classes form Q2

    class DepositCommand(amount: Int) extends Command[Account]{
        def execute(target: Account): Option[Change] = {
            if (amount < 0) None
            else{
                target.deposit(amount)
                Some(new Change {def undo() = target.withdraw(amount)})
            }
        }
    }

    class WithdrawCommand(amount: Int) extends Command[Account]{
        def execute(target: Account): Option[Change] = {
            if (amount < 0 || target.balance() < amount) None
            else{
                target.withdraw(amount)
                return Some(new Change {def undo() = target.deposit(amount)})
            }
        }
    }

    class BasicAccount(init_balance: Int) extends Account {
        private var account_balance = init_balance

        override def deposit(amount: Int) = account_balance += amount

        override def withdraw(amount: Int) = account_balance -= amount

        override def balance() : Int = account_balance
    }
}

