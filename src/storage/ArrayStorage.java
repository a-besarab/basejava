package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    public void save(Resume resume) {
        if (getPosition(resume.getUuid()) < 0) {
            if (counter < STORAGE_LIMIT) {
                storage[counter] = resume;
                counter++;
            } else {
                System.out.println("Base is full. Resume " + resume.getUuid() + " not saved.");
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
        }
    }

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        }
    }

    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index >= 0) {
            storage[index] = storage[counter - 1];
            storage[counter - 1] = null;
            counter--;
        } else {
            System.out.println("Resume " + uuid + " not exist.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, counter);
    }

    protected int getPosition(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
