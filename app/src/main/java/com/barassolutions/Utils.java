package com.barassolutions;

public class Utils {

    /**
     * Escape the special XML characters in the specified string and return the
     * escaped string.
     *
     * @param s
     *            string to escape.
     * @return the escaped string.
     *
     * @author Bill Campbell, Swami Iyer and Bahar Akbal-Delibas
     */
    public static String escapeSpecialXMLChars(String s) {
        String escapedString = s.replaceAll("&", "&amp;");
        escapedString = escapedString.replaceAll("<", "&lt;");
        escapedString = escapedString.replaceAll(">", "&gt;");
        escapedString = escapedString.replaceAll("\"", "&quot;");
        escapedString = escapedString.replaceAll("'", "&#39;");
        return escapedString;
    }

    /**
   * To use in codegen() calls, as Oz variables should start with a capital letter.
   */
  public static String ozFriendlyName(String in) {
    return in.substring(0,1).toUpperCase() + in.substring(1);
  }
}
