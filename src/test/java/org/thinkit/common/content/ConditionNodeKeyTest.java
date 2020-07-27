/**
 * Project Name : dev-utils<br>
 * File Name : ConditionNodeKeyTest.java<br>
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
 * {@link ConditionNodeKey} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ConditionNodeKeyTest {

    /**
     * キー名 : conditionNodes
     */
    private static final String KEY_CONDITION_NODES = "conditionNodes";

    /**
     * キー名 : node
     */
    private static final String KEY_NODE = "node";

    /**
     * キー名 : conditionId
     */
    private static final String KEY_CONDITION_ID = "conditionId";

    /**
     * キー名 : exclude
     */
    private static final String KEY_EXCLUDE = "exclude";

    /**
     * キー名 : conditions
     */
    private static final String KEY_CONDITIONS = "conditions";

    /**
     * キー名 : condition
     */
    private static final String KEY_CONDITION = "condition";

    /**
     * キー名 : keyName
     */
    private static final String KEY_KEY_NAME = "keyName";

    /**
     * キー名 : operand
     */
    private static final String KEY_OPERAND = "operand";

    /**
     * キー名 : value
     */
    private static final String KEY_VALUE = "value";

    /**
     * <pre>
     * ❏ 概要
     * {@link ConditionNodeKey} クラスの {@link ConditionNodeKey#getKey()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link ConditionNodeKey#CONDITION_NODES#getKey()} メソッドの返却値が <code>"conditionNodes"</code> であること
     * ・{@link ConditionNodeKey#NODE#getKey()} メソッドの返却値が <code>"node"</code> であること
     * ・{@link ConditionNodeKey#CONDITION_ID#getKey()} メソッドの返却値が <code>"conditionId"</code> であること
     * ・{@link ConditionNodeKey#EXCLUDE#getKey()} メソッドの返却値が <code>"exclude"</code> であること
     * ・{@link ConditionNodeKey#CONDITIONS#getKey()} メソッドの返却値が <code>"conditions"</code> であること
     * ・{@link ConditionNodeKey#CONDITION#getKey()} メソッドの返却値が <code>"condition"</code> であること
     * ・{@link ConditionNodeKey#KEY_NAME#getKey()} メソッドの返却値が <code>"keyName"</code> であること
     * ・{@link ConditionNodeKey#OPERAND#getKey()} メソッドの返却値が <code>"operand"</code> であること
     * ・{@link ConditionNodeKey#VALUE#getKey()} メソッドの返却値が <code>"value"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetKey() {
        assertEquals(KEY_CONDITION_NODES, ConditionNodeKey.CONDITION_NODES.getKey());
        assertEquals(KEY_NODE, ConditionNodeKey.NODE.getKey());
        assertEquals(KEY_CONDITION_ID, ConditionNodeKey.CONDITION_ID.getKey());
        assertEquals(KEY_EXCLUDE, ConditionNodeKey.EXCLUDE.getKey());
        assertEquals(KEY_CONDITIONS, ConditionNodeKey.CONDITIONS.getKey());
        assertEquals(KEY_CONDITION, ConditionNodeKey.CONDITION.getKey());
        assertEquals(KEY_KEY_NAME, ConditionNodeKey.KEY_NAME.getKey());
        assertEquals(KEY_OPERAND, ConditionNodeKey.OPERAND.getKey());
        assertEquals(KEY_VALUE, ConditionNodeKey.VALUE.getKey());
    }
}