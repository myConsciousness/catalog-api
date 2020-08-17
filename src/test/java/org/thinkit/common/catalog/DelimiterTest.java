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
 * {@link Delimiter}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class DelimiterTest {

    /**
     * カンマ
     */
    private static final String COMMA = ",";

    /**
     * ピリオド
     */
    private static final String PERIOD = ".";

    /**
     * コロン
     */
    private static final String COLON = ":";

    /**
     * セミコロン
     */
    private static final String SEMICOLON = ";";

    @Test
    public void testCodeValues() {
        assertEquals(0, Delimiter.COMMA.getCode());
        assertEquals(1, Delimiter.PERIOD.getCode());
        assertEquals(2, Delimiter.COLON.getCode());
        assertEquals(3, Delimiter.SEMICOLON.getCode());
    }

    @Test
    public void testComma() {
        assertEquals(COMMA, Delimiter.comma());
    }

    @Test
    public void testPeriod() {
        assertEquals(PERIOD, Delimiter.period());
    }

    @Test
    public void testColon() {
        assertEquals(COLON, Delimiter.colon());
    }

    @Test
    public void testSemicolon() {
        assertEquals(SEMICOLON, Delimiter.semicolon());
    }
}