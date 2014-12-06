# Team Name: 404 Name Not Found - National Hackathon 2014
### App Name: Prottoyee

## Quick start

First clone this project from bitbucket and navigate there from your command line/terminal.

**If you have gradle already installed**, just run `gradle build`
then you will find a **war** file in your projects **/build/libs/** folder. Deploy it in your favorite container e.g. tomcat.

**If you don't have gradle installed**, well, there is a awesome news for you! You don't need to download/install gradle to 
build this project! Here, [gradle wrapper](http://java.dzone.com/articles/use-gradle-wrapper-and-stop) is used to make your life easier ;)
All you have to do to run script, e.g. if you want to build this project, just execute following command from your terminal

* `./gradlew build ` (from Unix)
* `gradlew.bat build ` (from Windows)

This script will automatically determine your gradle installation or download & install gradle if required & then execute 
the gradle task, e.g. build war file for above command. To see the list of tasks available, use `./gradlew tasks` or `gradlew.bat tasks` 


## Database Connection

You need to create a database named "prottoyee" in your local MySQL database to work with this application. Best way to do it from phpmyadmin
is to to create a user name "prottoyee" and password "therap" and tick "Create database with same name and grant all privileges" option.
Check corresponding user credential to access database in `persistence-mysql.properties`.
**Note:** There is a `import.sql` file in classpath, which execute and insert a user 'admin' with password '123456' and proper roles
when hibernate.hbm2ddl.auto property is set to create-drop in applicationContext-jpa.xml.


## Quick development -> Direct deploy app on embedded tomcat
Well, building project and deploying manually in app container sometimes become pain as these are repetitive work. To make 
your life easier, here embedded tomcat container has been integrated. Just type `gradle tomcatRunWar` from your command line
and your project will build and run automatically on a tomcat container within a minute. You just have to go **http://localhost:8080/** from 
your browser to see it running. Cool...right? ;)

## Deploy in local Tomcat with JRebel Support
1. Install Tomcat 7 at `/usr/local/tomcat7`
2. Copy the script file from `config/catalina-jrebel.sh` in your `/usr/local/tomcat7/bin` directory
3. Make sure, you installed jrebel at `/usr/local/JRebel` directory
4. Run **gradle deployWar** in your source code which will copy the war file to tomcat's webapps folder
5. From tomcat7/bin, start tomcat server by `./catalina-jrebel.sh run`
6. Access application by browsing: [http://localhost:8080/prottoyee/](http://localhost:8080/prottoyee/)