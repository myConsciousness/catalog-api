/**
 * Project Name : dev-utils<br>
 * File Name : PlatformChecker.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/14<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import org.thinkit.common.catalog.PlatformPrefix;

/**
 * プログラムを実行しているプラットフォームを判定する汎用的な機能を提供します。<br>
 * 以下の静的メソッドを使用することで実行プラットフォームを判定することができます。<br>
 * {@link #isWindows()}<br>
 * {@link #isMac()}<br>
 * {@link #isLinux()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #isWindows()
 * @see #isMac()
 * @see #isLinux()
 */
public final class PlatformChecker {

    /**
     * OS名を保持しているプロパティ名
     */
    private static final String PROPERTY_OS_NAME = "os.name";

    /**
     * OS名
     */
    private static final String OS_NAME = System.getProperty(PROPERTY_OS_NAME).toLowerCase();

    /**
     * デフォルトコンストラクタ
     */
    private PlatformChecker() {
    }

    /**
     * プログラムを実行しているプラットフォームの判定を行います。<br>
     * プログラムを実行しているプラットフォームがWindows OSの場合は{@code true}を返却します。<br>
     * 実行プラットフォームがWindows OS以外の場合は必ず{@code false}を返却します。
     *
     * @return プログラムの実行プラットフォームがWindows OSの場合は{@code true}、それ以外は{@code false}
     */
    public static boolean isWindows() {
        return OS_NAME.startsWith(PlatformPrefix.windows());
    }

    /**
     * プログラムを実行しているプラットフォームの判定を行います。<br>
     * プログラムを実行しているプラットフォームがMac OSの場合は{@code true}を返却します。<br>
     * 実行プラットフォームがMac OS以外の場合は必ず{@code false}を返却します。
     *
     * @return プログラムの実行プラットフォームがMac OSの場合は{@code true}、それ以外は{@code false}
     */
    public static boolean isMac() {
        return OS_NAME.startsWith(PlatformPrefix.mac());
    }

    /**
     * プログラムを実行しているプラットフォームの判定を行います。<br>
     * プログラムを実行しているプラットフォームがLinux OSの場合は{@code true}を返却します。<br>
     * 実行プラットフォームがLinux OS以外の場合は必ず{@code false}を返却します。
     *
     * @return プログラムの実行プラットフォームがLinux OSの場合は{@code true}、それ以外は{@code false}
     */
    public static boolean isLinux() {
        return OS_NAME.startsWith(PlatformPrefix.linux());
    }
}