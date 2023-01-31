import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class CarNumberGenerator extends Thread {

    public static char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    public static long MAX_NUM = 1000L;
    public static long MAX_LENGTH_BUFFER = 10_000L;

    private static final AtomicLong count = new AtomicLong(0);
    private static TreeSet<String> numbers;
    private final StringBuilder buffer;
    private final String regionCode;
    private FileOutputStream writer;

    public CarNumberGenerator(int regionCode, String path) {

        setCount();
        this.buffer = new StringBuilder();
        this.regionCode = regionCode < 10 ? "0" + regionCode : String.valueOf(regionCode);

        try {
            this.writer = new FileOutputStream(path + "numbers_" + regionCode + ".txt");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        if (numbers.isEmpty()) {
            System.out.println("Данные не инициализированы");
            return;
        }

        for (String number : numbers) {
            buffer.append(number)
                    .append(regionCode)
                    .append('\n');

            if (buffer.length() > MAX_LENGTH_BUFFER) {
                whiteFile(buffer.toString().getBytes());
                buffer.delete(0, buffer.length());
            }
        }
        whiteFile(buffer.toString().getBytes());
        closeFile();
        System.out.println("Thread_" + regionCode + ": " + (System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number) {
        if (number > 100) {
            return String.valueOf(number);
        }
        if (number < 10) {
            return "00" + number;
        }
        return "0" + number;
    }

    private void whiteFile(byte[] buffer) {
        try {
            writer.write(buffer);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    private void closeFile() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void generationNumbers() {
        numbers = new TreeSet<>();
        StringBuilder buffer = new StringBuilder();
        String stringNumber = "";
        for (int number = 1; number < MAX_NUM; number++) {
            stringNumber = padNumber(number);
            for (char firstLetter : LETTERS) {
                for (char secondLetter : LETTERS) {
                    for (char thirdLetter : LETTERS) {
                        buffer.append(firstLetter)
                                .append(stringNumber)
                                .append(secondLetter)
                                .append(thirdLetter);
                        numbers.add(buffer.toString());
                        buffer.delete(0, buffer.length());
                    }
                }
            }
        }
    }

    public static AtomicLong getCount() {
        return count;
    }

    public static void setCount() {
        count.incrementAndGet();
    }
}
