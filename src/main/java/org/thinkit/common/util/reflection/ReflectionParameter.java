/**
 * Project Name : dev-utils<br>
 * File Name : ReflectionParameter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util.reflection;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.common.exception.LogicException;

import lombok.NonNull;

/**
 * リフレクション実行時に使用する引数に関する操作を定義したクラスです。<br>
 * インスタンスの生成後に引数情報を設定する機能として {@link ReflectionParameter#add(Class, Object)}
 * メソッドを提供しています。<br>
 * <p>
 * 設定した型を配列型として返却する機能として {@link ReflectionParameter#getTypes()}
 * メソッドを提供しています。<br>
 * 設定した値を配列型として返却する機能として {@link ReflectionParameter#getValues()}
 * メソッドを提供しています。<br>
 * <p>
 * 型情報を配列型として返却する {@link ReflectionParameter#getTypes()} メソッドおよび値情報を配列型として返却する
 * {@link ReflectionParameter#getValues()} メソッドを使用する際には、必ずメソッドの呼び出し前に
 * {@link ReflectionParameter#add(Class, Object)} メソッドを使用して引数情報を設定してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class ReflectionParameter {

    /**
     * リフレクション実行時に使用する引数情報を持つリスト
     */
    private final List<Parameter> parameters = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    public ReflectionParameter() {
    }

    /**
     * リフレクション実行時に使用する引数情報を設定します。<br>
     * リフレクション実行時に指定する引数の型を第1引数に、引数の値を第2引数に指定してください。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * <pre>
     * 使用例:
     * <code>ReflectionParameter parameter = new ReflectionParameter();
     * parameter.add(String.class, "test");
     * parameter.add(Integer.class, 123);
     * </code>
     * </pre>
     *
     * @param argumentType  引数の型
     * @param argumentValue 引数の値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public <T> void add(@NonNull Class<?> argumentType, @NonNull T argumentValue) {
        this.parameters.add(new Parameter(argumentType, argumentValue));
    }

    /**
     * 設定された引数の型を配列型として返却します。<br>
     * このメソッドを実行する際には必ず {@link ReflectionParameter#add(Class, Object)}
     * を実行し引数情報を設定してください。<br>
     * 引数情報を設定しない状態で {@link ReflectionParameter#getTypes()} を呼び出した場合は実行時に必ず失敗します。
     *
     * <pre>
     * 使用例:
     * <code>ReflectionParameter parameter = new ReflectionParameter();
     * parameter.add(String.class, "test");
     * parameter.add(Integer.class, 123);
     * parameter.getTypes();
     * </code>
     * </pre>
     *
     * @return リフレクション時に使用する引数の型を格納した配列
     *
     * @throws LogicException 引数情報を格納したリストが空の場合
     */
    public Class<?>[] getTypes() {

        if (this.parameters.isEmpty()) {
            throw new LogicException("No parameter is set. Parameter is required.");
        }

        final int parameterSize = this.parameters.size();
        final Class<?>[] types = new Class[parameterSize];

        for (int i = 0; i < parameterSize; i++) {
            types[i] = parameters.get(i).getType();
        }

        return types;
    }

    /**
     * 設定された引数の値を配列型として返却します。<br>
     * このメソッドを実行する際には必ず {@link ReflectionParameter#add(Class, Object)}
     * を実行し引数情報を設定してください。<br>
     * 引数情報を設定しない状態で {@link ReflectionParameter#getTypes()} を呼び出した場合は実行時に必ず失敗します。
     *
     * <pre>
     * 使用例:
     * <code>ReflectionParameter parameter = new ReflectionParameter();
     * parameter.add(String.class, "test");
     * parameter.add(Integer.class, 123);
     * parameter.getValues();
     * </code>
     * </pre>
     *
     * @return リフレクション時に使用する引数の値を格納した配列
     *
     * @throws LogicException 引数情報を格納したリストが空の場合
     */
    public Object[] getValues() {

        if (this.parameters.isEmpty()) {
            throw new LogicException("No parameter is set. Parameter is required.");
        }

        final int paramaterSize = this.parameters.size();
        final Object[] values = new Object[paramaterSize];

        for (int i = 0; i < paramaterSize; i++) {
            values[i] = parameters.get(i).getValue();
        }

        return values;
    }

    /**
     * 引数情報が設定されているか判定します。<br>
     * 引数情報が設定されていない場合は {@code true} を返却し、それ以外は {@code false} を返却します。
     *
     * @return 引数情報が設定されていない場合は {@code true} を返却し、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }
}