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

package org.thinkit.common.util.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.NonNull;

/**
 * Jacksonライブラリを使用したjsonの汎用的な変換処理を定義したクラスです。
 * jsonの変換処理を行う際にはこの{@link JsonConverter}に定義されたメソッドを使用してください。
 * オブジェクトに含まれる情報をjson文字列へ変換する際には{@link JsonConverter#toJsonString()}を使用してください。
 * json文字列を特定のオブジェクトへ変換する際には{@link JsonConverter#toObject(String, Class)}を使用し、
 * ジェネリクス情報を持つオブジェクトへ変換したい場合は{@link JsonConverter#toObject(String, TypeReference)}を使用してください。
 *
 * <pre>
 * オブジェクトをjson文字列へ変換する場合:
 * <code>String jsonString = JsonConverter.toJsonString(object);</code>
 * </pre>
 *
 * <pre>
 * json文字列を特定のオブジェクトへ変換する場合:
 * <code>Sample sample = JsonConverter.toObject(jsonString, Sample.class);</code>
 * </pre>
 *
 * <pre>
 * json文字列を特定のジェネリクスを持つオブジェクトへ変換する場合:
 * <code>Map<String, String> map = JsonConverter.toObject(jsonString, new TypeReference&lt;Map&lt;String, String&gt;&gt;() {});</code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class JsonConverter {

    /**
     * json変換する際に使用するオブジェクトマッパー。<br>
     * {@link ObjectMapper}はインスタンス生成時のコストが重いため静的フィールドにインスタンスを保持します。<br>
     * {@link ObjectMapper}はスレッドセーフです。
     */
    private static final ObjectMapper mapper = JsonMapper.builder()
            .enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES).build();

    /**
     * デフォルトコンストラクタ
     */
    private JsonConverter() {
    }

    /**
     * 引数として渡されたオブジェクト情報をjson文字列へ変換します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず例外が発生します。<br>
     *
     * @param object json文字列へ変換するオブジェクト
     * @return json文字列
     *
     * @exception NullPointerException 引数として{@code null}が発生した場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static String toJsonString(@NonNull final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(e);
        }
    }

    /**
     * 引数として渡されたオブジェクト情報をjson文字列へ変換します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず例外が発生します。<br>
     *
     * @param file jsonが定義されたファイルオブジェクト
     * @return json文字列
     *
     * @exception NullPointerException 引数として{@code null}が発生した場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static String toJsonString(@NonNull final File file) {
        try {
            return mapper.readTree(file).toString();
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(e);
        } catch (IOException e) {
            throw new JsonConvertingException(e);
        }
    }

    /**
     * 引数として指定されたjson文字列を{@link HashMap}へ変換して返却します。
     * {@link #toObject(String, TypeReference)}を使用することでも同様の返却値を得ることができますが、
     * json文字列を{@link HashMap}へ変換する際にはこの{@link #HashMap(String)}を使用してください。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     *
     * <p>
     * この{@link #toHashMap(String)}が返却するマップは{@link HashMap}で実装されます。
     * そのため、変換の際にjson文字列で定義された順序は保持されません。
     *
     * <pre>
     * 使用例:
     * <code>Map map = JsonConverter.toHashMap(jsonString);</code>
     * </pre>
     *
     * @param jsonString json文字列
     * @return json文字列を変換した{@link HashMap}オブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が指定された場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static Map<String, Object> toHashMap(@NonNull final String jsonString) {
        return toObject(jsonString, new TypeReference<HashMap<String, Object>>() {
        });
    }

    /**
     * 引数として指定されたjson文字列を{@link LinkedHashMap}へ変換して返却します。
     * {@link #toObject(String, TypeReference)}を使用することでも同様の返却値を得ることができますが、
     * json文字列を{@link LinkedHashMap}へ変換する際にはこの{@link #toLinkedHashMap(String)}を使用してください。
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     *
     * <p>
     * この{@link #toLinkedHashMap(String)}が返却するマップは{@link LinkedHashMap}で実装されます。
     * そのため、json文字列で定義された順序を保持した状態で変換が行われます。
     *
     * <pre>
     * 使用例:
     * <code>Map map = JsonConverter.toLinkedHashMap(jsonString);</code>
     * </pre>
     *
     * @param jsonString json文字列
     * @return json文字列を変換した{@link LinkedHashMap}オブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が指定された場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static Map<String, Object> toLinkedHashMap(@NonNull final String jsonString) {
        return toObject(jsonString, new TypeReference<LinkedHashMap<String, Object>>() {
        });
    }

    /**
     * 引数として指定されたオブジェクト情報を基にjson文字列を特定のオブジェクトへ変換します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     *
     * @param <T>        型ワイルドカード
     * @param jsonString json文字列
     * @param clazz      変換先の型情報
     * @return 変換されたjson文字列情報を含むオブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が発生した場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static <T> T toObject(@NonNull final String jsonString, @NonNull final Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(e);
        }
    }

    /**
     * 引数として指定されたオブジェクト情報を基にjson文字列を特定のオブジェクトへ変換します。<br>
     * {@link TypeReference<T>}を使用したjson文字列の変換はこのメソッドを使用してください。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     *
     * <pre>
     * 使用例:
     * <code>JsonConverter.toObject(jsonString, new TypeReference&lt;Map&lt;String, String&gt;&gt;() {});</code>
     * </pre>
     *
     * @param <T>          型ワイルドカード
     * @param jsonString   json文字列
     * @param valueTypeRef 変換先の型情報
     * @return 変換されたjson文字列情報を含むオブジェクト
     *
     * @exception NullPointerException 引数として{@code null}が発生した場合
     * @throws JsonConvertingException jsonコンテンツの解析または生成処理が異常終了した場合
     */
    public static <T> T toObject(@NonNull final String jsonString, @NonNull final TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(jsonString, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(e);
        }
    }
}
