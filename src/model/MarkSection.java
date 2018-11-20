package model;

import java.util.List;

public class MarkSection extends AbstractSection {
    private final List<String> markList;

    public MarkSection(List<String> markList) {
        this.markList = markList;
    }

    public List<String> getMarkList() {
        return markList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkSection that = (MarkSection) o;

        return markList != null ? markList.equals(that.markList) : that.markList == null;
    }

    @Override
    public int hashCode() {
        return markList != null ? markList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return markList.toString();
    }
}
