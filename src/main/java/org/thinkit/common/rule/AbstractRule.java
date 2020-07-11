/**
 * Project Name : dev-utils<br>
 * File Name : AbstractRule.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Catalog;
import org.thinkit.common.rule.exception.RuleHandlingException;
import org.thinkit.common.util.ContentLoader;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ルール処理の基底クラスです。<br>
 * ルール処理を実装する際には必ず当抽象クラスを継承し、<br>
 * 必要に応じ各メソッドをオーバーライドしてください。<br>
 * <br>
 * 状況に応じてオーバーライドが必要なメソッドは以下のものがあります。<br>
 * 1, {@link #getAttributes()} コンテンツファイルから情報を取得する場合には必ずオーバーライドして実装してください。<br>
 * 2, {@link #getConditions()}
 * コンテンツファイルから情報を取得する際に取得条件がある場合は必ずオーバーライドして実装してください。<br>
 * <br>
 * また、上記の{@link #getAttributes()}を実装せずに{@link #loadContent(Content)}を呼び出した場合は、<br>
 * ロード処理時にコンテンツファイルに定義されているアトリビュート名を判断できないため実行時に必ず失敗します。<br>
 * <br>
 * {@link #getConditions()}が実装されなかった場合であってもロード処理は正常に終了することが可能ですが、<br>
 * 条件情報が存在しないため設計・実装時に想定されたレコードは取得されません。<br>
 * <br>
 * ロードされたコンテンツレコードのリストから特定のレコードを抽出するために{@link #getRecordByKey(Attribute, Catalog)}メソッドを提供していますが、<br>
 * {@link #loadContent(Content)}メソッドでコンテンツファイルのロード処理を行う前に呼び出された場合は、<br>
 * 検索対象のコンテンツ情報が存在しないため必ず実行時に失敗します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see #loadContent(Content)
 * @see #getAttributes()
 * @see #getConditions()
 * @see #getRecordByKey(Attribute, Catalog)
 */
