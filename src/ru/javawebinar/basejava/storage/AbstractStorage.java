package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract boolean isExist(SK SearchKey);

    protected abstract void doSave(Resume resume, SK SearchKey);

    protected abstract void doUpdate(Resume resume, SK SearchKey);

    protected abstract Resume doGet(SK SearchKey);

    protected abstract void doDelete(SK SearchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        doSave(resume, checkNotExist(resume.getUuid()));
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        doUpdate(resume, checkExist(resume.getUuid()));
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return doGet(checkExist(uuid));
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        doDelete(checkExist(uuid));
    }

    private SK checkNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist.");
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    private SK checkExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist.");
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
