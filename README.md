# Catalog API

## What is it?

**_Make easier to maintain and use constants_**

This is a Java-based API designed to make the constants group more maintainable and extensible.<br>
It manages regularized constants as a single `catalog` and provides a convenient utility interface to manipulate the `catalog`.

## Benefits

- It assumes the use of `Enum`
- Necessary processes for constant operation are integrated in the interface
- Useful common behaviors can be used anywhere
- No need to use `public static final` constant classes anymore
- **_Easy to maintain and use constants!_**

## How To Use

### 1. Add the dependencies

> **_Note:_**<br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/catalog-api/packages).<br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.api.catalog</groupId>
  <artifactId>catalog-api</artifactId>
  <version>v1.0.1</version>
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
    implementation 'org.thinkit.api.catalog:catalog-api:v1.0.1'
}
```

### 2. Select interface you want to use

The `Catalog API` currently provides the following interfaces.<br>
Choose the interface you want to use depending on your needs.

| Interface                                     | Overview                                                                                                                                                                                                                                                                                 |
| --------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **_Catalog<E extends Catalog<E>>_**           | This is the basic catalog interface.<br>Elements of the concrete catalog class that implement this interface have a `code value`.<br>You can use the common behavior defined in the Catalog interface.                                                                                   |
| **_BiCatalog<E extends BiCatalog<E, T>, T>_** | Each element of a concrete catalog class that implements this interface can have a `code value` plus a `value of any data type`.<br>The arbitrary data type is defined at the time of implementation of the concrete catalog class.<br>You can use the common behavior of the interface. |

### 3. Import and implement Enum with Catalog / BiCatalog interface

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

## License

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

## More Information

`Catalog API` was designed and implemented by Kato Shinya, who works as a freelance developer from Japan.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [License](https://github.com/myConsciousness/catalog-api/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/catalog-api/releases)
- [Package](https://github.com/myConsciousness/catalog-api/packages)
- [File a Bug](https://github.com/myConsciousness/catalog-api/issues)
