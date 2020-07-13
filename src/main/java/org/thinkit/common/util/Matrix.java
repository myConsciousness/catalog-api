/**
 * Project Name : dev-utils<br>
 * File Name : Matrix.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 行列の行インデックスと列インデックスを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class Matrix {

    /**
     * 列インデックス
     */
    @Getter
    private int column = 0;

    /**
     * 行インデックス
     */
    @Getter
    private int row = 0;

    /**
     * デフォルトコンストラクタ
     */
    private Matrix() {
    }

    /**
     * コンストラクタ
     *
     * @param column 列インデックス
     * @param row    行インデックス
     */
    private Matrix(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * {@link Matrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param column 列インデックス
     * @param row    行インデックス
     */
    public static Matrix of(final int column, final int row) {
        return new Matrix(column, row);
    }
}