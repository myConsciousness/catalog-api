/**
 * Project Name : dev-utils<br>
 * File Name : OperandTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link Operand}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class OperandTest {

    /**
     * 足し算
     */
    private static final String OPERAND_PLUS = "+";

    /**
     * 引き算
     */
    private static final String OPERAND_MINUS = "-";

    /**
     * 割り算
     */
    private static final String OPERAND_DEVIDE = "/";

    /**
     * 掛け算
     */
    private static final String OPERAND_MULTIPLE = "*";

    /**
     * 等価
     */
    private static final String OPERAND_EQUAL = "==";

    /**
     * 割り当て
     */
    private static final String OPERAND_ASSIGNMENT = "=";

    @Test
    public void testCodeValues() {
        assertEquals(0, Operand.PLUS.getCode());
        assertEquals(1, Operand.MINUS.getCode());
        assertEquals(2, Operand.DEVIDE.getCode());
        assertEquals(3, Operand.MULTIPLE.getCode());
        assertEquals(4, Operand.EQUAL.getCode());
        assertEquals(5, Operand.ASSIGNMENT.getCode());
    }

    @Test
    public void testPlus() {
        assertEquals(OPERAND_PLUS, Operand.plus());
    }

    @Test
    public void testMinus() {
        assertEquals(OPERAND_MINUS, Operand.minus());
    }

    @Test
    public void testDevide() {
        assertEquals(OPERAND_DEVIDE, Operand.devide());
    }

    @Test
    public void testMultiple() {
        assertEquals(OPERAND_MULTIPLE, Operand.multiple());
    }

    @Test
    public void testEqual() {
        assertEquals(OPERAND_EQUAL, Operand.equal());
    }

    @Test
    public void testAssignment() {
        assertEquals(OPERAND_ASSIGNMENT, Operand.assignment());
    }
}