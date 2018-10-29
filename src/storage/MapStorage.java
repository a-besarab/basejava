package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    HashMap<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Resume resume) {
        if (map.containsValue(resume)) {
            return true;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected boolean isNotExist(Resume resume) {
        if (!map.containsValue(resume)) {
            return true;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void doSave(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[map.size()];                       //TODO via iterators...
    }

    @Override
    public int size() {
        return map.size();
    }
}
