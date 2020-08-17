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

package org.thinkit.common.util.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * このクラスは {@link FluentReflection} クラスをテストする {@link FluentReflectionTest}
 * クラスで使用するテスト用データセットを定義するクラスです。
 * <p>
 * このクラスは {@link FluentReflectionTest} クラスでのみ使用することを想定しています。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ReflectionTestDataSet {

    /**
     * 引数なしで文字列型として <code>"success"</code> を返却するメソッドです。
     *
     * @return <code>"success"</code>
     */
    @SuppressWarnings("unused")
    private String returnStringWithNoArgument() {
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
    private String returnStringWithArgument(String arg) {
        return arg.isEmpty() ? "failure" : "success";
    }

    /**
     * 複数の引数を受け文字列型として <code>"success"</code> を返却するメソッドです。
     *
     * @return <code>"success"</code>
     */
    @SuppressWarnings("unused")
    private String returnStringWithArguments(String arg1, int arg2, boolean arg3) {
        return "success";
    }

    /**
     * 引数なしで数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>1</code>
     */
    @SuppressWarnings("unused")
    private int returnIntegerWithNoArgument() {
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
    private int returnIntegerWithArgument(boolean arg) {
        return arg ? 1 : 0;
    }

    /**
     * 複数の引数を受け数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>1</code>
     */
    @SuppressWarnings("unused")
    private int returnIntegerWithArguments(int arg1, String arg2, boolean arg3) {
        return 1;
    }

    /**
     * 引数なしで真偽値として <code>true</code> を返却するメソッドです。
     *
     * @return <code>true</code>
     */
    @SuppressWarnings("unused")
    private boolean returnBooleanWithNoArgument() {
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
    private boolean returnBooleanWithArgument(int arg) {
        return arg == 1;
    }

    /**
     * 複数の引数を受け数値型として <code>1</code> を返却するメソッドです。
     *
     * @return <code>true</code>
     */
    @SuppressWarnings("unused")
    private boolean returnBooleanWithArguments(int arg1, String arg2, boolean arg3) {
        return true;
    }

    /**
     * 渡された複数の引数を格納したリストを返却します。
     *
     * @return 渡された全引数を格納したリスト
     */
    @SuppressWarnings("unused")
    private List<String> returnListWithArguments(String arg1, String arg2, String arg3) {
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
    private Map<String, Integer> returnMapWithArguments(int arg1, int arg2, int arg3) {
        final Map<String, Integer> result = new HashMap<>(3);
        result.put("result1", arg1);
        result.put("result2", arg2);
        result.put("result3", arg3);
        return result;
    }
}