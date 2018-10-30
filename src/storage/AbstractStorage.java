package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isNotExist(Resume resume);

    protected abstract void doSave(Resume resume);

    protected abstract void doUpdate(Resume resume);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);

    protected abstract Object getIndex(String uuid);


    public void save(Resume resume) {

        if (isNotExist(resume)) {
            doSave(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void update(Resume resume) {
        if ((int) getIndex(resume.getUuid()) >= 0) {
            doUpdate(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        if ((int) getIndex(uuid) >= 0) {
            return doGet(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        if ((int) getIndex(uuid) >= 0) {
            doDelete(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
