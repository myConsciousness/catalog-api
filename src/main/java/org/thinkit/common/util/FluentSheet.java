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

package org.thinkit.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.thinkit.common.catalog.MatrixQueue;
import org.thinkit.common.exception.ExcelHandlingException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
final class FluentSheet {

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
     * @exception IllegalArgumentException 引数として指定されたSheetオブジェクトがnullの場合
     */
    public FluentSheet(Sheet sheet) {

        if (sheet == null) {
            throw new IllegalArgumentException("wrong parameter was given. Sheet object is null.");
        }

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

                if (this.isCellNumeric(cell)) {
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
     * @exception IllegalArgumentException 引数として指定された文字列がnullまたは空文字列の場合
     */
    public boolean hasValue(final String value) {

        if (StringUtils.isEmpty(value)) {
            throw new ExcelHandlingException("wrong parameter was given. String is null or empty.");
        }

        for (Row row : this.sheet) {
            for (Cell cell : row) {
                if (this.isCellNumeric(cell)) {
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
     * @return 検索対象の文字列が含まれる一番始めのセルの行列インデックス。 検索対象の文字列が存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 引数で指定された検索対象の文字列がnullの場合
     */
    public EnumMap<MatrixQueue, Integer> findCellIndex(final String sequence) {

        if (sequence == null) {
            throw new IllegalArgumentException("wrong parameter was given. String is null.");
        }

        final EnumMap<MatrixQueue, Integer> cellIndexes = new EnumMap<>(MatrixQueue.class);

        for (Row row : this.sheet) {
            for (Cell cell : row) {
                if (this.isCellNumeric(cell)) {
                    final String cellValue = String.valueOf(cell.getNumericCellValue());

                    if (sequence.equals(cellValue)) {
                        cellIndexes.put(MatrixQueue.COLUMN, cell.getColumnIndex());
                        cellIndexes.put(MatrixQueue.ROW, cell.getRowIndex());
                        return cellIndexes;
                    }
                } else {
                    final String cellValue = cell.getRichStringCellValue().getString().trim();

                    if (sequence.equals(cellValue)) {
                        cellIndexes.put(MatrixQueue.COLUMN, cell.getColumnIndex());
                        cellIndexes.put(MatrixQueue.ROW, cell.getRowIndex());
                        return cellIndexes;
                    }
                }
            }
        }

        return cellIndexes;
    }

    /**
     * 引数として指定された文字列と一番初めに合致するセルの行インデックスを取得し返却します。
     * 引数として指定された検索対象の文字列がnullの場合は実行時に必ず失敗します。
     *
     * @param sequence 検索対象の文字列
     * @return 検索対象の文字列が含まれる一番始めのセルの行インデックス。 検索対象の文字列が存在しない場合は-1を返却します。
     * @exception IllegalArgumentException 引数で指定された検索対象の文字列がnullの場合
     */
    public int findRowIndex(final String sequence) {

        if (sequence == null) {
            throw new IllegalArgumentException("wrong parameter was given. String is null.");
        }

        for (Row row : this.sheet) {
            for (Cell cell : row) {
                if (this.isCellNumeric(cell)) {
                    final String cellValue = String.valueOf(cell.getNumericCellValue());
                    if (sequence.equals(cellValue)) {
                        cell.getRowIndex();
                    }
                } else {
                    final String cellValue = cell.getRichStringCellValue().getString().trim();
                    if (sequence.equals(cellValue)) {
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
     * @exception IllegalArgumentException 引数で指定された検索対象の文字列がnullの場合
     */
    public int findColumnIndex(final String sequence) {

        if (sequence == null) {
            throw new IllegalArgumentException("wrong parameter was given. String is null.");
        }

        for (Row row : this.sheet) {
            for (Cell cell : row) {
                if (this.isCellNumeric(cell)) {
                    final String cellValue = String.valueOf(cell.getNumericCellValue());
                    if (sequence.equals(cellValue)) {
                        return cell.getColumnIndex();
                    }
                } else {
                    final String cellValue = cell.getRichStringCellValue().getString().trim();
                    if (sequence.equals(cellValue)) {
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
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     */
    public EnumMap<MatrixQueue, Integer> findBorderTopIndex() {
        return this.findBorderTopIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから上部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderTopIndex(final int startColumnIndex) {

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
     * @return 上部に罫線が設定されているセルの行列インデックス。 上部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderTopIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final EnumMap<MatrixQueue, Integer> borderedIndexes = new EnumMap<>(MatrixQueue.class);

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = this.sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && this.isCellBorderedTop(cell)) {
                    borderedIndexes.put(MatrixQueue.COLUMN, columnIndex);
                    borderedIndexes.put(MatrixQueue.ROW, rowIndex);
                    return borderedIndexes;
                }
            }
        }

        return borderedIndexes;
    }

    /**
     * 下部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     */
    public EnumMap<MatrixQueue, Integer> findBorderBottomIndex() {
        return this.findBorderBottomIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから下部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderBottomIndex(final int startColumnIndex) {

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
     * @return 下部に罫線が設定されているセルの行列インデックス。 下部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see EMatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderBottomIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final EnumMap<MatrixQueue, Integer> borderedIndexes = new EnumMap<>(MatrixQueue.class);

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = this.sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && this.isCellBorderedBottom(cell)) {
                    borderedIndexes.put(MatrixQueue.COLUMN, columnIndex);
                    borderedIndexes.put(MatrixQueue.ROW, rowIndex);
                    return borderedIndexes;
                }
            }
        }

        return borderedIndexes;
    }

    /**
     * 右部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     */
    public EnumMap<MatrixQueue, Integer> findBorderRightIndex() {
        return this.findBorderRightIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから右部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderRightIndex(final int startColumnIndex) {

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
     * @return 右部に罫線が設定されているセルの行列インデックス。 右部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderRightIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final EnumMap<MatrixQueue, Integer> borderedIndexes = new EnumMap<>(MatrixQueue.class);

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = this.sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && this.isCellBorderedRight(cell)) {
                    borderedIndexes.put(MatrixQueue.COLUMN, columnIndex);
                    borderedIndexes.put(MatrixQueue.ROW, rowIndex);
                    return borderedIndexes;
                }
            }
        }

        return borderedIndexes;
    }

    /**
     * 左部に罫線が設定されているセルの行列インデックスを取得し返却します。
     *
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     */
    public EnumMap<MatrixQueue, Integer> findBorderLeftIndex() {
        return this.findBorderLeftIndex(0, 0);
    }

    /**
     * 引数として指定された列インデックスから左部に罫線が設定されているセルの行列インデックスを取得し返却します。
     * 引数として指定された列インデックスが負数の場合は実行時に必ず失敗します。
     *
     * @param startColumnIndex 探索開始列インデックス
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderLeftIndex(final int startColumnIndex) {

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
     * @return 左部に罫線が設定されているセルの行列インデックス。 左部に罫線が設定されているセルが存在しない場合は空のマップを返却します。
     * @see MatrixQueue
     * @exception IllegalArgumentException 探索開始列インデックスが負数の場合、または探索開始行インデックスが負数の場合
     */
    public EnumMap<MatrixQueue, Integer> findBorderLeftIndex(final int startColumnIndex, final int startRowIndex) {

        if (startColumnIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Column index must be positive.", startColumnIndex));
        }

        if (startRowIndex < 0) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Row index must be positive.", startRowIndex));
        }

        final EnumMap<MatrixQueue, Integer> borderedIndexes = new EnumMap<>(MatrixQueue.class);

        for (int rowIndex = startRowIndex, size = sheet.getPhysicalNumberOfRows(); rowIndex < size; rowIndex++) {
            final Row row = this.sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();

                if (startColumnIndex <= columnIndex && this.isCellBorderedLeft(cell)) {
                    borderedIndexes.put(MatrixQueue.COLUMN, columnIndex);
                    borderedIndexes.put(MatrixQueue.ROW, rowIndex);
                    return borderedIndexes;
                }
            }
        }

        return borderedIndexes;
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

        final EnumMap<MatrixQueue, Integer> startBorder = this.findBorderLeftIndex(baseStartColumnIndex + 1,
                baseStartRowIndex);

        final int startColumnIndex = startBorder.get(MatrixQueue.COLUMN);
        final int startRowIndex = startBorder.get(MatrixQueue.ROW);

        final EnumMap<MatrixQueue, Integer> endBorder = this.findBorderRightIndex(startColumnIndex + 1, startRowIndex);

        final int endColumnIndex = endBorder.get(MatrixQueue.COLUMN);
        final int endRowIndex = endBorder.get(MatrixQueue.ROW);

        for (int rowIndex = startRowIndex; rowIndex <= endRowIndex; rowIndex++) {
            final Row row = this.sheet.getRow(rowIndex);

            for (Cell cell : row) {
                final int columnIndex = cell.getColumnIndex();
                if (startColumnIndex <= columnIndex && columnIndex <= endColumnIndex) {

                    if (this.isCellNumeric(cell)) {
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
                if (!this.isCellBlank(cell)) {
                    if (this.isCellNumeric(cell)) {
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
                    if (this.isCellBorderedRight(cell)) {
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
            if (this.isCellBlank(cell)) {
                continue;
            }

            if (this.isCellNumeric(cell)) {
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
     * セルの上部に罫線が設定されているか判定します。 セルの上部に罫線が設定されている場合は{@code true}を返却し、
     * セルの上部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの上部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     * @exception IllegalArgumentException 引数として指定されたセルオブジェクトがnullの場合
     */
    private boolean isCellBorderedTop(final Cell cell) {

        if (cell == null) {
            throw new IllegalArgumentException("wrong parameter was given. Cell object is null.");
        }

        final BorderStyle borderStyle = cell.getCellStyle().getBorderTop();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return this.isStyleBordered(borderStyle);
    }

    /**
     * セルの下部に罫線が設定されているか判定します。 セルの下部に罫線が設定されている場合は{@code true}を返却し、
     * セルの下部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの下部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     * @exception IllegalArgumentException 引数として指定されたセルオブジェクトがnullの場合
     */
    private boolean isCellBorderedBottom(final Cell cell) {

        if (cell == null) {
            throw new IllegalArgumentException("wrong parameter was given. Cell object is null.");
        }

        final BorderStyle borderStyle = cell.getCellStyle().getBorderBottom();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return this.isStyleBordered(borderStyle);
    }

    /**
     * セルの右部に罫線が設定されているか判定します。 セルの右部に罫線が設定されている場合は{@code true}を返却し、
     * セルの右部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの右部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     * @exception IllegalArgumentException 引数として指定されたセルオブジェクトがnullの場合
     */
    private boolean isCellBorderedRight(final Cell cell) {

        if (cell == null) {
            throw new IllegalArgumentException("wrong parameter was given. Cell object is null.");
        }

        final BorderStyle borderStyle = cell.getCellStyle().getBorderRight();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return this.isStyleBordered(borderStyle);
    }

    /**
     * セルの左部に罫線が設定されているか判定します。 セルの左部に罫線が設定されている場合は{@code true}を返却し、
     * セルの左部に罫線が設定されていない場合は{@code false}を返却します。
     * 引数として指定されたセルオブジェクトがnullの場合は実行時に必ず失敗します。
     *
     * @param cell 判定対象のセルオブジェクト
     * @return セルの左部に罫線が設定されている場合は{@code true}、それ以外は{@code false}
     * @exception IllegalArgumentException 引数として指定されたセルオブジェクトがnullの場合
     */
    private boolean isCellBorderedLeft(final Cell cell) {

        if (cell == null) {
            throw new IllegalArgumentException("wrong parameter was given. Cell object is null.");
        }

        final BorderStyle borderStyle = cell.getCellStyle().getBorderLeft();

        if (borderStyle == BorderStyle.NONE) {
            return false;
        }

        return this.isStyleBordered(borderStyle);
    }

    /**
     * 罫線が設定されているか判定します。 罫線が設定されている場合は{@code true}を返却し、
     * 罫線が設定されていない場合は{@code false}を返却します。
     *
     * @param borderStyle スタイルオブジェクト
     * @return 罫線が設定されている場合は{@code true}、それ以外は{@code false}
     */
    private boolean isStyleBordered(final BorderStyle borderStyle) {

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
     */
    private boolean isCellBlank(final Cell cell) {
        return cell.getCellType() == CellType.BLANK;
    }

    /**
     * セルの書式設定を判定します。 セルの書式が数値の場合は{@code true}を返却し、
     * セルの書式が数値以外の場合は{@code false}を返却します。
     *
     * @param cell セルオブジェクト
     * @return セルの書式が数値の場合は{@code true}、それ以外は{@code false}
     */
    private boolean isCellNumeric(final Cell cell) {
        return cell.getCellType() == CellType.NUMERIC;
    }

    /**
     * 当該シートオブジェクトに含まれる全てのセルの値を文字列型のリスト形式で取得し返却します。
     *
     * @return 全てのセルの値を格納した文字列型のリスト
     */
    public List<List<String>> toStringList() {

        final List<List<String>> stringList = new ArrayList<>(this.sheet.getPhysicalNumberOfRows());

        for (Row row : this.sheet) {
            final List<String> rowList = new ArrayList<>(row.getPhysicalNumberOfCells());

            for (Cell cell : row) {
                if (this.isCellNumeric(cell)) {
                    rowList.add(String.valueOf(cell.getNumericCellValue()));
                } else {
                    rowList.add(cell.getRichStringCellValue().getString().trim());
                }
            }

            stringList.add(rowList);
        }

        return stringList;
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
}