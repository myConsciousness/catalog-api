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
import lombok.Setter;
import lombok.ToString;

/**
 * ファイル操作に関する汎用的な処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
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
     * 出力先
     */
    @Setter
    private String output = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private FluentFile() {
    }

    /**
     * コンストラクタ。<br>
     * 指定された出力先のディレクトリが存在しない場合は生成します。
     *
     * @param output 出力先
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public FluentFile(@NonNull String output) {
        this.output = output;

        final File outputDirectory = new File(output);

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
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