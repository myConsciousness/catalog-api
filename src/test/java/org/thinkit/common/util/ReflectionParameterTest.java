/**
 * Project Name : dev-utils<br>
 * File Name : ReflectionParameterTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/11<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.thinkit.common.exception.LogicException;

/**
 * {@link ReflectionParameter} クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class ReflectionParameterTest {

    /**
     * {@link ReflectionParameter#getTypes()} メソッドのテストメソッドを定義するテストクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetTypes {

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getTypes()} メソッドの返却値を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の長さが <code>1</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の0番目が <code>String.class</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testSimplePattern() {
            final ReflectionParameter parameter = new ReflectionParameter();
            parameter.add(String.class, "");
            final Class<?>[] types = parameter.getTypes();

            assertNotNull(types);
            assertTrue(types.length == 1);
            assertEquals(String.class, types[0]);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getTypes()} メソッドの返却値を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の長さが <code>6</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の0番目が <code>String.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の1番目が <code>Integer.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の2番目が <code>Boolean.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の3番目が <code>int.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の4番目が <code>boolean.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値の5番目が <code>Map.class</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testComplicatedPattern() {
            final ReflectionParameter parameter = new ReflectionParameter();
            parameter.add(String.class, "");
            parameter.add(Integer.class, 0);
            parameter.add(Boolean.class, true);
            parameter.add(int.class, 1);
            parameter.add(boolean.class, false);
            parameter.add(Map.class, new HashMap<>(0));
            final Class<?>[] types = parameter.getTypes();

            assertNotNull(types);
            assertTrue(types.length == 6);
            assertEquals(String.class, types[0]);
            assertEquals(Integer.class, types[1]);
            assertEquals(Boolean.class, types[2]);
            assertEquals(int.class, types[3]);
            assertEquals(boolean.class, types[4]);
            assertEquals(Map.class, types[5]);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getTypes()} メソッドの返却値を確認する。
         * {@link ReflectionParameter#add(Class, Object)} メソッドを使用しない状態で {@link ReflectionParameter#getTypes()} を呼び出す。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getTypes()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getTypes()} メソッドを実行した際の例外が <code>LogicException.class</code> であること
         * ・{@link ReflectionParameter#getTypes()} メソッドを実行した際のエラーメッセージが <code>"No parameter is set. Parameter is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testWhenParameterIsNotSet() {
            final ReflectionParameter parameter = new ReflectionParameter();

            final LogicException exception = assertThrows(LogicException.class, () -> parameter.getTypes());
            assertNotNull(exception);
            assertEquals("No parameter is set. Parameter is required.", exception.getMessage());
        }
    }
}