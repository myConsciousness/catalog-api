/**
 * Project Name : dev-utils<br>
 * File Name : Extension.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
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
 * 拡張子を管理するカタログです。<br>
 * {@link #getExtension()}を使用することで拡張子要素の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #java()}<br>
 * {@link #xml()}<br>
 * {@link #json()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #java()
 * @see #xml()
 * @see #json()
 */
@Getter
@RequiredArgsConstructor
public enum Extension implements Catalog<Extension> {

    /**
     * Java
     */
    JAVA(0, ".java"),

    /**
     * XML
     */
    XML(1, ".xml"),

    /**
     * json
     */
    JSON(2, ".json");

    /**
     * コード値
     */
    private final int code;

    /**
     * 拡張子
     */
    private final String extension;

    /**
     * Javaの拡張子を返却します。
     *
     * @return Javaの拡張子
     * @see #JAVA
     */
    public static String java() {
        return JAVA.getExtension();
    }

    /**
     * XMLの拡張子を返却します。
     *
     * @return XMLの拡張子
     * @see #XML
     */
    public static String xml() {
        return XML.getExtension();
    }

    /**
     * jsonの拡張子を返却します。
     *
     * @return jsonの拡張子
     * @see #JSON
     */
    public static String json() {
        return JSON.getExtension();
    }
}