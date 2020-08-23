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

package org.thinkit.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * {@link InputStream} クラスに対する汎用的な処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@EqualsAndHashCode
public final class FluentStreamReader {

    /**
     * 入力ストリーム
     */
    private InputStream stream;

    /**
     * デフォルトコンストラクタ
     */
    private FluentStreamReader() {
    }

    /**
     * コンストラクタ
     *
     * @param stream 入力ストリーム
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private FluentStreamReader(@NonNull final InputStream stream) {
        this.stream = stream;
    }

    /**
     * 引数として渡された {@code stream} を基に {@link FluentStreamReader}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param stream 入力ストリーム
     * @return {@link FluentStreamReader} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static FluentStreamReader of(@NonNull final InputStream stream) {
        return new FluentStreamReader(stream);
    }

    @Override
    public String toString() {

        final StringBuilder sequence = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sequence.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sequence.toString();
    }
}