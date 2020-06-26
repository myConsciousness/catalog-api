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
 * エスケープ文字列を管理するカテゴリです。<br>
 * 以下の静的メソッドを使用することで各要素のエスケープ文字列を取得することができます。
 * 
 * <p>
 * {@link #space()}<br>
 * {@link #fullWidthSpace()}<br>
 * {@link #carriageReturn()}<br>
 * {@link #lineFeed()}<br>
 * {@link #newLine()}<br>
 * {@link #tab()}<br>
 * {@link #leftBracket()}<br>
 * {@link #rightBracket()}<br>
 * {@link #singleQuotation()}<br>
 * {@link #doubleQuotation()}<br>
 * 
 * <pre>
 * 使用例:
 * <code>String space = EscapeSequence.space();</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #space()
 * @see #fullWidthSpace()
 * @see #carriageReturn()
 * @see #lineFeed()
 * @see #newLine()
 * @see #tab()
 * @see #leftBracket()
 * @see #rightBracket()
 * @see #singleQuotation()
 * @see #doubleQuotation()
 */
@Getter
@RequiredArgsConstructor
public enum EscapeSequence implements Catalog<EscapeSequence> {

    /**
     * 半角空白
     */
    SPACE(0, "&#xA0;"),

    /**
     * 全角空白
     */
    FULL_WIDTH_SPACE(1, "&#x3000;"),

    /**
     * 改行コード（CR）
     */
    CARRIAGE_RETURN(2, "&#x0D;"),

    /**
     * 改行コード（LF）
     */
    LINE_FEED(3, "&#x0A;"),

    /**
     * 改行コード（CR+LF）
     */
    NEW_LINE(4, "&#x0D;&#x0A;"),

    /**
     * タブ (\t)
     */
    TAB(5, "&#x08;"),

    /**
     * 左括弧 (<)
     */
    LEFT_BRACKET(6, "&lt;"),

    /**
     * 右括弧 (>)
     */
    RIGHT_BRACKET(7, "&gt;"),

    /**
     * シングルクオーテーション (')
     */
    SINGLE_QUOTATION(8, "&apos;"),

    /**
     * ダブルクオーテーション (")
     */
    DOUBLE_QUOTATION(9, "&quot;");

    /**
     * コード値
     */
    private final int code;

    /**
     * エスケープ文字列
     */
    private final String escapeSequence;

    /**
     * 半角空白のエスケープ文字列を返却します。
     * 
     * @return 半角空白のエスケープ文字列
     * @see #SPACE
     */
    public static String space() {
        return SPACE.getEscapeSequence();
    }

    /**
     * 全角空白のエスケープ文字列を返却します。
     * 
     * @return 全角空白のエスケープ文字列
     * @see #FULL_WIDTH_SPACE
     */
    public static String fullWidthSpace() {
        return FULL_WIDTH_SPACE.getEscapeSequence();
    }

    /**
     * キャリッジリターンのエスケープ文字列を返却します。
     * 
     * @return キャリッジリターンのエスケープ文字列
     * @see #CARRIAGE_RETURN
     */
    public static String carriageReturn() {
        return CARRIAGE_RETURN.getEscapeSequence();
    }

    /**
     * ラインフィードのエスケープ文字列を返却します。
     * 
     * @return ラインフィードのエスケープ文字列
     * @see #LINE_FEED
     */
    public static String lineFeed() {
        return LINE_FEED.getEscapeSequence();
    }

    /**
     * 改行のエスケープ文字列を返却します。
     * 
     * @return 改行のエスケープ文字列
     * @see #NEW_LINE
     */
    public static String newLine() {
        return NEW_LINE.getEscapeSequence();
    }

    /**
     * タブのエスケープ文字列を返却します。
     * 
     * @return タブのエスケープ文字列
     * @see #TAB
     */
    public static String tab() {
        return TAB.getEscapeSequence();
    }

    /**
     * 左括弧のエスケープ文字列を返却します。
     * 
     * @return 左括弧のエスケープ文字列
     * @see #LEFT_BRACKET
     */
    public static String leftBracket() {
        return LEFT_BRACKET.getEscapeSequence();
    }

    /**
     * 右括弧のエスケープ文字列を返却します。
     * 
     * @return 右括弧のエスケープ文字列
     * @see #RIGHT_BRACKET
     */
    public static String rightBracket() {
        return RIGHT_BRACKET.getEscapeSequence();
    }

    /**
     * シングルクオテーションのエスケープ文字列を返却します。
     * 
     * @return シングルクオテーションのエスケープ文字列
     * @see #SINGLE_QUOTATION
     */
    public static String singleQuotation() {
        return SINGLE_QUOTATION.getEscapeSequence();
    }

    /**
     * ダブルクオテーションのエスケープ文字列を返却します。
     * 
     * @return ダブルクオテーションのエスケープ文字列
     * @see #DOUBLE_QUOTATION
     */
    public static String doubleQuotation() {
        return DOUBLE_QUOTATION.getEscapeSequence();
    }
}
