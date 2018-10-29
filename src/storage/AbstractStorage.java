package storage;

import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExist(Resume resume);

    protected abstract boolean isNotExist(Resume resume);

    protected abstract void doSave(Resume resume);

    protected abstract void doUpdate(Resume resume);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);

    public void save(Resume resume) {
        if (isNotExist(resume)) {
            doSave(resume);
        }
    }

    public void update(Resume resume) {
        if (isExist(resume)) {
            doUpdate(resume);
        }
    }

    public Resume get(String uuid) {
        if (isExist(doGet(uuid))) {
            return doGet(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        if (isExist(get(uuid))) {
            doDelete(uuid);
        }
    }
}
