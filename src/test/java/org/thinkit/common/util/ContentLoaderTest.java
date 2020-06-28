package org.thinkit.common.util;

import org.thinkit.common.rule.Attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public final class ContentLoaderTest {

    /**
     * テスト用アトリビュートリスト
     */
    private static final List<String> TEST_ATTRIBUTE_LIST;

    static {
        final TestContentAttribute[] attributes = TestContentAttribute.values();
        TEST_ATTRIBUTE_LIST = new ArrayList<>(attributes.length);

        for (Attribute attribute : attributes) {
            TEST_ATTRIBUTE_LIST.add(attribute.getString());
        }
    }

    /**
     * テスト用アトリビュートクラス
     */
    private enum TestContentAttribute implements Attribute {
        test1, test2, test3, test4, test5;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Test
    public void testLoadWithSmallSelectionNodes() {

        final List<Map<String, String>> contents = ContentLoader.load("testContentWithSmallSelectionNodes",
                TEST_ATTRIBUTE_LIST);

        assertNotNull(contents);
        assertTrue(!contents.isEmpty());

        for (Map<String, String> content : contents) {
            assertEquals("0", content.get(TestContentAttribute.test1.getString()));
            assertEquals("false", content.get(TestContentAttribute.test2.getString()));
            assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
            assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
            assertEquals("test", content.get(TestContentAttribute.test5.getString()));
        }
    }

    @Test
    public void testLoadWithMediumSelectionNodes() {

        final List<Map<String, String>> contents = ContentLoader.load("testContentWithMediumSelectionNodes",
                TEST_ATTRIBUTE_LIST);

        assertNotNull(contents);
        assertTrue(!contents.isEmpty());

        for (Map<String, String> content : contents) {
            assertEquals("0", content.get(TestContentAttribute.test1.getString()));
            assertEquals("false", content.get(TestContentAttribute.test2.getString()));
            assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
            assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
            assertEquals("test", content.get(TestContentAttribute.test5.getString()));
        }
    }

    @Test
    public void testLoadWithLargeSelectionNodes() {

        final List<Map<String, String>> contents = ContentLoader.load("testContentWithLargeSelectionNodes",
                TEST_ATTRIBUTE_LIST);

        assertNotNull(contents);
        assertTrue(!contents.isEmpty());

        for (Map<String, String> content : contents) {
            assertEquals("0", content.get(TestContentAttribute.test1.getString()));
            assertEquals("false", content.get(TestContentAttribute.test2.getString()));
            assertEquals("0L", content.get(TestContentAttribute.test3.getString()));
            assertEquals("1.0", content.get(TestContentAttribute.test4.getString()));
            assertEquals("test", content.get(TestContentAttribute.test5.getString()));
        }
    }

    @Test
    public void testLoadWhenAttributeListIsEmpty() {

        final Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ContentLoader.load("test", new ArrayList<>(0)));
        assertNotNull(exception);
        assertEquals("wrong parameter was given. Attribute is required.", exception.getMessage());
    }

    @Test
    public void testLoadWhenContentNameIsEmpty() {

        final Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ContentLoader.load("", TEST_ATTRIBUTE_LIST));
        assertNotNull(exception);
        assertEquals("wrong parameter was given. Content name is required.", exception.getMessage());
    }

    @Test
    public void testLoadWithConditions() {

    }
}