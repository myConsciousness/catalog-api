/**
 * Project Name : dev-utils<br>
 * File Name : ReflectionStaticTestDataSet.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/10<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * このクラスは {@link FluentReflection} クラスをテストする {@link FluentReflectionTest}
 * クラスで使用するテスト用データセットを定義するクラスです。
 * <p>
 * このクラスでは静的メソッドのテスト用データセットを定義します。また、このクラスは {@link FluentReflectionTest}
 * クラスでのみ使用することを想定しています。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ReflectionStaticTestDataSet {

    /**
     * 引数なしで文字列型として <code>"success"</code> を返却するメソッドです。
     *
     * @return <code>"success"</code>
     */
    @SuppressWarnings("unused")
    private static String returnStringWithNoArgument() {
        return "success";
    }

    /**
     * 引数を受け文字列型として <code>"success"</code> を返却するメソッドです。<br>
     * 渡された文字列の値が空文字列の場合は <code>"failure"</code> を返却します。
     *
     * @return 引数として渡された文字列の値が空文字列の場合は <code>"failure"</code> 、それ以外は
     *         <code>"success"</code>
     */
    @SuppressWarnings("unused")
    private static String returnStringWithArgument(String arg) {
        return arg.isEmpty() ? "failure" : "success";
    }

    /**
     * 複数の引数を受け文字列型として <code>"success"</code> を返却するメソッドです。
     *
     * @return <code>"success"</code>
     */
    @SuppressWarnings("unused")
    private static String returnStringWithArguments(String arg1, int arg2, boolean arg3) {
        return "success";
    }

    /**
     * 引数なしで数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>1</code>
     */
    @SuppressWarnings("unused")
    private static int returnIntegerWithNoArgument() {
        return 1;
    }

    /**
     * 引数を受け数値型として <code>1</code> を返却するメソッドです。<br>
     * 渡された真偽値の値が <code>false</code> の場合は <code>0</code> を返却します。
     *
     * @return 渡された真偽値の値が <code>false</code> の場合は <code>0</code> 、それ以外は
     *         <code>1</code>
     */
    @SuppressWarnings("unused")
    private static int returnIntegerWithArgument(boolean arg) {
        return arg ? 1 : 0;
    }

    /**
     * 複数の引数を受け数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>1</code>
     */
    @SuppressWarnings("unused")
    private static int returnIntegerWithArguments(int arg1, String arg2, boolean arg3) {
        return 1;
    }

    /**
     * 引数なしで真偽値として <code>true</code> を返却するメソッドです。
     *
     * @return <code>true</code>
     */
    @SuppressWarnings("unused")
    private static boolean returnBooleanWithNoArgument() {
        return true;
    }

    /**
     * 引数を受け真偽値として <code>true</code> を返却するメソッドです。<br>
     * 渡された数値が <code>1</code> 以外の場合は <code>false</code> を返却します。
     *
     * @return 渡された数値が <code>1</code> の場合は <code>true</code> 、それ以外は
     *         <code>false</code>
     */
    @SuppressWarnings("unused")
    private static boolean returnBooleanWithArgument(int arg) {
        return arg == 1;
    }

    /**
     * 複数の引数を受け数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>true</code>
     */
    @SuppressWarnings("unused")
    private static boolean returnBooleanWithArguments(int arg1, String arg2, boolean arg3) {
        return true;
    }

    /**
     * 渡された複数の引数を格納したリストを返却します。
     *
     * @return 渡された全引数を格納したリスト
     */
    @SuppressWarnings("unused")
    private static List<String> returnListWithArguments(String arg1, String arg2, String arg3) {
        final List<String> result = new ArrayList<>(3);
        result.add(arg1);
        result.add(arg2);
        result.add(arg3);
        return result;
    }

    /**
     * 渡された複数の引数を格納したマップを返却します。<br>
     * マップに格納された値の各キーは <code>n = 1</code> から <code>"result" + n</code>
     * <code>"result3"/code> まで設定されます。
     *
     * @return 渡された全引数を格納したマップ
     */
    @SuppressWarnings("unused")
    private static Map<String, Integer> returnMapWithArguments(int arg1, int arg2, int arg3) {
        final Map<String, Integer> result = new HashMap<>(3);
        result.put("result1", arg1);
        result.put("result2", arg2);
        result.put("result3", arg3);
        return result;
    }
}