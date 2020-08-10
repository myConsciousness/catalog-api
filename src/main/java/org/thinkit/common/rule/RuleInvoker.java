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

package org.thinkit.common.rule;

import org.thinkit.common.Invokable;

import lombok.NonNull;

/**
 * {@link Rule} インターフェースを実装した具象ルールクラスの安全な呼び出しを行うルール起動クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class RuleInvoker<R> implements Invokable<R> {

    /**
     * ルール
     */
    private Rule<R> rule;

    /**
     * デフォルトコンストラクタ
     */
    private RuleInvoker() {
    }

    /**
     * コンストラクタ
     *
     * @param rule ルール
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private RuleInvoker(@NonNull Rule<R> rule) {
        this.rule = rule;
    }

    /**
     * 引数として渡された {@code rule} オブジェクトを基に {@link RuleInvoker} クラスの新しいインスタンスを生成し返却します。
     *
     * @param rule ルール
     * @return {@link RuleInvoker} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static <R> RuleInvoker<R> of(@NonNull Rule<R> rule) {
        return new RuleInvoker<>(rule);
    }

    @Override
    public R invoke() {
        try {
            return rule.execute();
        } catch (Exception e) {
            throw new RuleHandlingException(e);
        }
    }
}