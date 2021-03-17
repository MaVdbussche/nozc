package com.barassolutions;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

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

  /**
   * Sort a list of class descriptors according to their type. The order of the types is determined by their definition in {@link ClassDescriptor.SubType}
   * @param descriptors the class descriptors to sort
   */
  public static void sortDescriptors(@NotNull ArrayList<ClassDescriptor> descriptors) {
    descriptors.sort((o1, o2) -> o2.type().ordinal() - o1.type().ordinal());
  }
}
