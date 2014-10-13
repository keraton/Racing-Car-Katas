package tddmicroexercises.textconvertor;

import java.io.IOException;
import java.io.Reader;

class ReaderStub extends Reader {

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
    }
}
