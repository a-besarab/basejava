package storage;

import model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(String uuid) {
        return list.contains(uuid);
    }

    @Override
    public void doSave(Resume resume) {
        list.add(resume);
    }

    @Override
    public void doUpdate(Resume resume) {
        list.set((int) getIndex(resume.getUuid()), resume);
    }

    @Override
    public Resume doGet(String uuid) {
        return list.get((int) getIndex(uuid));
    }

    @Override
    public void doDelete(String uuid) {
        list.remove((int) getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    protected Object getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }
}
