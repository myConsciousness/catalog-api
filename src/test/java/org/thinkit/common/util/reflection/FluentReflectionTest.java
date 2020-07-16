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

package org.thinkit.common.util.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

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
     * <code>"success"</code> の文字列定数
     */
    private static final String STR_SUCCESS = "success";

    /**
     * <code>"failure"</code> の文字列定数
     */
    private static final String STR_FAILURE = "failure";

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
            final String actualResult = reflection.invoke(TestMethod.RETURN_STRING_WITH_NO_ARGUMENT.getName());

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
            final String actualResult = reflection.add(String.class, "test")
                    .invoke(TestMethod.RETURN_STRING_WITH_ARGUMENT.getName());

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
            final String actualResult = reflection.add(String.class, "")
                    .invoke(TestMethod.RETURN_STRING_WITH_ARGUMENT.getName());

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
            final String actualResult = reflection.invoke(TestMethod.RETURN_STRING_WITH_ARGUMENTS.getName());

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
            final int actualResult = reflection.invoke(TestMethod.RETURN_INTEGER_WITH_NO_ARGUMENT.getName());

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
            final int actualResult = reflection.add(boolean.class, true)
                    .invoke(TestMethod.RETURN_INTEGER_WITH_ARGUMENT.getName());

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
            final int actualResult = reflection.add(boolean.class, false)
                    .invoke(TestMethod.RETURN_INTEGER_WITH_ARGUMENT.getName());

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
            final int actualResult = reflection.invoke(TestMethod.RETURN_INTEGER_WITH_ARGUMENTS.getName());

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnBooleanWithNoArgument()} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanWithNoArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final boolean actualResult = reflection.invoke(TestMethod.RETURN_BOOLEAN_WITH_NO_ARGUMENT.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnBooleanWithArgument(int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanTrueWithArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final boolean actualResult = reflection.add(int.class, 1)
                    .invoke(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENT.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnBooleanWithArgument(int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>false</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanFalseWithArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final boolean actualResult = reflection.add(int.class, 0)
                    .invoke(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENT.getName());

            assertEquals(false, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnBooleanWithArguments(int, String, boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanWithArguments() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            reflection.add(int.class, 0).add(String.class, "test").add(boolean.class, true);
            final boolean actualResult = reflection.invoke(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENTS.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnListWithArguments(String, String, String)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したリストのサイズが <code>3</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したリストの0番目の値が <code>"test6"</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したリストの1番目の値が <code>"test1"</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したリストの2番目の値が <code>"test100"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnListWithArguments() {
            final FluentReflection<List<String>> reflection = new FluentReflection<>(ReflectionTestDataSet.class);
            final String[] expectedSequences = new String[] { "test6", "test1", "test100" };

            for (String expectedSequence : expectedSequences) {
                reflection.add(String.class, expectedSequence);
            }

            final List<String> actualResult = reflection.invoke(TestMethod.RETURN_LIST_WITH_ARGUMENTS.getName());
            final int actualSize = actualResult.size();

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertTrue(actualSize == expectedSequences.length);

            for (int i = 0; i < actualSize; i++) {
                assertEquals(expectedSequences[i], actualResult.get(i));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionTestDataSet#returnMapWithArguments(int, int, int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得した値が空ではないこと
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したマップのサイズが <code>3</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したマップの0番目の値が <code>100</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したマップの1番目の値が <code>1000</code> であること
         * ・{@link FluentReflection#invoke(String)} メソッドから取得したマップの2番目の値が <code>1</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnMapWithArguments() {
            final FluentReflection<Map<String, Integer>> reflection = new FluentReflection<>(
                    ReflectionTestDataSet.class);
            final int[] expectedNumbers = new int[] { 100, 1000, 1 };

            for (Integer expectedSequence : expectedNumbers) {
                reflection.add(int.class, expectedSequence.intValue());
            }

            final Map<String, Integer> actualResult = reflection.invoke(TestMethod.RETURN_MAP_WITH_ARGUMENTS.getName());
            final int actualSize = actualResult.size();

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertTrue(actualSize == expectedNumbers.length);

            for (int i = 0; i < actualSize; i++) {
                assertEquals(expectedNumbers[i], actualResult.get(String.format("result%s", i + 1)).intValue());
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invoke(String)} メソッドの引数として
         * 空文字列を渡した際の動作を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invoke(String)} メソッドを実行した際に {@link IllegalArgumentException} が発生すること
         * ・{@link IllegalArgumentException} のエラーメッセージが <code>"Method name is required."</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testWhenMethodNameIsEmpty() {
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new FluentReflection<>(ReflectionTestDataSet.class).invoke(""));
            assertEquals("Method name is required.", exception.getMessage());
        }
    }

    /**
     * {@link FluentReflection#invokeStatic(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link FluentReflection#invokeStatic(String)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestInvokeStatic {

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnStringWithNoArgument()} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringWithNoArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final String actualResult = reflection.invokeStatic(TestMethod.RETURN_STRING_WITH_NO_ARGUMENT.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnStringWithArgument(String)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringSuccessWithArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final String actualResult = reflection.add(String.class, "test")
                    .invokeStatic(TestMethod.RETURN_STRING_WITH_ARGUMENT.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnStringWithArgument(String)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>"failure"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringFailureWithArgument() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final String actualResult = reflection.add(String.class, "")
                    .invokeStatic(TestMethod.RETURN_STRING_WITH_ARGUMENT.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_FAILURE, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnStringWithArguments(String, int, boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空文字列ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>"success"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnStringWithArguments() {
            final FluentReflection<String> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            reflection.add(String.class, "").add(int.class, 0).add(boolean.class, true);
            final String actualResult = reflection.invokeStatic(TestMethod.RETURN_STRING_WITH_ARGUMENTS.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnIntegerWithNoArgument()} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>1</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerWithNoArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final int actualResult = reflection.invokeStatic(TestMethod.RETURN_INTEGER_WITH_NO_ARGUMENT.getName());

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnIntegerWithArgument(boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>1</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerTrueWithArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final int actualResult = reflection.add(boolean.class, true)
                    .invokeStatic(TestMethod.RETURN_INTEGER_WITH_ARGUMENT.getName());

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnIntegerWithArgument(boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>0</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerFalseWithArgument() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final int actualResult = reflection.add(boolean.class, false)
                    .invokeStatic(TestMethod.RETURN_INTEGER_WITH_ARGUMENT.getName());

            assertEquals(0, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnIntegerWithArguments(int, String, boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>0</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnIntegerWithArguments() {
            final FluentReflection<Integer> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            reflection.add(int.class, 1).add(String.class, "test").add(boolean.class, false);
            final int actualResult = reflection.invokeStatic(TestMethod.RETURN_INTEGER_WITH_ARGUMENTS.getName());

            assertEquals(1, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnBooleanWithNoArgument()} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanWithNoArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final boolean actualResult = reflection.invokeStatic(TestMethod.RETURN_BOOLEAN_WITH_NO_ARGUMENT.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnBooleanWithArgument(int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanTrueWithArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final boolean actualResult = reflection.add(int.class, 1)
                    .invokeStatic(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENT.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnBooleanWithArgument(int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>false</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanFalseWithArgument() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final boolean actualResult = reflection.add(int.class, 0)
                    .invokeStatic(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENT.getName());

            assertEquals(false, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnBooleanWithArguments(int, String, boolean)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>true</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnBooleanWithArguments() {
            final FluentReflection<Boolean> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            reflection.add(int.class, 0).add(String.class, "test").add(boolean.class, true);
            final boolean actualResult = reflection.invokeStatic(TestMethod.RETURN_BOOLEAN_WITH_ARGUMENTS.getName());

            assertEquals(true, actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnListWithArguments(String, String, String)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したリストのサイズが <code>3</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したリストの0番目の値が <code>"test6"</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したリストの1番目の値が <code>"test1"</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したリストの2番目の値が <code>"test100"</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnListWithArguments() {
            final FluentReflection<List<String>> reflection = new FluentReflection<>(ReflectionStaticTestDataSet.class);
            final String[] expectedSequences = new String[] { "test6", "test1", "test100" };

            for (String expectedSequence : expectedSequences) {
                reflection.add(String.class, expectedSequence);
            }

            final List<String> actualResult = reflection.invokeStatic(TestMethod.RETURN_LIST_WITH_ARGUMENTS.getName());
            final int actualSize = actualResult.size();

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertTrue(actualSize == expectedSequences.length);

            for (int i = 0; i < actualSize; i++) {
                assertEquals(expectedSequences[i], actualResult.get(i));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの返却値を確認する。
         * テストデータセットは {@link ReflectionStaticTestDataSet#returnMapWithArguments(int, int, int)} を使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が <code>null</code> ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得した値が空ではないこと
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したマップのサイズが <code>3</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したマップの0番目の値が <code>100</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したマップの1番目の値が <code>1000</code> であること
         * ・{@link FluentReflection#invokeStatic(String)} メソッドから取得したマップの2番目の値が <code>1</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testReturnMapWithArguments() {
            final FluentReflection<Map<String, Integer>> reflection = new FluentReflection<>(
                    ReflectionStaticTestDataSet.class);
            final int[] expectedNumbers = new int[] { 100, 1000, 1 };

            for (Integer expectedSequence : expectedNumbers) {
                reflection.add(int.class, expectedSequence.intValue());
            }

            final Map<String, Integer> actualResult = reflection
                    .invokeStatic(TestMethod.RETURN_MAP_WITH_ARGUMENTS.getName());
            final int actualSize = actualResult.size();

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertTrue(actualSize == expectedNumbers.length);

            for (int i = 0; i < actualSize; i++) {
                assertEquals(expectedNumbers[i], actualResult.get(String.format("result%s", i + 1)).intValue());
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#invokeStatic(String)} メソッドの引数として
         * 空文字列を渡した際の動作を確認する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・{@link FluentReflection#invokeStatic(String)} メソッドを実行した際に {@link IllegalArgumentException} が発生すること
         * ・{@link IllegalArgumentException} のエラーメッセージが <code>"Method name is required."</code> であること
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testWhenMethodNameIsEmpty() {
            final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> new FluentReflection<>(ReflectionTestDataSet.class).invokeStatic(""));
            assertEquals("Method name is required.", exception.getMessage());
        }
    }

    /**
     * {@link FluentReflection#add(Class, Object)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link FluentReflection#add(Class, Object)} はprivateメソッドです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestAdd {

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#add(Class, Object)} メソッドの機能を確認する。
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
        void testSimplePattern() {
            final String actualResult = new FluentReflection<String>(ReflectionTestDataSet.class)
                    .add(String.class, "test").invoke(TestMethod.RETURN_STRING_WITH_ARGUMENT.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals("success", actualResult);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link FluentReflection} クラスの {@link FluentReflection#add(Class, Object)} メソッドの機能を確認する。
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
        void testMethodChainPattern() {
            final String actualResult = new FluentReflection<String>(ReflectionTestDataSet.class)
                    .add(String.class, "test").add(int.class, 0).add(boolean.class, true)
                    .invoke(TestMethod.RETURN_STRING_WITH_ARGUMENTS.getName());

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals("success", actualResult);
        }
    }

    /**
     * テスト用データセットに定義されたprivateメソッドを管理するEnumクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     * @see {@link ReflectionTestDataSet}
     */
    private enum TestMethod {

        /**
         * 引数なしで文字列を返却するテスト用メソッド
         */
        RETURN_STRING_WITH_NO_ARGUMENT(Name.returnStringWithNoArgument),

        /**
         * 引数ありで文字列を返却するテスト用メソッド
         */
        RETURN_STRING_WITH_ARGUMENT(Name.returnStringWithArgument),

        /**
         * 複数の引数ありで文字列を返却するテスト用メソッド
         */
        RETURN_STRING_WITH_ARGUMENTS(Name.returnStringWithArguments),

        /**
         * 引数なしで数値を返却するテスト用メソッド
         */
        RETURN_INTEGER_WITH_NO_ARGUMENT(Name.returnIntegerWithNoArgument),

        /**
         * 引数ありで数値を返却するテスト用メソッド
         */
        RETURN_INTEGER_WITH_ARGUMENT(Name.returnIntegerWithArgument),

        /**
         * 複数の引数ありで数値を返却するテスト用メソッド
         */
        RETURN_INTEGER_WITH_ARGUMENTS(Name.returnIntegerWithArguments),

        /**
         * 引数なしで真偽値を返却するテスト用メソッド
         */
        RETURN_BOOLEAN_WITH_NO_ARGUMENT(Name.returnBooleanWithNoArgument),

        /**
         * 引数ありで真偽値を返却するテスト用メソッド
         */
        RETURN_BOOLEAN_WITH_ARGUMENT(Name.returnBooleanWithArgument),

        /**
         * 複数の引数ありで真偽値を返却するテスト用メソッド
         */
        RETURN_BOOLEAN_WITH_ARGUMENTS(Name.returnBooleanWithArguments),

        /**
         * 複数の引数ありでリストを返却するテスト用メソッド
         */
        RETURN_LIST_WITH_ARGUMENTS(Name.returnListWithArguments),

        /**
         * 複数の引数ありでマップを返却するテスト用メソッド
         */
        RETURN_MAP_WITH_ARGUMENTS(Name.returnMapWithArguments);

        /**
         * テスト用メソッドの名前
         */
        private Name methodName;

        /**
         * コンストラクタ
         *
         * @param methodName テスト用メソッドの名前
         */
        TestMethod(final Name methodName) {
            this.methodName = methodName;
        }

        /**
         * テスト用メソッドの名前を管理するEnumクラスです。
         *
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        private enum Name {
            returnStringWithNoArgument, returnStringWithArgument, returnStringWithArguments,
            returnIntegerWithNoArgument, returnIntegerWithArgument, returnIntegerWithArguments,
            returnBooleanWithNoArgument, returnBooleanWithArgument, returnBooleanWithArguments, returnListWithArguments,
            returnMapWithArguments;
        }

        /**
         * テスト用メソッドの名前を返却します。
         *
         * @return テスト用メソッドの名前
         */
        private String getName() {
            return this.methodName.name();
        }
    }
}