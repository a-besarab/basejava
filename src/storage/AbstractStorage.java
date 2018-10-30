package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExist(Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract void doUpdate(Resume resume);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);

    protected abstract Object getIndex(String uuid);


    public void save(Resume resume) {
        doSave(resume, checkExist(resume.getUuid()));
    }

    public void update(Resume resume) {
        checkNotExist(resume.getUuid());
        doUpdate(resume);
    }

    public Resume get(String uuid) {
        return doGet(checkNotExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(checkNotExist(uuid));
    }

    private String checkNotExist(String uuid) {
        if (isExist(uuid)) {
            return uuid;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object checkExist(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }
}
