# Home Worth Service

A home appraisal service.

## Prerequisites

* [Docker Engine (>=19.03.10)](https://docs.docker.com/engine)
* [Docker Compose (>=1.27.0)](https://docs.docker.com/compose)

## Installation

Build and run service:

```
docker-compose up -d
```

## Requirements

* US00001: `POST /api/v1/houses/area`
* US00002: `POST /api/v1/houses/valuation`
* US00003: `POST /api/v1/rooms/largest`
* US00004: `POST /api/v1/rooms/areas`

## Documentation

Check the API reference for models and routes:

```
http://localhost:8000/api/v1/docs
```

The hostname and port of the server are defined by the `.env` file in the project root directory.

## Copyright and license

Copyright (c) 2021, Mercado Libre, Inc. All rights reserved.

Project developed under a [BSD-3-Clause license](LICENSE.md).
