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