/**
 * Project Name : dev-utils<br>
 * File Name : FluentSheet.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/12<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util.workbook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Sheetオブジェクトに関する汎用的な操作を行う機能を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class FluentSheet {

    /**
     * シートオブジェクト
     */
    @Getter
    private Sheet sheet = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private FluentSheet() {
    }

    /**
     * 引数として渡されたシートオブジェクトを基に初期化を行うコンストラクタです。
     * 引数として指定されたSheetオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param sheet シートオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public FluentSheet(@NonNull Sheet sheet) {
        this.sheet = sheet;
    }

    /**
     * 引数として指定された列番号と行番号を基にセルの値を取得し返却します。
     *
     * @param columnIndex 列番号
     * @param rowIndex    行番号
     * @return セルの値
     * @exception 引数として指定された列番号が負数の場合、または行番号が負数の場合
     */
    public String get(final int columnIndex, final int rowIndex) {

        if (columnIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Column index must be positive.");
        }

        if (rowIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Row index must be positive.");
        }

        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            final int _rowIndex = row.getRowNum();
            if (rowIndex != _rowIndex) {
                continue;
            }

            for (Cell cell : row) {
                final int _columnIndex = cell.getColumnIndex();
                if (columnIndex != _columnIndex) {
                    continue;
                }

                if (FluentCell.isNumeric(cell)) {
                    return String.valueOf(cell.getNumericCellValue());
                }

                return cell.getRichStringCellValue().getString().trim();
            }
        }

        throw new ExcelHandlingException(String
                .format("indicated cell (column index = %s, row index = %s) does not exist.", columnIndex, rowIndex));
    }

    /**
     * 引数として指定された列番号と行番号を基にセルへ値を代入します。 引数として指定された列番号が負数の場合は実行時に必ず失敗します。
     * 引数として指定された行番号が負数の場合は実行時に必ず失敗します。
     *
     * @param columnIndex 列番号
     * @param rowIndex    行番号
     * @param cellValue   代入する値
     */
    public void put(final int columnIndex, final int rowIndex, final Object cellValue) {

        if (columnIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Column index must be positive.");
        }

        if (rowIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Row index must be positive.");
        }

        final Row row = this.getNewRow(rowIndex);
        final Cell cell = this.getNewCell(row, columnIndex);

        if (cellValue instanceof Boolean) {
            cell.setCellValue((Boolean) cellValue);
        } else if (cellValue instanceof Calendar) {
            cell.setCellValue((Calendar) cellValue);
        } else if (cellValue instanceof java.util.Date) {
            cell.setCellValue((Date) cellValue);
        } else if (cellValue instanceof Double) {
            cell.setCellValue((Double) cellValue);
        } else if (cellValue instanceof RichTextString) {
            cell.setCellValue((RichTextString) cellValue);
        } else if (cellValue instanceof String) {
            cell.setCellValue((String) cellValue);
        } else {
            cell.setCellValue(String.valueOf(cellValue));
        }
    }

    /**
     * シート中に引数として渡された文字列が存在するか判定します。 シート中に指定された文字列が存在する場合は{@code true}を返却し、
     * シート中に指定された文字列が存在しない場合は{@code false}を返却します。
     *
     * @param value 検査対象の文字列
     * @return シート中に指定された文字列が存在する場合は{@code true}、それ以外は{@code false}
     *
     * @exception IllegalArgumentException 引数として指定された文字列が {@code null} または空文字列の場合
     */
    public boolean hasValue(final String value) {

        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
        }

        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (FluentCell.isNumeric(cell)) {
                    final String cellValue = String.valueOf(cell.getNumericCellValue());
                    if (value.equals(cellValue)) {
                        return true;
                    }
                } else {
                    final String cellValue = cell.getRichStringCellValue().getString().trim();
                    if (value.equals(cellValue)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 引数として指定された文字列と一番初めに合致するセルの行列インデックスを取得し返却します。
     * 引数として指定された検索対象の文字列がnullの場合は実行時に必ず失敗します。
     *
     * @param sequence 検索対象の文字列
     * @return 検索対象の文字列が含まれる一番始めのセルの行列インデックス。 検索対象の文字列が存在しない場合は {@code null} を返却します。
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Matrix findCellIndex(@NonNull final String sequence) {

        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (FluentCell.isNumeric(cell)) {
                    if (sequence.equals(String.valueOf(cell.getNumericCellValue()))) {
                        return Matrix.of(cell.getColumnIndex(), cell.getRowIndex());
                    }
                } else {
                    if (sequence.equals(cell.getRichStringCellValue().getString().trim())) {
                        return Matrix.of(cell.getColumnIndex(), cell.getRowIndex());
                    }
                }
            }
        }

        return null;
    }

    /**
     * 引数として指定された文字列と一番初めに合致するセルの行インデックスを取得し返却します。
     * 引数として指定された検索対象の文字列がnullの場合は実行時に必ず失敗します。
     *
     * @param sequence 検索対象の文字列
     * @return 検索対象の文字列が含まれる一番始めのセルの行インデックス。 検索対象の文字列が存在しない場合は-1を返却します。
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public int findRowIndex(@NonNull final String sequence) {

        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (FluentCell.isNumeric(cell)) {
                    if (sequence.equals(String.valueOf(cell.getNumericCellValue()))) {
                        cell.getRowIndex();
                    }
                } else {
                    if (sequence.equals(cell.getRichStringCellValue().getString().trim())) {
                        cell.getRowIndex();
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 引数として指定された文字列と一番初めに合致するセルの列インデックスを取得し返却します。
     * 引数として指定された検索対象の文字列がnullの場合は実行時に必ず失敗します。
     *
     * @param sequence 検索対象の文字列
     * @return 検索対象の文字列が含まれる一番始めのセルの列インデックス。 検索対象の文字列が存在しない場合は-1を返却します。
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public int findColumnIndex(@NonNull final String sequence) {

        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (FluentCell.isNumeric(cell)) {
                    if (sequence.equals(String.valueOf(cell.getNumericCellValue()))) {
                        return cell.getColumnIndex();
                    }
                } else {
                    if (sequence.equals(cell.getRichStringCellValue().getString().trim())) {
                        return cell.getColumnIndex();
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 上部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     */
    public Matrix findBorderTopIndex() {
        return this.findBorderTopIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから上部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public Matrix findBorderTopIndex(final int startColumnIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        return this.findBorderTopIndex(startColumnIndex, 0);
    }

    /**
     * 引数として指定された行列インデックスから上部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された行列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @param startRowIndex    探索開始行インデックス
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public Matrix findBorderTopIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final Sheet sheet = this.sheet;

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && FluentCell.isBorderedTop(cell)) {
                    return Matrix.of(columnIndex, rowIndex);
                }
            }
        }

        return null;
    }

    /**
     * 下部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     */
    public Matrix findBorderBottomIndex() {
        return this.findBorderBottomIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから下部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public Matrix findBorderBottomIndex(final int startColumnIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        return this.findBorderBottomIndex(startColumnIndex, 0);
    }

    /**
     * 引数として指定された行列インデックスから下部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された行列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @param startRowIndex    探索開始行インデックス
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public Matrix findBorderBottomIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final Sheet sheet = this.sheet;

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && FluentCell.isBorderedBottom(cell)) {
                    return Matrix.of(columnIndex, rowIndex);
                }
            }
        }

        return null;
    }

    /**
     * 右部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     */
    public Matrix findBorderRightIndex() {
        return this.findBorderRightIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから右部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public Matrix findBorderRightIndex(final int startColumnIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        return this.findBorderRightIndex(startColumnIndex, 0);
    }

    /**
     * 引数として指定された行列インデックスから右部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された行列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @param startRowIndex    探索開始行インデックス
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public Matrix findBorderRightIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final Sheet sheet = this.sheet;

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && FluentCell.isBorderedRight(cell)) {
                    return Matrix.of(columnIndex, rowIndex);
                }
            }
        }

        return null;
    }

    /**
     * 左部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     */
    public Matrix findBorderLeftIndex() {
        return this.findBorderLeftIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから左部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は
     *         {@code null] を返却します。
     *
     *         @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public Matrix findBorderLeftIndex(final int startColumnIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        return this.findBorderLeftIndex(startColumnIndex, 0);
    }

    /**
     * 引数として指定された行列インデックスから左部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された行列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @param startRowIndex    探索開始行インデックス
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は {@code null}
     *         を返却します。
     *
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public Matrix findBorderLeftIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final Sheet sheet = this.sheet;

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && FluentCell.isBorderedLeft(cell)) {
                    return Matrix.of(columnIndex, rowIndex);
                }
            }
        }

        return null;
    }

    /**
     * 指定された行列インデックスから探索を開始し、 罫線で囲まれた領域内の値を取得して返却します。
     * 引数として指定された行列インデックスが負数の場合は実行時に必ず失敗します。 罫線内に含まれる値が存在しない場合は必ず空文字列が返却されます。
     *
     * @param baseStartColumnIndex 列の探索開始基準インデックス
     * @param baseStartRowIndex    行の探索開始基準インデックス
     * @return 罫線で囲まれた領域内の値
     * @exception IllegalArgumentException 引数として指定された行列インデックスが負数の場合
     */
    public String getRegionSequence(final int baseStartColumnIndex, final int baseStartRowIndex) {

        if (baseStartColumnIndex < 0) {
            throw new IllegalArgumentException(String
                    .format("wrong parameter (%s) was given. Column index must be positive.", baseStartColumnIndex));
        }

        if (baseStartRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", baseStartRowIndex));
        }

        final Matrix startBorder = this.findBorderLeftIndex(baseStartColumnIndex + 1, baseStartRowIndex);

        final int startColumnIndex = startBorder.getColumn();
        final int startRowIndex = startBorder.getRow();

        final Matrix endBorder = this.findBorderRightIndex(startColumnIndex + 1, startRowIndex);

        final int endColumnIndex = endBorder.getColumn();
        final int endRowIndex = endBorder.getRow();

        final Sheet sheet = this.sheet;

        for (int rowIndex = startRowIndex; rowIndex <= endRowIndex; rowIndex++) {
            final Row row = sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();
                if (startColumnIndex <= columnIndex && columnIndex <= endColumnIndex) {

                    if (FluentCell.isNumeric(cell)) {
                        final String cellValue = String.valueOf(cell.getNumericCellValue());
                        if (!StringUtils.isEmpty(cellValue)) {
                            return cellValue;
                        }
                    } else {
                        final String cellValue = cell.getRichStringCellValue().getString().trim();
                        if (!StringUtils.isEmpty(cellValue)) {
                            return cellValue;
                        }
                    }
                }
            }
        }

        return "";
    }

    /**
     * 指定された行列インデックスからExcelに記述されたマトリクス情報を取得しリスト形式で返却します。
     *
     * @param startColumnIndex 開始開始インデックス
     * @param startRowIndex    開始行インデックス
     * @return マトリクス情報
     * @exception IllegalArgumentException 引数として指定された行列インデックスが負数の場合
     */
    public List<Map<String, String>> getMatrixList(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Column Index must be positive.");
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Row index must be positive.");
        }

        final Sheet sheet = this.sheet;
        final List<String> matrixHeader = this.getMatrixHeader(startColumnIndex, startRowIndex);
        final int rowSize = matrixHeader.size();

        final List<Map<String, String>> matrixList = new ArrayList<>();

        for (int i = startRowIndex + 1, size = sheet.getPhysicalNumberOfRows() + 1; i < size; i++) {
            final Map<String, String> record = new HashMap<>(rowSize);
            final Row row = sheet.getRow(i);

            boolean alreadySet = false;
            int countHeader = 0;

            for (Cell cell : row) {
                if (!FluentCell.isBlank(cell)) {
                    if (FluentCell.isNumeric(cell)) {
                        final String cellValue = String.valueOf(cell.getNumericCellValue());
                        record.put(matrixHeader.get(countHeader), cellValue);
                        alreadySet = true;
                    } else {
                        final String cellValue = cell.getRichStringCellValue().getString().trim();
                        record.put(matrixHeader.get(countHeader), cellValue);
                        alreadySet = true;
                    }

                    countHeader++;
                } else {
                    if (FluentCell.isBorderedRight(cell)) {
                        if (alreadySet) {
                            alreadySet = false;
                        } else {
                            countHeader++;
                        }
                    }
                }
            }

            matrixList.add(record);
        }

        return matrixList;
    }

    /**
     * マトリクスのヘッダー部分を取得しリストとして返却します。
     *
     * @param startColumnIndex 開始列インデックス
     * @param rowIndex         行インデックス
     * @return マトリクスのヘッダー部分
     * @exception IllegalArgumentException 引数として指定された行列インデックスが負数の場合
     */
    private List<String> getMatrixHeader(final int startColumnIndex, final int rowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Column Index must be positive.");
        }

        if (rowIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Row index must be positive.");
        }

        final Sheet sheet = this.sheet;
        final Row headers = sheet.getRow(rowIndex);

        final List<String> matrixHeader = new ArrayList<>();

        for (Cell cell : headers) {
            if (FluentCell.isBlank(cell)) {
                continue;
            }

            if (FluentCell.isNumeric(cell)) {
                final String cellValue = String.valueOf(cell.getNumericCellValue());
                matrixHeader.add(cellValue);
            } else {
                final String cellValue = cell.getRichStringCellValue().getString().trim();
                matrixHeader.add(cellValue);
            }
        }

        return matrixHeader;
    }

    /**
     * 引数として指定された行オブジェクトと列番号を基にセルオブジェクトを取得して返却します。
     * 引数として指定された列番号に紐づくセルオブジェクトが存在しない場合は、 指定された列番号に紐づく新しいセルオブジェクトを生成し返却します。
     * 引数として指定された列番号が負数の場合は実行時に必ず失敗します。
     *
     * @param row         行オブジェクト
     * @param columnIndex 列番号
     * @return 列番号に紐づくセルオブジェクト
     * @exception IllegaArgumentException 引数として指定された行オブジェクトがnullの場合、または列番号が負数の場合
     */
    private Cell getNewCell(final Row row, final int columnIndex) {

        if (columnIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Column index must be positive.");
        }

        Cell cell = row.getCell(columnIndex);

        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        return cell;
    }

    /**
     * 引数として指定された行番号を基に行オブジェクトを取得して返却します。 引数として指定された行番号に紐づく行オブジェクトが存在しない場合は、
     * 指定された行番号に紐づく行オブジェクトを新しく生成し返却します。 引数として指定された行番号が負数の場合は実行時に必ず失敗します。
     *
     * @param rowIndex 行番号
     * @return 行番号に紐づく行オブジェクト
     * @exception IllegaArgumentException 引数として指定された行番号が負数の場合
     */
    private Row getNewRow(final int rowIndex) {

        if (rowIndex < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Row index must be positive.");
        }

        Row row = this.sheet.getRow(rowIndex);

        if (null == row) {
            row = this.sheet.createRow(rowIndex);
        }

        return row;
    }

    /**
     * 当該シートオブジェクトに含まれる全てのセルの値を文字列型のリスト形式で取得し返却します。
     *
     * @return 全てのセルの値を格納した文字列型のリスト
     */
    public List<List<String>> toStringList() {

        final List<List<String>> stringList = new ArrayList<>(this.sheet.getPhysicalNumberOfRows());
        final Sheet sheet = this.sheet;

        for (Row row : sheet) {
            final List<String> rowList = new ArrayList<>(row.getPhysicalNumberOfCells());

            for (Cell cell : row) {
                if (FluentCell.isNumeric(cell)) {
                    rowList.add(String.valueOf(cell.getNumericCellValue()));
                } else {
                    rowList.add(cell.getRichStringCellValue().getString().trim());
                }
            }

            stringList.add(rowList);
        }

        return stringList;
    }
}