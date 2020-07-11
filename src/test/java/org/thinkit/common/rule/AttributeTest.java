/**
 * Project Name : dev-utils<br>
 * File Name : AttributeTest.java<br>
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
 * {@link Attribute} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class AttributeTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "TEST_ATTRIBUTE";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストアトリビュート";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestAttribute implements Attribute {
        /**
         * テスト用アトリビュート要素 (英名)
         */
        TEST_ATTRIBUTE,

        /**
         * テスト用アトリビュート要素 (和名)
         */
        テストアトリビュート;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Attibute} インターフェースの {@link Attribute#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestAttribute} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestAttribute#TEST_ATTRIBUTE#getString()} の返却値が <code>"TEST_ATTRIBUTE"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestAttribute.TEST_ATTRIBUTE.getString());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Attibute} インターフェースの {@link Attribute#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestAttribute} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestAttribute#テストアトリビュート#getString()} の返却値が <code>"テストアトリビュート"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestAttribute.テストアトリビュート.getString());
    }
}