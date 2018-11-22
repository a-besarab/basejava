package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final Organization.Content[] content;

    public Organization(String name, String url, Organization.Content... list) {
        this.homePage = new Link(name, url);
        this.content = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return "Organization: " + homePage + Arrays.toString(content);
    }

    public static class Content {
        private final LocalDate periodStart;
        private final LocalDate periodEnd;
        private final String position;
        private final String description;

        public Content(LocalDate periodStart, LocalDate periodEnd, String position, String description) {
            Objects.requireNonNull(periodStart, "periodStart must not be null");
            Objects.requireNonNull(periodEnd, "periodEnd must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.periodStart = periodStart;
            this.periodEnd = periodEnd;
            this.position = position;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Content content = (Content) o;

            if (!periodStart.equals(content.periodStart)) return false;
            if (!periodEnd.equals(content.periodEnd)) return false;
            if (!position.equals(content.position)) return false;
            return description != null ? description.equals(content.description) : content.description == null;
        }

        @Override
        public int hashCode() {
            int result = periodStart.hashCode();
            result = 31 * result + periodEnd.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return " From " + periodStart + " To " + periodEnd + " Position: " + position + " Description: " + description;
        }
    }

}
