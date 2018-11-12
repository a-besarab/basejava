package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract boolean isExist(SK index);

    protected abstract void doSave(Resume resume, SK index);

    protected abstract void doUpdate(Resume resume, SK index);

    protected abstract Resume doGet(SK index);

    protected abstract void doDelete(SK index);

    protected abstract SK getIndex(String index);

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
        SK index = getIndex(uuid);
        if (isExist(index)) {
            LOG.warning("Resume " + uuid + "not exist.");
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private SK checkExist(String uuid) {
        SK index = getIndex(uuid);
        if (!isExist(index)) {
            LOG.warning("Resume " + uuid + "already exist.");
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
