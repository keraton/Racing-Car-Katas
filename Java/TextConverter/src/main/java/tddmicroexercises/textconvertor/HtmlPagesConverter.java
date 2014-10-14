package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlPagesConverter {

    public static final String PAGE_BREAK = "PAGE_BREAK";
    public static final String BR = "<br />";
    private final String filename;

    private List<Integer> breaks = new ArrayList<>();

    static class MutableInteger {
        int internaLInteger = 0;
        void add(int i) {
            internaLInteger += i;
        }
    }

    static class MutableBoolean {
        boolean internalBoolean = false;
        void set(boolean b) {
            internalBoolean = b;
        }
    }

    public HtmlPagesConverter(String filename) throws IOException {
        this.filename = filename;
        this.breaks.add(0);
        final MutableInteger counter = new MutableInteger();
        BufferedReader reader = getBufferedReader(filename);

        reader.lines().map(line -> addToCounter(counter, line))
                    .filter(line -> line.contains(PAGE_BREAK))
                    .forEach(line -> breaks.add(counter.internaLInteger));

        reader.close();
    }

    protected BufferedReader getBufferedReader(String filename) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filename));
    }

    private String addToCounter(MutableInteger counter, String line) {
        counter.add(line.length() + 1);
        return line;
    }

    public String getHtmlPage(int page) throws IOException {
        BufferedReader reader = getBufferedReader(filename);
        reader.skip(breaks.get(page)); // Need to implement skip, yucks
        MutableBoolean stop = new MutableBoolean();
        String html = reader.lines().filter(line -> stopWhenPageBreakIsFound(stop, line))
                                    .reduce("", (a, b) -> a + StringEscapeUtils.escapeHtml(b) + BR);
        reader.close();
        return html;
    }

    private boolean stopWhenPageBreakIsFound(MutableBoolean stop, String line) {
        if (line.contains(PAGE_BREAK))
            stop.set(true);
        return stop.internalBoolean == false;
    }

    protected List<Integer> getBreaks() {
        return breaks;
    }
}
