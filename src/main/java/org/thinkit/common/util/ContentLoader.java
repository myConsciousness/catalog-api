/**
 * Project Name : dev-utils<br>
 * File Name : ContentLoader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/20<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Extension;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 指定されたコンテンツ定義を基にコンテンツをロードする処理を定義したクラスです。<br>
 * 条件指定なしでコンテンツ情報をロードするための{@link #load(String, List)}メソッドと、
 * 条件指定ありでコンテンツ情報をロードするための{@link #load(String, List, Map)}メソッドを提供しています。<br>
 * <p>
 * コンテンツに定義されたconditionIdの値が空文字列のレコードは無条件でロードされます。<br>
 * コンテンツのconditionIdに値を定義した場合は必ずコンテンツに条件を定義し{@link #load(String, List, Map)}を呼び出してください。
 * 
 * <pre>
 * 条件指定なしの使用例:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentName, attributes);</code>
 * </pre>
 * 
 * <pre>
 * 条件指定ありの使用例:
 * <code>List&lt;Map&lt;String, String&gt;&gt; contents = ContentLoader.load(contentName, attributes, conditions);</code>
 * </pre>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #load(String, List)
 * @see #load(String, List, Map)
 */
public final class ContentLoader {

    /**
     * コンテンツファイルへのパス（形式）
     */
    private static final String FORMAT_FILE_PATH_TO_CONTENT = "%s/src/main/resources/content/%s%s";

    /**
     * デフォルトコンストラクタ
     */
    private ContentLoader() {
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。<br>
     * コンテンツ定義に取得条件が存在しない場合はこの {@link ContentLoader#load(String, List)}
     * メソッドを使用してください。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     * 
     * <pre>
     * 使用例:
     * <code>List<Map<String, String>> contents = ContentLoader.load(contentName, attributes);</code>
     * </pre>
     *
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @return 定義体ファイルから取得した要素を格納した配列
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     * @throws IllegalArgumentException コンテンツ名が空文字列、またはアトリビュートリストが空の場合
     */
    public static List<Map<String, String>> load(@NonNull final String contentName,
            @NonNull final List<String> attributes) {

        if (StringUtils.isBlank(contentName)) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Content name is required.", contentName));
        }

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("wrong parameter was given. Attribute is required.");
        }

        return load(contentName, attributes, new HashMap<>(0));
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。<br>
     * コンテンツ定義に取得条件が存在しない場合はこの {@link ContentLoader#load(String, List, Map)}
     * メソッドを使用してください。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     * 
     * <pre>
     * 使用例:
     * <code>List<Map<String, String>> contents = ContentLoader.load(contentName, attributes, conditions);</code>
     * </pre>
     * 
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @param conditions  取得条件
     * @return 定義体ファイルから取得した要素を格納した配列
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     * @throws IllegalArgumentException コンテンツ名が空文字列、またはアトリビュートリストが空の場合
     */
    public static List<Map<String, String>> load(@NonNull final String contentName, @NonNull List<String> attributes,
            @NonNull final Map<String, String> conditions) {

        if (StringUtils.isBlank(contentName)) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Content name is required.", contentName));
        }

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("wrong parameter was given. Attribute is required.");
        }

        final Map<String, Object> rawContent = getContent(contentName);
        final List<Map<String, Object>> conditionNodes = getNodeList(rawContent, ConditionNodeKey.CONDITION_NODES);

        final List<String> conditionIdList = conditionNodes.isEmpty() ? new ArrayList<>(0)
                : getConditionIdList(conditionNodes, conditions);

        return getContentList(attributes, rawContent, conditionIdList);
    }

