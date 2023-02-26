<p align="center">
    <img alt="Repository size" src="https://img.shields.io/github/repo-size/DarlanSilv4/curious-fox.svg?style=flat-square&color=64a4a">
 <a href="https://github.com/DarlanSilv4/curious-fox/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/DarlanSilv4/curious-fox.svg?style=flat-square&color=f8cd65">
  </a>
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/DarlanSilv4/curious-fox.svg?style=flat-square&color=fb7c58">
</p>

<h1 align="center">
  <img alt="curious fox" title="curious fox" src="https://user-images.githubusercontent.com/77397497/221369516-e73a0678-3aee-4a6f-858e-1d54b30d6541.png" />
</h1>

<br>

## 🦊 Curious Fox

Curious Fox is an anonymous Q&A social network made for study purposes only.


## ✨ Built With

- [Java 17](https://www.java.com/en/)
- [Java Server Page (JSP)](https://jakarta.ee/specifications/pages/)
- [Tailwind](https://tailwindcss.com/)
- [Tomcat 9](https://tomcat.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)

## 🚀 Getting Started
```
# Clone this repository
$ git clone https://github.com/DarlanSilv4/curious-fox

# Go into the folder
$ cd curious-fox
```
This project doesn't have a dependency manager like Gradle or Maven, so you need to install them all by yourself, but it'll be an easy task because that's not many dependencies to install.

### Dependencies

First you need download this dependencies:
  - [Jakarta Standard Tag Library API - v1.2.7](https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api/1.2.7)
  -  [Jakarta Standard Tag Library Implementation - v1.2.6](https://mvnrepository.com/artifact/org.glassfish.web/jakarta.servlet.jsp.jstl/1.2.6)
  -  [PostgreSQL JDBC Driver - v42.5.2 or higher](https://jdbc.postgresql.org/download/)

Then, move the .jar files to ```/webapp/WEB-INF/lib```.

After downloading all the dependencies, you need to configure the Tomcat server. Use this tutorial in case you need help: [How to configure tomcat server in Eclipse IDE](https://www.javatpoint.com/how-to-configure-tomcat-server-in-eclipse-ide).

Now, add all the dependencies in the Java Build Path. If you using the Eclipse IDE, right-click in the .jar file and, in the contex menu, go to ```Build Path > Add to Build Path```. Do this for all .jar files.

### Database

Go to ```src/main/java/com/curiousfox/jdbc```, open ```ConnectionFactory.java```, and update this follow lines with your database information.

```java

/** The url. */
	private final String url = "jdbc:postgresql://localhost:5432/curious_fox";
	
/** The user. */
	private final String user = "postgres";
	
/** The password. */
	private final String password = "password";

```
For the last part, execute all query in the ```schema.sql``` file. Now, the application is ready to run.

---
logo by [zayronxio](https://github.com/zayronxio/Zafiro-icons)