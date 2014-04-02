Adapter
-------

A class extends another class, takes in an object, and makes the taken object behave like the extended class.

In the Adapter Design Pattern, a class converts the interface of one class to be what another class expects. 

The adapter does this by taking an instance of the class to be converted (the adaptee) and uses the methods the adaptee has available to create the methods which are expected. 


In this example we have a TeaBall class which takes in an instance of LooseLeafTea. The TeaBall class uses the steepTea method from LooseLeafTea and adapts it to provide the steepTeaInCup method which the TeaCup class requires. 
