import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    static int count = 0;
    static long delay = 5000L;

    public static void main(String[] args) {

        long maxDeep = 10;
        String path = "src/data";
        String root = "https://skillbox.ru";
//          String root = "https://lenta.ru";

        System.out.print("Обработано ссылок: ");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(Main::printCount
                , 1000
                , delay
                , TimeUnit.MILLISECONDS);

        try {
            String result = new ForkJoinPool().invoke(new Parser(maxDeep, root));
            saveFile(path, result);
            service.shutdown();
            System.out.println("Всего ссылок: " + Parser.getHrefCount());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static void printCount() {
        int newCount = Parser.getHrefCount();
        if (newCount != count) {
            System.out.print(Parser.getHrefCount() + ", ");
            count = newCount;
        }
    }

    public static void saveFile(String path, String stringsMap) throws IOException {
        FileWriter file = new FileWriter(path + "/siteMap.txt");
        file.write(stringsMap);
        file.flush();
        file.close();
    }
}