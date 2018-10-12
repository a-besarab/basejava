import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int counter = 0;

    void clear() {
        Arrays.fill(storage, null);
        counter = 0;
    }

    void save(Resume r) {
        if (!isResumeExist(r.uuid)) {
            if (counter < storage.length) {
                System.out.println("Base isn't full.");
                storage[counter] = r;
                counter++;
                System.out.println("Resume " + r.uuid + " saved.");
            } else {
                System.out.println("Base is full. Resume " + r.uuid + " not saved.");
            }
        }
    }

    void update(Resume r) {
        if (isResumeExist(r.uuid)) {
            for (int i = 0; i < counter; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    storage[i] = r;
                }
            }
        }
    }

    Resume get(String uuid) {
        if (isResumeExist(uuid)) {
            for (int i = 0; i < counter; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (isResumeExist(uuid)) {
            for (int i = 0; i < counter; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = storage[counter - 1];
                    storage[counter - 1] = null;
                    counter--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] getAll = Arrays.copyOf(storage, counter);
        return getAll;
    }

    int size() {
        return counter;
    }

    boolean isResumeExist(String uuid) {
        boolean result = false;
        for (int i = 0; i < counter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                result = true;
                System.out.println("Resume " + uuid + " already exists.");
            }
        }
        if (!result) {
            System.out.println("Resume " + uuid + " not exist.");
        }
        return result;
    }
}
