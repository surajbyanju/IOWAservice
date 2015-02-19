# IOWAservice

Welcome to iowa service project.

Please make sure you have mysql installed and netbeans with glassfish 4.1.

Follow the following steps to run the application:

1) create database "iowa_services" in mysql with connection port 3306 and user name and password as "root" and "root" respectively.
if you have different user name and password or port, you have to change so in IowaServices-web/WEB-INF/spring/application-context.xm
l

2) Create JDBC resource in glassfish server. You can refer http://www.albeesonline.com/blog/2008/08/06/creating-and-configuring-a-mysql-datasource-in-glassfish-application-server/
if you dont know how to do this. Make sure to keep "IOWA_Service" in "JNDI Name" of jdbc resource. Before doing this make sure you have connector "mysql-connector-java-5.1.18-bin"
in "glassfish-4.1\glassfish\lib" installation directory of glassfish.

3)First IowaServices-ejb and then build  IowaServices-web. If two projects are not showing in netbeans then click IowaServices-> Modules. Double click the project you want to open.

4) Run IowaServices-web. You are ready to go with register.

Note there is a start-up file in IowaServices-web/sourcePackages/com.bigbang.iowaservices.startup. Uncomment the startup once you for first run. It will create admin user and various services.

Thank you !!!!
