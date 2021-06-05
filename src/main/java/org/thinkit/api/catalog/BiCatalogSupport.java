/*
 * Copyright 2021 Kato Shinya.
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

import java.util.Objects;

/**
 * The interface that represents the basic functions of BiCatalog.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public interface BiCatalogSupport<E extends BiCatalog<E, T>, T> extends CodeSupport, TagSupport<T> {

    /**
     * Converts to the Enum class object.
     *
     * @return Converted Enum class
     */
    @SuppressWarnings("unchecked")
    default E toEnum() {
        return (E) this;
    }

    /**
     * Checks if the code value given as an argument is equal to the code value of
     * this catalog element.
     *
     * @param code The code value
     * @return {@code true} if the code value given as an argument is equal to the
     *         code value of this catalog element, othereise {@code false}
     */
    default boolean equalsByCode(int code) {
        return this.getCode() == code;
    }

    /**
     * Checks if the tag value given as an argument is equal to the tag value of
     * this catalog element.
     *
     * @param tag The tag value
     * @return {@code true} if the tag value given as an argument is equal to the
     *         tag value of this catalog element, othereise {@code false}
     */
    default boolean equalsByTag(T tag) {
        return Objects.equals(this.getTag(), tag);
    }
}
