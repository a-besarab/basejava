package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListStorageTest {

    private AbstractStorage list = new ListStorage();

    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume resume4 = new Resume(UUID_4);

    @Before
    public void setUp() {
        list.save(resume1);
        list.save(resume2);
        list.save(resume3);
    }

    @Test
    public void clear() {
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void doSave() {
        list.save(resume4);
        Assert.assertEquals(resume4, list.get(resume4.getUuid()));
        Assert.assertEquals(4, list.size());
    }

    @Test(expected = ExistStorageException.class)
    public void isExist() {
        list.save(resume3);
    }

    @Test
    public void doUpdate() {
        Resume resume = new Resume(UUID_1);
        list.update(resume);
        Assert.assertSame(resume, list.get(resume.getUuid()));
    }

    @Test
    public void doGet() {
        Assert.assertEquals(resume1, list.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void doDelete() {
        list.delete(UUID_1);
        Assert.assertEquals(2, list.size());
        list.get(UUID_1);
    }

    @Test
    public void getAll() {
        Resume arrayResumeReceived[] = list.getAll();
        Resume arrayResumeOriginal[] = new Resume[]{resume1, resume2, resume3};
        Assert.assertEquals(arrayResumeOriginal.length, arrayResumeReceived.length);
        Assert.assertArrayEquals(arrayResumeOriginal, arrayResumeReceived);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(1, list.getIndex(UUID_2));
    }
}