/**
 * Project Name : dev-utils<br>
 * File Name : HtmlTag.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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