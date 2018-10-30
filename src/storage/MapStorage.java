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
    protected boolean isExist(Object index) {
        return map.containsKey(index);
    }

    @Override
    public void doSave(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return map.get(index);
    }

    @Override
    public void doDelete(Object index) {
        map.remove(index);
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
