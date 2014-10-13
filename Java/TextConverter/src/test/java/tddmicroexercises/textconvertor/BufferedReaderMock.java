package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Queue;

class BufferedReaderMock extends BufferedReader {

    private final Queue<String> lines = new LinkedList<String>();

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
}
