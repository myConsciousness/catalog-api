/**
 * Project Name : dev-utils<br>
 * File Name : Parameter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

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