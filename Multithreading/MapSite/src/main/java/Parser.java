import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class Parser extends RecursiveTask<String> {

    private final String href;
    private String prefix = "";
    private final String separator = System.lineSeparator();
    private final StringBuilder text = new StringBuilder();
    private final long deep;
    private static long maxDeep;
    private static final HashMap<String, Long> hrefCount = new HashMap<>();

    public Parser(String href, long deep) {
        this.href = href;
        this.prefix = String.join("", Collections.nCopies((int) deep, "\t"));
        this.deep = deep;
    }

    public Parser(long maxDeep, String mainHref) {
        this.href = mainHref;
        this.deep = 0;
        ParserHTML.initialized(mainHref);
        Parser.maxDeep = maxDeep;
    }

    @Override
    protected String compute() {

        addHrefCount(href);

        try {
            HashSet<String> refs = ParserHTML.getArrayHref(href);
            addString(href + " :: count: " + refs.size());

            if (refs.size() > 0) {
                List<Parser> taskList = new ArrayList<>();
                refs.forEach(child -> startThread(child, taskList));
                taskList.forEach(task -> addSimpleString(task.join()));
            }
        } catch (IllegalAccessException | InterruptedException | IOException e) {
            addString("Parsing error: " + deep + ", " + href);
        }
        return text.toString().replaceAll(separator.concat("*$"), "");
    }

    private void startThread(String child, List<Parser> taskList) {

        if (getHrefCount(child) > 0 || deep > maxDeep) {
            text.append("\t");
            addString(child);
            return;
        }

        Parser parser = new Parser(child, deep + 1);
        parser.fork();
        taskList.add(parser);
    }

    private void addString(String toString) {
        if (toString.length() > 0) {
            text.append(prefix);
            addSimpleString(toString);
        }
    }

    private void addSimpleString(String toString) {
        text.append(toString).append(separator);
    }

    public static int getHrefCount() {
        return hrefCount.size();
    }

    private static synchronized void addHrefCount(String href) {
        long rate = getHrefCount(href);
        hrefCount.put(href, ++rate);
    }

    private static long getHrefCount(String href) {
        return hrefCount.getOrDefault(href, 0L);
    }
}
