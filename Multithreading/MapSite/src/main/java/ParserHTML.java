import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;

public class ParserHTML extends Thread {

    private static String regexHref;
    private static String mainRegex;

    public static void initialized(String mainHref) {
        regexHref = mainHref.concat(".*[^#\\.pdf]");
        mainRegex = mainHref.concat("/*");
    }

    public static HashSet<String> getArrayHref(String root)
            throws IllegalAccessException, InterruptedException, IOException {

        sleep((long) ((Math.random() * 200) + 10));
        HashSet<String> arrayHref = new HashSet<>();
        Document document = Jsoup.connect(root).get();
        document.select("a[href]")
                .forEach(hrefObject -> {
                            String href = hrefObject.attr("href");
                            if (href.matches(regexHref) && !href.equals(root) && !href.matches(mainRegex)) {
                                arrayHref.add(href.trim());
                            }
                        }
                );
        return arrayHref;
    }
}
