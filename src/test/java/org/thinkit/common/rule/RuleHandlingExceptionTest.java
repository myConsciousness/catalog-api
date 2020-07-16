/**
 * Project Name : dev-utils<br>
 * File Name : RuleHandlingExceptionTest.java<br>
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * {@link RuleHandlingException} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class RuleHandlingExceptionTest {

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのデフォルトコンストラクタの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(new RuleHandlingException());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithMessage() {
        final String message = "This is a test message.";
        final RuleHandlingException exception = new RuleHandlingException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列と例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getCause()} の返却値が {@code null} ではないこと。
     * ・{@link RuleHandlingException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithMessageAndException() {
        final String message = "This is a test message.";
        RuleHandlingException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new RuleHandlingException(message, e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link RuleHandlingException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link RuleHandlingException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link RuleHandlingException#getCause()} の返却値が {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testConstructorWithException() {

        RuleHandlingException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new RuleHandlingException(e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
    }
}