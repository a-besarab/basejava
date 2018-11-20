package model;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private Map<ContactType, String> contact = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

    public void setContact(Map<ContactType, String> contact) {
        this.contact = contact;
    }

    public void setSection(Map<SectionType, AbstractSection> section) {
        this.section = section;
    }

    public Map<ContactType, String> getContact() {
        return contact;
    }

    public Map<SectionType, AbstractSection> getSection() {
        return section;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (uuid != null ? !uuid.equals(resume.uuid) : resume.uuid != null) return false;
        if (fullName != null ? !fullName.equals(resume.fullName) : resume.fullName != null) return false;
        if (contact != null ? !contact.equals(resume.contact) : resume.contact != null) return false;
        return section != null ? section.equals(resume.section) : resume.section == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        int result = this.fullName.compareTo(o.fullName);
        if (result == 0) {
            result = this.uuid.compareTo(o.uuid);
        }
        return result;
    }
}
