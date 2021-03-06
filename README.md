![Build](https://img.shields.io/badge/Build-Automated-2980b9.svg?style=for-the-badge)
![Latest Version](https://img.shields.io/badge/Latest_Version-v1.0.3-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/catalog-api/workflows/Java%20CI%20with%20Gradle/badge.svg)

# 1. Catalog API

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [1.1. What is it?](#11-what-is-it)
- [1.2. Benefits](#12-benefits)
- [1.3. How To Use](#13-how-to-use)
  - [1.3.1. Add the dependencies](#131-add-the-dependencies)
  - [1.3.2. Select interface you want to use](#132-select-interface-you-want-to-use)
  - [1.3.3. Import and implement Enum with Catalog / BiCatalog interface](#133-import-and-implement-enum-with-catalog--bicatalog-interface)
- [1.4. License](#14-license)
- [1.5. More Information](#15-more-information)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.1. What is it?

**_Make easier to maintain and use constants!_**

This is a Java-based API designed to make the constants group more maintainable and extensible.<br>
It manages regularized constants as a single `catalog` and provides a convenient utility interface to manipulate the `catalog`.

## 1.2. Benefits

- It assumes the use of `Enum`
- Necessary processes for constant operation are integrated in the interface
- Useful common behaviors can be used anywhere
- No need to use `public static final` constant classes anymore
- **_Easy to maintain and use constants!_**

## 1.3. How To Use

### 1.3.1. Add the dependencies

> **_Note:_**<br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/catalog-api/packages).<br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.api.catalog</groupId>
  <artifactId>catalog-api</artifactId>
  <version>v1.0.3</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/catalog-api")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.thinkit.api.catalog:catalog-api:v1.0.3'
}
```

### 1.3.2. Select interface you want to use

The `Catalog API` currently provides the following interfaces.<br>
Choose the interface you want to use depending on your needs.

| Interface                                     | Overview                                                                                                                                                                                                                                                                                 |
| --------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **_Catalog<E extends Catalog<E>>_**           | This is the basic catalog interface.<br>Elements of the concrete catalog class that implement this interface have a `code value`.<br>You can use the common behavior defined in the Catalog interface.                                                                                   |
| **_BiCatalog<E extends BiCatalog<E, T>, T>_** | Each element of a concrete catalog class that implements this interface can have a `code value` plus a `value of any data type`.<br>The arbitrary data type is defined at the time of implementation of the concrete catalog class.<br>You can use the common behavior of the interface. |

### 1.3.3. Import and implement Enum with Catalog / BiCatalog interface

**_Catalog<E extends Catalog<E>>_**

```java
import org.thinkit.api.catalog.Catalog;

/**
 * Because the catalog applies the mechanism of recursive generics,
 * specify own type to the generics when implement the Catalog interface.
 */
public enum TestCatalog implements Catalog<TestCatalog> {

    /**
     * Catalog constants need unique code value as an parameter
     */
    TEST(0);

    /**
     * Required code value
     */
    private int code;

    /**
     * Simple constructor
     */
    TestCatalog(int code) {
        this.code ＝ code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
```

**_BiCatalog<E extends BiCatalog<E, T>, T>_**

```java
import org.thinkit.api.catalog.BiCatalog;

/**
 * Because the catalog applies the mechanism of recursive generics,
 * specify own type to the generics when implement the BiCatalog interface.
 */
public enum TestBiCatalog implements BiCatalog<TestBiCatalog, String> {

    /**
     * BiCatalog constants need unique code value and any tag value as parameters
     */
    TEST(0, "test");

    /**
     * Required code value
     */
    private int code;

    /**
     * Required tag value.
     * The data type of the tag is the data type specified to generics when implement BiCatalog interface.
     */
    private String tag;

    /**
     * Simple constructor
     */
    TestCatalog(int code, String tag) {
        this.code ＝ code;
        this.tag ＝ tag;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getTag() {
        return this.tag;
    }
}
```

## 1.4. License

```
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## 1.5. More Information

`Catalog API` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io)
- [License](https://github.com/myConsciousness/catalog-api/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/catalog-api/releases)
- [Package](https://github.com/myConsciousness/catalog-api/packages)
- [File a Bug](https://github.com/myConsciousness/catalog-api/issues)
- [Reference](https://myconsciousness.github.io/catalog-api/org/thinkit/api/catalog/package-summary.html)
