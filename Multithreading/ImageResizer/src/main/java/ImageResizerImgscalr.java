import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizerImgscalr implements Runnable {

    private File[] files;
    private int newWidth;
    private long start;
    private String dstFolder;

    private static int count;
    private int number;

    public ImageResizerImgscalr(File[] files, int newWight, long start, String dstFolder) {
        this.files = files;
        this.newWidth = newWight;
        this.start = start;
        this.dstFolder = dstFolder;
        setCount();
        this.number = getCount();
    }

    @Override
    public void run() {

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth));

                BufferedImage newImage = Scalr.resize(image, newWidth, newHeight);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration thread " + number + " : " + (System.currentTimeMillis() - start));
    }

    public static int getCount() {
        return count;
    }

    public static void setCount() {
        ImageResizerImgscalr.count++;
    }

}
