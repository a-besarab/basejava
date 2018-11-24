package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResumeKey extends AbstractStorage<Resume> {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    public void doSave(Resume resume, Resume r) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(Resume resume, Resume r) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    public void doDelete(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
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
