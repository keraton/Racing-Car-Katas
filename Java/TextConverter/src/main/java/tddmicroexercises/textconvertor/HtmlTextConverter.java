package tddmicroexercises.textconvertor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HtmlTextConverter
{
    public static final String BR = "<br />";
    private String fullFilenameWithPath;

    public HtmlTextConverter(String fullFilenameWithPath)
    {
        this.fullFilenameWithPath = fullFilenameWithPath;
    }

    public String convertToHtml() throws IOException{

        BufferedReader reader = getBufferedReader();

        String line = reader.readLine();
	    String html = "";
	    while (line != null)
	    {
	    	html += StringEscapeUtils.escapeHtml(line);
	        html += BR;
	        line = reader.readLine();
	    }
	    return html;

    }

    protected BufferedReader getBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(fullFilenameWithPath));
    }

    public String getFilename() {
		return this.fullFilenameWithPath;
	}
}
