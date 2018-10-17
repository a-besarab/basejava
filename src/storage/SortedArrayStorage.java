package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

    @Override
    protected void insertNewElement(Resume resume) {
        int index = -(getPosition(resume.getUuid())) - 1;
        for (int i = counter; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = resume;
    }

    @Override
    protected void deleteOldElement(int index) {
        for (int i = index; i < counter; i++) {
            storage[i] = storage[i + 1];
        }
    }
}
