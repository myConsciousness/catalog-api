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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プラットフォームの接頭辞を管理するカタログです。
 * {@link #getPrefix()}を使用することで接頭辞要素の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #windows()}<br>
 * {@link #mac()}<br>
 * {@link #linux()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum PlatformPrefix implements Catalog<PlatformPrefix> {

    /**
     * Windows
     */
    WINDOWS(0, "windows"),

    /**
     * Mac
     */
    MAC(1, "mac"),

    /**
     * Linux
     */
    LINUX(2, "linux");

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * 接頭辞
     */
    @Getter
    private final String prefix;

    /**
     * Windows OSの接頭辞を返却します。<br>
     * 接頭辞は全て小文字で構成されています。
     *
     * @return Windows OSの接頭辞
     * @see WINDOWS
     */
    public static String windows() {
        return WINDOWS.getPrefix();
    }

    /**
     * Mac OSの接頭辞を返却します。<br>
     * 接頭辞は全て小文字で構成されています。
     *
     * @return Mac OSの接頭辞
     * @see MAC
     */
    public static String mac() {
        return MAC.getPrefix();
    }

    /**
     * Linux OSの接頭辞を返却します。<br>
     * 接頭辞は全て小文字で構成されています。
     *
     * @return Linux OSの接頭辞
     * @see LINUX
     */
    public static String linux() {
        return LINUX.getPrefix();
    }
}