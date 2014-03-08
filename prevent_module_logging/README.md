What's this
===========

This is a project show how to Prevent a Module Being Implicitly Loaded.


How to run
==========

* The `jboss-deployment-structure.xml` under WEB-INF looks like:
~~~
<jboss-deployment-structure>
        <deployment>
                <exclusions>
                        <!--  <module name="org.jboss.logging" />-->
                        <module name="org.apache.log4j" />
                </exclusions>
        </deployment>
</jboss-deployment-structure>
~~~

* The `log4j.xml` under WEB-INF/classes, and log4j-1.2.16.jar under WEB-INF/lib.

* maven build and deploy the project to WildFly
~~~
mvn clean install
~~~

* Test from `http://$IP:8080/log4jTest/Log4jTestServlet`, The Application log will be seprated in file MyAppLogs.log

