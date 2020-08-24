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
    TEST_1(0),

    /**
     * テスト2
     */
    TEST_2(1),

    /**
     * テスト3
     */
    TEST_3(2);

    /**
     * コード値
     */
    private int code;

    /**
     * コンストラクタ
     *
     * @param code コード値
     */
    CatalogForTest(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}