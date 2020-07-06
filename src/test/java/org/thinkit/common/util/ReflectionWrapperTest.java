package org.thinkit.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class ReflectionWrapperTest {

    /**
     * {@link ReflectionWrapper#invoke(String)} メソッドのテストメソッドを定義するテストクラスです。
     * {@link ReflectionWrapper#invoke(String)} はprivateメソッドです。
     * 
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestInvoke {

        /**
         * <code>"success"</code> の文字列定数
         */
        private static final String STR_SUCCESS = "success";

        /**
         * <code>"failure"</code> の文字列定数
         */
        private static final String STR_FAILURE = "failure";

        @Test
        void testReturnStringWithNoArgument() {
            final ReflectionWrapper<String> reflection = new ReflectionWrapper<>(ReflectionTestDataSet.class);
            final String actualResult = reflection.invoke("returnStringWithNoArgument");

            assertNotNull(actualResult);
            assertTrue(!actualResult.isEmpty());
            assertEquals(STR_SUCCESS, actualResult);
        }
    }
}