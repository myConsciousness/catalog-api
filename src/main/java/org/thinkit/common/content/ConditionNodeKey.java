/**
 * Project Name : dev-utils<br>
 * File Name : ConditionNodeKey.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/28<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.content;

import org.thinkit.common.key.Key;
import lombok.RequiredArgsConstructor;

/**
 * コンテンツの条件ノードのキーを管理するEnumクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
enum ConditionNodeKey implements Key {

    /**
     * 条件ノード群
     */
    CONDITION_NODES(Key.conditionNodes),

    /**
     * ノード
     */
    NODE(Key.node),

    /**
     * 条件ID
     */
    CONDITION_ID(Key.conditionId),

    /**
     * 除外
     */
    EXCLUDE(Key.exclude),

    /**
     * 条件群
     */
    CONDITIONS(Key.conditions),

    /**
     * 条件
     */
    CONDITION(Key.condition),

    /**
     * キー名
     */
    KEY_NAME(Key.keyName),

    /**
     * 演算子
     */
    OPERAND(Key.operand),

    /**
     * 値
     */
    VALUE(Key.value);

    /**
     * キー
     */
    private final Key key;

    /**
     * キー定数
     */
    private enum Key {
        conditionNodes, node, conditionId, exclude, conditions, condition, keyName, operand, dataType, value;
    }

    @Override
    public String getKey() {
        return this.key.name();
    }
}
