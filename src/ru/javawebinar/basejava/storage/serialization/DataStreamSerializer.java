package ru.javawebinar.basejava.storage.serialization;


import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeWithException(dos, resume.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeWithException(dos, resume.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(entry.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeWithException(dos, ((MarkSection) entry.getValue()).getMarkList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(dos, ((OrganizationSection) entry.getValue()).getOrganization(), org -> {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            writeWithException(dos, org.getContent(), section -> {
                                writeDate(dos, section.getPeriodStart());
                                writeDate(dos, section.getPeriodEnd());
                                dos.writeUTF(section.getPosition());
                                dos.writeUTF(section.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @FunctionalInterface
    private interface WriteInterface<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, WriteInterface<T> writeInterface) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writeInterface.write(item);
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate localdate) throws IOException {
        dos.writeInt(localdate.getYear());
        dos.writeInt(localdate.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            readWithException(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            });
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
                return new MarkSection(readListWithException(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(readListWithException(dis,
                        () -> new Organization(new Link(dis.readUTF(), dis.readUTF()), readListWithException(dis, () ->
                                new Organization.Content(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()
                                ))
                        )));
            default:
                throw new IllegalStateException();
        }
    }

    @FunctionalInterface
    private interface ReadInterface {
        void read() throws IOException;
    }

    private void readWithException(DataInputStream dis, ReadInterface readInterface) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            readInterface.read();
        }
    }

    @FunctionalInterface
    private interface ReadListInterface<T> {
        T read() throws IOException;
    }

    private <T> List<T> readListWithException(DataInputStream dis, ReadListInterface<T> readList) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(readList.read());
        }
        return list;
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return DateUtil.of(dis.readInt(), Month.of(dis.readInt()));
    }
}