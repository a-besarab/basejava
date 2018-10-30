package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertNewElement(Resume resume, int index) {
        storage[counter] = resume;
    }

    @Override
    protected void deleteOldElement(int index) {
        storage[index] = storage[counter - 1];
    }

//    @Override
//    protected boolean isExist(Resume resume) {
//        if ((int)getIndex(resume.getUuid()) >= 0) {//TODO
//            return true;
//        } else {
//            throw new NotExistStorageException(resume.getUuid());
//        }
//    }
//
//    @Override
//    protected boolean isNotExist(Resume resume) {//TODO
//        if ((int)getIndex(resume.getUuid()) < 0) {
//            return true;
//        } else {
//            throw new ExistStorageException(resume.getUuid());
//        }
//    }
}
