package storage.serialization;


import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType type = entry.getKey();
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        MarkSection markSection = (MarkSection) entry.getValue();
                        dos.writeUTF(entry.getKey().name());
                        dos.writeInt(markSection.getMarkList().size());
                        for (String str : markSection.getMarkList()) {
                            dos.writeUTF(str);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        dos.writeUTF(entry.getKey().name());
                        dos.writeInt(organizationSection.getOrganization().size());
                        for (Organization organization : organizationSection.getOrganization()) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            dos.writeInt(organization.getContent().length);
                            writeContent(dos, organization);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            }
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                int sizeMarkSection = dis.readInt();
                List<String> markSection = new ArrayList<>();
                for (int i = 0; i < sizeMarkSection; i++) {
                    markSection.add(dis.readUTF());
                }
                return new MarkSection(markSection);
            case EXPERIENCE:
            case EDUCATION:
                int sizeOrganizationSection = dis.readInt();
                List<Organization> organizationSection = new ArrayList<>();
                for (int i = 0; i < sizeOrganizationSection; i++) {
                    organizationSection.add(new Organization(dis.readUTF(), dis.readUTF(), readContent(dis)));
                }
                return new OrganizationSection(organizationSection);
            default:
                throw new IllegalStateException();
        }
    }

    private void writeContent(DataOutputStream dos, Organization org) throws IOException {
        for (int i = 0; i < org.getContent().length; i++) {
            dos.writeInt(Organization.Content.getPeriodStart().getYear());
            dos.writeInt(Organization.Content.getPeriodEnd().getMonth().getValue());
            dos.writeInt(Organization.Content.getPeriodStart().getYear());
            dos.writeInt(Organization.Content.getPeriodEnd().getMonth().getValue());
            dos.writeUTF(Organization.Content.getPosition());
            dos.writeUTF(Organization.Content.getDescription());
        }
    }

    private Organization.Content[] readContent(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        Organization.Content[] content = new Organization.Content[size];
        for (int i = 0; i < size; i++) {
            content[i] = new Organization.Content(LocalDate.of(dis.readInt(), dis.readInt(), 1),
                    LocalDate.of(dis.readInt(), dis.readInt(), 1), dis.readUTF(), dis.readUTF());
        }
        return content;
    }
}