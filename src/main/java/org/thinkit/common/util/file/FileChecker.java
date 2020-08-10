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

package org.thinkit.common.util.file;

import java.io.File;

import lombok.NonNull;

/**
 * ファイルに関する汎用的な検査機能を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class FileChecker {

    /**
     * デフォルトコンストラクタ
     */
    private FileChecker() {
    }

    /**
     * 引数として指定された {@code filePath} に格納されたファイルパスが存在するか確認します。<br>
     * 指定されたファイルパスが存在する場合は {@code true} を返却し、ファイルパスが存在しない場合は {@code false} を返却します。
     *
     * @param filePath 検査対象のファイルパス
     * @return 引数として指定されたファイルパスが存在する場合は {@code true}、それ以外は {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean exists(@NonNull String filePath) {
        return exists(new File(filePath));
    }

    /**
     * 引数として指定された {@code filePath} に格納されたファイルパスが存在するか確認します。<br>
     * 指定されたファイルパスが存在する場合は {@code true} を返却し、ファイルパスが存在しない場合は {@code false} を返却します。
     *
     * @param filePath 検査対象のファイルオブジェクト
     * @return 引数として指定されたファイルパスが存在する場合は {@code true}、それ以外は {@code false}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static boolean exists(@NonNull File filePath) {
        return filePath.exists();
    }
}