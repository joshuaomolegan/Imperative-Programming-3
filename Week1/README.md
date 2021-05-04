# Week 1

## Question 1
Q1.scala contains a set of classes to represent the shapes rectangles, squares, ellipses and circles.

## Question 2
The width and height parameters can be changed from outside the class without any checks to make sure they are valid (i.e. greater than 0). You could fix this by only allowing access to these parameters through getter and setter methods, and checking validity within the setter method.  
The area variable is also changable from outside the class, however we don't want the user to be able to do this as this may break our invariant. We can get around this by replacing the variable with a method called area that returns width * height.  

## Question 3  
Suppose we have:   
> var rect = new Rectangle(h, w)  
> var slab = new Slab(rect)  
  
There are now 2 ways to access the Rectangle object created: rect and slab.dimension. Although we can't alter the width and height of dimension directly, due to aliasing, altering rect.width or rect.height also changes slab.dimension.width or slab.dimension.height. This breaks the invariant because
the area is no longer equal to width * height as they have been changed but area hasn't.  

Assuming we want the width and height fields in the Rectangle class to remain mutable, we can create the rectangle object within the class and assign it to a private variable.  

## Question 4  
Q4.scala contains the classes Triangle, OpaqueTriangle, Renderer and RayTracingRenderer, along with answers to question 4 of the problem sheets.  

## Question 5  
Q5.scala contains the classes Ellipse and LoggedEllipse, along with answers to question 5 of the problem sheet.  

## Question 6
a) Using compostition, we can have an instance variable that references a Text object within the Planetext class. We can then use the Text object methods to implement plaintext versions of the same methods. However, Text has some private variables such as buffer and gap which can't be accessed so we may need to add more methods to get these values.  

b) By having a trait class that both Text and Plaintext inherit from, we can change the input type of transmit to be the trait class name, as this will be the static type of both Text and Plaintext.  

c) Due to the use of composition instead of inheritance, the code is no longer exposed to the fragile base class problem and has looser coupling. However, we have to add more methods since we don't have access to the private variables in the Text class.  

## Question 7  
Q7.scala contains a rectangle class with custom equals and hashCode functions, along with a test suite for them.
