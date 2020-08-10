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
import lombok.RequiredArgsConstructor;

/**
 * 区切り文字を管理するカテゴリです。<br>
 * {@link #getDelimiter()}を使用することで区切り要素の文字列表現を取得できます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #comma()}<br>
 * {@link #period()}<br>
 * {@link #colon()}<br>
 * {@link #semicolon()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Delimiter implements Catalog<Delimiter> {

    /**
     * カンマ
     */
    COMMA(0, ","),

    /**
     * ピリオド
     */
    PERIOD(1, "."),

    /**
     * コロン
     */
    COLON(2, ":"),

    /**
     * セミコロン
     */
    SEMICOLON(3, ";");

    /**
     * コード値
     */
    private final int code;

    /**
     * 区切り文字
     */
    private final String delimiter;

    /**
     * カンマの文字列表現を返却します。
     *
     * @return カンマの文字列表現
     */
    public static String comma() {
        return COMMA.getDelimiter();
    }

    /**
     * ピリオドの文字列表現を返却します。
     *
     * @return ピリオドの文字列表現
     */
    public static String period() {
        return PERIOD.getDelimiter();
    }

    /**
     * コロンの文字列表現を返却します。
     *
     * @return コロンの文字列表現
     */
    public static String colon() {
        return COLON.getDelimiter();
    }

    /**
     * セミコロンの文字列表現を返却します。
     *
     * @return セミコロンの文字列表現
     */
    public static String semicolon() {
        return SEMICOLON.getDelimiter();
    }
}
