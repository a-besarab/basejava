import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "C:\\basejava\\basejava\\.gitignore";
        File file = new File(filePath);
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

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printFiles("C:\\basejava\\basejava", "");
    }

    private static void printFiles(String path, String space) {
        File directory = new File(path);
        File[] list = directory.listFiles();

        assert list != null;
        for (File file : list) {
            if (file.isFile()) {
                System.out.println(space + file.getName());
            }
        }
        for (File file : list) {
            if (file.isDirectory()) {
                System.out.println(space + file.getName());
                printFiles(file.getAbsolutePath(), space + "  ");
            }
        }
    }
}
