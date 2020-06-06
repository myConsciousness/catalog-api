/**
 * Project Name : dev-utils<br>
 * File Name : Indentation.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.catalog;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

/**
 * インデントを管理するカタログです。<br>
 * {@link #getBrace()}を使用することでインデント要素の文字列表現を取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各要素の文字列表現を取得できます。<br>
 * {@link #space()}<br>
 * {@link #tabCode()}<br>
 * {@link #returnCode()}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Indentation implements Catalog<Indentation> {

    /**
     * 半角スペース
     */
    SPACE(0, "&#xA0;"),

    /**
     * 水平タブ
     */
    TAB(1, "&#x08;"),

    /**
     * 改行
     */
    RETURN(2, "&#x0D;&#x0A;");

    /**
     * コード値
     */
    private final int code;

    /**
     * インデント文字列
     */
    private final String character;

    /**
     * 半角空白を返却します。
     * 
     * @return 半角空白
     * @see #SPACE
     */
    public static String space() {
        return SPACE.getCharacter();
    }

    /**
     * タブコードを返却します。
     * 
     * @return タブコード
     * @see #TAB
     */
    public static String tabCode() {
        return TAB.getCharacter();
    }

    /**
     * 改行コードを返却します。
     * 
     * @return 改行コード
     * @see #RETURN
     */
    public static String returnCode() {
        return RETURN.getCharacter();
    }

    /**
     * インデント用の半角空白を返却します。<br>
     * このメソッドからは必ず半角空白4個を結合したインデント用文字列が返却されます。<br>
     * 任意の個数のインデント用文字列を取得したい場合は{@link #getIndentSpaces(int)}を使用してください。
     * 
     * @return 半角空白4個のインデント用文字列
     * @see #getIndentSpaces(int)
     */
    public static String getIndentSpaces() {
        return getIndentSpaces(4);
    }

    /**
     * 引数として指定された個数分の半角空白を返却します。<br>
     * このメソッドからは引数として指定された任意の個数分のインデント用文字列が返却されます。<br>
     * 半角空白4個のインデント用文字列を取得したい場合はこのメソッドを使用しても問題はありませんが、<br>
     * 専用のメソッドを用意しているので{@link #getIndentSpaces()}を使用するようにしてください。
     * 
     * @param number インデント用文字列を生成する際に使用する任意の空白数
     * @return 指定された空白数に対応したインデント用文字列
     * @see #getIndentSpaces()
     */
    public static String getIndentSpaces(int number) {

        final StringBuilder indentSpaces = new StringBuilder(number);
        final String space = space();

        for (int i = 0; i < number; i++) {
            indentSpaces.append(space);
        }

        return indentSpaces.toString();
    }

    /**
     * インデント用のタブコードを返却します。<br>
     * このメソッドからは必ずタブコード1個が返却されます。<br>
     * 任意の個数のインデント用文字列を取得したい場合は{@link #getIndentTabs(int)}を使用してください。
     * 
     * @return タブコード1個のインデント用文字列
     * @see #getIndentTabs(int)
     */
    public static String getIndentTabs() {
        return getIndentTabs(1);
    }

    /**
     * 引数として指定された個数分のタブコードを返却します。<br>
     * このメソッドからは引数として指定された任意の個数分のインデント用文字列が返却されます。<br>
     * タブコード1個のインデント用文字列を取得したい場合はこのメソッドを使用しても問題はありませんが、<br>
     * 専用のメソッドを用意しているので{@link #getIndentTab()}を使用するようにしてください。
     * 
     * @param number インデント用文字列を生成する際に使用する任意のタブコード数
     * @return 指定されたタブコード数に対応したインデント用文字列
     * @see #getIndentTabs()
     */
    public static String getIndentTabs(int number) {

        final StringBuilder indentTabs = new StringBuilder(number);
        final String tabCode = tabCode();

        for (int i = 0; i < number; i++) {
            indentTabs.append(tabCode);
        }

        return indentTabs.toString();
    }
}