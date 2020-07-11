/**
 * Project Name : dev-utils<br>
 * File Name : DelimiterTest.java<br>
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
        assertEquals(COMMA, Delimiter.commma());
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