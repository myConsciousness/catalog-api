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

package org.thinkit.common.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang3.StringUtils;

import lombok.Cleanup;

/**
 * コンテンツファイルに対する汎用的な操作を行う機能を定義したクラスです。 <br>
 * 当該クラスではStAX形式でのXML解析を行います。<br>
 * <br>
 * コンテンツファイルの管理方法がXMLからjsonへ変更したため{@link StaxContentHandler}は非推奨になりました。<br>
 * json形式のコンテンツを解析する場合は{@link ContentLoader}を使用してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @deprecated
 */
@Deprecated
public final class StaxContentHandler {

    /**
     * コンテンツファイルの拡張子
     */
    private static final String CONTENT_FILE_EXTENSION = "xml";

    /**
     * 形式 : コンテンツファイルへのパス
     */
    private static final String FORMAT_FILE_PATH_TO_CONTENT = "%s/src/main/resources/xml/%s.%s";

    /**
     * 要素定数
     */
    private enum Element {
        NODE(Key.Node), CONDITION(Key.Condition), CONDITIONS(Key.Conditions);

        /**
         * 要素キー
         */
        private Key key;

        /**
         * コンストラクタ
         *
         * @param key 要素キー
         */
        Element(Key key) {
            this.key = key;
        }

        /**
         * 要素キー定数
         */
        private enum Key {
            Node, Condition, Conditions;
        }

        /**
         * 要素キー名を返却します。
         *
         * @return 要素キー名
         */
        private String getKeyName() {
            return this.key.name();
        }
    }

    /**
     * アトリビュート定数
     */
    private enum Attribute {
        KEY_NAME(Key.keyName), OPERAND(Key.operand), VALUE(Key.value), CONDITION_ID(Key.conditionId);

        /**
         * アトリビュートキー
         */
        private Key key;

        /**
         * コンストラクタ
         *
         * @param key アトリビュートキー
         */
        Attribute(Key key) {
            this.key = key;
        }

        /**
         * アトリビュートキー定数
         */
        private enum Key {
            keyName, operand, value, conditionId;
        }

        /**
         * アトリビュートキー名を返却します。
         *
         * @return アトリビュートキー名
         */
        private String getKeyName() {
            return this.key.name();
        }
    }

    /**
     * デフォルトコンストラクタ
     */
    private StaxContentHandler() {
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。
     *
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @return 定義体ファイルから取得した要素を格納した配列
     * @throws XMLStreamException    XMLファイルの形式が誤っている場合
     * @throws FileNotFoundException 指定されたXMLファイルが存在しない場合
     */
    public static List<Map<String, String>> scrape(final String contentName, final List<String> attributes)
            throws XMLStreamException, FileNotFoundException {

        Objects.requireNonNull(attributes, "wrong parameter was given. Object is null.");

        if (StringUtils.isEmpty(contentName)) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Content name is required.", contentName));
        }

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("wrong parameter was given. Attribute is required.");
        }

        return scrape(contentName, attributes, new HashMap<>(0));
    }

    /**
     * 引数として指定された定義体ファイルに定義された各要素を取得し配列として返却します。
     *
     * @param contentName 定義体ファイル名
     * @param attributes  定義体から取得する要素名
     * @param conditions  取得条件
     * @return 定義体ファイルから取得した要素を格納した配列
     * @throws XMLStreamException    XMLファイルの形式が誤っている場合
     * @throws FileNotFoundException 指定されたXMLファイルが存在しない場合
     */
    public static List<Map<String, String>> scrape(final String contentName, final List<String> attributes,
            final Map<String, String> conditions) throws XMLStreamException, FileNotFoundException {

        Objects.requireNonNull(attributes, "wrong parameter was given. Object is null.");
        Objects.requireNonNull(conditions, "wrong parameter was given. Object is null.");

        if (StringUtils.isEmpty(contentName)) {
            throw new IllegalArgumentException(
                    String.format("wrong parameter (%s) was given. Content name is required.", contentName));
        }

        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("wrong parameter was given. Attribute is required.");
        }

        final String currentDirectory = new File(".").getAbsoluteFile().getParent();
        final File file = Paths
                .get(String.format(FORMAT_FILE_PATH_TO_CONTENT, currentDirectory, contentName, CONTENT_FILE_EXTENSION))
                .toFile();

        final List<String> nodeKeys = conditions.isEmpty() ? new ArrayList<>(0) : getConditionKeys(file, conditions);

        return getContent(file, attributes, nodeKeys);
    }

    /**
     * 引数として指定された条件マップを基に対象の条件IDリストを取得します。
     *
     * @param file       コンテンツファイルオブジェクト
     * @param conditions 条件マップ
     * @return 取得対象の条件IDリスト
     * @throws XMLStreamException    XMLファイルの形式が誤っている場合
     * @throws FileNotFoundException 指定されたXMLファイルが存在しない場合
     */
    private static List<String> getConditionKeys(final File file, final Map<String, String> conditions)
            throws XMLStreamException, FileNotFoundException {

        @Cleanup
        final XMLStreamReader condition = XMLInputFactory.newInstance()
                .createXMLStreamReader(new FileInputStream(file));

        final List<String> nodeKeys = new ArrayList<>();

        while (condition.hasNext()) {
            int eventType = condition.next();
            if (eventType != XMLStreamConstants.START_ELEMENT) {
                continue;
            }

            if (Element.CONDITION.getKeyName().equals(condition.getName().getLocalPart())) {
                final String keyName = condition.getAttributeValue("", Attribute.KEY_NAME.getKeyName());
                // final String operand = condition.getAttributeValue("",
                // Attribute.OPERAND.getKeyName());
                final String value = condition.getAttributeValue("", Attribute.VALUE.getKeyName());

                if (value.equals(conditions.get(keyName))) {
                    nodeKeys.add(condition.getAttributeValue("", Attribute.CONDITION_ID.getKeyName()));
                }
            }
        }

        return nodeKeys;
    }

    /**
     * 引数として指定された情報を基にコンテンツを取得して返却します。 条件に関わらず条件IDが未設定の要素は必ず設定されます。
     *
     * @param file          コンテンツファイルオブジェクト
     * @param attributes    定義体から取得する要素名
     * @param conditionKeys 取得対象の条件IDリスト
     * @return 定義体から取得したコンテンツ情報
     * @throws XMLStreamException    XMLファイルの形式が誤っている場合
     * @throws FileNotFoundException 指定されたXMLファイルが存在しない場合
     */
    private static List<Map<String, String>> getContent(final File file, final List<String> attributes,
            final List<String> conditionKeys)
            throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {

        @Cleanup
        final XMLStreamReader node = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(file));
        final int attributeSize = attributes.size();

        final List<Map<String, String>> contents = new ArrayList<>();

        while (node.hasNext()) {
            int eventType = node.next();
            if (eventType != XMLStreamConstants.START_ELEMENT) {
                continue;
            }

            final String elementName = node.getName().getLocalPart();

            if (Element.CONDITIONS.getKeyName().equals(elementName)) {
                break;
            }

            if (Element.NODE.getKeyName().equals(elementName)) {
                final String conditionId = node.getAttributeValue("", Attribute.CONDITION_ID.getKeyName());
                if (!StringUtils.isEmpty(conditionId) && !conditionKeys.contains(conditionId)) {
                    continue;
                }

                final Map<String, String> content = new HashMap<>(attributeSize);
                for (String attribute : attributes) {
                    final String attributeValue = node.getAttributeValue("", attribute);
                    content.put(attribute, attributeValue);
                }

                contents.add(content);
            }
        }

        return contents;
    }
}
