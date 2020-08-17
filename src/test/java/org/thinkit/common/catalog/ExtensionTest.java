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
 * {@link Extension}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ExtensionTest {

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