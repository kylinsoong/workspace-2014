The Java synchronized Keyword
-----------------------------

Synchronized blocks in Java are marked with the synchronized keyword. A synchronized block in Java is synchronized on some object. All synchronized blocks synchronized on the same object can only have one thread executing inside them at the same time. All other threads attempting to enter the synchronized block are blocked until the thread inside the synchronized block exits the block. 

The `synchronized` keyword can be used to mark four different types of blocks: 

* Instance methods
* Static methods
* Code blocks inside instance methods
* Code blocks inside static methods

These blocks are synchronized on different objects. Which type of synchronized block you need depends on the concrete situation. 

Synchronized Instance Methods
-----------------------------

Here is a synchronized instance method: 
~~~
  public synchronized void add(int value){
      this.count += value;
  }
~~~

Notice the use of the synchronized keyword in the method declaration. This tells Java that the method is synchronized. 
