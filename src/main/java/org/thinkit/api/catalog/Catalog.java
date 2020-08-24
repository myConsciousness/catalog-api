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

/**
 * An interface that defines common operations of the Catalog.
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @param <E> Wildcard type
 */
public interface Catalog<E extends Catalog<E>> {

    /**
     * Return a code value.
     *
     * @return Code value
     */
    public int getCode();

    /**
     * Convert to an Enum class object.
     *
     * @return Converted an Enum class object
     */
    @SuppressWarnings("unchecked")
    default E toEnum() {
        return (E) this;
    }

    /**
     * Check whether the code value of the Enum element is equal to the value passed
     * as an argument.
     *
     * @param code Code value
     * @return If the number passed as an argument is equal to the code value of the
     *         Enum element, {@code true}, otherwise {@code false}.
     */
    default boolean equalsByCode(int code) {
        return getCode() == code;
    }

    /**
     * Each element of the Enum class that implements this interface is sorted based
     * on the code value defined for each element. The result of the sorting process
     * is converted into a list structure with the order in which it is displayed
     * and returned.
     *
     * @param <E>   Wildcard type
     * @param clazz Enum class object to be operated on
     * @return A list containing the values sorted based on the code values defined
     *         for each element of the Enum class
     */
    public static <E extends Catalog<E>> List<E> getOrderedList(Class<? extends Catalog<E>> clazz) {
        return Arrays.stream(clazz.getEnumConstants()).sorted(Comparator.comparing(Catalog::getCode))
                .map(Catalog::toEnum).collect(Collectors.toList());
    }

    /**
     * The code values defined for each element of the Enum class that implements
     * the interface are compared with the numbers passed as arguments, and the Enum
     * element that matches the values is returned.
     *
     * @param <E>   Wildcard type
     * @param clazz Enum class object to be operated on
     * @param code  Code value
     * @return Enum element with a code value equal to the number specified as an
     *         argument
     */
    public static <E extends Catalog<E>> E getEnum(Class<? extends Catalog<E>> clazz, int code) {
        return Arrays.stream(clazz.getEnumConstants()).filter(e -> e.equalsByCode(code)).map(Catalog::toEnum)
                .findFirst().orElse(null);
    }

    /**
     * The code value defined for each element of the Enum class that implements the
     * interface is compared with the number specified as an argument. Return the
     * Enum element whose value matches the code value as a key.
     *
     * @param <E>   Wildcard type
     * @param clazz Enum class object to be operated on
     * @return Map containing an Enum element with a code value equal to the number
     *         specified as an argument as a code key
     */
    public static <E extends Catalog<E>> Map<Integer, E> getMap(Class<? extends Catalog<E>> clazz) {
        return Arrays.stream(clazz.getEnumConstants()).collect(Collectors.toMap(Catalog::getCode, Catalog::toEnum));
    }

    /**
     * This function determines whether the code value specified as an argument is
     * defined in each element of the Enum class that implements the interface
     * concerned.
     *
     * @param <E>   Wildcard type
     * @param clazz Enum class object to be operated on
     * @param code  Code value
     * @return {@code true} if there is an Enum element with a code value equal to
     *         the number specified as an argument, otherwise {@false}
     */
    public static <E extends Catalog<E>> boolean hasCode(Class<? extends Catalog<E>> clazz, int code) {
        return Arrays.stream(clazz.getEnumConstants()).anyMatch(e -> e.equalsByCode(code));
    }
}
