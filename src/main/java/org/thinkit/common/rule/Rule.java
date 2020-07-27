/**
 * Project Name : dev-utils<br>
 * File Name : Rule.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/14<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.rule;

/**
 * ルールを抽象化したインターフェースです。
 * <p>
 * {@link Rule} インターフェースを実装する際には総称型として {@link Rule#execute()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestRule implements Rule&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Rule<R> {

    /**
     * ルールを実行します。<br>
     * このメソッドは {@link Rule} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Rule} インターフェースの宣言時に定義した総称型の値
     */
    public R execute();
}
