## Refactoring Summary

This project has been refactored to improve code quality and maintainability. 
The following code smells were identified and resolved across 11 files:

### Code Smells Fixed
- **Long Method** => MainScreen.java
- **Long Parameter List** => Book.java
- **Primitive Obsession** => MainScreen.java, Book.java, Customer.java, Fare.java, Laundry.java, Transportation.java
- **Switch Statement** => MainScreen.java, Book.java, Laundry.java, Transportation.java
- **Duplicate Code** => Book.java, Deluxe.java, Laundry.java, Luxury.java, SuperDeluxe.java, Transportation.java
- **Dead Code** => MainScreen.java, Book.java, Customer.java, Service.java
- **Lazy Class** => Room.java, Service.java
- **Inappropriate Intimacy** => Book.java
- **Comment** => Deluxe.java, Luxury.java, SuperDeluxe.java

### Refactoring Techniques Applied
- Extract Method
- Pull Up Method
- Replace Magic Number with Symbolic Constant
- Replace Conditional with Polymorphism
- Consolidate Conditional Expression
- Encapsulate Field
