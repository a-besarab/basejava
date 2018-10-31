package storage;

import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        map.put(index.toString(), resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return map.get(index.toString());
    }

    @Override
    public void doDelete(Object index) {
        map.remove(index.toString());
    }

    @Override
    protected String getIndex(String uuid) {
        for (String key : map.keySet()) {
            if (key.equals(uuid)) {
                return null;
            }
        }
        return uuid;
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
