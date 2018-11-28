package storage;

import model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume resume, OutputStream os) throws IOException {
        //TODO
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return null; //TODO
    }
}
