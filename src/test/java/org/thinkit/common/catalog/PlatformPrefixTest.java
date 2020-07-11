/**
 * Project Name : dev-utils<br>
 * File Name : PlatformPrefixTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/27<br>
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
 * {@link PlatformPrefix} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class PlatformPrefixTest {

    /**
     * 接頭辞 : windows
     */
    private static final String PREFIX_WINDOWS = "windows";

    /**
     * 接頭辞 : mac
     */
    private static final String PREFIX_MAC = "mac";

    /**
     * 接頭辞 : linux
     */
    private static final String PREFIX_LINUX = "linux";

    /**
     * <pre>
     * ❏ 概要
     * {@link PlatformPrefix} クラスの各要素に定義されたコード値を確認する。
     * コード値は {@link PlatformPrefix#getCode()} メソッドから取得する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PlatformPrefix#WINDOWS} に定義されたコード値が <code>0</code> であること。
     * ・{@link PlatformPrefix#MAC} に定義されたコード値が <code>1</code> であること。
     * ・{@link PlatformPrefix#LINUX} に定義されたコード値が <code>2</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testCodeValues() {
        assertEquals(0, PlatformPrefix.WINDOWS.getCode());
        assertEquals(1, PlatformPrefix.MAC.getCode());
        assertEquals(2, PlatformPrefix.LINUX.getCode());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PlatformPrefix} クラスの {@link PlatformPrefix#windows()} メソッドの返却値を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PlatformPrefix#windows()} メソッドの返却値が <code>"windows"</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testWindows() {
        assertEquals(PREFIX_WINDOWS, PlatformPrefix.windows());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PlatformPrefix} クラスの {@link PlatformPrefix#mac()} メソッドの返却値を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PlatformPrefix#mac()} メソッドの返却値が <code>"mac"</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testMac() {
        assertEquals(PREFIX_MAC, PlatformPrefix.mac());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PlatformPrefix} クラスの {@link PlatformPrefix#linux()} メソッドの返却値を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PlatformPrefix#linux()} メソッドの返却値が <code>"linux"</code> であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testLinux() {
        assertEquals(PREFIX_LINUX, PlatformPrefix.linux());
    }
}