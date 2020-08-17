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

package org.thinkit.common.util.reflection;

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

    /**
     * {@link ReflectionParameter#getValues()} メソッドのテストメソッドを定義するテストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetValues {

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getValues()} メソッドの返却値を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の長さが <code>1</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の0番目が <code>"test"</code> であること
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
            parameter.add(String.class, "test");
            final Object[] values = parameter.getValues();

            assertNotNull(values);
            assertTrue(values.length == 1);
            assertEquals("test", values[0]);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getValues()} メソッドの返却値を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の長さが <code>5</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の0番目が <code>""</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の1番目が <code>0</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の2番目が <code>true</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の3番目が <code>1</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値の4番目が <code>false</code> であること
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
            final Object[] values = parameter.getValues();

            assertNotNull(values);
            assertTrue(values.length == 5);
            assertEquals("", values[0]);
            assertEquals(0, values[1]);
            assertEquals(true, values[2]);
            assertEquals(1, values[3]);
            assertEquals(false, values[4]);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#getValues()} メソッドの返却値を確認する。
         * {@link ReflectionParameter#add(Class, Object)} メソッドを使用しない状態で {@link ReflectionParameter#getTypes()} を呼び出す。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#getValues()} メソッドの返却値が <code>null</code> ではないこと
         * ・{@link ReflectionParameter#getValues()} メソッドを実行した際の例外が <code>LogicException.class</code> であること
         * ・{@link ReflectionParameter#getValues()} メソッドを実行した際のエラーメッセージが <code>"No parameter is set. Parameter is required."</code> であること
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

            final LogicException exception = assertThrows(LogicException.class, () -> parameter.getValues());
            assertNotNull(exception);
            assertEquals("No parameter is set. Parameter is required.", exception.getMessage());
        }
    }

    /**
     * {@link ReflectionParameter#isEmpty()} メソッドのテストメソッドを定義するテストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestIsEmpty {

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#isEmpty()} メソッドの返却値を確認する。
         * {@link ReflectionParameter#add(Class, Object)} メソッドを使用した状態で {@link ReflectionParameter#isEmpty()} を呼び出す。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#isEmpty()} メソッドの返却値が <code>false</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testWhenParameterisSet() {
            final ReflectionParameter parameter = new ReflectionParameter();
            parameter.add(String.class, "test");
            assertTrue(!parameter.isEmpty());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ReflectionParameter} クラスの {@link ReflectionParameter#isEmpty()} メソッドの返却値を確認する。
         * {@link ReflectionParameter#add(Class, Object)} メソッドを使用していない状態で {@link ReflectionParameter#isEmpty()} を呼び出す。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link ReflectionParameter#isEmpty()} メソッドの返却値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testWhenParameterisNotSet() {
            final ReflectionParameter parameter = new ReflectionParameter();
            assertTrue(parameter.isEmpty());
        }
    }
}