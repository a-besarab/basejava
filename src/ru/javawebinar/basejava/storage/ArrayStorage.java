package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based ru.javawebinar.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
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
