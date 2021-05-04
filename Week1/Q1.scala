trait Shape {
    def isCircle : Boolean
    def isSquare : Boolean
}

class Rectangle(var w : Double, var h : Double) extends Shape{
    require(w > 0 && h > 0)

    // Rectangles cannot be circles
    def isCircle : Boolean = false

    def isSquare : Boolean = w == h

    // Getter and setter methods for width and height
    def width : Double = w
    def width_=(new_width : Double) = {
        require(new_width > 0)
        w = new_width
    }

    def height : Double = h
    def height_=(new_height : Double) = {
        require(new_height > 0)
        h = new_height
    }
}

class Ellipse(var maj : Double, var min : Double) extends Shape{
    require(maj > 0 && min > 0)

    def isCircle : Boolean = maj == min

    // Ellipses cannot be squares
    def isSquare : Boolean = false

    // Getter and setter methods for major and minor axes
    def major : Double = maj
    def major_=(new_maj : Double) = {
        require(new_maj > 0)
        maj = new_maj
    }

    def minor : Double = min
    def minor_=(new_min : Double) = {
        require(new_min > 0)
        min = new_min
    }
}

/* This solution was better than having a square class that extends rectangle and a circle class
   that extends ellipse because these relationships are not consistend, as we can change the 
   dimensions of each shape. */