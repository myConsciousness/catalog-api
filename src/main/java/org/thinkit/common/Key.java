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

package org.thinkit.common;

/**
 * コンテンツのキーに関する汎用的な処理を定義したインターフェースです。<br>
 * {@link Key} の具象クラスは必ず {@link #getKey()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Key {

    /**
     * キーの文字列表現を返却します。
     *
     * @return キーの文字列表現
     */
    public String getKey();
}
