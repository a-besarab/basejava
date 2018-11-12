package model;

import java.util.List;

public class MarckedSections extends Sections {
    private final List<String> marckedList;

    public MarckedSections(List<String> marckedList) {
        this.marckedList = marckedList;
    }

    public List<String> getMarckedList() {
        return marckedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarckedSections that = (MarckedSections) o;

        return marckedList != null ? marckedList.equals(that.marckedList) : that.marckedList == null;
    }

    @Override
    public int hashCode() {
        return marckedList != null ? marckedList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return marckedList.toString();
    }
}
