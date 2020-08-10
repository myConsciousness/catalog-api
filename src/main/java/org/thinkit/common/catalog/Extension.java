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