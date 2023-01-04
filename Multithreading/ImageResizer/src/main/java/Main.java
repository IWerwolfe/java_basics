import java.io.File;

public class Main {

    private static String srcFolder = "src/data/src";
    private static String dstFolder = "src/data/dst";
    private static int newWight = 300;
    private static int ProcessorCore;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        ProcessorCore = Runtime.getRuntime().availableProcessors();
        int size = (files.length / ProcessorCore) + 1;

        for (int i = 0; i < files.length; ) {

            if (i + size > files.length) {
                size = files.length - i;
            }

            File[] files1 = new File[size];
            System.arraycopy(files, i, files1, 0, size);
//            ImageResizer imageResizer = new ImageResizer(files1, newWight, start, dstFolder);
            ImageResizerImgscalr imageResizer = new ImageResizerImgscalr(files1, newWight, start, dstFolder);
            new Thread(imageResizer).start();

            i += size;
        }
    }
}
