package storage;

import model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {
    HashMap<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isNotExist(Resume resume) {
        return !map.containsValue(resume);
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
    protected Object getIndex(String uuid) {
        int index = -1;
        if (map.containsKey(uuid)) {
            index = 0;                                              //TODO or not TODO?
        }
        return index;
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
