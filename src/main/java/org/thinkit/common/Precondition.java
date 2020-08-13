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

import java.util.List;
import java.util.Map;

import org.thinkit.common.exception.IllegalListFoundException;
import org.thinkit.common.exception.IllegalMapFoundException;
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

    /**
     * 引数として渡された {@code object} オブジェクトの参照が {@code null} であるか判定します。 {@code object}
     * オブジェクトの参照が {@code null} である場合には {@link NullPointerException} が必ず実行時に発生します。
     *
     * @param object {@code null} 判定対象のオブジェクト
     *
     * @throws NullPointerException 引数として {@code null} が渡された場合
     */
    static void requireNonNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの文字列が空文字列であるか判定します。 {@code sequence}
     * オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException} が必ず実行時に発生します。
     * <p>
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null} である可能性がある場合は
     * {@link #requireNonEmpty(String)} メソッドを使用してください。
     *
     * @param sequence 判定対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonBlank(String sequence) {
        if (sequence.isEmpty()) {
            throw new IllegalSequenceFoundException("String must not be blank");
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null} 、または文字列が空文字列であるか判定します。
     * <p>
     * {@code sequence} オブジェクトの参照が {@code null} の場合は {@link NullPointerException}
     * が必ず実行時に発生します。
     * <p>
     * {@code sequence} オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException}
     * が必ず実行時に発生します。
     *
     * @param sequence 判定対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonEmpty(String sequence) {
        requireNonNull(sequence);
        requireNonBlank(sequence);
    }

    /**
     * 引数として指定された {@code number} の数値が正数であるか判定します。引数として指定された {@code number}
     * の数値が負数である場合は {@link IllegalNumberFoundException} が必ず実行時に発生します。
     *
     * @param number 判定対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が負数の場合
     */
    static void requirePositive(int number) {
        if (number < 0) {
            throw new IllegalNumberFoundException(String.format("Number must be positive but %s was given", number));
        }
    }

    /**
     * 引数として指定された {@code number} の数値が負数であるか判定します。引数として指定された {@code number}
     * の数値が正数である場合は {@link IllegalNumberFoundException} が必ず実行時に発生します。
     *
     * @param number 判定対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が正数の場合
     */
    static void requireNegative(int number) {
        if (number < 0) {
            throw new IllegalNumberFoundException(String.format("Number must be negative but %s was given", number));
        }
    }

    /**
     * 引数として指定された {@code index} が {@code 0} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index} が範囲外にある数値である場合は
     * {@link IndexOutOfBoundsException} が必ず実行時に発生します。
     *
     * @param index 判定対象のインデックス
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code number} の数値が {@code 0} から
     *                                   {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to) {
        if (index < 0 || to < index) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to));
        }
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index} が範囲外にある数値である場合は
     * {@link IndexOutOfBoundsException} が必ず実行時に発生します。
     *
     * @param index 判定対象のインデックス
     * @param from  判定時の最低値
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code number} の数値が {@code from}
     *                                   から {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to) {
        if (index < from || to < index) {
            throw new IndexOutOfBoundsException(
                    String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to));
        }
    }

    /**
     * 引数として渡された {@code list} が空か判定します。
     * <p>
     * この {@link #requireNonEmpty(List)} メソッドは {@link List#isEmpty()} メソッドでの判定前に
     * {@code null} の判定を行いません。
     *
     * @param list 判定対象のリスト
     *
     * @throws IllegalListFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalListFoundException("List must contain at least one or more elements");
        }
    }

    /**
     * 引数として渡された {@code map} が空か判定します。
     * <p>
     * この {@link #requireNonEmpty(Map)} メソッドは {@link Map#isEmpty()} メソッドでの判定前に
     * {@code null} の判定を行いません。
     *
     * @param list 判定対象のリスト
     *
     * @throws IllegalMapFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(Map<?, ?> map) {
        if (map.isEmpty()) {
            throw new IllegalMapFoundException("Map must contain at least one or more elements");
        }
    }
}