package com.globallogic.psv;

import java.util.*;

public class PSVHashSetImpl<E> implements Set<E> {

    static class Node<E> {
        E element;

        Node(E element) {
            this.element = element;
        }

        public final E getElement() {
            return element;
        }

        public final String toString() {
            if (element == null) {
                return "null";
            }
            return element.toString();
        }

        public final int hashCode() {
            if (element == null) {
                return 0;
            }
            return element.hashCode();
        }

        public final boolean equals(Object o) {
            return Objects.equals(element, o);
        }
    }

    Node<E>[] table;

    public PSVHashSetImpl() {
        this.table = new Node[0];
    }

    @Override
    public boolean add(Object element) {
        if (table == null) {
            table = new Node[0];
        }
        if (element == null && isTableHasOneNull()) {
            return false;
        }
        if (table.length == 0) {
            table = new Node[1];
            table[table.length - 1] = new Node(element);
            return true;
        }
        if (!containsSameElement(element)) {
            Node<E>[] oldTable = table;
            Node<E>[] newTable;
            newTable = Arrays.copyOf(oldTable, table.length + 1);
            newTable[table.length] = new Node(element);
            table = newTable;
        }
        return table[table.length - 1] != null;
    }

    private boolean isTableHasOneNull() {
        for (Node<E> elem : table) {
            if (elem.element == null) {
                return true;
            }
        }
        return false;
    }

    public E getElement(E element) {
        if (table == null) {
            return null;
        }
        if (table.length == 0) {
            return null;
        } else if (table.length > 0 && containsSameElement(element)) {
            return element;
        }
        return null;
    }

    @Override
    public boolean contains(Object element) {
        if (table != null && table.length != 0) {
            return containsSameElement(element);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (table == null) {
            return false;
        } else if (table.length == 0) {
            return false;
        } else if (table.length > 0 && containsSameElement(element)) {
            int sameElementIndex = getSameElementIndex(element);
            Node<E>[] newTable = new Node[table.length - 1];
            for (int i = 0, k = 0; i < table.length; i++) {
                if (i == sameElementIndex) {
                    continue;
                }
                newTable[k++] = table[i];
            }
            table = newTable;
            return true;
        }
        return false;
    }

    private int getSameElementIndex(Object element) {
        int index = -1;
        for (Node<E> elem : table) {
            ++index;
            if (element == null && elem.element == null) {
                return index;
            } else if (element != null) {
                if (elem.hashCode() == element.hashCode() && elem.equals(element)) {
                    return index;
                }
            }
        }
        return index;
    }

    public boolean isEmpty() {
        if (table != null && table.length > 0) {
            return false;
        } else
            return true;
    }

    public void clear() {
        Node<E>[] tab = new Node[0];
        table = tab;
    }

    private boolean containsSameElement(Object element) {
        for (Node<E> elem : table) {
            if (element == null && elem.element == null) {
                return true;
            } else if (element != null) {
                if (elem.hashCode() == element.hashCode() && elem.equals(element))
                    return true;
            }
        }
        return false;
    }

    public int size() {
        if (table != null && table.length > 0) {
            return table.length;
        }
        return 0;
    }

    public String toString() {
        StringBuilder setString = new StringBuilder();
        if (table != null && table.length > 0) {
            setString = setString.append("{");
            for (int i = 0; i < table.length; i++) {
                setString = setString.append(table[table.length - i - 1]).append(";");
            }
            setString = setString.append("}");
        }
        return setString.deleteCharAt(setString.lastIndexOf(";")).toString();
    }
}
