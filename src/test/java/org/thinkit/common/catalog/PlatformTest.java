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