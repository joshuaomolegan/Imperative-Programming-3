import Q4._
import org.scalatest.FunSuite

class TestQ4 extends FunSuite{
    test("test 1"){
        val ac1 =  new BasicAccount(50)
        val d10 = new DepositCommand(10)
        val w5 = new WithdrawCommand(5)

        val t = makeTransaction(List(d10,d10,w5,d10,w5))
        val c1 = t.execute(ac1)
        assert(ac1.balance === 70)

        c1.get.undo()
        assert(ac1.balance === 50)
    }

    test("test 2"){
        val ac2 = new BasicAccount(0)
        val d20 = new DepositCommand(20)
        val d14 = new DepositCommand(14)
        val w30 = new WithdrawCommand(30)

        val t = makeTransaction(List(d20, d14, w30, d20, d20, w30, d14))
        val c2 = t.execute(ac2)
        assert(ac2.balance === 28)

        c2.get.undo()
        assert(ac2.balance === 0)
    }
}