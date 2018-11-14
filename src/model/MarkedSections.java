package model;

import storage.ListStorage;

import java.util.List;

public class MarkedSections extends Sections {
    private final List<String> markedList;

    public MarkedSections(List<String> markedList) {
        this.markedList = markedList;
    }

    public List<String> getMarkedList() {
        return markedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkedSections that = (MarkedSections) o;

        return markedList != null ? markedList.equals(that.markedList) : that.markedList == null;
    }

    @Override
    public int hashCode() {
        return markedList != null ? markedList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return markedList.toString();
    }
}
