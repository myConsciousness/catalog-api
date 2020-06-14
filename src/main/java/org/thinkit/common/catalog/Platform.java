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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プラットフォームを管理するカタログです。<br>
 * 以下の要素が定義されています。<br>
 * {@link WINDOWS}<br>
 * {@link MAC}<br>
 * {@link LINUX}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #WINDOWS
 * @see #MAC
 * @see #LINUX
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
}