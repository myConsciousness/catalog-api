/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.api.catalog;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The interface that extends the functionality of Enum, a built-in feature of
 * Java, and consolidates general-purpose functions used in many situations.
 * This interface can be implements in existing Enum classes to easily add
 * useful functionality. When implementing this interface, please specify the
 * type of the class that implements this interface in the generic. The
 * {@link BiCatalog} interface will then manage values of any type as tags, and
 * the second generic type should be the type of value you want to manage in the
 * Enum element.
 *
 * <p>
 * This interface manages the code value used to identify each element of an
 * Enum. The code value is an arbitrary numeric value that can be specified for
 * each element of the Enum class that implements this interface; the code value
 * for each element of the Enum does not need to be in the order of the
 * elements, and can be specified in any way that the implementor of this
 * interface chooses. However, since this code value is used to identify each
 * element of the Enum, it is important that the specified value does not
 * overlap with any other element in the Enum class.
 *
 * <p>
 * The value managed as a tag can be any value as long as it conforms to the
 * type specified in the generics. However, since Java does not currently allow
 * primitive types to be specified in generics, only object types can be managed
 * as tags. This specification may be improved in a later version of Java.
 *
 * <p>
 * This interface provides generic functions based on the code values specified
 * in the Enum class that implements this interface. For example, there is a
 * {@link #hasCode(Class, int)} to determine whether the code value given as an
 * argument is defined in the target Enum class, and there is a
 * {@link #getEnum(Class, int)} that returns the Enum element associated with
 * the number given as an argument from the target Enum class. Other generic
 * methods are also provided, but all of them require the target Enum class to
 * implement this interface.
 *
 * <p>
 * The basic implementation and usage examples of this interface are briefly
 * described below.
 *
 * <pre>
 * <code>
 * public enum EnumClass implements BiCatalog&lt;EnumClass, String&gt; {
 *
 *    ELEMENT_1(0, "test1"),
 *
 *    ELEMENT_2(1, "test2");
 *
 *    private int code;
 *
 *    private String tag;
 *
 *    EnumClass(int code) {
 *        this.code = code;
 *    }
 *
 *    &#64;Override
 *    public int getCode() {
 *        return this.code;
 *    }
 *
 *    &#64;Override
 *    public String getTag() {
 *        return this.tag;
 *    }
 * }
 * </code>
 * </pre>
 *
 * <pre>
 * <code>
 * public class TestBiCatalog {
 *
 *     public static void main(String[] args) {
 *         BiCatalog.hasCode(EnumClass.class, 1); // Returns true
 *         BiCatalog.getEnum(EnumClass.class, 1); // Returns EnumClass#ELEMENT_2

 *         BiCatalog.contains(EnumClass.class, "test1"); // Returns true
 *         BiCatalog.getEnumByTag(EnumClass.class, "test1"); // Returns EnumClass#ELEMENT_1
 *     }
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface BiCatalog<E extends BiCatalog<E, T>, T> extends BiCatalogSupport<E, T> {

    /**
     * Returns the {@link List} representation of this catalog class.
     *
     * <p>
     * The {@link List} representation of this catalog class is sorted each element
     * based on the code value specified for each element of the Enum class that
     * implements the {@link Catalog} interface. This sorting process is based on
     * the {@link Stream#sorted()} algorithm.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The Enum class to be sorted
     * @return The sorted Enum elements based on the code value
     */
    public static <E extends BiCatalog<E, T>, T> List<E> getOrderedList(Class<? extends BiCatalog<E, T>> clazz) {
        return stream(clazz).sorted(Comparator.comparing(BiCatalog::getCode)).map(BiCatalog::toEnum)
                .collect(Collectors.toList());
    }

    /**
     * Returns the Enum element linked to the code value given as an argument from
     * the target Enum class. If the target Enum class does not have an Enum element
     * linked to the code value given as an argument, {@code null} is returned.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @param code  The code value linked to the Enum element
     * @return The Enum element linked to the code value, or {@code null} if the
     *         target Enum class does not have an Enum element linked to the code
     *         value
     */
    public static <E extends BiCatalog<E, T>, T> E getEnum(Class<? extends BiCatalog<E, T>> clazz, int code) {
        return stream(clazz).filter(e -> e.equalsByCode(code)).map(BiCatalog::toEnum).findFirst().orElse(null);
    }

    /**
     * Returns the Enum element linked to the tag value given as an argument from
     * the target Enum class. If the target Enum class does not have an Enum element
     * linked to the tag value given as an argument, {@code null} is returned.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @param tag   The tag value linked to the Enum element
     * @return The Enum element linked to the tag value, or {@code null} if the
     *         target Enum class does not have an Enum element linked to the tag
     *         value
     */
    public static <E extends BiCatalog<E, T>, T> E getEnumByTag(Class<? extends BiCatalog<E, T>> clazz, T tag) {
        return stream(clazz).filter(e -> e.equalsByTag(tag)).map(BiCatalog::toEnum).findFirst().orElse(null);
    }

    /**
     * Returns the {@link Map} representation of this catalog class.
     *
     * <p>
     * The structure of the map returned by this {@link #getMap} has the code value
     * specified in the Enum element of the Enum class that implements this
     * {@link Catalog} interface as the key and the Enum element linked to the code
     * value as the value.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @return The {@link Map} representation of this catalog class
     */
    public static <E extends BiCatalog<E, T>, T> Map<Integer, E> getMap(Class<? extends BiCatalog<E, T>> clazz) {
        return stream(clazz).collect(Collectors.toMap(BiCatalog::getCode, BiCatalog::toEnum));
    }

    /**
     * Checks if the target Enum class has an Enum element linked to the code value
     * passed as an argument.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @param code  The code value
     * @return {@code true} if the target Enum class has an Enum element linked to
     *         the code value passed as an argument, otherwise {@code false}
     */
    public static <E extends BiCatalog<E, T>, T> boolean hasCode(Class<? extends BiCatalog<E, T>> clazz, int code) {
        return stream(clazz).anyMatch(e -> e.equalsByCode(code));
    }

    /**
     * Checks if the target Enum class has an Enum element linked to the tag value
     * passed as an argument.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @param tag   The tag value
     * @return {@code true} if the target Enum class has an Enum element linked to
     *         the tag value passed as an argument, otherwise {@code false}
     */
    public static <E extends BiCatalog<E, T>, T> boolean contains(Class<? extends BiCatalog<E, T>> clazz, T tag) {
        return stream(clazz).anyMatch(e -> e.equalsByTag(tag));
    }

    /**
     * Returns the {@link Stream} representation of the target Enum class.
     *
     * @param <E>   The type of Enum class
     * @param <T>   The type of tag value
     * @param clazz The target Enum class
     * @return The {@link Stream} representation of the target Enum class
     */
    public static <E extends BiCatalog<E, T>, T> Stream<? extends BiCatalog<E, T>> stream(
            Class<? extends BiCatalog<E, T>> clazz) {
        return Arrays.stream(clazz.getEnumConstants());
    }
}
