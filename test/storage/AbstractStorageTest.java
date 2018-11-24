package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DateUtil;

import java.time.Month;
import java.util.*;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String NAME_1 = "name1";
    private static final Resume resume1 = new Resume(UUID_1, NAME_1);
    private static final String UUID_2 = "uuid2";
    private static final String NAME_2 = "name2";
    private static final Resume resume2 = new Resume(UUID_2, NAME_2);
    private static final String UUID_3 = "uuid3";
    private static final String NAME_3 = "name3";
    private static final Resume resume3 = new Resume(UUID_3, NAME_3);
    private static final String UUID_4 = "uuid4";
    private static final String NAME_4 = "name4";
    private static final Resume resume4 = new Resume(UUID_4, NAME_4);

    static {
//        Map<ContactType, String> contact = new EnumMap<>(ContactType.class);
//        Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);
//        List<String> markedList = new ArrayList<>();
//        List<Organization> organizationList = new ArrayList<>();
//
//        contact.put(ContactType.TEL, "89998887766");
//        contact.put(ContactType.SKYPE, "SkYpe.skype");
//        contact.put(ContactType.EMAIL, "mail@yandex.ru");
//        contact.put(ContactType.LINKEDIN, "linkId");
//        contact.put(ContactType.GITHUB, "GitHub");
//        contact.put(ContactType.STACKOVERFLOW, "StOver");
//        contact.put(ContactType.HOMEPAGE, "HomePage");
//
//        markedList.add("Content marked list 1");
//        markedList.add("Content marked list 2");
//
//        Organization.Content cont_1 = new Organization.Content(DateUtil.of(1999, Month.JANUARY), DateUtil.of(2008, Month.AUGUST), "Test position", "Description");
//        Organization.Content cont_2 = new Organization.Content(DateUtil.of(2008, Month.SEPTEMBER), DateUtil.of(2018, Month.DECEMBER), "Test 2 position", "Description 2");
//
//        organizationList.add(new Organization("OOO 1", "url_1", cont_1, cont_2));
//        organizationList.add(new Organization("OOO 2", "url_2", cont_2));
//
//        section.put(SectionType.OBJECTIVE, new TextSection("Test section for Objective"));
//        section.put(SectionType.PERSONAL, new TextSection("Test section for Personal"));
//        section.put(SectionType.ACHIEVEMENT, new MarkSection(markedList));
//        section.put(SectionType.QUALIFICATIONS, new MarkSection(markedList));
//        section.put(SectionType.EXPERIENCE, new OrganizationSection(organizationList));
//        section.put(SectionType.EDUCATION, new OrganizationSection(organizationList));
//
//        resume1.setContact(contact);
//        resume1.setSection(section);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(resume4, storage.get(resume4.getUuid()));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume3);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list1 = storage.getAllSorted();
        Assert.assertEquals(3, list1.size());
        Assert.assertEquals(list1, Arrays.asList(resume1, resume2, resume3));
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1, NAME_1);
        storage.update(resume);
        Assert.assertSame(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}