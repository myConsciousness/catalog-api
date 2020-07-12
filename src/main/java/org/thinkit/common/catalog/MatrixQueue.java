/**
 * Project Name : dev-utils<br>
 * File Name : MatrixQueue.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/12<br>
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
 * マトリクスの行列区分を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum MatrixQueue implements Catalog<MatrixQueue> {

    /**
     * 行
     */
    ROW(0),

    /**
     * 列
     */
    COLUMN(1);

    /**
     * コード値
     */
    @Getter
    private final int code;
}