# Carbon CID

Controlled Identifiers (CIDs) are a W3C standard for identifiers that are cryptographically bound to a controller. They provide a verifiable way to associate identifiers with key material and establish control, which is essential for security-sensitive applications such as verifiable credentials and authorization systems.  

Carbon is a Java implementation of the [Controlled Identifiers (CID) 1.0](https://www.w3.org/TR/cid) standard, making it possible to create, validate, and resolve controlled identifiers in Java applications.  

The library provides core primitives for representing and processing CIDs, along with resolver interfaces for obtaining their associated documents.


[![Java 8 CI](https://github.com/filip26/carbon-cid/actions/workflows/java8-build.yml/badge.svg)](https://github.com/filip26/carbon-cid/actions/workflows/java8-build.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dd79aafc6eb14ed18f2217de62585ba7)](https://app.codacy.com/gh/filip26/carbon-cid/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/dd79aafc6eb14ed18f2217de62585ba7)](https://app.codacy.com/gh/filip26/carbon-cid/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)
[![javadoc](https://javadoc.io/badge2/com.apicatalog/carbon-cid/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/carbon-did)
[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/carbon-cid.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.apicatalog%20AND%20a:carbon-cid)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


## Features

* DID, DID URL, DID Document
* DID Methods
  * did:key
  * did:web
* Controller Document
* Verification Methods
  * Multikey
  * JsonWebKey

## Installation

### Maven

```xml
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>carbon-cid</artifactId>
    <version>0.2.0</version>
</dependency>
```

## Contributing

All PR's welcome!


### Building

Fork and clone the project repository.

```bash
> cd carbon-cid
> mvn clean package
```

## Resources

- [Controller Identifiers v1.0](https://www.w3.org/TR/cid)
- [Carbon CID JSON-LD](https://github.com/filip26/carbon-cid-json-ld)

## Sponsors

<a href="https://github.com/digitalbazaar">
  <img src="https://avatars.githubusercontent.com/u/167436?s=200&v=4" width="40" />
</a> 

## Commercial Support

Commercial support and consulting are available.  
For inquiries, please contact: filip26@gmail.com

