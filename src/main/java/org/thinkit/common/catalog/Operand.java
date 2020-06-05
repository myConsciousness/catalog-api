/**
 * Project Name : dev-utils<br>
 * File Name : Operand.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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