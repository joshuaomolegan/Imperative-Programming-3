# Week 3 Comments

## Question 5
Suppose we allowed HashSet to be covariant. The add method of HashSet[Int] (which is only meant to accept Ints) can instead take any inputs as HashSet[Int] <: HashSet[Any], as is the case with all methods that have integer inputs.  
  
Similarly if we allow the HashSet to be contravariant, the ouputs of HashSet[Any]'s methods like head and last (which are of type Any) can be cast to Ints as HashSet[Any] <: HashSet[Int]. Both of these situations are dangerous so it is better to define mutable collections to be immutable  

## Question 6
componentOrientation is inherited from scala.swing.UIElement  

## Question 7
The main component of the Facade pattern is the Facade Interface. It is mainly used when we have a group of classes that need to work together to execute a task, but we want to encapsulate the individual class calls into a single class. The AppFrame class implements this pattern. Its role is to simplify access to all the individual GUI components.
