/**
 * Project Name : dev-utils<br>
 * File Name : IdentifierTest.java<br>
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