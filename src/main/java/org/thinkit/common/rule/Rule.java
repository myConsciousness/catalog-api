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

package org.thinkit.common.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.thinkit.common.content.ContentLoader;

import lombok.NonNull;

/**
 * ルールを抽象化したインターフェースです。
 * <p>
 * {@link Rule} インターフェースを実装する際には総称型として {@link Rule#execute()}
 * メソッドが返却する値の型を指定してください。
 *
 * <pre>
 * 使用例 (String型を返却する場合):
 * <code>
 * public class TestRule implements Rule&lt;String&gt; {
 *      // do something
 * }
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Rule<R> {

    /**
     * コンテンツファイルに定義されているアトリビュート名を格納したリストを返却します。
     *
     * @return コンテンツファイルに定義されたアトリビュート名を格納したリスト
     * @see #getConditions()
     * @see #loadContent(Content)
     */
    public List<Attribute> getAttributes();

    /**
     * コンテンツファイルから情報を取得する際の条件を格納したマップを返却します。
     *
     * @return コンテンツファイルに定義されたレコード取得条件を格納したマップ
     * @see #loadContent(Content)
     */
    public Map<Condition, String> getConditions();

    /**
     * ルールを実行します。<br>
     * このメソッドは {@link Rule} インターフェースの宣言時に定義した総称型の値を返却します。
     *
     * @return {@link Rule} インターフェースの宣言時に定義した総称型の値
     */
    public R execute();

    /**
     * 引数として渡されたコンテンツオブジェクトに紐づくコンテンツファイルを参照しロード処理を行います。
     *
     * @param content コンテンツ名
     * @return 引数として指定された {@code content} に紐づくコンテンツ情報を格納したマップ
     *
     * @see #getAttributes()
     * @see #getConditions()
     *
     * @exception NullPointerException 引数として指定された {@code content} が {@code null} の場合
     * @throws RuleHandlingException 実装された {@link #getAttributes()} メソッドの返却値が
     *                               {@code null} の場合、または {@link #getAttributes()}
     *                               メソッドの返却値が空リストの場合
     */
    default List<Map<String, String>> loadContent(@NonNull Content content) {

        final List<Attribute> attributes = this.getAttributes();
        final Map<Condition, String> conditions = this.getConditions();

        if (attributes == null || attributes.isEmpty()) {
            throw new RuleHandlingException(
                    "Attribute is required. Check the implementation of the getAttributes() method.");
        }

        final List<Map<String, String>> contents = ContentLoader.load(content.getString(),
                attributes.stream().map(Attribute::getString).collect(Collectors.toList()),
                conditions == null ? new HashMap<>(0)
                        : conditions.entrySet().stream()
                                .collect(Collectors.toMap(e -> e.getKey().getString(), e -> e.getValue())));

        if (contents.isEmpty()) {
            throw new RuleHandlingException(
                    "Could not get a value from the content. Please check the input information or implementation.");
        }

        return contents;
    }
}
