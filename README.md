# FreeTTS Log4J Appender

Talking log appender to keep your eyes :)

# Add to your project
```xml
<repositories>
    <repository>
        <id>bintray-stokito-maven-FreeTTSLog4JAppender</id>
        <name>stokito-maven-FreeTTSLog4JAppender</name>
        <url>https://api.bintray.com/maven/stokito/maven/FreeTTSLog4JAppender</url>
    </repository>
</repositories>

<dependency>
    <groupId>name.stokito</groupId>
    <artifactId>FreeTTSLog4JAppender</artifactId>
    <version>1.0.1</version>
</dependency>
```

Then add it as usual Log4J appender.

## How to use in Grails
Add dependency in `BuildConfig.groovy`:

```groovy
repositories {
    mavenRepo 'http://dl.bintray.com/stokito/maven'
}

dependencies {
    compile('name.stokito:FreeTTSLog4JAppender:1.0.1')
}
```
Then add following lines to `Config.groovy`:

```groovy
// log4j configuration
log4j = {
    appenders {
        appender(new FreeTTSAppender(name: 'freetts', 
            threshold: org.apache.log4j.Level.INFO,
            voiceName: 'kevin16'))
    }

    root {
        error 'freetts'
    }
}
```

