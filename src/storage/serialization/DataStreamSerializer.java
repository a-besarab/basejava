package storage.serialization;


import model.*;
import util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
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

            writeWithException(dos, contacts.entrySet(), writeInterface -> {
                dos.writeUTF(writeInterface.getKey().name());
                dos.writeUTF(writeInterface.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());

            writeWithException(dos, sections.entrySet(), writeInterface -> {
                SectionType type = writeInterface.getKey();
                dos.writeUTF(writeInterface.getKey().name());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(writeInterface.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        MarkSection markSection = (MarkSection) writeInterface.getValue();
                        dos.writeInt(markSection.getMarkList().size());
                        writeWithException(dos, markSection.getMarkList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) writeInterface.getValue();
                        dos.writeInt(organizationSection.getOrganization().size());
                        for (Organization organization : organizationSection.getOrganization()) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            dos.writeInt(organization.getContent().size());
                            writeContent(dos, organization.getContent());
                        }
                        break;
                }
            });
        }
    }

    @FunctionalInterface
    private interface WriteInterface<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> contacts, WriteInterface<T> writeInterface) throws IOException {
        for (T item : contacts) {
            writeInterface.write(item);
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
                    organizationSection.add(new Organization(new Link(dis.readUTF(), dis.readUTF()), readContent(dis)));
                }
                return new OrganizationSection(organizationSection);
            default:
                throw new IllegalStateException();
        }
    }

    private void writeContent(DataOutputStream dos, List<Organization.Content> contentList) throws IOException {
        for (Organization.Content aContentList : contentList) {
            writeDate(dos, aContentList.getPeriodStart());
            writeDate(dos, aContentList.getPeriodEnd());
            dos.writeUTF(aContentList.getPosition());
            dos.writeUTF(aContentList.getDescription());
        }
    }

    private List<Organization.Content> readContent(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization.Content> contentList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            contentList.add(new Organization().new Content(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()));
        }
        return contentList;
    }

    private void writeDate(DataOutputStream dos, LocalDate localdate) throws IOException {
        dos.writeInt(localdate.getYear());
        dos.writeInt(localdate.getMonth().getValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return DateUtil.of(dis.readInt(), Month.of(dis.readInt()));
    }
}