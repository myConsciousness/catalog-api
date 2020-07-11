/**
 * Project Name : dev-utils<br>
 * File Name : ContentTest.java<br>
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
 * {@link Content} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentTest {

    /**
     * 英名の期待値
     */
    private static final String EXPECTED_ENGLISH_SEQUENCE = "TEST_CONTENT";

    /**
     * 和名の期待値
     */
    private static final String EXPECTED_JAPANAESE_SEQUENCE = "テストコンテンツ";

    /**
     * テスト用の具象Enumクラスです。
     */
    private enum TestContent implements Content {

        /**
         * テスト用コンテンツ要素 : 英名
         */
        TEST_CONTENT,

        /**
         * テスト用コンテンツ要素 : 和名
         */
        テストコンテンツ;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Content} インターフェースの {@link Content#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestContent} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestContent#TEST_CONTENT#getString()} の返却値が <code>"TEST_CONTENT"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInEnglish() {
        assertEquals(EXPECTED_ENGLISH_SEQUENCE, TestContent.TEST_CONTENT.getString());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link Content} インターフェースの {@link Content#getString()} メソッドの機能を確認する。
     * テスト用の具象クラスは {@link TestContent} を使用する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link TestContent#テストコンテンツ#getString()} の返却値が <code>"テストコンテンツ"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testGetStringInJapanese() {
        assertEquals(EXPECTED_JAPANAESE_SEQUENCE, TestContent.テストコンテンツ.getString());
    }
}