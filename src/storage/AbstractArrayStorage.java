package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void save(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index < 0) {
            if (counter < STORAGE_LIMIT) {
                insertNewElement(resume, index);
                counter++;
            } else {
                throw new StorageException("Storage overflow", resume.getUuid());
            }
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            deleteOldElement(index);
            storage[counter - 1] = null;
            counter--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public int size() {
        return counter;
    }

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counter);
    }

    public Resume get(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int getPosition(String uuid);

    protected abstract void insertNewElement(Resume resume, int index);

    protected abstract void deleteOldElement(int index);
}
