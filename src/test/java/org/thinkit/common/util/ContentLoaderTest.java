/**
 * Project Name : dev-utils<br>
 * File Name : ContentLoaderTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import org.thinkit.common.key.ConditionNodeKey;
import org.thinkit.common.key.Key;
import org.thinkit.common.key.SelectionNodeKey;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.thinkit.common.rule.Content;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link ContentLoader} クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentLoaderTest {

    /**
     * テスト用アトリビュートリスト
     */
    private static final List<String> TEST_ATTRIBUTE_LIST;

    static {
        final TestContentAttribute[] attributes = TestContentAttribute.values();
        TEST_ATTRIBUTE_LIST = new ArrayList<>(attributes.length);

        for (Attribute attribute : attributes) {
            TEST_ATTRIBUTE_LIST.add(attribute.getString());
        }
    }

    /**
     * {@link ContentLoader#load(String, List)} メソッドのテストメソッドを定義するネストクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestLoad {

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が小規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testSmallSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(TestContentName.SMALL_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が中規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testMediumSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(TestContentName.MEDIUM_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの返却値を確認する。
         * このテストでは選択ノードの個数が大規模のコンテンツファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#load(String, List)} から取得したリストが {@code null} ではないこと
         * ・{@link ContentLoader#load(String, List)} から取得したリストが空リストではないこと
         * ・キー名 <code>"test1"</code> に紐づく項目の値が <code>"0"</code> であること
         * ・キー名 <code>"test2"</code> に紐づく項目の値が <code>"false"</code> であること
         * ・キー名 <code>"test3"</code> に紐づく項目の値が <code>"0L"</code> であること
         * ・キー名 <code>"test4"</code> に紐づく項目の値が <code>"1.0"</code> であること
         * ・キー名 <code>"test5"</code> に紐づく項目の値が <code>"test"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testLargeSelectionNodes() {

            final List<Map<String, String>> contents = ContentLoader
                    .load(TestContentName.LARGE_SELECTION_NODES.getString(), TEST_ATTRIBUTE_LIST);

            assertNotNull(contents);
            assertTrue(!contents.isEmpty());

            for (Map<String, String> content : contents) {
                assertEquals("0", content.get(TestContentAttribute.test1.getString()));
                assertEquals("false", content.get(TestContentAttribute.test2.getString()));
                assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
                assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
                assertEquals("test", content.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの引数として空のコンテンツ名が渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Content name is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenContentNameIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("", new ArrayList<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Content name is required.", exception.getMessage());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List)} メソッドの引数として空のアトリビュートリストが渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Attribute is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenAttributeListIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("test", new ArrayList<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Attribute is required.", exception.getMessage());
        }
    }

    /**
     * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestLoadWithConditions {

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは小規模条件ノードを定義したコンテンツファイルを使用してください。
         * 
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestSmallConditionNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"1"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1"</code>
             * ・<code>"testCondition2" : "0"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "0");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"0"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "0"</code>
             * ・<code>"testCondition2" : ""</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "0");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("0", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が小規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1"</code>
             * ・<code>"testCondition2" : ""</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1");
                conditions.put(TestCondition.testCondition2.getString(), "");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.SMALL_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは中規模条件ノードを定義したコンテンツファイルを使用してください。
         * 
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestMediumConditionsNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"1"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "false"</code>
             * ・<code>"testCondition2" : "test"</code>
             * ・<code>"testCondition3" : "100"</code>
             * ・<code>"testCondition4" : "テスト"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "false");
                conditions.put(TestCondition.testCondition2.getString(), "test");
                conditions.put(TestCondition.testCondition3.getString(), "100");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"3"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "test"</code>
             * ・<code>"testCondition2" : "100"</code>
             * ・<code>"testCondition3" : "テスト"</code>
             * ・<code>"testCondition4" : "true"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "test");
                conditions.put(TestCondition.testCondition2.getString(), "100");
                conditions.put(TestCondition.testCondition3.getString(), "テスト");
                conditions.put(TestCondition.testCondition4.getString(), "true");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("3", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が中規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "100"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "test"</code>
             * ・<code>"testCondition4" : "テスト"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "test");
                conditions.put(TestCondition.testCondition4.getString(), "テスト");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * {@link ContentLoader#load(String, List, Map)} メソッドのテストメソッドを定義するネストクラスです。
         * このクラスに定義するテストメソッドは大規模条件ノードを定義したコンテンツファイルを使用してください。
         * 
         * @author Kato Shinya
         * @since 1.0
         * @version 1.0
         */
        @Nested
        final class TestLargeConditionsNodes {

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"5"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1000"</code>
             * ・<code>"testCondition2" : "false"</code>
             * ・<code>"testCondition3" : "true"</code>
             * ・<code>"testCondition4" : "100"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "false");
                conditions.put(TestCondition.testCondition3.getString(), "true");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.LARGE_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("5", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} メソッドから取得したリストのサイズが <code>1</code> であること
             * ・以下の条件でコンテンツをロードした場合 <code>"result"</code> に紐づく値が <code>"6"</code> であること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "1000"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "false"</code>
             * ・<code>"testCondition4" : "100"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testAnotherRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "1000");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "100");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.LARGE_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(!contents.isEmpty());
                assertTrue(contents.size() == 1);
                assertEquals("1", contents.get(0).get(resultAttribute));
            }

            /**
             * <pre>
             * ❏ 概要
             * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの返却値を確認する。
             * このテストでは条件ノードの個数が大規模のコンテンツファイルを使用する。
             * </pre>
             * 
             * <pre>
             * ❏ 観点
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが {@code null} ではないこと
             * ・以下の条件でコンテンツをロードした場合 {@link ContentLoader#load(String, List, Map)} から取得したリストが空リストであること
             * </pre>
             * 
             * <pre>
             * ❏ コンテンツ取得条件
             * ・<code>"testCondition1" : "100"</code>
             * ・<code>"testCondition2" : "true"</code>
             * ・<code>"testCondition3" : "false"</code>
             * ・<code>"testCondition4" : "1000"</code>
             * ・<code>"testCondition5" : "test"</code>
             * ・<code>"testCondition6" : "10L"</code>
             * </pre>
             * 
             * <pre>
             * ❏ 留意点
             * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
             * </pre>
             */
            @Test
            public void testNoRecordWithConditions() {

                final String resultAttribute = "result";
                final List<String> attributes = new ArrayList<>(1);
                attributes.add(resultAttribute);

                final Map<String, String> conditions = new HashMap<>(2);
                conditions.put(TestCondition.testCondition1.getString(), "100");
                conditions.put(TestCondition.testCondition2.getString(), "true");
                conditions.put(TestCondition.testCondition3.getString(), "false");
                conditions.put(TestCondition.testCondition4.getString(), "1000");
                conditions.put(TestCondition.testCondition5.getString(), "test");
                conditions.put(TestCondition.testCondition6.getString(), "10L");

                final List<Map<String, String>> contents = ContentLoader
                        .load(TestContentName.MEDIUM_CONDITION_NODES.getString(), attributes, conditions);

                assertNotNull(contents);
                assertTrue(contents.isEmpty());
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの引数として空のコンテンツ名が渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Content name is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenContentNameIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("", new ArrayList<>(0), new HashMap<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Content name is required.", exception.getMessage());
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#load(String, List, Map)} メソッドの引数として空のアトリビュートリストが渡された際の機能を確認する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・例外として {@link IllegalArgumentException} が発生すること
         * ・例外発生時のメッセージが <code>"wrong parameter was given. Attribute is required."</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWhenAttributeListIsEmpty() {
            final Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> ContentLoader.load("test", new ArrayList<>(0), new HashMap<>(0)));
            assertNotNull(exception);
            assertEquals("wrong parameter was given. Attribute is required.", exception.getMessage());
        }
    }

    /**
     * {@link ContentLoader#getNodeList(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getNodeList(Map, Key)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetNodeList {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getNodeList(List, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツリストを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getNodeList(List, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final Map<String, Object> content = new HashMap<>();
            final List<Map<String, String>> expectedNodeList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                final Map<String, String> node = new HashMap<>();
                node.put("testNode1", "something");
                node.put("testNode2", "something");
                node.put("testNode3", "something");

                expectedNodeList.add(node);
            }

            content.put(SelectionNodeKey.SELECTION_NODES.getKey(), expectedNodeList);
            final List<Map<String, Object>> actualNodeList = this.invoke(content, SelectionNodeKey.SELECTION_NODES);

            assertNotNull(actualNodeList);
            assertTrue(!actualNodeList.isEmpty());
            assertEquals(expectedNodeList, actualNodeList);
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getNodeList(List, Key)} メソッドを呼び出すメソッドです。
         * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため {@link SuppressWarnings}
         * でuncheckedを指定しています。
         * 
         * @param content    コンテンツマップ
         * @param contentKey コンテンツキー
         * @return {@link Key}に紐づくノードリスト
         */
        @SuppressWarnings("unchecked")
        private List<Map<String, Object>> invoke(Map<String, Object> content, Key contentKey) {

            List<Map<String, Object>> nodeList = new ArrayList<>(0);

            try {
                nodeList = (List<Map<String, Object>>) this.getTestMethod().invoke(TEST_CLASS, content, contentKey);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return nodeList;
        }

        /**
         * {@link ContentLoader#getNodeList(List, Key)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getNodeList(List, Key)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getNodeList";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, Map.class, Key.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getNodeMap(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getNodeMap(Map, Key)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetNodeMap {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getNodeMap(Map, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツマップを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値が空マップではないこと
         * ・{@link ContentLoader#getNodeMap(Map, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final Map<String, Object> nodes = new HashMap<>();
            final Map<String, String> expectedNodeMap = new HashMap<>();
            expectedNodeMap.put("testNode1", "something");
            expectedNodeMap.put("testNode2", "something");
            expectedNodeMap.put("testNode3", "something");

            nodes.put(ConditionNodeKey.CONDITIONS.getKey(), expectedNodeMap);
            final Map<String, Object> actualNodeMap = this.invoke(nodes, ConditionNodeKey.CONDITIONS);

            assertNotNull(actualNodeMap);
            assertTrue(!actualNodeMap.isEmpty());
            assertEquals(expectedNodeMap, actualNodeMap);
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getNodeMap(Map, Key)} メソッドを呼び出すメソッドです。
         * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため {@link SuppressWarnings}
         * でuncheckedを指定しています。
         * 
         * @param content    コンテンツマップ
         * @param contentKey コンテンツキー
         * @return {@link Key}に紐づくノードマップ
         */
        @SuppressWarnings("unchecked")
        private Map<String, Object> invoke(Map<String, Object> content, Key contentKey) {

            Map<String, Object> nodeMap = new HashMap<>(0);

            try {
                nodeMap = (Map<String, Object>) this.getTestMethod().invoke(TEST_CLASS, content, contentKey);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return nodeMap;
        }

        /**
         * {@link ContentLoader#getNodeMap(Map, Key)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getNodeMap(Map, Key)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getNodeMap";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, Map.class, Key.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getString(Map, Key)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getString(Map, Key)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetString {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getString(Map, Key)} メソッドの返却値を確認する。
         * 期待値は任意のコンテンツ値を使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getString(Map, Key)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getString(Map, Key)} の返却値が空文字列ではないこと
         * ・{@link ContentLoader#getString(Map, Key)} の返却値と生成した任意の値が等価であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testSimplePattern() {
            final String expectedContentValue = "Hello World!";

            final Map<String, Object> node = new HashMap<>();
            node.put(SelectionNodeKey.CONDITION_ID.getKey(), expectedContentValue);

            final String actualContentValue = this.invoke(node, SelectionNodeKey.CONDITION_ID);

            assertNotNull(actualContentValue);
            assertTrue(!actualContentValue.isEmpty());
            assertEquals(expectedContentValue, actualContentValue);
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getString(Map, Key)} メソッドを呼び出すメソッドです。
         * 
         * @param content    コンテンツマップ
         * @param contentKey コンテンツキー
         * @return {@link Key}に紐づくコンテンツ値
         */
        private String invoke(Map<String, Object> content, Key contentKey) {

            String contentValue = "";

            try {
                contentValue = (String) this.getTestMethod().invoke(TEST_CLASS, content, contentKey);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return contentValue;
        }

        /**
         * {@link ContentLoader#getString(Map, Key)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getString(Map, Key)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getString";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, Map.class, Key.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getContent(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContent(String)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetContent {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContent(String)} メソッドの返却値を確認する。
         * 期待値として標準のテスト用コンテンツファイルを使用することとする。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContent(String)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContent(String)} の返却値が空リストではないこと
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * このテストケースおよび期待値は使用するテスト用のコンテンツに定義されたキーと値に依存しています。
         * </pre>
         */
        @Test
        public void testSimplePattern() {

            final Map<String, Object> content = this.invoke(TestContentName.DEFAULT.getString());

            assertNotNull(content);
            assertTrue(!content.isEmpty());
            assertTrue(content instanceof LinkedHashMap);
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getContent(String)} メソッドを呼び出すメソッドです。
         * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため {@link SuppressWarnings}
         * でuncheckedを指定しています。
         * 
         * @param contentName コンテンツ名
         * @return コンテンツマップ
         */
        @SuppressWarnings("unchecked")
        private Map<String, Object> invoke(String contentName) {

            Map<String, Object> content = new LinkedHashMap<>();

            try {
                content = (LinkedHashMap<String, Object>) this.getTestMethod().invoke(TEST_CLASS, contentName);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return content;
        }

        /**
         * {@link ContentLoader#getContent(String)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getContent(String)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getContent";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, String.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getFormatFilePath(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getFormatFilePath(String)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetFormatFilePath {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getFormatFilePath(String)} メソッドの返却値を確認する。
         * 使用するコンテンツはファイル名が <code>"test"</code> で始まらないファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が空文字列ではないこと
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が <code>"%s/src/main/resources/content/%s%s</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testFormatFilePathForProduction() {
            final String expectedFormatFilePath = "%s/src/main/resources/content/%s%s";
            final String actualFormatFilePath = this.invoke(TestContentName.PRODUCTION.getString());

            assertNotNull(actualFormatFilePath);
            assertTrue(!actualFormatFilePath.isEmpty());
            assertEquals(expectedFormatFilePath, actualFormatFilePath);
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getFormatFilePath(String)} メソッドの返却値を確認する。
         * 使用するコンテンツはファイル名が <code>"test"</code> で始まるファイルを使用する。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が空文字列ではないこと
         * ・{@link ContentLoader#getFormatFilePath(String)} の返却値が <code>"%s/src/test/resources/content/%s%s</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testFormatFilePathForTest() {
            final String expectedFormatFilePath = "%s/src/test/resources/content/%s%s";
            final String actualFormatFilePath = this.invoke(TestContentName.DEFAULT.getString());

            assertNotNull(actualFormatFilePath);
            assertTrue(!actualFormatFilePath.isEmpty());
            assertEquals(expectedFormatFilePath, actualFormatFilePath);
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getCgetFormatFilePathontent(String)}
         * メソッドを呼び出すメソッドです。
         * 
         * @param contentName コンテンツ名
         * @return コンテンツファイルへのパスのフォーマット
         */
        private String invoke(String contentName) {

            String formatFilePath = "";

            try {
                formatFilePath = (String) this.getTestMethod().invoke(TEST_CLASS, contentName);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return formatFilePath;
        }

        /**
         * {@link ContentLoader#getFormatFilePath(String)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getFormatFilePath(String)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getFormatFilePath";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, String.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getContentList(List, Map, List)}
     * メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContentList(List, Map, List)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetContentList {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(List, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義の全ノードにconditionIdの値が設定されている場合を想定して行う。
         * conditionIdが <code>"1"</code> のレコードを取得しテストを行う。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値のサイズが <code>1</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の0番インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の0番インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の0番インデックスに紐づくレコードのサイズが <code>3</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の0番インデックスに紐づくレコードの値が全て <code>"something1"</code> であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithConditionId() {
            final List<String> attributes = new ArrayList<>(3);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());

            final Map<String, Object> selectionNodes = new HashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(2);

            for (int i = 0; i < 2; i++) {
                final Map<String, Map<String, String>> node = new HashMap<>(2);
                final Map<String, String> items = new HashMap<>(3);
                final String itemValue = String.format("something%s", i);

                items.put(SelectionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final List<String> conditionIdList = new ArrayList<>(1);
            conditionIdList.add("1");

            final List<Map<String, String>> actualContentList = this.invoke(attributes, selectionNodes,
                    conditionIdList);
            final Map<String, String> actualRecord = actualContentList.get(0);

            assertNotNull(actualContentList);
            assertNotNull(actualRecord);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(!actualRecord.isEmpty());
            assertTrue(actualContentList.size() == 1);
            assertTrue(actualRecord.size() == 3);
            assertEquals("something1", actualRecord.get(TestContentAttribute.test1.getString()));
            assertEquals("something1", actualRecord.get(TestContentAttribute.test2.getString()));
            assertEquals("something1", actualRecord.get(TestContentAttribute.test3.getString()));
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(List, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義の全ノードにconditionIdの値が設定されていない場合を想定して行う。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値のサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードのサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードの値が全て生成した期待値と等価であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithNoConditionId() {
            final List<String> attributes = new ArrayList<>(5);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());
            attributes.add(TestContentAttribute.test4.getString());
            attributes.add(TestContentAttribute.test5.getString());

            final Map<String, Object> selectionNodes = new HashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(5);

            for (int i = 0; i < 5; i++) {
                final Map<String, Map<String, String>> node = new HashMap<>(2);
                final Map<String, String> items = new HashMap<>(5);
                final String itemValue = String.format("something%s", i);

                items.put(SelectionNodeKey.CONDITION_ID.getKey(), StringUtils.EMPTY);
                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);
                items.put(TestContentAttribute.test4.getString(), itemValue);
                items.put(TestContentAttribute.test5.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final List<Map<String, String>> actualContentList = this.invoke(attributes, selectionNodes,
                    new ArrayList<>(0));

            assertNotNull(actualContentList);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(actualContentList.size() == 5);

            for (int i = 0; i < 5; i++) {
                final String expectedItemValue = String.format("something%s", i);
                final Map<String, String> actualRecord = actualContentList.get(i);

                assertNotNull(actualRecord);
                assertTrue(!actualRecord.isEmpty());
                assertTrue(actualRecord.size() == 5);
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test1.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test2.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test3.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test4.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getContentList(List, Map, List)} メソッドの返却値を確認する。
         * このテストではコンテンツ定義のノードにconditionIdの値が設定されているレコードと設定されていないレコードが存在する場合を想定して行う。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値が空リストではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の返却値のサイズが <code>7</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードが {@code null} ではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードが空マップではないこと
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードのサイズが <code>5</code> であること
         * ・{@link ContentLoader#getContentList(List, Map, List)} の各インデックスに紐づくレコードの値が全て生成した期待値と等価であること
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithConditionIdAndNoConditionId() {
            final List<String> attributes = new ArrayList<>(5);
            attributes.add(TestContentAttribute.test1.getString());
            attributes.add(TestContentAttribute.test2.getString());
            attributes.add(TestContentAttribute.test3.getString());
            attributes.add(TestContentAttribute.test4.getString());
            attributes.add(TestContentAttribute.test5.getString());

            final Map<String, Object> selectionNodes = new LinkedHashMap<>(1);
            final List<Map<String, Map<String, String>>> selectionNodesList = new ArrayList<>(10);

            for (int i = 0; i < 10; i++) {
                final Map<String, Map<String, String>> node = new LinkedHashMap<>(2);
                final Map<String, String> items = new LinkedHashMap<>(5);
                final String itemValue = String.format("something%s", i);

                if (i % 2 == 0) {
                    items.put(SelectionNodeKey.CONDITION_ID.getKey(), StringUtils.EMPTY);
                } else {
                    items.put(SelectionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                }

                items.put(TestContentAttribute.test1.getString(), itemValue);
                items.put(TestContentAttribute.test2.getString(), itemValue);
                items.put(TestContentAttribute.test3.getString(), itemValue);
                items.put(TestContentAttribute.test4.getString(), itemValue);
                items.put(TestContentAttribute.test5.getString(), itemValue);

                node.put(SelectionNodeKey.NODE.getKey(), items);
                selectionNodesList.add(node);
            }

            selectionNodes.put(SelectionNodeKey.SELECTION_NODES.getKey(), selectionNodesList);

            final List<String> conditionIdList = new ArrayList<>(2);
            conditionIdList.add("1");
            conditionIdList.add("7");

            final List<Map<String, String>> actualContentList = this.invoke(attributes, selectionNodes,
                    conditionIdList);

            assertNotNull(actualContentList);
            assertTrue(!actualContentList.isEmpty());
            assertTrue(actualContentList.size() == 7);

            final List<Integer> expectedRecordIndexes = new ArrayList<>(7);
            expectedRecordIndexes.add(0);
            expectedRecordIndexes.add(1);
            expectedRecordIndexes.add(2);
            expectedRecordIndexes.add(4);
            expectedRecordIndexes.add(6);
            expectedRecordIndexes.add(7);
            expectedRecordIndexes.add(8);

            for (int i = 0, size = expectedRecordIndexes.size(); i < size; i++) {
                final String expectedItemValue = String.format("something%s", expectedRecordIndexes.get(i));
                final Map<String, String> actualRecord = actualContentList.get(i);

                assertNotNull(actualRecord);
                assertTrue(!actualRecord.isEmpty());
                assertTrue(actualRecord.size() == 5);
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test1.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test2.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test3.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test4.getString()));
                assertEquals(expectedItemValue, actualRecord.get(TestContentAttribute.test5.getString()));
            }
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getContentList(List, Map, List)}
         * メソッドを呼び出すメソッドです。 ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため
         * {@link SuppressWarnings}でuncheckedを指定しています。
         * 
         * @param attributes      コンテンツから取得する値に紐づくキー
         * @param rawContent      加工されていないコンテンツオブジェクト
         * @param conditionIdList 取得する対象の条件IDが格納されたリスト
         * @return コンテンツリスト
         */
        @SuppressWarnings("unchecked")
        private List<Map<String, String>> invoke(List<String> attributes, Map<String, Object> rawContent,
                List<String> conditionIdList) {

            List<Map<String, String>> contentList = new ArrayList<>(0);

            try {
                contentList = (ArrayList<Map<String, String>>) this.getTestMethod().invoke(TEST_CLASS, attributes,
                        rawContent, conditionIdList);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return contentList;
        }

        /**
         * {@link ContentLoader#getContentList(List, Map, List)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getContentList(List, Map, List)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getContentList";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, List.class, Map.class,
                            List.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * {@link ContentLoader#getContentList(List, Map, List)}
     * メソッドのテストメソッドを定義するテストクラスです。
     * {@link ContentLoader#getContentList(List, Map, List)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    final class TestGetConditionIdList {

        /**
         * テスト対象のクラスオブジェクト
         */
        private final Class<ContentLoader> TEST_CLASS = ContentLoader.class;

        /**
         * テスト用メソッド
         */
        private Method testMethod = null;

        /**
         * <pre>
         * ❏ 概要
         * {@link ContentLoader} クラスの {@link ContentLoader#getConditionIdList(List, Map)} メソッドの返却値を確認する。
         * このテストではコンテンツファイルに各条件ノードが一つの条件のみを持っている状態を想定して行う。
         * また、 {@link ContentLoader#getConditionIdList(List, Map)} メソッドを実行した結果、conditionIdが <code>"1"</code> の条件に合致するようにテストを行う。
         * </pre>
         * 
         * <pre>
         * ❏ 観点
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が {@code null} ではないこと
         * ・{@link ContentLoader#getConditionIdList(List, Map)} の返却値が空リストではないこと
         * </pre>
         * 
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        public void testWithOneCondition() {

            final List<Map<String, Object>> conditionNodes = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                final Map<String, Object> nodes = new HashMap<>();
                final Map<String, Object> items = new HashMap<>();

                items.put(ConditionNodeKey.CONDITION_ID.getKey(), String.valueOf(i));
                items.put(ConditionNodeKey.EXCLUDE.getKey(), "false");

                final List<Map<String, Object>> conditionList = new ArrayList<>();
                final Map<String, Object> condition = new HashMap<>();

                condition.put(ConditionNodeKey.KEY_NAME.getKey(), "testCondition1");
                condition.put(ConditionNodeKey.OPERAND.getKey(), "=");
                condition.put(ConditionNodeKey.VALUE.getKey(), String.valueOf(i));

                conditionList.add(condition);
                items.put(ConditionNodeKey.CONDITIONS.getKey(), conditionList);
                nodes.put(ConditionNodeKey.NODE.getKey(), items);
                conditionNodes.add(nodes);
            }

            final String expectedConditionId = "1";
            final Map<String, String> conditions = new HashMap<>();
            conditions.put(TestCondition.testCondition1.getString(), expectedConditionId);

            final List<String> actualConditionIdList = this.invoke(conditionNodes, conditions);

            assertNotNull(actualConditionIdList);
            assertTrue(!actualConditionIdList.isEmpty());
            assertTrue(actualConditionIdList.size() == 1);
            assertEquals(expectedConditionId, actualConditionIdList.get(0));
        }

        /**
         * 引数の情報を基に {@link ContentLoader#getContentList(List, Map, List)}
         * メソッドを呼び出すメソッドです。 ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため
         * {@link SuppressWarnings}でuncheckedを指定しています。
         * 
         * @param conditionNodes 条件ノードリスト
         * @param conditions     条件の照合時に使用する条件マップ
         * @return 条件IDのリスト
         */
        @SuppressWarnings("unchecked")
        private List<String> invoke(List<Map<String, Object>> conditionNodes, Map<String, String> conditions) {

            List<String> conditionIdList = new ArrayList<>(0);

            try {
                conditionIdList = (List<String>) this.getTestMethod().invoke(TEST_CLASS, conditionNodes, conditions);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return conditionIdList;
        }

        /**
         * {@link ContentLoader#getConditionIdList(List, Map)} メソッドを取得し返却します。
         * 
         * @return {@link ContentLoader#getConditionIdList(List, Map)} メソッド
         */
        private Method getTestMethod() {
            if (this.testMethod == null) {
                try {
                    final String testMethodName = "getConditionIdList";
                    this.testMethod = this.TEST_CLASS.getDeclaredMethod(testMethodName, List.class, Map.class);
                    this.testMethod.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            return this.testMethod;
        }
    }

    /**
     * テスト用コンテンツ名クラス
     */
    private enum TestContentName implements Content {

        /**
         * 小規模選択ノードのテスト用コンテンツ
         */
        SMALL_SELECTION_NODES(Name.testContentWithSmallSelectionNodes),

        /**
         * 中規模選択ノードのテスト用コンテンツ
         */
        MEDIUM_SELECTION_NODES(Name.testContentWithMediumSelectionNodes),

        /**
         * 大規模選択ノードのテスト用コンテンツ
         */
        LARGE_SELECTION_NODES(Name.testContentWithLargeSelectionNodes),

        /**
         * 小規模条件ノードのテスト用コンテンツ
         */
        SMALL_CONDITION_NODES(Name.testContentWithSmallConditionNodes),

        /**
         * 中規模条件ノードのテスト用コンテンツ
         */
        MEDIUM_CONDITION_NODES(Name.testContentWithMediumConditionNodes),

        /**
         * 大規模条件ノードのテスト用コンテンツ
         */
        LARGE_CONDITION_NODES(Name.testContentWithLargeConditionNodes),

        /**
         * 標準のテスト用コンテンツ
         */
        DEFAULT(Name.testContent),

        /**
         * 本番仕様のテスト用コンテンツ
         */
        PRODUCTION(Name.content);

        /**
         * コンテンツ名
         */
        private Name contentName;

        /**
         * コンストラクタ
         * 
         * @param contentName コンテンツ名
         */
        TestContentName(Name contentName) {
            this.contentName = contentName;
        }

        /**
         * コンテンツ名
         */
        private enum Name {
            testContentWithSmallSelectionNodes, testContentWithMediumSelectionNodes, testContentWithLargeSelectionNodes,
            testContentWithSmallConditionNodes, testContentWithMediumConditionNodes, testContentWithLargeConditionNodes,
            testContent, content;
        }

        @Override
        public String getString() {
            return this.contentName.name();
        }
    }

    /**
     * テスト用アトリビュートクラス
     */
    private enum TestContentAttribute implements Attribute {
        test1, test2, test3, test4, test5;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * テスト用条件クラス
     */
    private enum TestCondition implements Condition {
        testCondition1, testCondition2, testCondition3, testCondition4, testCondition5, testCondition6;

        @Override
        public String getString() {
            return this.name();
        }
    }
}