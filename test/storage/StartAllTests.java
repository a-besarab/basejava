package storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapStorageResumeKeyTest.class,
        ObjectStreamFileStorageTest.class,
        ObjectStreamPathStorageTest.class})
public class StartAllTests {
}
