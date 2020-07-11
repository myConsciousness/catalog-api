/**
 * Project Name : dev-utils<br>
 * File Name : ConditionTest.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * {@link Condition} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ConditionTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "TEST_CONDITION";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストコンディション";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestCondition implements Condition {

        /**
         * テスト用コンディション要素 (英名)
         */
        TEST_CONDITION,

        /**
         * テスト用コンディション要素 (和名)
         */
        テストコンディション;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Condition} インターフェースの {@link Condition#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestCondition} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestCondition#TEST_CONDITION#getString()} の返却値が <code>"TEST_CONDITION"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestCondition.TEST_CONDITION.getString());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Condition} インターフェースの {@link Condition#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestCondition} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestCondition#テストコンディション#getString()} の返却値が <code>"テストコンディション"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestCondition.テストコンディション.getString());
    }
}