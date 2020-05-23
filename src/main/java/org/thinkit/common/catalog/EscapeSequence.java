/**
 * Project Name : dev-utils<br>
 * File Name : EscapeSequence.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/18<br>
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
 * エスケープ文字列を管理するカテゴリです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum EscapeSequence implements Catalog<EscapeSequence> {

    /**
     * 半角空白
     */
    SPACE(0, "&#xA0;", " "),

    /**
     * 全角空白
     */
    FULL_WIDTH_SPACE(1, "&#x3000;", "　"),

    /**
     * 改行コード（CR）
     */
    CARRIAGE_RETURN(2, "&#x0D;", "\r"),

    /**
     * 改行コード（LF）
     */
    LINE_FEED(3, "&#x0A;", "\n"),

    /**
     * 改行コード（CR+LF）
     */
    NEW_LINE(4, "&#x0D;&#x0A;", "\r\n"),

    /**
     * タブ
     */
    TAB(5, "&#x08;", "\t"),

    /**
     * 左括弧
     */
    LEFT_BRACKET(6, "&lt;", "<"),

    /**
     * 右括弧
     */
    RIGHT_BRACKET(7, "&gt;", ">"),

    /**
     * シングルクオーテーション
     */
    SINGLE_QUOTATION(8, "&apos;", "'"),

    /**
     * ダブルクオーテーション
     */
    DOUBLE_QUOTATION(9, "&quot;", "\"");

    /**
     * コード値
     */
    private final int code;

    /**
     * ユニコード文字列
     */
    private final String unicode;

    /**
     * エスケープ文字列
     */
    private final String escapeSequence;
}
