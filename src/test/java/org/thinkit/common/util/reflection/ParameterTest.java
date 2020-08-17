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

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link Parameter} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class ParameterTest {

    /**
     * {@link Parameter#Parameter(Class, Object)} コンストラクタのテストメソッドを定義するテストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestParameter {

        /**
         * <pre>
         * ❏ 概要
         * {@link Parameter} クラスの {@link Parameter#Parameter(Class, Object)} コンストラクタの機能を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link Parameter#Parameter(Class, Object)} コンストラクタで生成したインスタンスが <code>null</code> ではないこと
         * ・{@link Parameter#getType()} メソッドの返却値が <code>String.class</code> であること
         * ・{@link Parameter#getValue()} メソッドの返却値が <code>"test"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testSimplePattern() {
            final Parameter parameter = new Parameter(String.class, "test");

            assertNotNull(parameter);
            assertEquals(String.class, parameter.getType());
            assertEquals("test", parameter.getValue());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link Parameter} クラスの {@link Parameter#Parameter(Class, Object)} コンストラクタの機能を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link Parameter#Parameter(Class, Object)} コンストラクタで生成したインスタンスが <code>null</code> ではないこと
         * ・{@link Parameter#getType()} メソッドの返却値が <code>Map.class</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testCollectionPattern() {
            final Parameter parameter = new Parameter(Map.class, new HashMap<>(0));

            assertNotNull(parameter);
            assertEquals(Map.class, parameter.getType());
        }
    }
}