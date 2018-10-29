package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void doSave(Resume resume) {
        int index = getPosition(resume.getUuid());

        if (counter < STORAGE_LIMIT) {
            insertNewElement(resume, index);
            counter++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

    }

    public void doDelete(String uuid) {
        int index = getPosition(uuid);
            deleteOldElement(index);
            storage[counter - 1] = null;
            counter--;
    }

    public int size() {
        return counter;
    }

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    public void doUpdate(Resume resume) {
        int index = getPosition(resume.getUuid());
        storage[index] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counter);
    }

    public Resume doGet(String uuid) {
        int index = getPosition(uuid);
        return storage[index];
    }

    protected abstract int getPosition(String uuid);

    protected abstract void insertNewElement(Resume resume, int index);

    protected abstract void deleteOldElement(int index);
}
