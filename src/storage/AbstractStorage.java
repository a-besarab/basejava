package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExist(Object index);

    protected abstract void doSave(Resume resume, Object index);

    protected abstract void doUpdate(Resume resume, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract void doDelete(Object index);

    protected abstract Object getIndex(String index);

    protected abstract List<Resume> doCopyAll();


    public void save(Resume resume) {
        doSave(resume, checkNotExist(resume.getUuid()));
    }

    public void update(Resume resume) {
        doUpdate(resume, checkExist(resume.getUuid()));
    }

    public Resume get(String uuid) {
        return doGet(checkExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(checkExist(uuid));
    }

    private Object checkNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object checkExist(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }
    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
       // Collections.sort(list);
        return list;
    }
}
