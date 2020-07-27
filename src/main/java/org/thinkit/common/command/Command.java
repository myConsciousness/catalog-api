/**
 * Project Name : dev-utils<br>
 * File Name : Command.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.command;

/**
 * コマンドを抽象化したインターフェースです。
 * <p>
 * {@link Command} インターフェースを実装する際には総称型として {@link Command#run()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestCommand implements Command&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Command<R> {

    /**
     * コマンドを実行します。<br>
     * このメソッドは {@link Command} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Command} インターフェースの宣言時に定義した総称型の値
     */
    public R run();
}