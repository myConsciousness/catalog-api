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