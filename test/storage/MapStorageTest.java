package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapStorageTest {

    private AbstractStorage map = new MapStorage();

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
        map.save(resume1);
        map.save(resume2);
        map.save(resume3);
    }

    @Test
    public void doSave() {
        map.save(resume4);
        Assert.assertEquals(resume4, map.get(resume4.getUuid()));
        Assert.assertEquals(4, map.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        map.save(resume3);
    }

    @Test
    public void doGet() {
        Assert.assertEquals(resume1, map.get(resume1.getUuid()));
    }

    @Test
    public void getAll() {
        Resume arrayResumeReceived[] = map.getAll();
        Resume arrayResumeOriginal[] = new Resume[]{resume1, resume2, resume3};
        Assert.assertEquals(arrayResumeOriginal.length, arrayResumeReceived.length);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        map.get(UUID_4);
    }

    @Test
    public void getIndex() {
        Assert.assertEquals(UUID_2, map.getIndex(UUID_2));
    }

    @Test
    public void doUpdate() {
        Resume resume = new Resume(UUID_1);
        map.update(resume);
        Assert.assertSame(resume, map.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        map.update(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void doDelete() {
        map.delete(UUID_1);
        Assert.assertEquals(2, map.size());
        map.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        map.delete(UUID_4);
    }

    @Test
    public void clear() {
        map.clear();
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, map.size());
    }
}