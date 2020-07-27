/**
 * Project Name : dev-utils<br>
 * File Name : SelectionNodeKeyTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/28<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.content;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link SelectionNodeKey} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class SelectionNodeKeyTest {

    /**
     * キー名 : selectionNodes
     */
    private static final String KEY_SELECTION_NODES = "selectionNodes";

    /**
     * キー名 : node
     */
    private static final String KEY_NODE = "node";

    /**
     * キー名 : conditionId
     */
    private static final String KEY_CONDITION_ID = "conditionId";

    /**
     * <pre>
     * ❏ 概要
     * {@link SelectionNodeKey} クラスの {@link SelectionNodeKey#getKey()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link SelectionNodeKey#SELECTION_NODES#getKey()} メソッドの返却値が <code>"selectionNodes"</code> であること
     * ・{@link SelectionNodeKey#NODE#getKey()} メソッドの返却値が <code>"node"</code> であること
     * ・{@link SelectionNodeKey#CONDITION_ID#getKey()} メソッドの返却値が <code>"conditionId"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetKey() {
        assertEquals(KEY_SELECTION_NODES, SelectionNodeKey.SELECTION_NODES.getKey());
        assertEquals(KEY_NODE, SelectionNodeKey.NODE.getKey());
        assertEquals(KEY_CONDITION_ID, SelectionNodeKey.CONDITION_ID.getKey());
    }
}