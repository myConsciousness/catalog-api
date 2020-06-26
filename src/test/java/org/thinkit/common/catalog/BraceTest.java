/**
 * Project Name : dev-utils<br>
 * File Name : BraceTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/25<br>
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
 * {@link Brace}クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class BraceTest {

    /**
     * 開始ブレース
     */
    private static final String BRACE_START = "{";

    /**
     * 終了ブレース
     */
    private static final String BRACE_END = "}";

    @Test
    public void testCodeValues() {
        assertEquals(0, Brace.START.getCode());
        assertEquals(1, Brace.END.getCode());
    }

    @Test
    public void testStart() {
        assertEquals(BRACE_START, Brace.start());
    }

    @Test
    public void testEnd() {
        assertEquals(BRACE_END, Brace.end());
    }
}