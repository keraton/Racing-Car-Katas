package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class BufferedReaderMock extends BufferedReader {

    private Queue<String> lines = new LinkedList<>();

    public BufferedReaderMock(Reader in) {
        super(in);
    }

    public BufferedReaderMock putLine(String line) {
        lines.add(line);
        return this;
    }

    @Override
    public String readLine() throws IOException {
        return lines.poll();
    }

    @Override
    public Stream<String> lines() {
        return lines.stream();
    }

    @Override
    public long skip(long n) throws IOException {
        String[] arrayOfLines = lines.stream()
                    .reduce("", (a, b) -> a + b + "9")
                    .substring(Long.valueOf(n).intValue())
                    .split("9");
        lines = new LinkedList<>(Arrays.asList(arrayOfLines));
        return n;
    }
}
