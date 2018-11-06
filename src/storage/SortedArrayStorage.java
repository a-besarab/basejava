package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey, RESUME_COMPARATOR);
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
