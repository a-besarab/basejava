package model;

public class OrganizationSection extends Section {
    private final String name;
    private final String periodStart;
    private final String periodEnd;
    private final String position;
    private final String description;

    public OrganizationSection(String name, String periodStart, String periodEnd, String position, String description) {
        this.name = name;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.position = position;
        this.description = description;
    }

    public String getOrganization() {
        return getOrganization();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        if (!name.equals(that.name)) return false;
        if (!periodStart.equals(that.periodStart)) return false;
        if (!periodEnd.equals(that.periodEnd)) return false;
        if (!position.equals(that.position)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + periodStart.hashCode();
        result = 31 * result + periodEnd.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "name='" + name + '\'' +
                ", periodStart='" + periodStart + '\'' +
                ", periodEnd='" + periodEnd + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
