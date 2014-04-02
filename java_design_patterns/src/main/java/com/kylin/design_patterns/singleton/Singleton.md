Singleton
---------

A class distributes the only instance of itself. 

In this example SingleSpoon class holds one instance of SingleSpoon in "private static SingleSpoon theSpoon;". The SingleSpoon class determines the spoons availability using "private static boolean theSpoonIsAvailable = true;" The first time SingleSpoon.getTheSpoon() is called it creates an instance of a SingleSpoon. The SingleSpoon can not be distributed again until it is returned with SingleSpoon.returnTheSpoon().

If you were to create a spoon "pool" you would have the same basic logic as shown, however multiple spoons would be distributed. The variable theSpoon would hold an array or collection of spoons. The variable theSpoonIsAvaialable would become a counter of the number of available spoons. 
