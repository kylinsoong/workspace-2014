The readme.txt contain the following topic:
  1. about the application
  2. how to run
  3. design architecture

1. About the Application
   The Hotel Reservation Application only contain one executable jar hotel-reservation.jar, the quick start docs and source code as below:
    Quick Start Doc: https://github.com/kylinsoong/workspace-2014/blob/master/hotel_reservation/README.md
    Source Code: https://github.com/kylinsoong/workspace-2014/tree/master/hotel_reservation
  The hotel-reservation.jar are well tested in Red Hat Linux 6.4 + Java 7 environment

2. How to Run
   There are two ways can run the Hotel Reservation Application: Command Line, UI, the default is Command Line, Runing as Command Line:
       java -jar hotel-reservation.jar
       java -jar hotel-reservation.jar -c
   Each of the above commands will start the Command Line mode, which a consloe show up like Linux terminal, enter the input will output the chepest hotel. The following is a Command Line Running footprint:
   ~~~
   [kylin@localhost target]$ java -jar hotel-reservation.jar 
        Select Cheapest Hotel
   INPUT: Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)
   OUTPUT: Lakewood
   INPUT: Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)
   OUTPUT: Bridgewood
   INPUT: Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)
   OUTPUT: Ridgewood
   ~~~

   Running as UI:
      java -jar hotel-reservation.jar -u 
   This will pop up a UI, In the main panel, from left to right, select Customer Type, Start Date, End Date, then click the Submit Button, the cheapest hotel will list the center panel.Navigate the mouse to select start date or select end date InputText, click the mouse key, the date selector will pop up.

3. How to Design
  Traditional pure java application, MVC design pattern be used in UI mode.
