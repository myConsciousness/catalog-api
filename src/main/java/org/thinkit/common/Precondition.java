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

package org.thinkit.common;

import org.thinkit.common.exception.IllegalNumberFoundException;
import org.thinkit.common.exception.IllegalSequenceFoundException;

/**
 * 前提条件を判定する処理を定義したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Precondition {

    static void requireNonNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    static void requireNonBlank(String sequence) {
        if (sequence.isEmpty()) {
            throw new IllegalSequenceFoundException("String must not be blank");
        }
    }

    static void requireNonEmpty(String sequence) {
        requireNonNull(sequence);
        requireNonBlank(sequence);
    }

    static void requirePositive(int number) {
        if (number < 0) {
            throw new IllegalNumberFoundException(String.format("Number must be positive but %s was given", number));
        }
    }

    static void requireNegative(int number) {
        if (number < 0) {
            throw new IllegalNumberFoundException(String.format("Number must be negative but %s was given", number));
        }
    }

    static void requireRange(int index, int to) {
        if (index < 0 || to < index) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to));
        }
    }

    static void requireRange(int index, int from, int to) {
        if (index < from || to < index) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to));
        }
    }
}