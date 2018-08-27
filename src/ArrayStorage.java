/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int counter = 0;

    void clear() {
        for (int i = 0; i < counter; i++) {
            storage[i] = null;
        }
        counter = 0;
    }

    void save(Resume r) {
        if (counter < storage.length) {
            storage[counter] = r;
            counter++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[counter - 1];
                storage[counter - 1] = null;
                counter--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] getAll = new Resume[counter];
        System.arraycopy(storage, 0, getAll, 0, counter);
        return getAll;
    }

    int size() {
        return counter;
    }
}
