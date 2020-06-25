/**
 * Project Name : dev-utils<br>
 * File Name : CatalogTest.java<br>
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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * {@link Catalog}インターフェースのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version
 */
public final class CatalogTest {

    @Test
    public void testGetCode() {
        assertEquals(0, Brace.START.getCode());
        assertEquals(1, Brace.END.getCode());
    }

    @Test
    public void testGetOrderedList() {
        final List<Brace> expectedBraceList = new ArrayList<>();
        expectedBraceList.add(Brace.START);
        expectedBraceList.add(Brace.END);

        assertEquals(expectedBraceList, Catalog.getOrderedList(Brace.class));
    }

    @Test
    public void testGetEnum() {
        assertEquals(Brace.START, Catalog.getEnum(Brace.class, Brace.START.getCode()));
        assertEquals(Brace.END, Catalog.getEnum(Brace.class, Brace.END.getCode()));
    }
}