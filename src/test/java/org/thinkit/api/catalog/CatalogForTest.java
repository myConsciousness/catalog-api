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

/**
 * {@link Catalog} インタフェースのテスト用カタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public enum CatalogForTest implements Catalog<CatalogForTest> {

    /**
     * テスト1
     */
    TEST_1(0, "failure"),

    /**
     * テスト2
     */
    TEST_2(1, "success"),

    /**
     * テスト3
     */
    TEST_3(2, "failure");

    /**
     * コード値
     */
    private int code;

    /**
     * 文字列
     */
    private String sequence;

    /**
     * コンストラクタ
     *
     * @param code     コード値
     * @param sequence 文字列
     */
    CatalogForTest(int code, String sequence) {
        this.code = code;
        this.sequence = sequence;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    /**
     * テスト要素に定義された文字列を返却します。
     *
     * @return テスト要素に定義された文字列
     */
    public String getSequence() {
        return this.sequence;
    }
}