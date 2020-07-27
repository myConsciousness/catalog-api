/**
 * Project Name : dev-utils<br>
 * File Name : Invokable.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common;

/**
 * 呼び出し可能なオブジェクトを抽象化したインターフェースです。
 * <p>
 * {@link Invokable} インターフェースを実装する際には総称型として {@link Invokable#invoke()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestInvoker implements Invokable&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Invokable<R> {

    /**
     * {@link Invokable#invoke()} メソッドを実装した具象クラスの処理を実行します。<br>
     * このメソッドは {@link Invokable} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Invokable} インターフェースの宣言時に定義した総称型の値
     */
    public R invoke();
}