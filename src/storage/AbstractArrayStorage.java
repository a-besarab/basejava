package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
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
        int index = (int)getIndex(resume.getUuid());

        if (counter < STORAGE_LIMIT) {
            insertNewElement(resume, index);
            counter++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }

    }

    public void doDelete(String uuid) {
        int index = (int)getIndex(uuid);
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
        int index = (int)getIndex(resume.getUuid());
        storage[index] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counter);
    }

    public Resume doGet(String uuid) {
        int index = (int)getIndex(uuid);
        return storage[index];
    }
    @Override
    protected boolean isExist(Resume resume) {
        if ((int)getIndex(resume.getUuid()) >= 0) {//TODO
            return true;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected boolean isNotExist(Resume resume) {//TODO
        if ((int)getIndex(resume.getUuid()) < 0) {
            return true;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void insertNewElement(Resume resume, int index);

    protected abstract void deleteOldElement(int index);
}
