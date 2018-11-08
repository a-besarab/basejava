package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResumeKey extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    public void doSave(Resume resume, Object r) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void doUpdate(Resume resume, Object r) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Object resume) {
        return (Resume)resume;
    }

    @Override
    public void doDelete(Object resume) {
        map.remove(((Resume)resume).getUuid());
    }

    @Override
    protected Object getIndex(String uuid) {
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
