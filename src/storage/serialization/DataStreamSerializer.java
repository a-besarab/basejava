package storage.serialization;


import model.*;

import java.io.*;
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
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case PERSONAL:
                        break;
                    case ACHIEVEMENT:
                        break;
                    case QUALIFICATIONS:
                        break;
                    case EXPERIENCE:
                        break;
                    case EDUCATION:
                        break;
                }

            }
            //TODO
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
            int size1 = dis.readInt();
            for (int i = 0; i < size1; i++) {
                resume.setSection(SectionType.valueOf(dis.readUTF()), new OrganizationSection(
                        new Organization(dis.readUTF(), dis.readUTF(),
                                new Organization.Content(java.time.LocalDate.parse(dis.readUTF()), java.time.LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()))));
            }

//TODO
            return resume;
        }
    }
}
