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

import lombok.Getter;
import lombok.NonNull;

/**
 * 引数情報を管理するクラスです。<br>
 * 引数の型と値をセットで管理します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
final class Parameter {

    /**
     * 引数の型
     */
    private Class<?> type;

    /**
     * 引数の値
     */
    private Object value;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Parameter() {
    }

    /**
     * コンストラクタ
     *
     * @param type  引数の型
     * @param value 引数の値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Parameter(@NonNull Class<?> type, @NonNull Object value) {
        this.type = type;
        this.value = value;
    }
}