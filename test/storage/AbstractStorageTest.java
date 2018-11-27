package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

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
    private static final Resume resume4;

    static {
        resume4 = new Resume(UUID_4, NAME_4);
        resume4.setContact(ContactType.EMAIL, "mail@mail.com");
        resume4.setContact(ContactType.TEL, "1232455");
        resume4.setContact(ContactType.GITHUB, "git");
        resume4.setContact(ContactType.HOMEPAGE, "homePage");
        resume4.setSection(SectionType.OBJECTIVE, new TextSection("Objective"));
        resume4.setSection(SectionType.PERSONAL, new TextSection("Personal"));
        resume4.setSection(SectionType.ACHIEVEMENT, new MarkSection("Ach1", "Ach2", "Ach3"));
        resume4.setSection(SectionType.QUALIFICATIONS, new MarkSection("Qual1", "Qual2", "Qual3"));
        resume4.setSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("OOO", "url1",
                        new Organization.Content(1999, Month.AUGUST, 2002, Month.AUGUST, "position1", "Description1"),
                        new Organization.Content(2002, Month.SEPTEMBER, 2015, Month.OCTOBER, "position2", "Description2")),
                new Organization("ZAO", "url2",
                        new Organization.Content(2015, Month.OCTOBER, 2018, Month.DECEMBER, "position3", "Description3"))));
        resume4.setSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", "url",
                        new Organization.Content(1994, Month.SEPTEMBER, 1999, Month.JUNE, "position", "Description")),
                new Organization("ZAO", "url2",
                        new Organization.Content(1984, Month.SEPTEMBER, 1994, Month.JUNE, "position", "Description"))));


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