package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }

    @Override
    protected void insertNewElement(Resume resume, int index) {
        index = -(index + 1);
        System.arraycopy(storage, index, storage, index + 1, counter - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteOldElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, counter - index - 1);
    }
}
