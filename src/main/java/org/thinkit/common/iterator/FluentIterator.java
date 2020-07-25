package org.thinkit.common.iterator;

import java.util.Iterator;
import java.util.List;

import lombok.NonNull;

public final class FluentIterator<T> implements Iterator<T> {

    private List<T> nodes;

    private int size;

    private int cursorIndex;

    private FluentIterator() {
    }

    private FluentIterator(@NonNull IterableNode<T> iterableNode) {
        this.nodes = iterableNode.nodes();
        this.size = iterableNode.size();
        this.cursorIndex = 0;
    }

    public static <T> FluentIterator<T> of(@NonNull IterableNode<T> iterableNode) {
        return new FluentIterator<>(iterableNode);
    }

    @Override
    public T next() {
        return nodes.get(++cursorIndex);
    }

    @Override
    public boolean hasNext() {
        return this.size > this.cursorIndex;
    }
}