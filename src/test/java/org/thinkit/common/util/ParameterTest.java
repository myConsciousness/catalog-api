/**
 * Project Name : dev-utils<br>
 * File Name : ParameterTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/10<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

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