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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Cellオブジェクトに関する汎用的な操作を行う機能を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
final class FluentCell {

    /**
     * デフォルトコンストラクタ
     */
    private FluentCell() {
    }

    /**
     * セルの上部に罫線が設定されているか判定します。 セルの上部に罫線が設定されている場合は{@code true}を返却し、
     * セルの上部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの上部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean isBorderedTop(@NonNull final Cell cell) {

        final BorderStyle borderStyle = cell.getCellStyle().getBorderTop();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return isStyleBordered(borderStyle);
    }

    /**
     * セルの下部に罫線が設定されているか判定します。 セルの下部に罫線が設定されている場合は{@code true}を返却し、
     * セルの下部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの下部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean isBorderedBottom(@NonNull final Cell cell) {

        final BorderStyle borderStyle = cell.getCellStyle().getBorderBottom();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return isStyleBordered(borderStyle);
    }

    /**
     * セルの右部に罫線が設定されているか判定します。 セルの右部に罫線が設定されている場合は{@code true}を返却し、
     * セルの右部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの右部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean isBorderedRight(@NonNull final Cell cell) {

        final BorderStyle borderStyle = cell.getCellStyle().getBorderRight();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return isStyleBordered(borderStyle);
    }

    /**
     * セルの左部に罫線が設定されているか判定します。 セルの左部に罫線が設定されている場合は{@code true}を返却し、
     * セルの左部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの左部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     * @exception IllegalArgumentException 引数として指定されたセルオブジェクトがnullの場合
     *
     * @exception NullPointerException     引数として {@code null} が渡された場合
     */
    public static boolean isBorderedLeft(@NonNull final Cell cell) {

        final BorderStyle borderStyle = cell.getCellStyle().getBorderLeft();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return isStyleBordered(borderStyle);
    }

    /**
     * 罫線が設定されているか判定します。 罫線が設定されている場合は{@code true}を返却し、
     * 罫線が設定されていない場合は{@code false}を返却します。
     *
     * @param borderStyle スタイルオブジェクト
     * @return 罫線が設定されている場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private static boolean isStyleBordered(@NonNull final BorderStyle borderStyle) {

        if (borderStyle == BorderStyle.THIN || borderStyle == BorderStyle.MEDIUM || borderStyle == BorderStyle.DASHED
                || borderStyle == BorderStyle.DOTTED || borderStyle == BorderStyle.THICK
                || borderStyle == BorderStyle.DOUBLE || borderStyle == BorderStyle.HAIR
                || borderStyle == BorderStyle.MEDIUM_DASHED || borderStyle == BorderStyle.DASH_DOT
                || borderStyle == BorderStyle.MEDIUM_DASH_DOT || borderStyle == BorderStyle.DASH_DOT_DOT
                || borderStyle == BorderStyle.MEDIUM_DASH_DOT_DOT || borderStyle == BorderStyle.SLANTED_DASH_DOT) {
            return true;
        }

        return false;
    }

    /**
     * セルの書式設定を判定します。セルの書式がブランクの場合は{@code true}を返却し、セルの書式がブランク以外の場合は{@code false}を返却します。
     *
     * @param cell セルオブジェクト
     * @return セルの書式がブランクの場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean isBlank(@NonNull final Cell cell) {
        return cell.getCellType() == CellType.BLANK;
    }

    /**
     * セルの書式設定を判定します。 セルの書式が数値の場合は{@code true}を返却し、
     * セルの書式が数値以外の場合は{@code false}を返却します。
     *
     * @param cell セルオブジェクト
     * @return セルの書式が数値の場合は{@code true}、それ以外は{@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean isNumeric(@NonNull final Cell cell) {
        return cell.getCellType() == CellType.NUMERIC;
    }
}