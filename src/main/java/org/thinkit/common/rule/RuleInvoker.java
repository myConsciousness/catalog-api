/**
 * Project Name : dev-utils<br>
 * File Name : RuleInvoker.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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