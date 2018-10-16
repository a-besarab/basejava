package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


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

    protected int getPosition(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
