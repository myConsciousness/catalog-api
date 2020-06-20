/**
 * Project Name : dev-utils<br>
 * File Name : JsonConverter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/20<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.NonNull;

/**
 * Jacksonライブラリを使用したjsonの汎用的な変換処理を定義したクラスです。
 * jsonの変換処理を行う際にはこの{@link JsonConverter}に定義されたメソッドを使用してください。
 * オブジェクトに含まれる情報をjson文字列へ変換する際には{@link JsonConverter#toString()}を使用してください。
 * json文字列を特定のオブジェクトへ変換する際には{@link JsonConverter#toObject(String, Class)}を使用し、
 * ジェネリクス情報を持つオブジェクトへ変換したい場合は{@link JsonConverter#toObject(String, TypeReference)}を使用してください。
 * 
 * <pre>
 * オブジェクトをjson文字列へ変換する場合:
 * <code>String jsonString = JsonConverter.toString(object);</code>
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
            .enable(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES).build();;

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
    public static String toString(@NonNull final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(e);
        }
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

    /**
     * Thrown to indicate that an illegal operation has been detected during the
     * conversion of an object to a json string, or a json string to an object.
     * 
     * <pre>
     * Example:
     * <code>throw new JsonConvertingException();</code>
     * </pre>
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    private static final class JsonConvertingException extends RuntimeException {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 0L;

        /**
         * Constructs an <code>JsonConvertException</code> with no detail message.
         */
        @SuppressWarnings("unused")
        public JsonConvertingException() {
            super();
        }

        /**
         * Constructs an <code>JsonConvertException</code> with the specified detail
         * message.
         *
         * @param s the detail message.
         */
        @SuppressWarnings("unused")
        public JsonConvertingException(String s) {
            super(s);
        }

        /**
         * Constructs a new exception with the specified detail message and cause.
         * <p>
         * Note that the detail message associated with <code>cause</code> is <i>not</i>
         * automatically incorporated in this exception's detail message.
         *
         * @param message the detail message (which is saved for later retrieval by the
         *                {@link Throwable#getMessage()} method).
         * @param cause   the cause (which is saved for later retrieval by the
         *                {@link Throwable#getCause()} method). (A <tt>null</tt> value
         *                is permitted, and indicates that the cause is nonexistent or
         *                unknown.)
         * @since 1.0
         */
        @SuppressWarnings("unused")
        public JsonConvertingException(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * Constructs a new exception with the specified cause and a detail message of
         * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains
         * the class and detail message of <tt>cause</tt>). This constructor is useful
         * for exceptions that are little more than wrappers for other throwables (for
         * example, {@link java.security.PrivilegedActionException}).
         *
         * @param cause the cause (which is saved for later retrieval by the
         *              {@link Throwable#getCause()} method). (A <tt>null</tt> value is
         *              permitted, and indicates that the cause is nonexistent or
         *              unknown.)
         * @since 1.0
         */
        public JsonConvertingException(Throwable cause) {
            super(cause);
        }
    }
}
