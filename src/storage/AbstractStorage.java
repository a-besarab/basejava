package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExist(Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract Object getIndex(String index);


    public void save(Resume resume) {
        doSave(resume, checkExist(resume.getUuid()));
    }

    public void update(Resume resume) {
        checkNotExist(resume.getUuid());
        doUpdate(resume, checkNotExist(resume.getUuid()));
    }

    public Resume get(String uuid) {
        return doGet(checkNotExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(checkNotExist(uuid));
    }

    private Object checkNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object checkExist(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }
}
