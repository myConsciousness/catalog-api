/**
 * Project Name : dev-utils<br>
 * File Name : FileHandler.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.thinkit.common.catalog.Delimiter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * ファイル操作に関する汎用的な処理を定義したクラスです。<br>
 * {@link FileHandler}では以下の操作を提供しています。<br>
 * <br>
 * {@link #write(String, String, String)}
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class FileHandler {

    /**
     * プラットフォームに対応したファイルの区切り文字列
     */
    private static String FILE_SEPARATOR;

    /**
     * プラットフォームに対応した改行文字列
     */
    private static String NEW_LINE;

    static {
        try {
            FILE_SEPARATOR = File.separator;
        } catch (SecurityException e) {
            FILE_SEPARATOR = "¥¥";
        }

        try {
            NEW_LINE = System.lineSeparator();
        } catch (SecurityException e) {
            NEW_LINE = "¥r¥n";
        }
    }

    /**
     * 出力先
     */
    @Setter
    private String output = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private FileHandler() {
    }

    /**
     * コンストラクタ
     * 
     * @param output 出力先
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public FileHandler(@NonNull String output) {
        this.output = output;
    }

    /**
     * ファイルへの書き込み処理を行います。<br>
     * 出力先は{@link FileHandler}のインスタンス生成時に渡した文字列に<br>
     * {@link #write(String, String, String)}の第１引数として渡した文字列を結合したものになります。<br>
     * 出力先を変更した場合は{@link #setOutput(String)}を使用してください。 <br>
     * <br>
     * 書き込み処理で{@link IOException}が発生した場合は{@code false}を返却します。
     * 
     * @param fileName  ファイル名
     * @param extension 拡張子
     * @param content   書き込む内容
     * @return 書き込み処理が正常終了した場合は{@code true}、{@link IOException}が発生した場合は{@code false}
     * 
     * @exception IOException 出力処理が異常終了した場合
     */
    public boolean write(@NonNull String fileName, @NonNull String extension, @NonNull String content) {

        final StringBuilder fullFileNameBuilder = new StringBuilder();
        fullFileNameBuilder.append(fileName).append(Delimiter.commma()).append(extension);
        final String fullFileName = fullFileNameBuilder.toString();

        final StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(output).append("\\").append(fullFileName.toString());

        final File file = new File(new File(filePathBuilder.toString()).getParentFile(), fullFileName.toString());

        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
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