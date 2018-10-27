package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
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
        if (list.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            list.add(resume);
        }
    }

    @Override
    public void update(Resume resume) {
        list.set(getIndex(resume.getUuid()), resume);
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
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                index = i;
            }
        }
        if (index >= 0) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
