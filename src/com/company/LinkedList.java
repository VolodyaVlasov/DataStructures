package com.company;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private Item<T> firstInList = null;
    private Item<T> lastInList = null;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[size()];
        Item<T> start = firstInList;
        int i = 0;
        while (start != null) {
            array[i++] = start.element;
            start = start.nextItem;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size()) {
            a = Arrays.copyOf(a, size());
        }
        Item<T> start = firstInList;
        int i = 0;
        while (start != null) {
            a[i] = (T1) start.element;
            start = start.nextItem;
            i++;
        }
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }

    @Override
    public boolean add(final T newElement) {
        final Item<T> item = new Item<>(newElement, lastInList, null);
        if (firstInList == null) {
            firstInList = item;
        } else {
            lastInList.nextItem = item;
        }
        lastInList = item;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object o) {
        Item<T> start = firstInList;
        if (o == null) {
            while (start != null) {
                if (start.element == null) {
                    remove(start);
                    return true;
                }
                start = start.nextItem;
            }
        } else {
            while (start != null) {
                if (o.equals(start.element)) {
                    remove(start);
                    return true;
                }
                start = start.nextItem;
            }
        }
        return false;
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        Item<T> item = getItemByIndex(index);
        remove(item);
        return item.element;
    }

    private void remove(final Item<T> current) {
        if (current.prevItem != null) {
            current.prevItem.nextItem = current.nextItem;
        } else {
            firstInList = current.nextItem;
        }
        if (current.nextItem != null) {
            current.nextItem.prevItem = current.prevItem;
        } else {
            lastInList = current.prevItem;
        }
        size--;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        this.removeIf(item -> !c.contains(item));
        return true;
    }

    @Override
    public void clear() {
        firstInList = null;
        lastInList = null;
        size = 0;
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object o) {
        Item<T> start = firstInList;
        int index = 0;
        if (o == null) {
            while (start != null) {
                if (start.element == null) {
                    return index;
                }
                start = start.nextItem;
                index++;
            }
        } else {
            while (start != null) {
                if (o.equals(start.element)) {
                    return index;
                }
                start = start.nextItem;
                index++;
            }
        }
        return -1;
    }

    @Override
    public T set(final int index, final T element) throws IndexOutOfBoundsException {
        checkIndex(index);
        Item<T> item = getItemByIndex(index);
        T e = item.element;
        item.element = element;
        return e;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(final int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return getItemByIndex(index).element;
    }

    private Item<T> getItemByIndex(final int index) {
        Item<T> start = firstInList;
        for (int i = 0; i < index; i++) {
            start = start.nextItem;
        }
        return start;
    }

    private class ElementsIterator implements ListIterator<T> {
        private Item<T> currentItemInIterator;
        private Item<T> lastReturnedItemFromIterator;
        private int index;

        ElementsIterator(final int index) {
            currentItemInIterator = index < size() ? getItemByIndex(index) : null;
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedItemFromIterator = currentItemInIterator;
            currentItemInIterator = currentItemInIterator.nextItem;
            index++;
            return lastReturnedItemFromIterator.element;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            currentItemInIterator = currentItemInIterator == null ?
                    lastInList : currentItemInIterator.prevItem;
            lastReturnedItemFromIterator = currentItemInIterator;
            index--;
            return currentItemInIterator.element;
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            if(lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            lastReturnedItemFromIterator.element = element;

        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public void remove() {
            if(lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(lastReturnedItemFromIterator);
            index--;
            lastReturnedItemFromIterator = null;

        }
    }

    private static class Item<T> {
        private T element;
        private Item<T> nextItem;
        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}