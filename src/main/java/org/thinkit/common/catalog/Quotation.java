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

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * クォーテーションを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum Quotation implements Catalog<Quotation> {

    /**
     * シングルクォート
     */
    SINGLE_QUOTE(0, "'"),

    /**
     * ダブルクォート
     */
    DOUBLE_QUOTE(1, "\"");

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * クォーテーション
     */
    @Getter
    private final String quotation;

    /**
     * 引数として与えられた {@code token} の文字列が {@link Quotation} に定義されているか判定します。
     *
     * @param token 判定対象のトークン
     * @return 引数として与えられた {@code token} の文字列が {@link Quotation} に定義されている場合は
     *         {@code true} 、それ以外は {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public boolean contains(@NonNull String token) {

        for (Quotation quotation : Quotation.values()) {
            if (quotation.getQuotation().equals(token)) {
                return true;
            }
        }

        return false;
    }
}