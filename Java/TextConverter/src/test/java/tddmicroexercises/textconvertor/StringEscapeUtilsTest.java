package tddmicroexercises.textconvertor;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StringEscapeUtilsTest {

    @Test
    public void should_replaces_escape_char() {
        String containsEscapesChars = "<a href=\"http=localhost:7070/&test=true\" target=\'_blank\' >links</a>";

        String escapesCharsReplaces = StringEscapeUtils.escapeHtml(containsEscapesChars);

        Assertions.assertThat(escapesCharsReplaces)
                .isEqualTo("&lt;a href=&quot;http=localhost:7070/&amp;test=true&quot; target=&quot;_blank&quot; &gt;links&lt;/a&gt;");
    }

}