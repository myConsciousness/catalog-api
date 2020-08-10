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
 * プリミティブデータ型区分を管理するカテゴリです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum PrimitiveDataType implements Catalog<PrimitiveDataType> {

    /**
     * 真偽値
     */
    BOOLEAN(0),

    /**
     * Unicodeを表現する16bitの整数
     */
    CHAR(1),

    /**
     * 8bitの整数
     */
    BYTE(2),

    /**
     * 16bitの整数
     */
    SHORT(3),

    /**
     * 16bitの整数
     */
    INT(4),

    /**
     * 32bitの整数
     */
    LONG(5),

    /**
     * 32bitの実数
     */
    FLOAT(6),

    /**
     * 64bitの実数
     */
    DOUBLE(7);

    /**
     * コード値
     */
    private final int code;

    /**
     * データ型の文字列表現を返却します。<br>
     * 言語仕様として基本データ型が全て小文字で定義されている特性を利用し、<br>
     * このEnumクラスで定義されている要素を小文字に変換して返却します。
     *
     * @return データ型の文字列表現
     */
    public String toDataType() {
        return this.name().toLowerCase();
    }

    /**
     * 引数として渡されたデータ型がプリミティブ型か判定します。<br>
     * 以下の型をプリミティブ型として判定します。<br>
     * <br>
     * 1, {@link #BOOLEAN}<br>
     * 2, {@link #CHAR}<br>
     * 3, {@link #BYTE}<br>
     * 4, {@link #SHORT}<br>
     * 5, {@link #INT}<br>
     * 6, {@link #LONG}<br>
     * 7, {@link #FLOAT}<br>
     * 8, {@link #DOUBLE}<br>
     *
     * @param dataType データ型
     * @return 引数として渡されたデータ型がプリミティブ型の場合は{@code true}、それ以外は{@code false}
     */
    public static boolean isPrimitive(String dataType) {
        final PrimitiveDataType[] primitiveDataTypes = values();

        for (PrimitiveDataType primitiveDataType : primitiveDataTypes) {
            if (primitiveDataType.toDataType().equals(dataType)) {
                return true;
            }
        }

        return false;
    }
}