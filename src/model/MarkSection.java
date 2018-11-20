package model;

import java.util.List;
import java.util.Objects;

public class MarkSection extends AbstractSection {
    private final List<String> markList;

    public MarkSection(List<String> markList) {
        Objects.requireNonNull(markList,"markList must not be null");
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

        return markList.equals(that.markList);
    }

    @Override
    public int hashCode() {
        return markList.hashCode();
    }

    @Override
    public String toString() {
        return markList.toString();
    }
}
