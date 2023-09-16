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
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable(getHumanReadableSize(size)));
    }

    public static String getHumanReadableSize(long size) {
        int i = 4;
        long newSize = Math.round(size / Math.pow(1024, i));

        while (newSize < 1 && i >= 0) {
            --i;
            newSize = Math.round(size / Math.pow(1024, i));
        }

        return newSize + switch (i) {
            case 4 -> "Tb";
            case 3 -> "Gb";
            case 2 -> "Mb";
            case 1 -> "Kb";
            default -> "B";
        };
    }

    public static long getSizeFromHumanReadable(String size) {
        String regexSizeName = "[^A-Z]";
        String regexNumber = "[^0-9]";

        String sizeName = size.replaceAll(regexSizeName, "");
        long numb = Long.parseLong(size.replaceAll(regexNumber, ""));

        return switch (sizeName) {
            case "T" -> (long) (numb * Math.pow(1024, 4));
            case "G" -> (long) (numb * Math.pow(1024, 3));
            case "M" -> (long) (numb * Math.pow(1024, 2));
            case "K" -> (long) (numb * Math.pow(1024, 1));
            default -> (long) (numb * Math.pow(1024, 0));
        };
    }
}
