/**
 * Project Name : dev-utils<br>
 * File Name : FluentReflectionTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link FluentReflectionTest} クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class FluentReflectionTest {

    /**
     * {@link FluentReflection#invoke(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link FluentReflection#invoke(String)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestInvoke {

        /**
         * <code>"success"</code> の文字列定数
         */
        private static final String STR_SUCCESS = "success";

        /**
         * <code>"failure"</code> の文字列定数
         */
        private static final String STR_FAILURE = "failure";

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnStringWithNoArgument()} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringWithNoArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final String actualResult = reflection.invoke("returnStringWithNoArgument");

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnStringWithArgument(String)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringSuccessWithArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final String actualResult = reflection.add(String.class, "test").invoke("returnStringWithArgument");

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnStringWithArgument(String)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>"failure"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringFailureWithArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final String actualResult = reflection.add(String.class, "").invoke("returnStringWithArgument");

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_FAILURE, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnStringWithArguments(String, int, boolean)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringWithArguments() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            reflection.add(String.class, "").add(int.class, 0).add(boolean.class, true);
            final String actualResult = reflection.invoke("returnStringWithArguments");

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnIntegerWithNoArgument()} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>1</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerWithNoArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final int actualResult = reflection.invoke("returnIntegerWithNoArgument");

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnIntegerWithArgument(boolean)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>1</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerTrueWithArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final int actualResult = reflection.add(boolean.class, true).invoke("returnIntegerWithArgument");

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnIntegerWithArgument(boolean)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>0</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerFalseWithArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final int actualResult = reflection.add(boolean.class, false).invoke("returnIntegerWithArgument");

            assertEquals(0, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnIntegerWithArguments(int, String, boolean)} を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>0</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerWithArguments() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            reflection.add(int.class, 1).add(String.class, "test").add(boolean.class, false);
            final int actualResult = reflection.invoke("returnIntegerWithArguments");

            assertEquals(1, actualResult);
        }
    }
}