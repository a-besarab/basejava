import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int counter = 0;

    void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    void save(Resume resume) {
        if (getResumePosition(resume.getUuid()) < 0) {
            if (counter < storage.length) {
                storage[counter] = resume;
                counter++;
            } else {
                System.out.println("Base is full. Resume " + resume.getUuid() + " not saved.");
            }
        } else {
            System.out.println("Resume " + resume.getUuid() + " already exists.");
        }
    }

    void update(Resume resume) {
        int currentResumePosition = getResumePosition(resume.getUuid());
        if (currentResumePosition >= 0) {
            storage[currentResumePosition] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not exist.");
        }
    }

    Resume get(String uuid) {
        int currentResumePosition = getResumePosition(uuid);
        if (currentResumePosition >= 0) {
            return storage[currentResumePosition];
        } else {
            System.out.println("Resume " + uuid + " not exist.");
            return null;
        }
    }

    void delete(String uuid) {
        int currentResumePosition = getResumePosition(uuid);
        if (currentResumePosition >= 0) {
            storage[currentResumePosition] = storage[counter - 1];
            storage[counter - 1] = null;
            counter--;
        } else {
            System.out.println("Resume " + uuid + " not exist.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, counter);
    }

    int size() {
        return counter;
    }

    private int getResumePosition(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
