package storage;

import util.Config;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(Config.get().getStorage());
    }
}