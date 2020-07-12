/**
 * Project Name : dev-utils<br>
 * File Name : FluentWorkbook.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/12<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.thinkit.common.exception.ExcelHandlingException;

import com.google.common.flogger.FluentLogger;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Excelワークブックに対する汎用的な操作を行う機能を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class FluentWorkbook implements AutoCloseable {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 出力ストリーム
     */
    private OutputStream outputStream = null;

    /**
     * 操作対象のExcelワークブックオブジェクト
     */
    private Workbook workbook = null;

    /**
     * Date型オブジェクトを時間に変換して返却するメソッドです。 引数として指定されたDateオブジェクトがnullの場合は必ずnullを返却します。
     *
     * @param date Dateオブジェクト
     * @return Dateオブジェクトから変換された時間
     */
    public static Double timeOnly(final Date date) {

        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        Double time = (double) 0;
        time += (double) 1 / 24 * hour;
        time += (double) 1 / 24 / 60 * minute;

        return time;
    }

    /**
     * 引数として渡されたシート番号を基にシートオブジェクトを取得して返却します。 引数として指定されたシート番号が負数の場合は実行時に必ず失敗します。
     *
     * @param sheetNo シート番号
     * @return 引数として指定されたシート番号に紐づくSheetオブジェクト
     * @exception IllegalArgumentException 指定されたシート番号が負数の場合
     */
    public FluentSheet sheet(final int sheetNo) {

        if (sheetNo > 0) {
            throw new IllegalArgumentException("wrong parameter was given. Sheet number must be positive.");
        }

        return new FluentSheet(this.getSheetAt(sheetNo));
    }

    /**
     * 引数として渡されたシート名を基にシートオブジェクトを取得して返却します。
     * 引数として指定された文字列がnullまたは空文字列の場合は実行時に必ず失敗します。
     *
     * @param sheetName シート名
     * @return 引数として指定されたシート名に紐づくSheetオブジェクト
     * @exception IllegalArgumentException 指定されたシート番号がnullまたは空文字列の場合
     */
    public FluentSheet sheet(final String sheetName) {

        if (StringUtils.isEmpty(sheetName)) {
            throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
        }

        return new FluentSheet(this.getSheet(sheetName));
    }

    /**
     * Excelワークブックオブジェクトのインスタンスを返却します。
     *
     * @return 当該Excelワークブックオブジェクトのインスタンス
     */
    public Workbook book() {
        return this.workbook;
    }

    /**
     * 引数として渡されたシート番号からシートオブジェクトを取得し返却します。 引数として渡されたシート番号が負数の場合、
     * または指定されたシート番号に紐づくシートが存在しない場合は実行に必ず例外が発生します。
     *
     * @param sheetNo シート番号
     * @return 引数として指定されたシート番号に紐づくシートオブジェクト
     * @exception IllegalArgumentException 引数として指定されたシート番号が負数の場合
     * @exception ExcelHandlingException   引数として渡されたシート番号に紐づくシートが存在しない場合
     */
    private Sheet getSheetAt(final int sheetNo) {

        if (sheetNo < 0) {
            throw new IllegalArgumentException("wrong parameter was given. Sheet number must be positive.");
        }

        final Sheet sheet = workbook.getSheetAt(sheetNo);

        if (sheet == null) {
            throw new ExcelHandlingException(String.format("The sheet number (%s) does not exist.", sheetNo));
        }

        return sheet;
    }

    /**
     * 引数として指定されたシート名からシートオブジェクトを取得し返却します。 引数として指定されたシート名に紐づくシートが存在しない場合は、
     * 指定されたシート名で新しいシートを生成し返却します。 引数として指定された文字列が無効の場合は実行時に必ず例外が発生します。
     *
     * @param sheetName シート名
     * @return 引数として指定されたシート名に紐づくシートオブジェクト
     * @exception IllegalArgumentException 引数として渡された文字列がnullまたは空文字列の場合
     */
    private Sheet getSheet(final String sheetName) {

        if (StringUtils.isEmpty(sheetName)) {
            throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
        }

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        return sheet;
    }

    @Override
    public void close() {
        final Workbook workbook = this.workbook;

        if (workbook == null) {
            throw new ExcelHandlingException("unexpected error has occured. Excel workbook object is null.");
        }

        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        if (this.outputStream == null) {
            logger.atWarning().log("The output stream object to be closed is null.");
            return;
        }

        try (final OutputStream outputStream = this.outputStream;) {
            if (workbook instanceof XSSFWorkbook) {
                @SuppressWarnings("resource")
                final SXSSFWorkbook sxss = new SXSSFWorkbook((XSSFWorkbook) workbook);
                sxss.write(outputStream);
            } else {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            logger.atSevere().withCause(e)
                    .log("An exception occurred when accessing information using streams, files and directories.");
        }
    }

    /**
     * ExcelHandlerのインスタンスを生成する処理を定義したビルダークラスです。
     * 操作対象のExcelワークブックオブジェクトと入出力ストリームの設定を行います。
     * 入出力ストリームの設定に関しては、入力ストリームの設定は必須ですが、出力ストリームの設定に関しては任意です。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @ToString
    @EqualsAndHashCode
    public static class Builder {

        /**
         * 出力ストリーム
         */
        private OutputStream outputStream = null;

        /**
         * 操作対象のExcelワークブックオブジェクト
         */
        private Workbook workbook = null;

        /**
         * 引数として渡された文字列を基にExcelワークブックオブジェクトを取得して返却します。
         * 引数として渡される文字列は既に存在してるExcelワークブックを表すものでなければなりません。 無効な引数が渡された場合は実行時に必ず例外が発生します。
         *
         * @param origin 操作するExcelワークブックを表す文字列
         * @return Builderオブジェクト
         * @see #build()
         * @exception IllegalArgumentException 引数の文字列がnullまたは空文字列の場合
         */
        public Builder fromFile(String origin) {

            if (StringUtils.isEmpty(origin)) {
                throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
            }

            try {
                InputStream is = new FileInputStream(origin);
                this.workbook = WorkbookFactory.create(is);
            } catch (FileNotFoundException e) {
                logger.atSevere().withCause(e).log(
                        "The file indicated by the specified path name could not be opened. Check if the file exists.");
            } catch (EncryptedDocumentException e) {
                logger.atSevere().withCause(e).log("A password has been set for the specified Excel file.");
            } catch (IOException e) {
                logger.atSevere().withCause(e)
                        .log("Exception occurred while accessing information using streams, files and directories.");
            }

            return this;
        }

        /**
         * 引数として渡されたクラスパスを基にExcelワークブックオブジェクトを取得して返却します。
         * 引数として渡されるクラスパスは既に存在してるExcelワークブックを表すものでなければなりません。
         * 無効な引数が渡された場合は実行時に必ず例外が発生します。
         *
         * @param origin 操作するExcelワークブックを表すクラスパス
         * @return Builderオブジェクト
         * @see #build()
         * @exception IllegalArgumentException 引数の文字列がnullまたは空文字列の場合
         */
        public Builder fromClassPath(String origin) {

            if (StringUtils.isEmpty(origin)) {
                throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
            }

            final InputStream is = FluentWorkbook.class.getClassLoader().getResourceAsStream(origin);

            try {
                this.workbook = WorkbookFactory.create(is);
            } catch (EncryptedDocumentException e) {
                logger.atSevere().withCause(e).log("A password has been set for the specified Excel file.");
            } catch (IOException e) {
                logger.atSevere().withCause(e)
                        .log("Exception occurred while accessing information using streams, files and directories.");
            }

            return this;
        }

        /**
         * 引数として渡されたExcelワークブックオブジェクトを基に新しいExcelワークブックオブジェクトを取得して返却します。
         * 引数として渡されるExcelワークブックオブジェクトは既に存在してるExcelワークブックを表すものでなければなりません。
         * 無効な引数が渡された場合は実行時に必ず例外が発生します。
         *
         * @param workbook 操作する対象のExcelワークブックオブジェクト
         * @return Builderオブジェクト
         * @see #build()
         * @exception IllegalArgumentException 引数のExcelワークブックオブジェクトがnullの場合
         */
        public Builder fromBook(Workbook workbook) {

            if (workbook == null) {
                throw new IllegalArgumentException("wrong parameter was given. Workbook object is null.");
            }

            this.workbook = workbook;

            return this;
        }

        /**
         * 引数として渡されたクラスパスを基にExcelワークブックオブジェクトを取得して返却します。
         * 引数として渡されるクラスパスは既に存在してるExcelワークブックを表すものでなければなりません。
         * 無効な引数が渡された場合は実行時に必ず例外が発生します。
         *
         * @param clazz  入力ストリームと紐付けるクラス
         * @param origin 操作するExcelワークブックを表すクラスパス
         * @return Builderオブジェクト
         * @see #build()
         * @exception IllegalArgumentException Classオブジェクトがnull、または引数の文字列がnullまたは空文字列の場合
         */
        public Builder fromClassPath(Class<?> clazz, String origin) {

            if (clazz == null) {
                throw new IllegalArgumentException("wrong parameter was given. Class object is null.");
            }

            if (StringUtils.isEmpty(origin)) {
                throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
            }

            final InputStream is = clazz.getClassLoader().getResourceAsStream(origin);

            try {
                this.workbook = WorkbookFactory.create(is);
            } catch (EncryptedDocumentException e) {
                logger.atSevere().withCause(e).log("A password has been set for the specified Excel file.");
            } catch (IOException e) {
                logger.atSevere().withCause(e)
                        .log("Exception occurred while accessing information using streams, files and directories.");
            }

            return this;
        }

        /**
         * 引数として渡された文字列から出力ストリームの設定を行います。 出力ストリームの設定は必須ではありません。
         * 無効な文字列が渡された場合は実行時に必ず例外が発生します。
         *
         * @see #build()
         * @param fileName 出力するファイル名
         * @return Builderオブジェクト
         * @exception IllegalArgumentException 引数の文字列がnullまたは空文字列の場合
         */
        public Builder output(String fileName) {

            if (StringUtils.isEmpty(fileName)) {
                throw new IllegalArgumentException("wrong parameter was given. String is null or empty.");
            }

            try {
                this.outputStream = new FileOutputStream(fileName);
            } catch (FileNotFoundException e) {
                logger.atSevere().withCause(e).log(
                        "The file indicated by the specified path name could not be opened. Check if the file exists.");
            }

            return this;
        }

        /**
         * 出力ストリームオブジェクトから出力設定を行います。 出力ストリームの設定は必須ではありません。
         * 出力ストリームオブジェクトがnullの場合は必ず実行時に例外が発生します。
         *
         * @see #build()
         * @param os 出力ストリームオブジェクト
         * @return Builderオブジェクト
         * @exception IllegalArgumentException 引数の出力ストリームがnullの場合
         */
        public Builder output(OutputStream os) {

            if (os == null) {
                throw new IllegalArgumentException("wrong parameter was given. OutputStream object is null.");
            }

            this.outputStream = os;

            return this;
        }

        /**
         * ExcelHandlerのインスタンスを生成して返却します。 操作対象のExcelワークブックオブジェクトまたは入出力ストリームの設定が終わった後に、
         * 必ず当該メソッドが呼び出される必要があります。
         *
         * @return ExcelHandlerクラスの新しいインスタンス
         */
        public FluentWorkbook build() {
            FluentWorkbook workbook = new FluentWorkbook();
            workbook.workbook = this.workbook;
            workbook.outputStream = this.outputStream;

            return workbook;
        }
    }
}
