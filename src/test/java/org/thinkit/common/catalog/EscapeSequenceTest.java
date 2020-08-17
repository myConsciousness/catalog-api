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

import org.junit.jupiter.api.Test;

/**
 * {@link EscapeSequence}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class EscapeSequenceTest {

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