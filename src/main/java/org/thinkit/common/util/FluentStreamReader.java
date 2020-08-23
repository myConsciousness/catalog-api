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
public final class FluentInputStream {

    /**
     * 入力ストリーム
     */
    private InputStream stream;

    /**
     * デフォルトコンストラクタ
     */
    private FluentInputStream() {
    }

    /**
     * コンストラクタ
     *
     * @param stream 入力ストリーム
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private FluentInputStream(@NonNull final InputStream stream) {
        this.stream = stream;
    }

    /**
     * 引数として渡された {@code stream} を基に {@link FluentInputStream}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param stream 入力ストリーム
     * @return {@link FluentInputStream} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static FluentInputStream of(@NonNull final InputStream stream) {
        return new FluentInputStream(stream);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}