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
 * {@link Identifier}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class IdentifierTest {

    /**
     * 可視性 : public
     */
    private static final String IDENTIFIER_PUBLIC = "public";

    /**
     * 可視性 : private
     */
    private static final String IDENTIFIER_PRIVATE = "private";

    /**
     * 可視性 : protected
     */
    private static final String IDENTIFIER_PROTECTED = "protected";

    @Test
    public void testCodeValues() {
        assertEquals(0, Identifier.PUBLIC.getCode());
        assertEquals(1, Identifier.PRIVATE.getCode());
        assertEquals(2, Identifier.PROTECTED.getCode());
    }

    @Test
    public void testToIdentifier() {
        assertEquals(IDENTIFIER_PUBLIC, Identifier.PUBLIC.toIdentifier());
        assertEquals(IDENTIFIER_PRIVATE, Identifier.PRIVATE.toIdentifier());
        assertEquals(IDENTIFIER_PROTECTED, Identifier.PROTECTED.toIdentifier());
    }
}