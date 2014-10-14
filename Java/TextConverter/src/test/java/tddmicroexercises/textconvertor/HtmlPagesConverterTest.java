package tddmicroexercises.textconvertor;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static tddmicroexercises.textconvertor.HtmlPagesConverter.*;
import static tddmicroexercises.textconvertor.HtmlPagesConverter.PAGE_BREAK;

public class HtmlPagesConverterTest {

    public static final String LINE_1 = "line1";
    public static final String LINE_2 = "line2";
    public static final String LINE_3 = "line3";
    public static final String LINE_4 = "line4";
    public static final String LINE_5 = "line5";
    public static final int NEW_LINE = 1;

    private HtmlPagesConverter getHtmlPagesConverter() throws IOException {
        return new HtmlPagesConverter("fileName") {

            @Override
            protected BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
                return new BufferedReaderMock(new ReaderStub())
                        .putLine(LINE_1)
                        .putLine(LINE_2)
                        .putLine(PAGE_BREAK)
                        .putLine(LINE_3)
                        .putLine(PAGE_BREAK)
                        .putLine(LINE_4)
                        .putLine(LINE_5);
            }

        };
    }

    @Test
    public void given_list_of_lines_with_page_break_then_should_return_right_break_location() throws IOException {
        HtmlPagesConverter converter = getHtmlPagesConverter();

        assertThat(converter.getBreaks())
             .contains(0,
                     LINE_1.length() + LINE_2.length() + PAGE_BREAK.length() +
                     (NEW_LINE * 3),

                     LINE_1.length() + LINE_2.length() + PAGE_BREAK.length() +
                     LINE_3.length() + PAGE_BREAK.length() + (NEW_LINE * 5));
    }

    @Test
    public void given_list_of_lines_with_page_break_when_get_pages_should_return_the_right_line() throws IOException {
        HtmlPagesConverter converter = getHtmlPagesConverter();

        assertThat(converter.getHtmlPage(0)).isEqualTo(LINE_1+ BR + LINE_2 + BR);
        assertThat(converter.getHtmlPage(1)).isEqualTo(LINE_3 + BR);
        assertThat(converter.getHtmlPage(2)).isEqualTo(LINE_4 + BR + LINE_5 + BR);
    }

}
