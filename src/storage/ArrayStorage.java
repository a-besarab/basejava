package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertNewElement(Resume resume, int index) {
        storage[counter] = resume;
    }

    @Override
    protected void deleteOldElement(int index) {
        storage[index] = storage[counter - 1];
    }
}
