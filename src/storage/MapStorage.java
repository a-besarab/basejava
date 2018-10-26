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
    public void save(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void update(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public Resume get(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void delete(String uuid) {
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
