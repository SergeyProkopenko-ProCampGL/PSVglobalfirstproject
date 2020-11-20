package com.globallogic.psv;

import java.util.Arrays;
import java.util.Objects;

public class PSVHashSetImpl<E> {

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
                return "";
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

    public boolean addElement(E element) {
        if (table == null) {
            table = new Node[0];
        }
        if (element == null && isTableHasOneNull()) {
            return false;
        }
        if (table.length == 0) {
            table = new Node[1];
            table[table.length - 1] = new Node<E>(element);
            return true;
        }
        if ( !containsSameElement(element)) {
                Node<E>[] oldTable = table;
                Node<E>[] newTable;
                newTable = Arrays.copyOf(oldTable, table.length + 1);
                newTable[table.length] = new Node<E>(element);
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

    public boolean contains(E element) {
        if (table != null && table.length != 0) {
            return containsSameElement(element);
        }
        return false;
    }

    public boolean removeElement(E element) {
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

    private int getSameElementIndex(E element) {
        int index = -1;
        for (Node<E> elem : table) {
            ++index;
            if (elem.hashCode() == element.hashCode() && elem.equals(element))
                return index;
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

    private boolean containsSameElement(E element) {
        for (Node<E> elem : table) {
            if (element == null) continue;
            if (elem.hashCode() == element.hashCode() && elem.equals(element))
                return true;
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
