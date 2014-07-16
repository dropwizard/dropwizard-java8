Dropwizard Java 8 Bundle
========================

[![Build Status](https://travis-ci.org/joschi/dropwizard-java8.svg?branch=master)](https://travis-ci.org/joschi/dropwizard-java8)
[![Coverage Status](https://img.shields.io/coveralls/joschi/dropwizard-java8.svg)](https://coveralls.io/r/joschi/dropwizard-java8)

An addon bundle and a set of classes for using Java 8 features like `Optional` and the new Date/Time API (JSR-310) in a [Dropwizard](http://www.dropwizard.io/) application.


Usage
-----

Just add `Java8Bundle` to your [Application](https://dropwizard.github.io/dropwizard/0.7.1/dropwizard-core/apidocs/io/dropwizard/Application.html) class
as described in the manual in the [Bundles](https://dropwizard.github.io/dropwizard/0.7.1/docs/manual/core.html#man-core-bundles) paragraph.

This will add support for `Optional<T>` to Jersey and support for JSR-310 and `Optional<T>` to Jackson.

    public class DemoApplication extends Application<DemoConfiguration> {
        // [...]
        @Override
        public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
            bootstrap.addBundle(new Java8Bundle());
            // [...]
        }
    }


Maven Artifacts
---------------

This project is available on Maven Central. To add it to your project simply add the following dependencies to your
`pom.xml`:

    <dependency>
      <groupId>com.github.joschi</groupId>
      <artifactId>dropwizard-java8</artifactId>
      <version>0.1.0</version>
    </dependency>


Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/joschi/dropwizard-java8/issues).


License
-------

Copyright (c) 2014 Jochen Schalanda

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.
