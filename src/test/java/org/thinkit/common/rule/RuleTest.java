/**
 * Project Name : dev-utils<br>
 * File Name : RuleTest.java<br>
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

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * {@link Rule} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class RuleTest implements Rule<String> {

    /**
     * <pre>
     * ❏ 概要
     * {@link Rule} インターフェースの {@link Rule#execute()} メソッドの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link Rule#execute()} の返却値が <code>"test"</code> であること
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testExecute() {
        assertEquals("test", this.execute());
    }

    @Override
    public String execute() {
        // do nothing
        return "test";
    }

    @Override
    public List<Attribute> getAttributes() {
        // do nothing
        return List.of();
    }

    @Override
    public Map<Condition, String> getConditions() {
        // do nothing
        return Map.of();
    }
}