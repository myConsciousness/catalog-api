/**
 * Project Name : Generator<br>
 * File Name : EscapeSequenceTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link EscapeSequence}クラスのテストクラスです。
 */
public class EscapeSequenceTest {

    /**
     * 半角空白のエスケープ文字列
     */
    private static final String SPACE = "&#xA0;";

    /**
     * 全角空白のエスケープ文字列
     */
    private static final String FULL_WIDTH_SPACE = "&#x3000;";

    /**
     * キャリッジリターンのエスケープ文字列
     */
    private static final String CARRIAGE_RETURN = "\\u000d";

    /**
     * ラインフィードのエスケープ文字列
     */
    private static final String LINE_FEED = "\\u000a";

    /**
     * 改行のエスケープ文字列
     */
    private static final String NEW_LINE = "\\u000d\\u000a";

    /**
     * タブのエスケープ文字列
     */
    private static final String TAB = "\u0009";

    /**
     * 左括弧のエスケープ文字列
     */
    private static final String LEFT_BRACKET = "&lt;";

    /**
     * 右括弧のエスケープ文字列
     */
    private static final String RIGHT_BRACKET = "&gt;";

    /**
     * シングルクオーテーションのエスケープ文字列
     */
    private static final String SINGLE_QUOTATION = "\u0027";

    /**
     * ダブルクオーテーションのエスケープ文字列
     */
    private static final String DOUBLE_QUOTATION = "\\u0022";

    @Test
    public void testCodeValues() {
        assertEquals(0, EscapeSequence.SPACE.getCode());
        assertEquals(1, EscapeSequence.FULL_WIDTH_SPACE.getCode());
        assertEquals(2, EscapeSequence.CARRIAGE_RETURN.getCode());
        assertEquals(3, EscapeSequence.LINE_FEED.getCode());
        assertEquals(4, EscapeSequence.NEW_LINE.getCode());
        assertEquals(5, EscapeSequence.TAB.getCode());
        assertEquals(6, EscapeSequence.LEFT_BRACKET.getCode());
        assertEquals(7, EscapeSequence.RIGHT_BRACKET.getCode());
        assertEquals(8, EscapeSequence.SINGLE_QUOTATION.getCode());
        assertEquals(9, EscapeSequence.DOUBLE_QUOTATION.getCode());
    }

    @Test
    public void testSpace() {
        assertEquals(SPACE, EscapeSequence.space());
    }

    @Test
    public void testFullWidthSpace() {
        assertEquals(FULL_WIDTH_SPACE, EscapeSequence.fullWidthSpace());
    }

    @Test
    public void testCarriageReturn() {
        assertEquals(CARRIAGE_RETURN, EscapeSequence.carriageReturn());
    }

    @Test
    public void testLineFeed() {
        assertEquals(LINE_FEED, EscapeSequence.lineFeed());
    }

    @Test
    public void testNewLine() {
        assertEquals(NEW_LINE, EscapeSequence.newLine());
    }

    @Test
    public void testTab() {
        assertEquals(TAB, EscapeSequence.tab());
    }

    @Test
    public void testLeftBracket() {
        assertEquals(LEFT_BRACKET, EscapeSequence.leftBracket());
    }

    @Test
    public void testRightBracket() {
        assertEquals(RIGHT_BRACKET, EscapeSequence.rightBracket());
    }

    @Test
    public void testSingleQuotation() {
        assertEquals(SINGLE_QUOTATION, EscapeSequence.singleQuotation());
    }

    @Test
    public void testDoubleQuotation() {
        assertEquals(DOUBLE_QUOTATION, EscapeSequence.doubleQuotation());
    }
}