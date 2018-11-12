package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        list.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Integer index) {
        list.set(index, resume);
    }

    @Override
    public Resume doGet(Integer index) {
        return list.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        list.remove(index.intValue());
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
