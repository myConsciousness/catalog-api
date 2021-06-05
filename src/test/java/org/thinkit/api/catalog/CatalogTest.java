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

import org.junit.jupiter.api.Test;

/**
 * {@link Catalog} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class CatalogTest {

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
     * {@link Catalog} インターフェースの抽象メソッド {@link Catalog#getCode()} を確認する。
     * テストの際には {@link CatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link Catalog} インターフェースを実装した具象クラスが {@link Catalog#getCode()} メソッドを呼び出した際に正常に処理が終了すること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetCode() {
        assertEquals(0, CatalogForTest.TEST_1.getCode());
        assertEquals(1, CatalogForTest.TEST_2.getCode());
        assertEquals(2, CatalogForTest.TEST_3.getCode());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Catalog} インターフェースの {@link Catalog#getOrderedList(Class)} メソッドの返却値を確認する。
     * テストの際には {@link CatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・期待値と{@link Catalog#getOrderedList(Class)} メソッドから取得したリストが等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetOrderedList() {
        final List<CatalogForTest> expectedBraceList = new ArrayList<>(3);
        expectedBraceList.add(CatalogForTest.TEST_1);
        expectedBraceList.add(CatalogForTest.TEST_2);
        expectedBraceList.add(CatalogForTest.TEST_3);

        final List<CatalogForTest> catalogs = Catalog.getOrderedList(CatalogForTest.class);

        assertNotNull(catalogs);
        assertTrue(catalogs.size() == expectedBraceList.size());
        assertEquals(expectedBraceList, catalogs);
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Catalog} インターフェースの {@link Catalog#getEnum(Class, int)} メソッドの返却値を確認する。
     * テストの際には {@link CatalogForTest} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link CatalogForTest#TEST_1} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link CatalogForTest#TEST_2} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link CatalogForTest#TEST_3} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@code null} が返却されないこと。
     * ・{@link CatalogForTest#TEST_1} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@link CatalogForTest#TEST_1} が返却されること。
     * ・{@link CatalogForTest#TEST_2} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@link CatalogForTest#TEST_2} が返却されること。
     * ・{@link CatalogForTest#TEST_3} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@link CatalogForTest#TEST_3} が返却されること。
     * ・{@link CatalogForTest#TEST_1} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "failure"} であること。
     * ・{@link CatalogForTest#TEST_2} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "success""} であること。
     * ・{@link CatalogForTest#TEST_3} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に取得した要素の文字列が {@code "failure"} であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testGetEnum() {

        final CatalogForTest test1 = Catalog.getEnum(CatalogForTest.class, CatalogForTest.TEST_1.getCode());
        final CatalogForTest test2 = Catalog.getEnum(CatalogForTest.class, CatalogForTest.TEST_2.getCode());
        final CatalogForTest test3 = Catalog.getEnum(CatalogForTest.class, CatalogForTest.TEST_3.getCode());

        assertNotNull(test1);
        assertNotNull(test2);
        assertNotNull(test3);

        assertEquals(CatalogForTest.TEST_1, test1);
        assertEquals(CatalogForTest.TEST_2, test2);
        assertEquals(CatalogForTest.TEST_3, test3);

        assertEquals(SEQUENCE_FAILURE, test1.getSequence());
        assertEquals(SEQUENCE_SUCCESS, test2.getSequence());
        assertEquals(SEQUENCE_FAILURE, test3.getSequence());
    }
}
