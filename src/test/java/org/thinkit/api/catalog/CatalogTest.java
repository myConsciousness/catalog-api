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
     * <pre>
     * ❏ 概要
     * {@link Catalog} インターフェースの抽象メソッド {@link Catalog#getCode()} を確認する。
     * テストの際には {@link Brace} クラスを使用する。
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
    public void testGetCode() {
        assertEquals(0, Brace.START.getCode());
        assertEquals(1, Brace.END.getCode());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Catalog} インターフェースの {@link Catalog#getOrderedList(Class)} メソッドの返却値を確認する。
     * テストの際には {@link Brace} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・期待値と{@link Catalog#getOrderedList(Class)} メソッドから取得したリストが等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * 以下のリストを期待値とする。
     *
     * {@code final List<Brace> expectedBraceList = new ArrayList<>(2);
     *        expectedBraceList.add(Brace.START);
     *        expectedBraceList.add(Brace.END);}
     * </pre>
     */
    @Test
    public void testGetOrderedList() {
        final List<Brace> expectedBraceList = new ArrayList<>(2);
        expectedBraceList.add(Brace.START);
        expectedBraceList.add(Brace.END);

        assertEquals(expectedBraceList, Catalog.getOrderedList(Brace.class));
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Catalog} インターフェースの {@link Catalog#getEnum(Class, int)} メソッドの返却値を確認する。
     * テストの際には {@link Brace} クラスを使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link Brace#START} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@link Brace#START} が返却されること。
     * ・{@link Brace#END} のコード値を {@link Catalog#getEnum(Class, int)} へ渡した際に {@link Brace#END} が返却されること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetEnum() {
        assertEquals(Brace.START, Catalog.getEnum(Brace.class, Brace.START.getCode()));
        assertEquals(Brace.END, Catalog.getEnum(Brace.class, Brace.END.getCode()));
    }
}