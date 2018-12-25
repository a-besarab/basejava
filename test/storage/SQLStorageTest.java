package storage;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "pass")); //TODO via Config
    }
}