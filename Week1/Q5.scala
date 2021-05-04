class Ellipse(private var _a: Int, private var _b: Int) {
    def a = _a
    def a_=(new_a: Int) = {_a = new_a}

    def b = _b
    def b_=(new_b: Int) = {_b = new_b}

    // Doesn't need to be overwritten because new "a_=" and "b_=" methods in LoggedEllipse are used, so num_increases is increased appropriately
    def swap = {
        val tmp = _a
        a = _b
        b = tmp
    }

    // Needs to be overwritten
    def swap2 = {
        val tmp = _a
        _a = _b
        _b = tmp 
    }
}

class LoggedEllipse(private var _a: Int, private var _b: Int) extends Ellipse(_a, _b){
    private var num_increases = 0

    override def a_=(new_a: Int) = {
        if (new_a > _a) num_increases += 1
        _a = new_a
    }

    override def b_=(new_b: Int) = {
        if (new_b > _b) num_increases += 1
        _b = new_b
    }

    def getIncreases : Int = num_increases
}