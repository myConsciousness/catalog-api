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
 * HTMLタグを管理するカタログです。<br>
 * {@link #getHtmlTag()}を使用することでHTMLタグ要素の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #br()}<br>
 * {@link #p()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum HtmlTag implements Catalog<HtmlTag> {

    /**
     * 改行
     */
    BREAK(0, "<br>"),

    /**
     * パラグラフ
     */
    PARAGRAPH(1, "<p>");

    /**
     * コード値
     */
    private final int code;

    /**
     * HTMLタグ
     */
    private final String htmlTag;

    /**
     * 改行のHTMLタグを返却します。
     *
     * @return 改行のHTMLタグ
     * @see #BREAK
     */
    public static String br() {
        return BREAK.getHtmlTag();
    }

    /**
     * パラグラフのHTMLタグを返却します。
     *
     * @return パラグラフのHTMLタグ
     * @see #PARAGRAPH
     */
    public static String p() {
        return PARAGRAPH.getHtmlTag();
    }
}