    /**
     * コンテンツマップから指定された{@link ContentKey}に紐づくノードリストを取得し返却します。
     * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため
     * {@link SuppressWarnings}でuncheckedをこの{@link #getNodeList(Map, ContentKey)}メソッドへ指定しています。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param content    コンテンツマップ
     * @param contentKey コンテンツキー
     * @return {@link ContentKey}に紐づくノードリスト
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getNodeList(@NonNull Map<String, Object> content,
            @NonNull ContentKey contentKey) {
        return (List<Map<String, Object>>) content.get(contentKey.getKey());
    }

    /**
     * コンテンツマップから指定された{@link ContentKey}に紐づくノードマップを取得し返却します。
     * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため
     * {@link SuppressWarnings}でuncheckedをこの{@link #getNodeList(Map, ContentKey)}メソッドへ指定しています。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param content    コンテンツマップ
     * @param contentKey コンテンツキー
     * @return {@link ContentKey}に紐づくノードマップ
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getNodeMap(@NonNull Map<String, Object> content,
            @NonNull ContentKey contentKey) {
        return (Map<String, Object>) content.get(contentKey.getKey());
    }

    /**
     * ノードマップから引数として指定されたコンテンツキーを基に文字列型の値を取得し返却します。
     * 引数として{@code null}が渡された場合は実行時に必ず{@code null}が発生します。
     * 
     * @param nodeMap    ノードマップ
     * @param contentKey コンテンツキー
     * @return ノードマップに格納されたコンテンツキーに紐づく文字列型の値
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull ContentKey contentKey) {
        return (String) nodeMap.get(contentKey.getKey());
    }

    /**
     * ノードマップから引数として指定されたコンテンツキーを基に文字列型の値を取得し返却します。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param nodeMap    ノードマップ
     * @param contentKey コンテンツキー
     * @return ノードマップに格納されたコンテンツキーに紐づく文字列型の値
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static String getString(@NonNull Map<String, Object> nodeMap, @NonNull String contentKey) {
        return (String) nodeMap.get(contentKey);
    }

    /**
     * 引数として指定されたコンテンツ名に紐づくコンテンツファイルからコンテンツ情報を取得し返却します。
     * コンテンツ情報は{@link JsonConverter}に定義されているメソッドを使用して変換を行っています。
     * 
     * @param contentName コンテンツ名
     * @return コンテンツマップ
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static Map<String, Object> getContent(@NonNull final String contentName) {

        final String currentDirectory = new File(Delimiter.period()).getAbsoluteFile().getParent();
        final File file = Paths
                .get(String.format(FORMAT_FILE_PATH_TO_CONTENT, currentDirectory, contentName, Extension.json()))
                .toFile();

        return JsonConverter.toLinkedHashMap(JsonConverter.toJsonString(file));
    }

    /**
     * 引数として渡された情報を基にコンテンツリストを生成し返却します。 <br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     * <br>
     * コンテンツ定義に条件IDが設定されているレコードはコンテンツに定義された条件に合致する場合にのみ取得します。<br>
     * コンテンツ定義に条件IDが設定されていない（空文字列）の場合は無条件でレコードの取得を行います。
     * 
     * @param attributes      コンテンツから取得する値に紐づくキー
     * @param rawContent      加工されていないコンテンツオブジェクト
     * @param conditionIdList 取得する対象の条件IDが格納されたリスト
     * @return コンテンツリスト
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static List<Map<String, String>> getContentList(@NonNull List<String> attributes,
            @NonNull Map<String, Object> rawContent, @NonNull List<String> conditionIdList) {

        final List<Map<String, String>> contentList = new ArrayList<>(0);
        final List<Map<String, Object>> selectionNodes = getNodeList(rawContent, SelectionNodeKey.SELECTION_NODES);

        for (Map<String, Object> nodeList : selectionNodes) {
            final Map<String, Object> nodeMap = getNodeMap(nodeList, SelectionNodeKey.NODE);
            final Map<String, String> content = new HashMap<>(0);
            final String conditionId = getString(nodeMap, SelectionNodeKey.CONDITION_ID);

            if (!StringUtils.isEmpty(conditionId) && !conditionIdList.contains(conditionId)) {
                continue;
            }

            for (String attribute : attributes) {
                content.put(attribute, getString(nodeMap, attribute));
            }

            contentList.add(content);
        }

        return contentList;
    }

    /**
     * コンテンツをロードする際に使用する条件IDを取得しリストとして返却します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     * 
     * @param conditionNodes 条件ノードリスト
     * @param conditions     条件の照合時に使用する条件マップ
     * @return 条件IDのリスト
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static List<String> getConditionIdList(@NonNull List<Map<String, Object>> conditionNodes,
            @NonNull Map<String, String> conditions) {

        final List<String> conditionIdList = new ArrayList<>(0);

        for (Map<String, Object> nodeList : conditionNodes) {
            final Map<String, Object> nodeMap = getNodeMap(nodeList, ConditionNodeKey.NODE);
            final List<Map<String, Object>> conditionList = getNodeList(nodeMap, ConditionNodeKey.CONDITIONS);

            for (Map<String, Object> condition : conditionList) {
                if (all(condition, conditions)) {
                    conditionIdList.add(getString(nodeMap, ConditionNodeKey.CONDITION_ID));
                }
            }
        }

        return conditionIdList;
    }

    /**
     * コンテンツに定義された条件と{@link #load(String, List, Map)}に渡された条件を照合し、
     * 全ての条件を満たしているか判定します。<br>
     * 全ての条件を満たしている場合は{@code true}を返却し、それ以外の場合は{@code false}を返却します。<br>
     * <br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param contentCondition コンテンツに定義された条件
     * @param conditions       照合する値を格納したマップ
     * @return 全ての条件を満たしている場合は{@code true}、それ以外は{@code false}
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private static boolean all(@NonNull Map<String, Object> contentCondition, @NonNull Map<String, String> conditions) {

        final String keyName = getString(contentCondition, ConditionNodeKey.KEY_NAME);
        final String value = getString(contentCondition, ConditionNodeKey.VALUE);
        final Set<Entry<String, String>> entrySet = conditions.entrySet();

        for (Entry<String, String> entry : entrySet) {
            if (Objects.equals(keyName, entry.getKey()) && !Objects.equals(value, entry.getValue())) {
                return false;
            }
        }

        return true;
    }

    /**
     * コンテンツのキーに関する汎用的な処理を定義したインターフェースです。
     * {@link ContentKey}を実装する具象クラスは必ず{@link #getKey()}を実装してください。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     * 
     * @see SelectionNodeKey
     * @see ConditionNodeKey
     */
    private interface ContentKey {

