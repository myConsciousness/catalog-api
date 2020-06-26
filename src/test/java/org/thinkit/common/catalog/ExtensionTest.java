/**
 * Project Name : dev-utils<br>
 * File Name : ExtensionTest.java<br>
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
 * {@link Extension}クラスのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public class ExtensionTest {

    /**
     * Javaの拡張子
     */
    private static final String EXTENSION_JAVA = ".java";

    /**
     * XMLの拡張子
     */
    private static final String EXTENSION_XML = ".xml";

    /**
     * jsonの拡張子
     */
    private static final String EXTENSION_JSON = ".json";

    @Test
    public void testCodeValues() {
        assertEquals(0, Extension.JAVA.getCode());
        assertEquals(1, Extension.XML.getCode());
        assertEquals(2, Extension.JSON.getCode());
    }

    @Test
    public void testJava() {
        assertEquals(EXTENSION_JAVA, Extension.java());
    }

    @Test
    public void testXml() {
        assertEquals(EXTENSION_XML, Extension.xml());
    }

    @Test
    public void testJson() {
        assertEquals(EXTENSION_JSON, Extension.json());
    }
}