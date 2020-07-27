/**
 * Project Name : dev-utils<br>
 * File Name : IterableNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util.iterator;

import java.util.List;

/**
 * イテレート可能なノードの機能を抽象化したインターフェースです。
 * <p>
 * {@link FluentIterator} クラスの機能を使用する場合は必ず対象のクラスでこの {@link IterableNode}
 * インターフェースを実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see FluentIterator
 */
public interface IterableNode<T> {

    /**
     * イテレート対象ノード群のサイズを返却します。
     *
     * @return イテレート対象ノード群のサイズ
     */
    public int size();

    /**
     * イテレート対象ノード群を返却します。
     *
     * @return イテレート対象ノード群
     */
    public List<T> nodes();
}