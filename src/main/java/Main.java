import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        String folderPath = "/Users/kirillmagazinov/IT/java/Теория";
        File file = new File(folderPath);
        //System.out.println(file.length());
        System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }

        AtomicLong sum = new AtomicLong(0L);
        List<File> files = List.of(folder.listFiles());
        files.forEach(file -> sum.addAndGet((getFolderSize(file))));
        return sum.get();
    }
}
