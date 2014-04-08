Question
--------

[Hotel Reservation Question](docs/hotel-reservation-question.md)


Building
--------

Ensure you have JDK 7 (or newer) installed

> java -version

Ensure you have Maven 3.0.4 (or newer) installed 

> mvn install

The building will generate a executable `hotel-reservation.jar`.


Running
-------

Runing as Command Line:

> java -jar hotel-reservation.jar

> java -jar hotel-reservation.jar -c

The Command Line Running footprint:
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

> java -jar hotel-reservation.jar -u 

The poped UI like:

[Hotel Reservation UI](docs/hotel-reservation-ui.png)

In the main panel, from left to right, select `Customer Type`, `Start Date`, `End Date`, then click the `Submit` Button, the cheapest hotel will list the center panel.

Navigate the mouse to `select start date` or `select end date` InputText, click the mouse key, the date selector will pop up.


MVC
---

Hotel Reservation UI mode use MVC software pattern
