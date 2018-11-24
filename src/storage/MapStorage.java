package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(String index) {
        return map.containsKey(index);
    }

    @Override
    public void doSave(Resume resume, String index) {
        map.put(index, resume);
    }

    @Override
    public void doUpdate(Resume resume, String index) {
        map.put(index, resume);
    }

    @Override
    public Resume doGet(String index) {
        return map.get(index);
    }

    @Override
    public void doDelete(String index) {
        map.remove(index);
    }

    @Override
    protected String getSearchKey(String uuid) {
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
