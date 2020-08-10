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

package org.thinkit.common.catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 演算子を管理するカタログです。<br>
 * {@link} {@link #getOperand()}を使用することで各演算子の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各演算子の文字列を表現を取得できます。<br>
 * {@link #plus()}<br>
 * {@link #minus()}<br>
 * {@link #devide()}<br>
 * {@link #multiple()}<br>
 * {@link #equal()}<br>
 * {@link #assignment()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Operand implements Catalog<Operand> {

    /**
     * 足し算
     */
    PLUS(0, "+"),

    /**
     * 引き算
     */
    MINUS(1, "-"),

    /**
     * 割り算
     */
    DEVIDE(2, "/"),

    /**
     * 掛け算
     */
    MULTIPLE(3, "*"),

    /**
     * 等価
     */
    EQUAL(4, "=="),

    /**
     * 割り当て
     */
    ASSIGNMENT(5, "=");

    /**
     * コード値
     */
    private final int code;

    /**
     * 演算子
     */
    private final String operand;

    /**
     * 足し算の演算子を文字列表現として返却します。
     *
     * @return 足し算の演算子の文字列表現
     * @see #PLUS
     */
    public static String plus() {
        return PLUS.getOperand();
    }

    /**
     * 引き算の演算子を文字列表現として返却します。
     *
     * @return 引き算の演算子を文字列表現
     * @see #MINUS
     */
    public static String minus() {
        return MINUS.getOperand();
    }

    /**
     * 割り算の演算子を文字列表現として返却します。
     *
     * @return 割り算の演算子の文字列表現
     * @see #DEVIDE
     */
    public static String devide() {
        return DEVIDE.getOperand();
    }

    /**
     * 掛け算の演算子を文字列表現として返却します。
     *
     * @return 掛け算の演算子の文字列表現
     * @see #MULTIPLE
     */
    public static String multiple() {
        return MULTIPLE.getOperand();
    }

    /**
     * 等価演算子を文字列表現として返却します。
     *
     * @return 等価演算子の文字列表現
     * @see #EQUAL
     */
    public static String equal() {
        return EQUAL.getOperand();
    }

    /**
     * 割り当て演算子を文字列表現として返却します。
     *
     * @return 割り当て演算子の文字列表現
     * @see #ASSIGNMENT
     */
    public static String assignment() {
        return ASSIGNMENT.getOperand();
    }
}