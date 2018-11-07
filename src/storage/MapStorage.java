package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Object index) {
        return map.containsKey(index.toString());
    }

    @Override
    public void doSave(Resume resume, Object index) {
        map.put(index.toString(), resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        map.put(index.toString(), resume);
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
        return uuid;
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
