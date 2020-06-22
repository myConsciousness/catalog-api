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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Extension;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public final class ContentLoader {

    /**
     * コンテンツファイルへのパス（形式）
     */
    private static final String FORMAT_FILE_PATH_TO_CONTENT = "%s/src/main/resources/content/%s.%s";

    /**
     * デフォルトコンストラクタ
     */
    private ContentLoader() {
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。
     *
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @return 定義体ファイルから取得した要素を格納した配列
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
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
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。
     *
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @param conditions  取得条件
     * @return 定義体ファイルから取得した要素を格納した配列
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
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

        final Map<String, Object> content = getContent(contentName);

        final List<Map<String, Object>> selectionNodes = getNodeList(content, SelectionNodeKey.SELECTION_NODES);
        final List<Map<String, Object>> conditionNodes = getNodeList(content, ConditionNodeKey.CONDITION_NODES);

        return null;
    }

    /**
     * コンテンツマップから指定された{@link ContentKey}に紐づくノードリストを取得し返却します。
     * ジェネリクスを使用したキャスト処理の際にはunchecked警告を避けられないため
     * {@link SuppressWarnings}でuncheckedをこの{@link #getNodeList(Map, ContentKey)}メソッドへ指定しています。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param content    コンテンツマップ
     * @param contentKey コンテンツキー
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getNodeList(@NonNull Map<String, Object> content,
            @NonNull ContentKey contentKey) {
        return (List<Map<String, Object>>) content.get(contentKey.getKey());
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

        return JsonConverter.toLinkedHashMap(JsonConverter.toString(file));
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
        CONDITION_NODE(Key.conditionId);

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
            conditionNodes, node, conditionId, exclude, conditions, condition, keyName, operand, value;
        }

        @Override
        public String getKey() {
            return this.key.name();
        }
    }
}