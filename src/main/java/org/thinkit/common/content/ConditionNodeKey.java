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

package org.thinkit.common.content;

import org.thinkit.common.Key;

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
