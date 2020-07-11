/**
 * Project Name : dev-utils<br>
 * File Name : Brace.java<br>
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