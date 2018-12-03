import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePuth = "/Users/a123/IdeaProjects/basejava2/.gitignore";
        File file = new File(filePuth);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("/Users/a123/IdeaProjects/basejava2/src");
        System.out.println(dir.isDirectory());

        for (String name : Objects.requireNonNull(dir.list())) {
            System.out.println(name);
        }

        try (FileInputStream fis = new FileInputStream(filePuth)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printFiles("/Users/a123/IdeaProjects/basejava2");
    }

    private static void printFiles(String path) {
        File cat = new File(path);
        File[] list = cat.listFiles();
        int point = path.length();
        StringBuilder space = new StringBuilder();

        System.out.println(path);
        while (point >= 0) {
            space.append(" ");
            point--;
        }

        assert list != null;
        for (File file : list) {
            if (file.isFile()) {
                System.out.println(space + file.getName());
            }
        }
        for (File file : list) {
            if (file.isDirectory()) {
                System.out.println();
                printFiles(file.getAbsolutePath());
            }
        }
    }
}
