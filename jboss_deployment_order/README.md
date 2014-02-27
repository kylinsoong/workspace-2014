What's this
===========

This is a project for testing JBoss 5 deployment order in a ear.


How to run
==========

* deploy test queue to JBoss EAP 5.x
~~~
cp deploy-order-service.xml .../deploy/messaging/
~~~

* maven build and deploy the project
~~~
mvn clean install

~~~
