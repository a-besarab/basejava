package storage;

import model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void save(Resume resume) {
        list.add(resume);
    }

    @Override
    public void update(Resume resume) {
        list.add(getIndex(resume.getUuid()), resume);
    }

    @Override
    public Resume get(String uuid) {
        return list.get(getIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        list.remove(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
