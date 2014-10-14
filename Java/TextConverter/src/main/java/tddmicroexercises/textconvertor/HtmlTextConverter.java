package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static tddmicroexercises.textconvertor.StringEscapeUtils.escapeHtml;

public class HtmlTextConverter
{
    public static final String BR = "<br />";
    private String fullFilenameWithPath;

    public HtmlTextConverter(String fullFilenameWithPath)
    {
        this.fullFilenameWithPath = fullFilenameWithPath;
    }

    public String convertToHtml() throws IOException{
	    return getBufferedReader().lines().reduce("", (a, b) -> a + escapeHtml(b) + BR);
    }

    protected BufferedReader getBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(fullFilenameWithPath));
    }

    public String getFilename() {
		return this.fullFilenameWithPath;
	}

}
