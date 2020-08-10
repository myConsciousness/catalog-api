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
 * 括弧を管理するカタログです。<br>
 * {@link #getParenthesis()}を使用することで括弧要素の文字列表現を取得することができます。<br>
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
public enum Parenthesis implements Catalog<Parenthesis> {

    /**
     * 開始括弧
     */
    START(0, "("),

    /**
     * 終了括弧
     */
    END(1, ")");

    /**
     * コード値
     */
    private final int code;

    /**
     * 括弧
     */
    private final String parenthesis;

    /**
     * 開始括弧の文字列表現を返却します。
     *
     * @return 開始括弧の文字列表現
     */
    public static String start() {
        return START.getParenthesis();
    }

    /**
     * 終了括弧の文字列表現を返却します。
     *
     * @return 終了括弧の文字列表現
     */
    public static String end() {
        return END.getParenthesis();
    }
}