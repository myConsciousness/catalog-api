/**
 * Project Name : dev-utils<br>
 * File Name : Parenthesis.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
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