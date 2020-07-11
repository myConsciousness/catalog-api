/**
 * Project Name : dev-utils<br>
 * File Name : PlatformTest.java<br>
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
import org.thinkit.common.util.PlatformChecker;

/**
 * {@link Platform}クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class PlatformTest {

    @Test
    public void testCodeValues() {
        assertEquals(0, Platform.WINDOWS.getCode());
        assertEquals(1, Platform.MAC.getCode());
        assertEquals(2, Platform.LINUX.getCode());
    }

    @Test
    public void testGetPlatformOnWindows() {
        if (PlatformChecker.isWindows()) {
            assertEquals(Platform.WINDOWS, Platform.getPlatform());
        }
    }

    @Test
    public void testGetPlatformOnMac() {
        if (PlatformChecker.isMac()) {
            assertEquals(Platform.MAC, Platform.getPlatform());
        }
    }

    @Test
    public void testGetPlatformOnLinux() {
        if (PlatformChecker.isLinux()) {
            assertEquals(Platform.WINDOWS, Platform.getPlatform());
        }
    }
}