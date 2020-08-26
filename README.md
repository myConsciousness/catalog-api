# Catalog API

## What is it?

**_Make easier to manage and use constants_**

Catalog API main repository.

## Benefits

- It assumes the use of `Enum`
- Necessary processes for constant operation are integrated in the interface
- Useful common behaviors can be used anywhere
- No need to use `public static final` constant classes anymore
- **_Easy to manage and use constants!_**

## How To Use

### 1. Add the dependencies

> Note:<br>
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

### Select interface you want to use

The `Catalog API` currently provides the following interfaces.<br>
Choose the interface you want to use depending on your needs.

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
- [License](https://github.com/myConsciousness/json-formatter/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/catalog-api/releases)
- [Package](https://github.com/myConsciousness/catalog-api/packages)
- [File a Bug](https://github.com/myConsciousness/catalog-api/issues)
