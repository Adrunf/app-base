# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Base/template Spring Boot 3.2 project (Java 21, PostgreSQL, Flyway, QueryDSL, MapStruct, Lombok, JasperReports). Multi-module Gradle build; group `com.ec.app`, all code under package `com.ec.app.microservices`. Code comments and SQL are in Spanish.

## Build configuration

The build is self-contained. The root `build.gradle` declares the plugin versions (Spring Boot 3.2.0, dependency-management 1.1.0), repositories (`mavenCentral()` + `mavenLocal()`), and in `subprojects {}` applies `java-library`, Java 21, UTF-8, `-parameters`, `maven-publish` to mavenLocal, plus the `dependencyManagement` block with the managed versions. Dependencies in module `build.gradle` files are mostly declared **without versions**; add/change versions in the root `dependencyManagement` block. Gradle must run on JDK 17+ (the Spring Boot plugin requires it).

## Commands

```bash
./gradlew build                                   # build all modules
./gradlew :app-base-services:bootRun              # run the app (port 8081)
./gradlew :app-base-services:test                 # tests (JUnit 5; only this module is configured for tests)
./gradlew :app-base-services:test --tests 'com.ec.app.microservices.SomeTest.method'
./gradlew compileJava                             # regenerates QueryDSL Q-classes (annotation processing)
docker compose up -d                              # local PostgreSQL 17 (reads .env)
```

Running locally requires `SPRING_PROFILES_ACTIVE=dev` (the profile is not set in `application.yaml`, and `BaseAppConfiguration` injects `${spring.profiles.active}`, so startup fails without it). The dev profile reads DB credentials from `.env` (`POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_PORT`) via `spring.config.import: optional:file:.env[.properties]` — this path is relative to the process working directory, so `.env` must be resolvable from wherever the app is launched.

## Module layering

Dependencies flow `vo` ← `client` ← `core` ← `services`:

- **app-base-vo** – DTOs/VOs (`PersonDTO`, `ProvinceDTO`, report VOs) and the generic `config.Response<T>` envelope (`data`, `message`, `code`) returned by every controller.
- **app-base-client** – contracts and model: JPA `@Entity` classes, repository interfaces (`I*Repository`), service interfaces (`I*Service`), constants, and the `resources` properties bundle. QueryDSL Q-classes are generated here from the entities.
- **app-base-core** – implementations: `*Repository` and `*Service` classes, `BaseAppConfiguration` (entity scan, JPA repos, component scan on `com.ec.app`, transactions), `GlobalExceptionHandler`, and `CommonService` (Jasper → XLSX report generation from a classpath `.jrxml`).
- **app-base-services** – the only Spring Boot app (`PersonApplication`, `jar` disabled → produces `bootJar`), REST controllers, `application*.yaml`, Flyway migrations, `jrxml/` report templates.

Each feature (e.g. `person`, `province`) is split across modules using the same sub-package name: `entity`/`repository`/`service` interfaces in client, implementations in core, controller in services.

## Conventions

- **Repositories are QueryDSL, not Spring Data interfaces.** Implementations extend `config.JPAQueryDslBaseRepository<T>` (a `QuerydslRepositorySupport` providing `save`/`update`/`delete` via the EntityManager) and implement the matching `I*Repository` (which extends `IQueryDslBaseRepository<T>`). Queries use `from(qEntity).select(Projections.bean(SomeDTO.class, ...))` to project straight into DTOs.
- Beans are annotated `@Lazy` and wired with field `@Autowired` + `@Lazy` against the interface types. Services are `@Transactional`.
- Controllers wrap results: `new ResponseEntity<>(Response.<T>builder().data(...).message(...).code(HttpStatus.OK.value()).build(), HttpStatus.OK)`.
- Messages come from `PersonConstants` → `PersonProperties.getString(key)`, backed by `resources/person.properties` in app-base-client. The bundle is resolved with a locale equal to the active profile (`System.property "env"` set by `BaseAppConfiguration`), so profile-specific `person_<profile>.properties` files can override the base one.

## Database / Flyway

- `ddl-auto: validate` — Hibernate never creates tables; every schema change needs a Flyway migration. Entities map to schema `base_app`; Spring's default naming converts camelCase fields to snake_case columns.
- Migrations live in `app-base-services/src/main/resources/db/migration` (real) and `db/dev` (temporary test tables `person`/`province`, loaded only by the dev profile). Per comments in the SQL, `db/dev` plus the Person entity are meant to be removed once the base setup is verified.

## Known inconsistencies (leftovers from the template it was copied from)

- `Dockerfile` copies `app-person-services/build/libs/*.jar` and uses a Java 17 distroless image; the module is `app-base-services` and the build targets Java 21.
- `application-docker.yaml` points at MySQL and Eureka, while the project uses PostgreSQL and has no MySQL driver or Eureka client.
