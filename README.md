Dropwizard Java 8 Bundle
========================

[![Build Status](https://travis-ci.org/dropwizard/dropwizard-java8.svg?branch=master)](https://travis-ci.org/dropwizard/dropwizard-java8)
[![Coverage Status](https://img.shields.io/coveralls/dropwizard/dropwizard-java8.svg)](https://coveralls.io/r/dropwizard/dropwizard-java8)

An addon bundle and a set of classes for using Java 8 features like `Optional<T>` and the new Date/Time API (JSR-310) in a [Dropwizard](http://www.dropwizard.io/) application.

Currently it supports Java 8 versions of [dropwizard-auth](http://dropwizard.io/0.8.0/dropwizard-auth/) and [dropwizard-jdbi](http://dropwizard.io/0.8.0/dropwizard-jdbi/).


Usage
-----

Just add `Java8Bundle` to your [Application](http://dropwizard.io/0.8.0/dropwizard-core/apidocs/io/dropwizard/Application.html) class
as described in the manual in the [Bundles](http://dropwizard.io/0.8.0/docs/manual/core.html#man-core-bundles) paragraph.

This will add support for `Optional<T>` to Jersey and support for JSR-310 and `Optional<T>` to Jackson.

    public class DemoApplication extends Application<DemoConfiguration> {
        // [...]
        @Override
        public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
            bootstrap.addBundle(new Java8Bundle());
            // [...]
        }
    }

When using `ResourceTestRule` in unit tests, Java8 providers need to registered to ensure correct behaviour:

    public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(new HelloWorldResource())
        .addProvider(OptionalMessageBodyWriter.class)
        .addProvider(OptionalParamFeature.class)
        .build();


Maven Artifacts
---------------

This project is available on Maven Central. To add it to your project simply add the following dependencies to your
`pom.xml`:

    <dependency>
      <groupId>io.dropwizard.modules</groupId>
      <artifactId>dropwizard-java8</artifactId>
      <version>0.8.0-1</version>
    </dependency>

    <dependency>
      <groupId>io.dropwizard.modules</groupId>
      <artifactId>dropwizard-java8-auth</artifactId>
      <version>0.8.0-1</version>
    </dependency>

    <dependency>
      <groupId>io.dropwizard.modules</groupId>
      <artifactId>dropwizard-java8-jdbi</artifactId>
      <version>0.8.0-1</version>
    </dependency>

When using JSR-310 Dates with Hibernate, [Jadira UserType Extend](http://jadira.sourceforge.net/usertype.extended/) needs to be added:

    <dependency>
        <groupId>org.jadira.usertype</groupId>
        <artifactId>usertype.extended<artifactId>
        <version>3.2.0.GA</version>
    </dependency>

Dropwizard will auto register this in `SessionFactoryFactory`.

Support
-------

Please file bug reports and feature requests in [GitHub issues](https://github.com/dropwizard/dropwizard-java8/issues).


License
-------

Copyright (c) 2014 Jochen Schalanda

This library is licensed under the Apache License, Version 2.0.

See http://www.apache.org/licenses/LICENSE-2.0.html or the LICENSE file in this repository for the full license text.
