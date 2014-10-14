package tddmicroexercises.textconvertor;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static tddmicroexercises.textconvertor.HtmlTextConverter.BR;
import static tddmicroexercises.textconvertor.StringEscapeUtils.escapeHtml;

public class HtmlTextConverterTest {

    private static final String LINE_1 = "<line1>";
    private static final String LINE_2 = "<line2>";
    private static final String FILE_NAME = "htmlTextConverter.txt";

    @Test
    public void should_get_the_correct_file_name() {
        HtmlTextConverter converter = new HtmlTextConverter(FILE_NAME);

        assertThat(FILE_NAME).isEqualTo(converter.getFilename());
    }

    @Test
    public void should_read_the_file_when_the_file_name_is_entered() throws IOException {
        HtmlTextConverter converter = new HtmlTextConverter(FILE_NAME) {
            @Override
            protected BufferedReader getBufferedReader() throws FileNotFoundException {
                return new BufferedReaderMock(new ReaderStub())
                                                    .putLine(LINE_1)
                                                    .putLine(LINE_2);
            }
        };

        String html = converter.convertToHtml();

        assertThat(html).isEqualTo(escapeHtml(LINE_1)
                                               + BR
                                               + escapeHtml(LINE_2)
                                               + BR);

    }
}
