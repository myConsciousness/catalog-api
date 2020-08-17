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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * {@link PrimitiveDataType} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class PrimitiveDataTypeTest {

    /**
     * データ型 : boolean
     */
    private static final String DATA_TYPE_BOOLEAN = "boolean";

    /**
     * データ型 : char
     */
    private static final String DATA_TYPE_CHAR = "char";

    /**
     * データ型 : byte
     */
    private static final String DATA_TYPE_BYTE = "byte";

    /**
     * データ型 : short
     */
    private static final String DATA_TYPE_SHORT = "short";

    /**
     * データ型 : int
     */
    private static final String DATA_TYPE_INT = "int";

    /**
     * データ型 : long
     */
    private static final String DATA_TYPE_LONG = "long";

    /**
     * データ型 : float
     */
    private static final String DATA_TYPE_FLOAT = "float";

    /**
     * データ型 : double
     */
    private static final String DATA_TYPE_DOUBLE = "double";

    /**
     * <pre>
     * ❏ 概要
     * {@link PrimitiveDataType} クラスの各要素に定義されたコード値を確認する。
     * コード値は {@link PrimitiveDataType#getCode()} メソッドから取得する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PrimitiveDataType#BOOLEAN} に定義されたコード値が <code>0</code> であること。
     * ・{@link PrimitiveDataType#CHAR} に定義されたコード値が <code>1</code> であること。
     * ・{@link PrimitiveDataType#BYTE} に定義されたコード値が <code>2</code> であること。
     * ・{@link PrimitiveDataType#SHORT} に定義されたコード値が <code>3</code> であること。
     * ・{@link PrimitiveDataType#INT} に定義されたコード値が <code>4</code> であること。
     * ・{@link PrimitiveDataType#LONG} に定義されたコード値が <code>5</code> であること。
     * ・{@link PrimitiveDataType#FLOAT} に定義されたコード値が <code>6</code> であること。
     * ・{@link PrimitiveDataType#DOUBLE} に定義されたコード値が <code>7</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testCodeValues() {
        assertEquals(0, PrimitiveDataType.BOOLEAN.getCode());
        assertEquals(1, PrimitiveDataType.CHAR.getCode());
        assertEquals(2, PrimitiveDataType.BYTE.getCode());
        assertEquals(3, PrimitiveDataType.SHORT.getCode());
        assertEquals(4, PrimitiveDataType.INT.getCode());
        assertEquals(5, PrimitiveDataType.LONG.getCode());
        assertEquals(6, PrimitiveDataType.FLOAT.getCode());
        assertEquals(7, PrimitiveDataType.DOUBLE.getCode());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PrimitiveDataType} クラスの {@link PrimitiveDataType#toDataType()} メソッドの返却値を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PrimitiveDataType#BOOLEAN#toDataType()} の返却値が <code>"boolean"</code> であること。
     * ・{@link PrimitiveDataType#CHAR#toDataType()} の返却値が <code>"char"</code> であること。
     * ・{@link PrimitiveDataType#BYTE#toDataType()} の返却値が <code>"byte"</code> であること。
     * ・{@link PrimitiveDataType#SHORT#toDataType()} の返却値が <code>"short"</code> であること。
     * ・{@link PrimitiveDataType#INT#toDataType()} の返却値が <code>"int"</code> であること。
     * ・{@link PrimitiveDataType#LONG#toDataType()} の返却値が <code>"long"</code> であること。
     * ・{@link PrimitiveDataType#FLOAT#toDataType()} の返却値が <code>"float"</code> であること。
     * ・{@link PrimitiveDataType#DOUBLE#toDataType()} の返却値が <code>"double"</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testToDataType() {
        assertEquals(DATA_TYPE_BOOLEAN, PrimitiveDataType.BOOLEAN.toDataType());
        assertEquals(DATA_TYPE_CHAR, PrimitiveDataType.CHAR.toDataType());
        assertEquals(DATA_TYPE_BYTE, PrimitiveDataType.BYTE.toDataType());
        assertEquals(DATA_TYPE_SHORT, PrimitiveDataType.SHORT.toDataType());
        assertEquals(DATA_TYPE_INT, PrimitiveDataType.INT.toDataType());
        assertEquals(DATA_TYPE_LONG, PrimitiveDataType.LONG.toDataType());
        assertEquals(DATA_TYPE_FLOAT, PrimitiveDataType.FLOAT.toDataType());
        assertEquals(DATA_TYPE_DOUBLE, PrimitiveDataType.DOUBLE.toDataType());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PrimitiveDataType} クラスの {@link PrimitiveDataType#isPrimitive(String)} メソッドの返却値を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PrimitiveDataType#isPrimitive(String)} メソッドに以下の文字列を渡した場合に {@code true} が返却されること。
     * ---------------------------------------
     * <code>"boolean"</code>
     * <code>"char"</code>
     * <code>"byte"</code>
     * <code>"short"</code>
     * <code>"int"</code>
     * <code>"long"</code>
     * <code>"float"</code>
     * <code>"double"</code>
     * ----------------------------------------
     *
     * ・{@link PrimitiveDataType#isPrimitive(String)} メソッドに以下の文字列を渡した場合に {@code false} が返却されること。
     * ---------------------------------------
     * <code>"test"</code>
     * <code>"Test"</code>
     * <code>"TEST"</code>
     * <code>"String"</code>
     * <code>""</code>
     * <code>" "</code>
     * <code>"?><\^"</code>
     * ---------------------------------------
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testIsPrimitive() {
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_BOOLEAN));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_CHAR));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_BYTE));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_SHORT));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_INT));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_LONG));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_FLOAT));
        assertTrue(PrimitiveDataType.isPrimitive(DATA_TYPE_DOUBLE));

        assertFalse(PrimitiveDataType.isPrimitive("test"));
        assertFalse(PrimitiveDataType.isPrimitive("Test"));
        assertFalse(PrimitiveDataType.isPrimitive("TEST"));
        assertFalse(PrimitiveDataType.isPrimitive("String"));
        assertFalse(PrimitiveDataType.isPrimitive(""));
        assertFalse(PrimitiveDataType.isPrimitive(" "));
        assertFalse(PrimitiveDataType.isPrimitive("?><\\^"));
    }
}