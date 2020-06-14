/**
 * Project Name : dev-utils<br>
 * File Name : Platform.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/14<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.common.catalog;

import org.thinkit.common.util.PlatformChecker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プラットフォームを管理するカタログです。<br>
 * 以下の要素が定義されています。<br>
 * {@link WINDOWS}<br>
 * {@link MAC}<br>
 * {@link LINUX}<br>
 * <br>
 * 以下の静的メソッドを呼び出すことでプログラム実行時のプラットフォーム要素を取得することができます。<br>
 * {@link #getPlatform()}
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #WINDOWS
 * @see #MAC
 * @see #LINUX
 * @see #getPlatform()
 */
@RequiredArgsConstructor
public enum Platform implements Catalog<Platform> {

    /**
     * Windows OS
     */
    WINDOWS(0),

    /**
     * Mac OS
     */
    MAC(1),

    /**
     * Linux OS
     */
    LINUX(2);

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * プログラムの実行プラットフォームを判定し返却します。<br>
     * <br>
     * プラットフォームの判定には以下のメソッドを使用しています。<br>
     * そのため{@link #getPlatform()}はWindows、Mac、Linuxのみをサポートしています。<br>
     * 実行時のプラットフォームがWindows、Mac、Linux以外の場合は必ず{@code null}を返却します。
     * {@link PlatformChecker#isWindows()}<br>
     * {@link PlatformChecker#isMac()}<br>
     * {@link PlatformChecker#isLinux()}<br>
     * 
     * @return プログラム実行時のプラットフォーム要素
     */
    public static Platform getPlatform() {
        return PlatformChecker.isWindows() ? WINDOWS
                : PlatformChecker.isMac() ? MAC : PlatformChecker.isLinux() ? LINUX : null;
    }
}