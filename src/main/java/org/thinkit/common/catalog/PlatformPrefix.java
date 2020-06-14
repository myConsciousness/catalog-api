/**
 * Project Name : dev-utils<br>
 * File Name : PlatformPrefix.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/14<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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