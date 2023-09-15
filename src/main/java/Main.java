import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        String folderPath = "/Users/kirillmagazinov/IT/java/Теория";
        File file = new File(folderPath);

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
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
