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

package org.thinkit.common.util.workbook;

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