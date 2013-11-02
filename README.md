# FreeTTS Log4J Appender

Talking log appender to keep your eyes :)

# Add to your project
```xml
<dependency>
    <groupId>name.stokito</groupId>
    <artifactId>FreeTTSLog4JAppender</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Then add it as usual Log4J appender.

## How to use in Grails
Add dependecy in `BuildConfig.groovy`:

```groovy
dependencies {
    compile('name.stokito:FreeTTSLog4JAppender:1.0-SNAPSHOT')
}
```

Then add folowing lines to `Config.groovy`:

```groovy
// log4j configuration
log4j = {
    appender(new FreeTTSAppender(name: 'freetts', 
        threshold: Level.WARN,
        voiceName: 'kevin16'))

    root {
        all 'stdout', 'freetts'
    }
}
```

