/**
 * Project Name : dev-utils<br>
 * File Name : Key.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/28<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.key;

/**
 * コンテンツのキーに関する汎用的な処理を定義したインターフェースです。<br>
 * {@link Key} の具象クラスは必ず {@link #getKey()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Key {

    /**
     * キーの文字列表現を返却します。
     *
     * @return キーの文字列表現
     */
    public String getKey();
}
