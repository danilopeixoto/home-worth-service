# Home Worth API

A home appraisal API.

## Prerequisites

* [OpenJDK (>=11)](https://openjdk.java.net)

## Installation

Build and run dependencies:

```
docker-compose -f ../docker-compose.development.yml up -d
```

Run tests:

```
gradlew test
```

Run service:

```
gradlew bootRun --args='--spring.profiles.active=development'
```

## Tests

* Controllers and services are testable.
* Controller tests are unit or integration tests for the REST layer.
* District-related tests not implemented.

## Copyright and license

Copyright (c) 2021, Mercado Libre, Inc. All rights reserved.

Project developed under a [BSD-3-Clause license](LICENSE.md).
