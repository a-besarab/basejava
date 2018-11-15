package model;

public class OrganizationsSections extends Sections {
    private final String orgName;
    private final String orgPeriodStart;
    private final String orgPeriodEnd;
    private final String orgPosition;
    private final String orgDescription;

    public OrganizationsSections(String orgName, String orgPeriodStart, String orgPeriodEnd, String orgPosition, String orgDescription) {
        this.orgName = orgName;
        this.orgPeriodStart = orgPeriodStart;
        this.orgPeriodEnd = orgPeriodEnd;
        this.orgPosition = orgPosition;
        this.orgDescription = orgDescription;
    }

    public String getOrganization() {
        return getOrganization();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationsSections that = (OrganizationsSections) o;

        if (!orgName.equals(that.orgName)) return false;
        if (!orgPeriodStart.equals(that.orgPeriodStart)) return false;
        if (!orgPeriodEnd.equals(that.orgPeriodEnd)) return false;
        if (!orgPosition.equals(that.orgPosition)) return false;
        return orgDescription.equals(that.orgDescription);
    }

    @Override
    public int hashCode() {
        int result = orgName.hashCode();
        result = 31 * result + orgPeriodStart.hashCode();
        result = 31 * result + orgPeriodEnd.hashCode();
        result = 31 * result + orgPosition.hashCode();
        result = 31 * result + orgDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationsSections{" +
                "orgName='" + orgName + '\'' +
                ", orgPeriodStart='" + orgPeriodStart + '\'' +
                ", orgPeriodEnd='" + orgPeriodEnd + '\'' +
                ", orgPosition='" + orgPosition + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                '}';
    }
}
