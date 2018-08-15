# KSQL User Define Functions
This is a rewrite of the `multiply` KSQL user-defined function (UDF) from the Confluent documentation using Groovy with a Gradle-based build process.

https://docs.confluent.io/current/ksql/docs/udf.html#example-udf-class

Notice that [the multiply function](src/main/groovy/Multiply.groovy) written in Groovy only has three methods, while the [java equivalent](https://docs.confluent.io/current/ksql/docs/udf.html#example-udf-class) (from the Confluent documentation) requires four methods. This all has to do with Java primitives and NULL values, which the [Confluent documentation](https://docs.confluent.io/current/ksql/docs/udf.html#null-handling) does a really good job of explaining. So what's up?

We can use Groovy to compile Java libraries, but it goes about things just a little bit different. While Groovy accepts Java primitives (using long instead of Long, for instance), [Groovy "autowraps" them](https://stackoverflow.com/questions/37055883/explain-groovy-docs-on-autowrapping-primitives-and-wrappers) in the full Object class. So long story short... there is no reason to write separate overloaded methods for primitives versus objects in Groovy. Nice, right?

### Shadow Plugin
One requirement of KSQL UDF JARs is that they contain all external dependencies. This sample does not contain any external dependencies, but if it did, they would be included in the JAR file because we are using the [Shadow Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow). So, to get our "uber-jar", instead of:

`./gradlew build`

...we just do...

`./gradlew shadowJar`

This will generate a JAR library with an `-all` naming standard, and this will be the _shaded_ JAR file including all dependencies.

### Deployment
Currently, the deployment of the UDF JAR file to the KSQL Server is being handled with Gradle code that lives elsewhere. It's a TODO to get that code represented here as well.