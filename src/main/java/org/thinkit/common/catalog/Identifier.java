/**
 * Project Name : dev-utils<br>
 * File Name : Identifier.java<br>
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
 * アクセス修飾子を管理するカタログです。<br>
 * {@link #toIdentifier()}を使用することでアクセス修飾子の文字列表現を取得することができます。
 *
 * <pre>
 * 使用例:
 * <code>Identifier.PUBLIC.toIdentifier();
 * >> "public"</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Identifier implements Catalog<Identifier> {

    /**
     * 可視性 : public
     */
    PUBLIC(0),

    /**
     * 可視性 : private
     */
    PRIVATE(1),

    /**
     * 可視性 : protected
     */
    PROTECTED(2);

    /**
     * コード値
     */
    private final int code;

    /**
     * このEnumクラスに定義されたアクセス修飾子の文字列表現を返却します。<br>
     * 言語使用としてアクセス修飾子が全て小文字で定義されていることを利用し、<br>
     * このEnumクラスで定義されている要素を小文字に変換して返却します。
     *
     * @return アクセス修飾子の文字列表現
     */
    public String toIdentifier() {
        return this.name().toLowerCase();
    }
}