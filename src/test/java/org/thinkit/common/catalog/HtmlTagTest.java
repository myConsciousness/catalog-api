/**
 * Project Name : dev-utils<br>
 * File Name : HtmlTagTest.java<br>
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
 * {@link HtmlTag}クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class HtmlTagTest {

    /**
     * 改行タグ
     */
    private static final String BREAK_TAG = "<br>";

    /**
     * パラグラフタグ
     */
    private static final String PARAGRAPH_TAG = "<p>";

    @Test
    public void testCodeValues() {
        assertEquals(0, HtmlTag.BREAK.getCode());
        assertEquals(1, HtmlTag.PARAGRAPH.getCode());
    }

    @Test
    public void testBreak() {
        assertEquals(BREAK_TAG, HtmlTag.br());
    }

    @Test
    public void testParagraph() {
        assertEquals(PARAGRAPH_TAG, HtmlTag.p());
    }
}