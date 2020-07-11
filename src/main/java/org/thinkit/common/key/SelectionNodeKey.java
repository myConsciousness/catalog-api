/**
 * Project Name : dev-utils<br>
 * File Name : SelectionNodeKey.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/28<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.key;

import lombok.RequiredArgsConstructor;

/**
 * コンテンツの選択ノードのキーを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum SelectionNodeKey implements Key {

    /**
     * 選択ノード群
     */
    SELECTION_NODES(Key.selectionNodes),

    /**
     * ノード
     */
    NODE(Key.node),

    /**
     * 条件ID
     */
    CONDITION_ID(Key.conditionId);

    /**
     * キー
     */
    private final Key key;

    /**
     * キー定数
     */
    private enum Key {
        selectionNodes, node, conditionId;
    }

    @Override
    public String getKey() {
        return this.key.name();
    }
}
