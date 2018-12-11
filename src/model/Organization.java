package model;

import util.DateUtil;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private Organization.Content[] content;

    public Link getHomePage() {
        return homePage;
    }

    public Content[] getContent() {
        return content;
    }

    public Organization() {
    }

    public Organization(String name, String url, Organization.Content... content) {
        this.homePage = new Link(name, url);
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, content);
    }

    @Override
    public String toString() {
        return "Organization: " + homePage + Arrays.toString(content);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Content implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private static LocalDate periodStart;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private static LocalDate periodEnd;
        private static String position;
        private static String description;

        public Content() {
        }

        public Content(LocalDate periodStart, LocalDate periodEnd, String position, String description) {
            Objects.requireNonNull(periodStart, "periodStart must not be null");
            Objects.requireNonNull(periodEnd, "periodEnd must not be null");
            Objects.requireNonNull(position, "position must not be null");
            Content.periodStart = periodStart;
            Content.periodEnd = periodEnd;
            Content.position = position;
            Content.description = description;
        }

        public static LocalDate getPeriodStart() {
            return periodStart;
        }

        public static LocalDate getPeriodEnd() {
            return periodEnd;
        }

        public static String getPosition() {
            return position;
        }

        public static String getDescription() {
            return description;
        }

        public Content(int yearStart, Month monthStart, int yearEnd, Month monthEnd, String position, String description) {
            this(DateUtil.of(yearStart, monthStart), DateUtil.of(yearEnd, monthEnd), position, description);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Content content = (Content) o;
            return Objects.equals(periodStart, Content.periodStart) && Objects.equals(periodEnd, Content.periodEnd) && Objects.equals(position, Content.position) &&
                    Objects.equals(description, Content.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(periodStart, periodEnd, position, description);
        }

        @Override
        public String toString() {
            return " From " + periodStart + " To " + periodEnd + " Position: " + position + " Description: " + description;
        }
    }

}