        /**
         * キーの文字列表現を返却します。
         * 
         * @return キーの文字列表現
         */
        public String getKey();
    }

    /**
     * コンテンツの選択ノードのキーを管理するEnumクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @RequiredArgsConstructor
    private enum SelectionNodeKey implements ContentKey {

        /**
         * 選択ノード群
         */
        SELECTION_NODES(Key.selectionNodes),

        /**
         * ノード
         */
        NODE(Key.node),

        /**
         * 条件ID
         */
        CONDITION_ID(Key.conditionId);

        /**
         * キー
         */
        private final Key key;

        /**
         * キー定数
         */
        private enum Key {
            selectionNodes, node, conditionId;
        }

        @Override
        public String getKey() {
            return this.key.name();
        }
    }

    /**
     * コンテンツの条件ノードのキーを管理するEnumクラスです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @RequiredArgsConstructor
    private enum ConditionNodeKey implements ContentKey {

        /**
         * 条件ノード群
         */
        CONDITION_NODES(Key.conditionNodes),

        /**
         * ノード
         */
        NODE(Key.node),

        /**
         * 条件ID
         */
        CONDITION_ID(Key.conditionId),

        /**
         * 除外
         */
        EXCLUDE(Key.exclude),

        /**
         * 条件群
         */
        CONDITIONS(Key.conditions),

        /**
         * 条件
         */
        CONDITION(Key.condition),

        /**
         * キー名
         */
        KEY_NAME(Key.keyName),

        /**
         * 演算子
         */
        OPERAND(Key.operand),

        /**
         * 値
         */
        VALUE(Key.value);

        /**
         * キー
         */
        private final Key key;

        /**
         * キー定数
         */
        private enum Key {
            conditionNodes, node, conditionId, exclude, conditions, condition, keyName, operand, dataType, value;
        }

        @Override
        public String getKey() {
            return this.key.name();
        }
    }
}