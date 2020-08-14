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

package org.thinkit.common.command;

/**
 * コマンドを抽象化したインターフェースです。
 *
 * @param <R> コマンドの返却型
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Command<R> {

    /**
     * コマンドを実行します。 {@link Command#execute()} メソッドの返却値は {@link Command}
     * インターフェースを実装する際に指定した総称型の型として返却します。
     *
     * @return {@link Command} インターフェースを実装する際に指定した総称型を型に持つオブジェクト
     */
    public R execute();
}