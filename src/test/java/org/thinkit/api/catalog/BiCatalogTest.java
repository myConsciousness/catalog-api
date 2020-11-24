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

package org.thinkit.api.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link BiCatalog} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class BiCatalogTest {

    /**
     * 文字列 : {@code "success"}
     */
    private static final String SEQUENCE_SUCCESS = "success";

    /**
     * 文字列 : {@code "failure"}
     */
    private static final String SEQUENCE_FAILURE = "failure";

    /**
     * <pre>
     * ❏ 概要
     * {@link BiCatalog} インターフェースの抽象メソッド {@link BiCatalog#getCode()} を確認する。
     * テストの際には {@link BiCatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link BiCatalog} インターフェースを実装した具象クラスが {@link BiCatalog#getCode()} メソッドを呼び出した際に正常に処理が終了すること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetCode() {
        assertEquals(0, BiCatalogForTest.TEST_1.getCode());
        assertEquals(1, BiCatalogForTest.TEST_2.getCode());
        assertEquals(2, BiCatalogForTest.TEST_3.getCode());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link BiCatalog} インターフェースの {@link BiCatalog#getOrderedList(Class)} メソッドの返却値を確認する。
     * テストの際には {@link BiCatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・期待値と{@link BiCatalog#getOrderedList(Class)} メソッドから取得したリストが等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetOrderedList() {
        final List<BiCatalogForTest> expectedBraceList = new ArrayList<>(3);
        expectedBraceList.add(BiCatalogForTest.TEST_1);
        expectedBraceList.add(BiCatalogForTest.TEST_2);
        expectedBraceList.add(BiCatalogForTest.TEST_3);

        final List<BiCatalogForTest> BiCatalogs = BiCatalog.getOrderedList(BiCatalogForTest.class);

        assertNotNull(BiCatalogs);
        assertTrue(BiCatalogs.size() == expectedBraceList.size());
        assertEquals(expectedBraceList, BiCatalogs);
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link BiCatalog} インターフェースの {@link BiCatalog#getEnum(Class, int)} メソッドの返却値を確認する。
     * テストの際には {@link BiCatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link BiCatalogForTest#TEST_3} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@link BiCatalogForTest#TEST_1} が返却されること。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@link BiCatalogForTest#TEST_2} が返却されること。
     * ・{@link BiCatalogForTest#TEST_3} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に {@link BiCatalogForTest#TEST_3} が返却されること。
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "failure"} であること。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "success""} であること。
     * ・{@link BiCatalogForTest#TEST_3} のコード値を {@link BiCatalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "failure"} であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetEnum() {

        final BiCatalogForTest test1 = BiCatalog.getEnum(BiCatalogForTest.class, BiCatalogForTest.TEST_1.getCode());
        final BiCatalogForTest test2 = BiCatalog.getEnum(BiCatalogForTest.class, BiCatalogForTest.TEST_2.getCode());
        final BiCatalogForTest test3 = BiCatalog.getEnum(BiCatalogForTest.class, BiCatalogForTest.TEST_3.getCode());

        assertNotNull(test1);
        assertNotNull(test2);
        assertNotNull(test3);

        assertEquals(BiCatalogForTest.TEST_1, test1);
        assertEquals(BiCatalogForTest.TEST_2, test2);
        assertEquals(BiCatalogForTest.TEST_3, test3);

        assertEquals(SEQUENCE_FAILURE, test1.getTag());
        assertEquals(SEQUENCE_SUCCESS, test2.getTag());
        assertEquals(SEQUENCE_FAILURE, test3.getTag());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link BiCatalog} インターフェースの {@link BiCatalog#getEnumByTag(Class, Object)} メソッドの返却値を確認する。
     * テストの際には {@link BiCatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に {@link BiCatalogForTest#TEST_1} が返却されること。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に {@link BiCatalogForTest#TEST_2} が返却されること。
     * ・{@link BiCatalogForTest#TEST_1} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に取得した要素の文字列が {@code "failure"} であること。
     * ・{@link BiCatalogForTest#TEST_2} のコード値を {@link BiCatalog#getEnumByTag(Class, Object)} へ渡した際に取得した要素の文字列が {@code "success""} であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetEnumByTag() {

        final BiCatalogForTest test1 = BiCatalog.getEnumByTag(BiCatalogForTest.class, BiCatalogForTest.TEST_1.getTag());
        final BiCatalogForTest test2 = BiCatalog.getEnumByTag(BiCatalogForTest.class, BiCatalogForTest.TEST_2.getTag());

        assertNotNull(test1);
        assertNotNull(test2);

        assertEquals(BiCatalogForTest.TEST_1, test1);
        assertEquals(BiCatalogForTest.TEST_2, test2);

        assertEquals(SEQUENCE_FAILURE, test1.getTag());
        assertEquals(SEQUENCE_SUCCESS, test2.getTag());
    }

    /**
     * {@link BiCatalog#contains(Class, Object)} メソッドのインナーテストクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestContains {

        /**
         * <pre>
         * ❏ 概要
         * {@link BiCatalog} インターフェースの {@link BiCatalog#contains(Class, Object)} メソッドの返却値を確認する。
         * テストの際には {@link BiCatalogForTest} クラスを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・文字列の {@code "failure"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code true} が返却されること。
         * ・文字列の {@code "success"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code true} が返却されること。
         * ・文字列の {@code "not contained"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testSimplePattern() {
            assertTrue(BiCatalog.contains(BiCatalogForTest.class, SEQUENCE_FAILURE));
            assertTrue(BiCatalog.contains(BiCatalogForTest.class, SEQUENCE_SUCCESS));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "not contained"));
        }

        /**
         * <pre>
         * ❏ 概要
         * {@link BiCatalog} インターフェースの {@link BiCatalog#contains(Class, Object)} メソッドの返却値を確認する。
         * テストの際には {@link BiCatalogForTest} クラスを使用する。
         * </pre>
         *
         * <pre>
         * ❏ 観点
         * ・半角スペース {@code " "} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・全角スペース {@code "　"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・文字列の {@code "uccess"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・文字列の {@code "succes"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・文字列の {@code "Success"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・文字列の {@code "succesS"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * ・文字列の {@code "SUCCESS"} を {@link BiCatalog#contains(Class, Object)} へ渡した際に {@code false} が返却されること。
         * </pre>
         *
         * <pre>
         * ❏ 留意点
         * なし
         * </pre>
         */
        @Test
        void testBoundaryValue() {
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, " "));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "　"));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "uccess"));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "succes"));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "Success"));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "succesS"));
            assertTrue(!BiCatalog.contains(BiCatalogForTest.class, "SUCCESS"));
        }
    }
}