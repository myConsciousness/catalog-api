/**
 * Project Name : dev-utils<br>
 * File Name : Delimiter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/22<br>
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
 * 区切り文字を管理するカテゴリです。<br>
 * {@link #getDelimiter()}を使用することで区切り要素の文字列を表現を取得できます。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Delimiter implements Catalog<Delimiter> {

    /**
     * カンマ
     */
    COMMA(0, ","),

    /**
     * ピリオド
     */
    PERIOD(1, "."),

    /**
     * コロン
     */
    COLON(2, ":"),

    /**
     * セミコロン
     */
    SEMICOLON(4, ";");

    /**
     * コード値
     */
    private final int code;

    /**
     * 区切り文字
     */
    private final String delimiter;
}
