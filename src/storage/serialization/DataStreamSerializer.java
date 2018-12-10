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

//            Map<SectionType, AbstractSection> sections = resume.getSections();
//            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                SectionType type = entry.getKey();
                switch (type) {
                    case OBJECTIVE:
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case PERSONAL:
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                        MarkSection achievement = (MarkSection) entry.getValue();
                        dos.writeInt(achievement.getMarkList().size());
                        dos.writeUTF(entry.getKey().name());
                        for (String str : achievement.getMarkList()) {
                            dos.writeUTF(str);
                        }
                        break;
                    case QUALIFICATIONS:
                        MarkSection qualifications = (MarkSection) entry.getValue();
                        dos.writeInt(qualifications.getMarkList().size());
                        dos.writeUTF(entry.getKey().name());
                        for (String str : qualifications.getMarkList()) {
                            dos.writeUTF(str);
                        }
                        break;
                    case EXPERIENCE:
                        OrganizationSection experience = (OrganizationSection) entry.getValue();
                        dos.writeInt(experience.getOrganization().size());
                        dos.writeUTF(entry.getKey().name());
                        for (Organization org : experience.getOrganization()) {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            dos.writeInt(org.getContent().length);
                            for (Object str : org.getContent()) {
                                dos.writeUTF(str.toString());
                            }
                        }
                        break;
                    case EDUCATION:
                        OrganizationSection education = (OrganizationSection) entry.getValue();
                        dos.writeInt(education.getOrganization().size());
                        dos.writeUTF(entry.getKey().name());
                        for (Organization org : education.getOrganization()) {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            dos.writeInt(org.getContent().length);
                            for (Object str : org.getContent()) {
                                dos.writeUTF(str.toString());
                            }
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
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

         //   int size2 = dis.readInt();


            SectionType sectionType = SectionType.valueOf(dis.readUTF());
         //   for (int i = 0; i < size2; i++) {


            resume.setSection(sectionType, readSection(dis, sectionType));
           // resume.setSection(sectionType, readSection(dis, sectionType));
            //}

            System.out.println(resume.getFullName());
            for (ContactType type : ContactType.values()) {
                System.out.println(resume.getContact(type));
            }
            for (SectionType type : SectionType.values()) {
                System.out.println(resume.getSection(type));
            }
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
                //return new TextSection(dis.readUTF());
            case PERSONAL:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
                int size = dis.readInt();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(dis.readUTF());
                }
                return new MarkSection(list);
            case QUALIFICATIONS:
                int size2 = dis.readInt();
                List<String> list2 = new ArrayList<>();
                for (int i = 0; i < size2; i++) {
                    list2.add(dis.readUTF());
                }
                return new MarkSection(list2);
            case EXPERIENCE:
                int size3 = dis.readInt();
                List<Organization> list3 = new ArrayList<>();
                for (int i = 0; i < size3; i++) {
                    list3.add(new Organization(dis.readUTF(), dis.readUTF(), content(dis)));
                }
                return new OrganizationSection(list3);
            case EDUCATION:
                int size4 = dis.readInt();
                List<Organization> list4 = new ArrayList<>();
                for (int i = 0; i < size4; i++) {
                    list4.add(new Organization(dis.readUTF(), dis.readUTF(), content(dis)));
                }
                return new OrganizationSection(list4);
            default:
                throw new IllegalStateException();
        }
    }

    private Organization.Content[] content(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        Organization.Content[] content = new Organization.Content[size];
        for (int i = 0; i < size; i++) {
            content[i] = new Organization.Content(LocalDate.of(dis.readInt(), dis.readInt(), 1),
                    LocalDate.of(dis.readInt(), dis.readInt(), 1), dis.readUTF(), dis.readUTF());
        }
        return content;
    }
}
