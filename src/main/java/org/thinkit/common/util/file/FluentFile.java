/**
 * Project Name : dev-utils<br>
 * File Name : FluentFile.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util.file;

import java.io.File;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * ファイル操作に関する汎用的な処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #writerOf(String)
 * @see FileWriter#write(String, String, String)
 */
@ToString
@EqualsAndHashCode
public final class FluentFile {

    /**
     * プラットフォームに対応したファイルの区切り文字列
     */
    private static final String FILE_SEPARATOR = File.separator;

    /**
     * プラットフォームに対応した改行文字列
     */
    private static final String NEW_LINE = System.lineSeparator();

    /**
     * デフォルトコンストラクタ
     */
    private FluentFile() {
    }

    /**
     * 引数として指定されたパスをもとにファイルへ書き込みを行う {@link FileWriter} クラスのインスタンスを生成して返却します。
     * <p>
     * この静的メソッドを呼び出した後にファイルへ書き込み処理を行う際には、{@link #writerOf(String)} メソッドが返却する
     * {@link FileWriter} クラスのインスタンスの
     * {@link FileWriter#write(String, String, String)} メソッドをメソッドチェーンで呼び出してください。
     * <p>
     * {@link FileWriter}
     * クラスのインスタンス生成時に指定されたファイルパスが存在しない場合は自動でファイルパスに対応するディレクトリ構造を生成します。そのため、{@link FileWriter#write(String, String, String)}
     * メソッドを使用する前にファイルパスの存在を検査する必要はありません。但し、渡されたファイルパスが有効なパスではない場合はディレクトリの生成時に失敗し
     * {@link FileHandlingException} が実行時に必ず発生します。
     * <p>
     * 具体的な使用方法は以下のサンプルコードを参考にしてください。
     *
     * <pre>
     * 書き込み処理の例:
     * <code>FluentFile.writerOf(output).write(fileName, extension, content);</code>
     * </pre>
     *
     * @param output 出力先のパス
     * @return {@link FileWriter} クラスのインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws FileHandlingException 有効ではないファイルパスが引数として渡された場合
     */
    public static FileWriter writerOf(@NonNull String output) {
        return FileWriter.of(output);
    }

    /**
     * 引数として指定された {@code filePath} に格納されたファイルパスをもとにディレクトリを生成します。<br>
     * 指定されたファイルパスが既に存在する場合は常に {@code true} を返却します。
     *
     * @param filePath ディレクトリを生成する際に使用するファイルパス
     * @return ディレクトリが既に存在する場合、またはディレクトリの生成が正常終了した場合は {@code true}、それ以外は
     *         {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean mkdirs(@NonNull String filePath) {
        return mkdirs(new File(filePath));
    }

    /**
     * 引数として指定された {@code filePath} に格納されたファイルパスをもとにディレクトリを生成します。<br>
     * 指定されたファイルパスが既に存在する場合は常に {@code true} を返却します。
     *
     * @param filePath ディレクトリを生成する際に使用するファイルパス
     * @return ディレクトリが既に存在する場合、またはディレクトリの生成が正常終了した場合は {@code true}、それ以外は
     *         {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean mkdirs(@NonNull File filePath) {

        if (FileChecker.exists(filePath)) {
            return true;
        }

        return filePath.mkdirs();
    }

    /**
     * プラットフォームに対応したファイルの区切り文字を返却します。
     *
     * @return プラットフォームに対応したファイルの区切り文字
     */
    public static String getFileSeparator() {
        return FILE_SEPARATOR;
    }

    /**
     * プラットフォームに対応した改行コードを返却します。
     *
     * @return プラットフォームに対応した改行コード
     */
    public static String getNewLine() {
        return NEW_LINE;
    }
}