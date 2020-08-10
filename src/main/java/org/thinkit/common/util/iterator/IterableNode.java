/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
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