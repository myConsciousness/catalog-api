/**
 * Project Name : dev-utils<br>
 * File Name : FileWriter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/18<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * ファイルへの書き込み処理に関する汎用的な処理を定義したクラスです。
 *
 * <pre>
 * 使用例:
 * <code>FileWriter.of(output).write(fileName, extension, content);</code>
 * </pre>
 *
 * <pre>
 * 書き込み時の文字コードを変更した使用例:
 * <code>FileWriter.of(output).toCharsetUsAscii().write(fileName, extension, content);</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #write(String, String, String)
 */
@ToString
@EqualsAndHashCode
public final class FileWriter {

    /**
     * 出力先
     */
    @Getter(AccessLevel.PRIVATE)
    private String output;

    /**
     * ファイル書き込み時の文字コード
     */
    @Getter(AccessLevel.PRIVATE)
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * デフォルトコンストラクタ
     */
    private FileWriter() {
    }

    /**
     * コンストラクタ。 指定された出力先のディレクトリが存在しない場合は生成します。
     *
     * @param output 出力先のパス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws FileHandlingException ディレクトリの生成処理が異常終了した場合
     */
    private FileWriter(@NonNull String output) {
        this.output = output;

        if (!FluentFile.mkdirs(output)) {
            throw new FileHandlingException(String
                    .format("Failed to generate the directory of %s. Please pass a valid directory path.", output));
        }
    }

    /**
     * {@link FileWriter} クラスのインスタンスを生成し返却します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param output 出力先のファイルパス
     * @return {@link FileWriter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static FileWriter of(@NonNull String output) {
        return new FileWriter(output);
    }

    /**
     * ファイルへの書き込み処理を行います。 出力先は {@link FileWriter} のインスタンス生成時に渡した文字列に
     * {@link #write(String, String, String)} の第1引数として渡した文字列を結合したものになります。
     * <p>
     * 書き込み時のデフォルト文字コードは {@code StandardCharsets.UTF_8} です。 書き込み時の文字コードを変更したい場合は
     * {@link #write(String, String, String)} メソッドを呼び出す前に 文字コードを変更する
     * {@link #toCharsetIso()} や {@link #toCharsetUsAscii()} を呼び出してください。
     * 文字コードを変更するメソッドに関しては以下の参照を確認してください。
     * <p>
     * 書き込み処理で {@link IOException} が発生した場合は {@code false} を返却します。
     *
     * <pre>
     * 使用例:
     * <code>FileWriter.of(output).write(fileName, extension, content);</code>
     * </pre>
     *
     * <pre>
     * 文字コードを変更した使用例:
     * <code>FileWriter.of(output).toCharsetUsAscii().write(fileName, extension, content);</code>
     * </pre>
     *
     * @param fileName  ファイル名
     * @param extension 拡張子
     * @param content   書き込む内容
     * @return 書き込み処理が正常終了した場合は {@code true}、{@link IOException} が発生した場合は
     *         {@code false}
     *
     * @exception IOException          出力処理が異常終了した場合
     * @exception NullPointerException 引数として {@code null} が渡された場合
     *
     * @see #toCharsetIso()
     * @see #toCharsetUsAscii()
     * @see #toCharsetUtf16()
     * @see #toCharsetUtf16Be()
     * @see #toCharsetUtf16Le()
     * @see #toCharsetUtf8()
     */
    public boolean write(@NonNull String fileName, @NonNull String extension, @NonNull String content) {

        final StringBuilder fullFileNameBuilder = new StringBuilder().append(fileName).append(extension);
        final String fullFileName = fullFileNameBuilder.toString();

        final StringBuilder filePathBuilder = new StringBuilder();
        filePathBuilder.append(this.getOutput()).append("\\").append(fullFileName);

        final File file = new File(new File(filePathBuilder.toString()).getParentFile(), fullFileName);

        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), this.getCharset())))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.US_ASCII} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetUsAscii() {
        this.charset = StandardCharsets.US_ASCII;
        return this;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.ISO_8859_1} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetIso() {
        this.charset = StandardCharsets.ISO_8859_1;
        return this;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.UTF_8} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetUtf8() {
        this.charset = StandardCharsets.UTF_8;
        return this;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.UTF_16BE} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetUtf16Be() {
        this.charset = StandardCharsets.UTF_16BE;
        return this;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.UTF_16LE} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetUtf16Le() {
        this.charset = StandardCharsets.UTF_16LE;
        return this;
    }

    /**
     * 書き込み処理時の文字コードを {@code StandardCharsets.UTF_16} に変更します。
     * このメソッドは自分自身のインスタンスを返却するためメソッドチェーンで後続処理を行うことができます。
     *
     * @return 自分自身のインスタンス
     */
    public FileWriter toCharsetUtf16() {
        this.charset = StandardCharsets.UTF_16;
        return this;
    }
}