package model;

import util.DateUtil;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<Content> content = new ArrayList<>();

    public Organization() {
    }

    public Organization(Link homePage, List<Content> content) {
        this.homePage = homePage;
        this.content = content;
    }

    public Organization(String name, String url, Content... content) {
        this(new Link(name, url), Arrays.asList(content));
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Content> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && content.equals(that.content);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization: " + homePage + content.toString();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public class Content implements Serializable {
        private final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate periodStart;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate periodEnd;
        private String position;
        private String description;

        public Content() {
        }

        public Content(LocalDate periodStart, LocalDate periodEnd, String position, String description) {
            Objects.requireNonNull(periodStart, "periodStart must not be null");
            Objects.requireNonNull(periodEnd, "periodEnd must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.periodStart = periodStart;
            this.periodEnd = periodEnd;
            this.position = position;
            this.description = description == null ? "" : description;
        }

        public Content(int yearStart, Month monthStart, int yearEnd, Month monthEnd, String position, String description) {
            this(DateUtil.of(yearStart, monthStart), DateUtil.of(yearEnd, monthEnd), position, description);
        }

        public LocalDate getPeriodStart() {
            return periodStart;
        }

        public LocalDate getPeriodEnd() {
            return periodEnd;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Content content = (Content) o;
            return periodStart.equals(content.periodStart) && periodEnd.equals(content.periodEnd) && position.equals(content.position) && description.equals(content.description);
        }

        @Override
        public int hashCode() {
            int result = (int) (serialVersionUID);
            result = 31 * result + periodStart.hashCode();
            result = 31 * result + periodEnd.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return " From " + periodStart + " To " + periodEnd + " Position: " + position + " Description: " + description;
        }
    }

}
