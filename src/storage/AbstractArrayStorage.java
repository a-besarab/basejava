package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public int size() {
        return counter;
    }

    public Resume get(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " not exist.");
            return null;
        }
    }

    protected abstract int getPosition(String uuid);

}
