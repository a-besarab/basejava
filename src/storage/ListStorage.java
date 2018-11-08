package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        list.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
