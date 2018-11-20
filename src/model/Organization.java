package model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final LocalDate periodStart;
    private final LocalDate periodEnd;
    private final String position;
    private final String description;

    public Organization(String name, String url, LocalDate periodStart, LocalDate periodEnd, String position, String description) {
        Objects.requireNonNull(periodStart, "periodStart must not be null");
        Objects.requireNonNull(periodEnd, "periodEnd must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.homePage = new Link(name, url);
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!periodStart.equals(that.periodStart)) return false;
        if (!periodEnd.equals(that.periodEnd)) return false;
        if (!position.equals(that.position)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + periodStart.hashCode();
        result = 31 * result + periodEnd.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization: " + homePage + ',' + periodStart + ',' + periodEnd + ',' + position + ',' + description;
    }
}
