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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * {@link Rule} インターフェースのテストクラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class RuleTest implements Rule {

    /**
     * <pre>
     * ❏ 概要
     * {@link Rule} インターフェースの {@link Rule#execute()} メソッドの機能を確認する。
     * </pre>
     * 
     * <pre>
     * ❏ 観点
     * ・{@link Rule#execute()} の返却値が <code>true</code> であること
     * </pre>
     * 
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    public void testExecute() {
        assertTrue(this.execute());
    }

    @Override
    public boolean execute() {
        // do nothing
        return true;
    }
}