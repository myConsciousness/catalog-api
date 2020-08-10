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
 * ブレースを管理するカタログです。<br>
 * {@link #getBrace()}を使用することでブレース要素の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #start()}<br>
 * {@link #end()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Brace implements Catalog<Brace> {

    /**
     * 開始ブレース
     */
    START(0, "{"),

    /**
     * 終了ブレース
     */
    END(1, "}");

    /**
     * コード値
     */
    private final int code;

    /**
     * ブレース
     */
    private final String brace;

    /**
     * 開始ブレースの文字列表現を返却します。
     *
     * @return 開始ブレースの文字列表現
     */
    public static String start() {
        return START.getBrace();
    }

    /**
     * 終了ブレースの文字列表現を返却します。
     *
     * @return 終了ブレースの文字列表現
     */
    public static String end() {
        return END.getBrace();
    }
}