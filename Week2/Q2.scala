object Q2{
    trait Change {
        def undo(): Unit
    }

    trait Command[T] {
        def execute(target: T): Option[Change]
    }

    trait Account {
        // Deposit money into the account
        def deposit(amount: Int): Unit

        // Withdraw money from the account
        def withdraw(amount: Int): Unit

        // Display the account balance
        def balance() : Int
    }

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