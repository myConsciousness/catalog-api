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