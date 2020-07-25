package org.thinkit.common.iterator;

import java.util.List;

public interface IterableNode<T> {

    public int size();

    public List<T> nodes();
}