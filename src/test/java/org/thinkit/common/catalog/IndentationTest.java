/**
 * Project Name : dev-utils<br>
 * File Name : Indentation.java<br>
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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.thinkit.common.exception.LogicException;

/**
 * {@link Indentation}クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public class IndentationTest {

    /**
     * 半角空白
     */
    private static final String SPACE = " ";

    /**
     * タブ
     */
    private static final String TAB = "\t";

    /**
     * 改行
     */
    private static final String RETURN = "\r\n";

    @Test
    public void testCodeValues() {
        assertEquals(0, Indentation.SPACE.getCode());
        assertEquals(1, Indentation.TAB.getCode());
        assertEquals(2, Indentation.RETURN.getCode());
    }

    @Test
    public void testSpace() {
        assertEquals(SPACE, Indentation.space());
    }

    @Test
    public void testTab() {
        assertEquals(TAB, Indentation.tabCode());
    }

    @Test
    public void testReturn() {
        assertEquals(RETURN, Indentation.returnCode());
    }

    @Test
    public void testGetIndentSpacesWithDefaultSpace() {
        final int spaceCount = 4;
        final String fourSpaces = this.createSpaces(spaceCount);
        final String indentSpaces = Indentation.getIndentSpaces();

        assertEquals(spaceCount, indentSpaces.length());
        assertEquals(fourSpaces, indentSpaces);
    }

    @Test
    public void testGetIndentSpacesWithOneSpace() {
        final int spaceCount = 1;
        final String space = this.createSpaces(spaceCount);
        final String indentSpaces = Indentation.getIndentSpaces(spaceCount);

        assertEquals(spaceCount, indentSpaces.length());
        assertEquals(space, indentSpaces);
    }

    @Test
    public void testGetIndentSpacesWithTenSpace() {
        final int spaceCount = 10;
        final String tenSpaces = this.createSpaces(spaceCount);
        final String indentSpaces = Indentation.getIndentSpaces(spaceCount);

        assertEquals(spaceCount, indentSpaces.length());
        assertEquals(tenSpaces, indentSpaces);
    }

    @Test
    public void testGetIndentSpacesWithHundredSpaces() {
        final int spaceCount = 100;
        final String hundredSpaces = this.createSpaces(spaceCount);
        final String indentSpaces = Indentation.getIndentSpaces(spaceCount);

        assertEquals(spaceCount, indentSpaces.length());
        assertEquals(hundredSpaces, indentSpaces);
    }

    @Test
    public void testGetIndentSpacesWithThousandSpaces() {
        final int spaceCount = 1000;
        final String thousandSpaces = this.createSpaces(spaceCount);
        final String indentSpaces = Indentation.getIndentSpaces(spaceCount);

        assertEquals(spaceCount, indentSpaces.length());
        assertEquals(thousandSpaces, indentSpaces);
    }

    @Test
    public void testGetIndentSpacesWithMinusCase() {
        Throwable exception = assertThrows(LogicException.class, () -> Indentation.getIndentSpaces(-1));
        assertEquals("Indent count must be positive.", exception.getMessage());
    }

    @Test
    public void testGetIndentTabsWithDefault() {
        final int tabCount = 1;
        final String tab = this.createTabs(tabCount);
        final String indentTab = Indentation.getIndentTabs();

        assertEquals(tabCount, indentTab.length());
        assertEquals(tab, indentTab);
    }

    @Test
    public void testGetIndentTabsWithTenTabs() {
        final int tabCount = 10;
        final String tenTabs = this.createTabs(tabCount);
        final String indentTab = Indentation.getIndentTabs(tabCount);

        assertEquals(tabCount, indentTab.length());
        assertEquals(tenTabs, indentTab);
    }

    @Test
    public void testGetIndentTabsWithHundredTabs() {
        final int tabCount = 100;
        final String hundredTabs = this.createTabs(tabCount);
        final String indentTab = Indentation.getIndentTabs(tabCount);

        assertEquals(tabCount, indentTab.length());
        assertEquals(hundredTabs, indentTab);
    }

    @Test
    public void testGetIndentTabsWithThousandTabs() {
        final int tabCount = 1000;
        final String thousandTabs = this.createTabs(tabCount);
        final String indentTab = Indentation.getIndentTabs(tabCount);

        assertEquals(tabCount, indentTab.length());
        assertEquals(thousandTabs, indentTab);
    }

    @Test
    public void testGetIndentTabsWithMinus() {
        Throwable exception = assertThrows(LogicException.class, () -> Indentation.getIndentTabs(-1));
        assertEquals("Tab count must be positive.", exception.getMessage());
    }

    /**
     * 引数として指定された回数分の半角空白を返却します。
     * 
     * @param count 繰り返し回数
     * @return 指定された繰り返し回数分の半角空白
     */
    private String createSpaces(final int count) {
        final StringBuilder spaces = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            spaces.append(SPACE);
        }

        return spaces.toString();
    }

    /**
     * 引数として指定された回数分のタブを返却します。
     * 
     * @param count 繰り返し回数
     * @return 指定された繰り返し回数分のタブ
     */
    private String createTabs(final int count) {
        final StringBuilder tabs = new StringBuilder(count);

        for (int i = 0; i < count; i++) {
            tabs.append(TAB);
        }

        return tabs.toString();
    }
}