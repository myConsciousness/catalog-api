/**
 * Project Name : dev-utils<br>
 * File Name : ParenthesisTest.java<br>
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
 * {@link Parenthesis}クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ParenthesisTest {

    /**
     * 開始括弧
     */
    private static final String PARENTHESIS_START = "(";

    /**
     * 終了括弧
     */
    private static final String PARENTHESIS_END = ")";

    @Test
    public void testCodeValues() {
        assertEquals(0, Parenthesis.START.getCode());
        assertEquals(1, Parenthesis.END.getCode());
    }

    @Test
    public void testStart() {
        assertEquals(PARENTHESIS_START, Parenthesis.start());
    }

    @Test
    public void testEnd() {
        assertEquals(PARENTHESIS_END, Parenthesis.end());
    }
}