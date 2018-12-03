package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory mast not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory");
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
//TODO
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    @Override
    protected void doUpdate(Resume resume, Path path) {//TODO
//        try {
//            doWrite(resume, new BufferedOutputStream(new FileOutputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("IO error", path.getName(), e);
//        }
    }

    @Override
    protected Resume doGet(Path path) {//TODO
//        try {
//            return doRead(new BufferedInputStream(new FileInputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("IO error", path.getName(), e);
//        }
        return new Resume("te");
    }

    @Override
    protected void doDelete(Path path) {//TODO
//        if (!path.delete()) {
//            throw new StorageException("File delete error", path.getName());
//        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory;//TODO
    }

    @Override
    protected List<Resume> doCopyAll() {//TODO
        ArrayList<Resume> list = new ArrayList<>();
//        for (Path file : Objects.requireNonNull(directory.listFiles())) {
//            list.add(doGet(file));
//        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.size(directory);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }
}
