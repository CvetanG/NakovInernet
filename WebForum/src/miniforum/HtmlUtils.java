package miniforum;

public class HtmlUtils {
	/**
	 * Escapes given text for placing it in the HTML body. If
	 * you need escaping for placing text in an attribut value,
	 * you should remove the escaping for the "\n" character.
	 *
	 * (c) Svetlin Nakov, 2004 - http://www.nakov.com
	 */
	public static String htmlEscape(String aText) {
		if (aText == null) {
			return "";
		}
		StringBuffer escapedText = new StringBuffer();
		for (int i=0; i<aText.length(); i++) {
			char ch = aText.charAt(i);
			if (ch == '\'')
				escapedText.append("&#39;");
			else if (ch == '\"')
				escapedText.append("&#34;");
			else if (ch == '<')
				escapedText.append("&lt;");
			else if (ch == '>')
				escapedText.append("&gt;");
			else if (ch == '&')
				escapedText.append("&amp;");
			else if (ch == '\n')
				escapedText.append("<br>\n");
			else if (ch == ' ')
				escapedText.append("&nbsp;");
			else if (ch == '\t')
				escapedText.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			else
				escapedText.append(ch);
		}
		String result = escapedText.toString();
		return result;
	}
}
