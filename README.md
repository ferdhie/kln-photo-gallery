KNL Photo Gallery Application
=============================

This is a source for a simple photo gallery applicaton. We have some problem with it. The
original developer that created the apps from scratch has *Gone Fishing* so we need your help
to finish this application. The application is a java web application using
[Spring Boot](http://projects.spring.io/spring-boot/) framework. Your task is:

* Complete the application, if you browse the source, you will find `@TODO` tag in the comments,
  please complete the code, some of them are:
  1.  Add check for valid file extension for uploaded image
  2.  Add check for valid mime type when for uploaded image.
  3.  Add a highres copy of image, we actually already implement the resizing code, and how to
      generate a thumbnail as an example, we need you to use the same function, but add another
      image size, stored in the `photo` folder with a proportional size of `1024px` in width
      and `(n)px` in height depending on the ratio.
  4. Fix the 404 not found when user click on the image. Show the highres version if user
     click the thumbnail image. Preferably done using JS effect such as http://fancybox.net/.
  5. Implement a delete mechanism. Only authorized (logged in) user can delete the image.
     We have implemented the login mechanism, and the code for deleting the image, all you
     have to do is implementing the logic. Use Ajax if possible.
  6. Paginate the displayed image for 9 image per page. We recommends (http://jscroll.com)
     for example for best user experiences.

* **Second tasks**;
  Once you have all the `@TODO`s covered, we need you to use your imagination and some
  CSS & Javascript skills to make this application look beautiful.

Please zip the result and mail it to `ferdhie-at-kapanlagi-dot-net` or fork this application on
github and mail me your git repo link.

If you never worked with Java / Spring, we have some small guide on how to setup and run your
application in your workstation, read on the text below or you can google on how to run a
spring-boot application.

Getting The Source
-------------------
In order to grab the source of this application, please visit this application
[Git Repository](https://github.com/ferdhie/kln-photo-gallery) and then checkout the repository. If you have your own [Github](https://github.com) account, you can directly `fork` it into
your own repository.

For some git tutorial, please follow the link below:
* https://www.atlassian.com/git/tutorials/
* https://try.github.io/levels/1/challenges/1
* we usually use [SmartGit](http://www.syntevo.com/smartgit/) to make git simpler.

Or you can directly download the
[ZIP source](https://github.com/ferdhie/kln-photo-gallery/archive/master.zip).

Run The Application
-------------------
To compile and run this application you will need these application installed on your development machine.

* You will need Java Development Kit(JDK) for compile and run the application, you can download
  [the latest version (1.8)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
  Please choose one that match your development machine platform and operating system.
* Set the environment variable `JAVA_HOME` to point to your JDK installation directory.
  For example, if you install on `C:\JDK18`, you can right click "Computers" menu,
  choose "Advance System Settings", and then click the "Environment Variables" button.
  You should add `JAVA_HOME` as the key and, the path of Java installation as the value.
  If you have any problem with this you can always Google on how to add environment variables
  for your operating system.

* You will need to install [Maven](https://maven.apache.org/) for compiling the application.
  Please follow the documentation on the website to install maven. Follow
  [this link ](http://www.tutorialspoint.com/maven/maven_environment_setup.htm) for a simple
  guide on how to install java and maven.

* Install some Java IDE (Editors) . There are a lot of options for IDE, but we especially
  recommends [IDEA](https://www.jetbrains.com/idea/) or [Netbeans](https://netbeans.org/).
  They have a great support for Java and Maven. These IDE's has builtin maven support.

* After all is done, you can open the project inside your IDE. For IDEA, click
 "Create New Project from Existing Source" and choose maven type. Netbeans also do the same,
 "New Project", choose Maven types. If you don't have this menu, you might need to install
 maven plugin for Netbeans / IDEA.

* To run the application, type in command inside console/command-prompt/DOS prompt
  ```
  C:\Users\user\Documents\photogallery>mvn spring-boot:run`
  ```
  and then visit http://localhost:8080/ from your browser

* All of the above download could take some time, while you wait, you can use the spare time to
  getting to know more about Java, JSP and Spring boot by googling for them.
  Live demo of the application is [here](http://203.12.20.70/).

