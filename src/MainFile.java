import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePuth = ".\\.gitignore";
        File file = new File(filePuth);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("C:\\basejava\\basejava\\src");
        System.out.println(dir.isDirectory());

        for (String name : Objects.requireNonNull(dir.list())) {
            System.out.println(name);
        }

        try (FileInputStream fis = new FileInputStream(filePuth)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printFiles(".\\");
    }

    private static void printFiles(String path) {
        File cat = new File(path);
        File[] list = cat.listFiles();

        assert list != null;
        for (File file : list) {
            if (file.isDirectory()) {
                printFiles(file.getAbsolutePath());
            } else {
                System.out.println(file.getName());
            }
        }
    }
}