@ToString
@EqualsAndHashCode
public abstract class AbstractRule implements Rule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * コンテンツから取得したコンテンツ情報を格納したリスト
     */
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PROTECTED)
    private List<Map<String, String>> contents = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    protected AbstractRule() {
    }

    /**
     * コンテンツファイルに定義されているアトリビュート名を格納したリストを返却します。<br>
     * コンテンツファイル情報を取得するために当抽象クラスに定義されている{@link #loadContent(Content)}を実行する際には、<br>
     * 当抽象クラスを定義した子クラスは必ず当メソッドをオーバライドしアトリビュート名を格納したリストを返却する処理を実装してください。<br>
     * <br>
     * 当抽象メソッドは{@link #loadContent(Content)}実行時に取得する対象のアトリビュートを判断するために使用されます。<br>
     * そのため、当メソッドをオーバーライドした子クラスで実装した処理を使用する必要はありません。<br>
     *
     * @return コンテンツファイルに定義されたアトリビュート名を格納したリスト
     * @see #getConditions()
     * @see #loadContent(Content)
     */
    protected List<Attribute> getAttributes() {
        return new ArrayList<>(0);
    }

    /**
     * コンテンツファイルから情報を取得する際の条件を格納したマップを返却します。<br>
     * コンテンツファイルに取得条件が定義されている場合は、<br>
     * 必ず{@link #loadContent(Content)}の実行前に当メソッドをオーバーライドし、<br>
     * 取得条件を格納したマップを返却する処理を実装してください。<br>
     * <br>
     * 当メソッドがオーバーライドされずに実行された場合でも実行時にエラーは発生しませんが、<br>
     * 条件に合った目的のレコードは取得できません。
     *
     * @return コンテンツファイルに定義されたレコード取得条件を格納したマップ
     * @see #loadContent(Content)
     */
    protected Map<Condition, String> getConditions() {
        return new HashMap<>(0);
    }

    /**
     * {@link #loadContent(Content)}メソッドの処理でロードされたコンテンツ情報から、<br>
     * 引数として指定されたキー情報を基に対象レコードを取得しマップとして返却します。<br>
     * <br>
     * 当メソッドを実行する際には必ず{@link #loadContent(Content)}メソッドが実行され、<br>
     * そのロード処理で取得したコンテンツ情報が当抽象クラスのフィールドに格納されている必要があります。<br>
     * <br>
     * {@link #loadContent(Content)}メソッドが実行されていない状態で当メソッドが呼び出された場合は、<br>
     * 検索対象のコンテンツ情報が存在しないため実行時に必ず失敗します。
     *
     * @param keyAttribute キーとなるアトリビュート名を保持するアトリビュートオブジェクト
     * @param keyCatalog   キーとなるコード値を保持するカタログオブジェクト
     * @return 引数として渡されたキー情報に紐づくコンテンツ情報を格納したマップ
     * @see #loadContent(Content)
     * @throws RuleHandlingException {@link #loadContent(Content)}が実行されていない状態で当メソッドが呼び出された場合
     */
    protected final Map<String, String> getRecordByKey(final Attribute keyAttribute,
            final Catalog<? extends Catalog<?>> keyCatalog) {
        logger.atInfo().log("START");
        Objects.requireNonNull(keyAttribute, "Catalog must not be null.");
        Objects.requireNonNull(keyCatalog, "Catalog must not be null.");

        final List<Map<String, String>> contents = this.getContents();

        if (contents.isEmpty()) {
            throw new RuleHandlingException(
                    "The content information is not loaded. Be sure to execute the loadContent() method.");
        }

        final int catalogCode = keyCatalog.getCode();

        for (Map<String, String> content : contents) {
            final String templateCode = content.get(keyAttribute.getString());

            if (Integer.parseInt(templateCode) == catalogCode) {
                logger.atInfo().log("Fetched record = (%s)", content);
                logger.atInfo().log("END");
                return content;
            }
        }

        logger.atWarning().log("The content record associated with the specified catalog code could not be retrieved.");
        logger.atInfo().log("END");
        return new HashMap<>(0);
    }

    /**
     * 引数として渡されたコンテンツオブジェクトに紐づくコンテンツファイルを参照しロード処理を行います。<br>
     * ロードされたコンテンツオブジェクトは当オブジェクトのフィールドに保持されます。<br>
     * 当メソッドを実行する際には以下の手続きを遵守する必要があります。<br>
     * <br>
     * 1, 当抽象クラスを継承した子クラスは{@link #getAttributes()}を必ず実装してください。<br>
     * 2, コンテンツから取得する情報に条件が存在する場合は{@link #getConditions()}を必ず実装してください。 <br>
     * <br>
     * 仮に{@link #getAttributes()}が実装されていない場合に当ロード処理が呼び出された場合は、<br>
     * 指定されたコンテンツファイル内に定義されているアトリビュート名が判断できないため実行時に必ず失敗します。<br>
     * <br>
     * {@link #getConditions()}が実装されていない場合は実行時にエラーは発生しませんが、<br>
     * 特定のレコードを取得条件に合わせて取得したい場合は必ず子クラスで実装してください。<br>
     *
     * @see #getAttributes()
     * @see #getConditions()
     * @throws RuleHandlingException コンテンツのロード時に{@link #getAttributes()}が子クラスで実装されていない、<br>
     *                               または子クラスの実装が誤っていることによって処理を行えない場合に発生します。<br>
     *                               またはコンテンツの取得処理でエラーが発生し、コンテンツ情報を取得できなかった場合に発生します。
     */
    protected final void loadContent(Content content) {
        logger.atInfo().log("START");

        final List<Attribute> attributes = this.getAttributes();
        final Map<Condition, String> conditions = this.getConditions();

        Objects.requireNonNull(content, "wrong parameter was given. Object is null.");
        Objects.requireNonNull(attributes, "Attribute object is null.");
        Objects.requireNonNull(conditions, "Condition object is null.");

        if (attributes.isEmpty()) {
            throw new RuleHandlingException(
                    "Attribute is required. Check the implementation of the getAttributes() method.");
        }

        final String contentName = content.getString();
        final List<String> attributeSequences = this.getAttributeSequences(attributes);
        final Map<String, String> conditionSequences = this.getConditionSequences(conditions);

        logger.atInfo().log("Content name = (%s)", contentName);
        logger.atInfo().log("Attribute = (%s)", attributeSequences);
        logger.atInfo().log("Condition = (%s)", conditionSequences);

        final List<Map<String, String>> contents = ContentLoader.load(contentName, attributeSequences,
                conditionSequences);

        if (contents.isEmpty()) {
            throw new RuleHandlingException(
                    "Could not get a value from the content. Please check the input information or implementation.");
        }

        this.setContents(contents);

        logger.atInfo().log("Loaded content records = (%s)", contents);
        logger.atInfo().log("END");
    }

    /**
     * アトリビュートを文字列表現に変換した値を格納したリストを返却します。
     *
     * @param attributes アトリビュートリスト
     * @return アトリビュートを文字列表現に変換した値を格納したリスト
     */
    private final List<String> getAttributeSequences(final List<Attribute> attributes) {
        logger.atInfo().log("SATRT");
        assert !attributes.isEmpty();

        final List<String> attributeSequences = new ArrayList<>(attributes.size());

        for (Attribute attribute : attributes) {
            attributeSequences.add(attribute.getString());
        }

        logger.atInfo().log("END");
        return attributeSequences;
    }

    /**
     * 取得条件を文字列表現に変換した値を格納したマップを返却します。
     *
     * @param conditions 取得条件
     * @return 取得条件を文字列表現に変換した値を格納したマップ
     */
    private final Map<String, String> getConditionSequences(final Map<Condition, String> conditions) {
        logger.atInfo().log("START");
        assert !conditions.isEmpty();

        final Map<String, String> conditionSequences = new HashMap<>(conditions.size());

        if (conditions.isEmpty()) {
            return conditionSequences;
        }

        final Set<Map.Entry<Condition, String>> entrySet = conditions.entrySet();

        for (Map.Entry<Condition, String> entry : entrySet) {
            conditionSequences.put(entry.getKey().getString(), entry.getValue());
        }

        logger.atInfo().log("END");
        return conditionSequences;
    }
}
