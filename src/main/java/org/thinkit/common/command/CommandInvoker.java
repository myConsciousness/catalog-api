/**
 * Project Name : dev-utils<br>
 * File Name : CommandInvoker.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.command;

import org.thinkit.common.Invokable;

import lombok.NonNull;

/**
 * {@link Command} インターフェースを実装した具象コマンドクラスの安全な呼び出しを行うコマンド起動クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class CommandInvoker<R> implements Invokable<R> {

    /**
     * コマンド
     */
    private Command<R> command;

    /**
     * デフォルトコンストラクタ
     */
    private CommandInvoker() {
    }

    /**
     * コンストラクタ
     *
     * @param command コマンド
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CommandInvoker(@NonNull Command<R> command) {
        this.command = command;
    }

    /**
     * 引数として指定された {@code command} オブジェクトを基に {@link CommandInvoker}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param command コマンド
     * @return {@link CommandInvoker} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static <R> CommandInvoker<R> of(@NonNull Command<R> command) {
        return new CommandInvoker<>(command);
    }

    @Override
    public R invoke() {
        try {
            return command.run();
        } catch (Exception e) {
            throw new CommandHandlingException(e);
        }
    }
}