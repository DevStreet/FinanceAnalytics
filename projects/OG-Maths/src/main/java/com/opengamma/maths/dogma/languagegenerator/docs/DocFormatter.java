/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.docs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;
import com.opengamma.maths.dogma.engine.language.ArbitraryFunction;
import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;
import com.opengamma.maths.dogma.languagegenerator.FullToken;

/**
 * Formats documentation by combining Doc and FullToken instances.
 * Methods are procedural static for the minute to keep things simple
 */
public class DocFormatter {

  private FullToken _tokenPointer;
  private static Set<Doc> s_docClazz;
  private static Map<Class<?>, SpecificFormatter> s_classToSpecificFnMap = new HashMap<Class<?>, SpecificFormatter>();

  static {
    s_classToSpecificFnMap.put(UnaryFunction.class, new UnarySpecificFormatting());
    s_classToSpecificFnMap.put(InfixOperator.class, new InfixSpecificFormatting());
    s_classToSpecificFnMap.put(ArbitraryFunction.class, new ArbitrarySpecificFormatting());
    s_classToSpecificFnMap.put(VoidUnaryFunction.class, new VoidUnarySpecificFormatting());
  }

  // This is the text wrapping width for the docs for which 80 seems a good default, 
  // also useful just in case we need to run javac from punch cards ;-)
  private static int s_termWidth = 80;

  public DocFormatter(FullToken tok) {
    _tokenPointer = tok;
    s_docClazz = s_classToDocsMap.get(tok.getInterfaceClass());
  }

  private static Logger s_log = LoggerFactory.getLogger(DocFormatter.class);
  private static DocsDictionaryPopulator<Doc> s_docsDict = new DocsDictionaryPopulator<Doc>();
  private static Map<Class<?>, Set<Doc>> s_classToDocsMap = s_docsDict.getOperationsMap();

  private StringBuffer _docs = new StringBuffer();

  /**
   * Gets the doc string
   * @return the documentation string as a buffer
   */
  public StringBuffer getDocs() {
    _docs.append(formatGenericPart());
    SpecificFormatter formatter = s_classToSpecificFnMap.get(_tokenPointer.getInterfaceClassType());
    if (formatter != null) {
      _docs.append(formatter.evalFormat(_tokenPointer));
    } else {
      throw new MathsExceptionConfigProblem("Cannot find formatter for the Function<T,S> class provided in the FullToken");
    }
    return _docs;
  }

  /**
   * Takes a Doc class and formats it as something suitable for sticking into DOGMA function docs
   * @return a formatted version of the Doc suitable for sticking into DOGMA function docs
   */
  private String formatGenericPart() {
    StringBuffer tmp = new StringBuffer();
    String functionTitle = "DOGMA Function: " + _tokenPointer.getSimpleName();
    String shortDesc = "Docs Missing - No description given";
    String longDesc = "Docs Missing - No description given";
    String exampleIn = "Docs Missing - No description given";
    String exampleOut = "Docs Missing - No description given";
    if (s_docClazz != null) {
      if (s_docClazz.size() > 1) {
        s_log.warn("Multiple documentation classes found for function " + _tokenPointer.getSimpleName() + " results may be strange!");
      }
      shortDesc = "";
      longDesc = "";
      exampleIn = "";
      exampleOut = "";
      Iterator<Doc> it = s_docClazz.iterator();
      Doc next = null;
      while (it.hasNext()) {
        next = it.next();
        shortDesc += next.shortDescription();
        longDesc += next.longDescription();
        exampleIn += next.exampleInput();
        exampleOut += next.exampleOutput();
      }
    }

    tmp.append("\n");

    tmp.append("/**\n");
    tmp.append(" * ");
    tmp.append(functionTitle + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Short Description:\n");
    tmp.append(" * <p>\n");
    tmp.append(" * ");
    tmp.append(shortDesc + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Full Description:\n");
    tmp.append(" * <p>\n");
    tmp.append(" * ");
    tmp.append(lineWrap(longDesc, s_termWidth) + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Example Code:\n");
    tmp.append(" * <pre>\n * {@code \n");
    tmp.append(" * ");
    tmp.append(codeLineWrap(exampleIn, s_termWidth) + "\n");
    tmp.append(" * }\n * </pre>\n * <p>\n");
    tmp.append(" * Example Output:\n");
    tmp.append(" * <pre>\n * {@code \n");
    tmp.append(" * ");
    tmp.append(exampleOut + "\n");
    tmp.append(" * }\n * </pre>\n *\n");

    return tmp.toString();
  }

  /**
   * Interface to the function specific formatting behaviours
   */
  private interface SpecificFormatter {
    String evalFormat(FullToken tok);
  }

  /**
   * Specific formatting for the Unary function classes
   */
  private static class UnarySpecificFormatting implements SpecificFormatter {
    public String evalFormat(FullToken tok) {
      String[] argumentValues = {"Docs Missing - No description given" };
      String returnValueDescription = "Docs Missing - No description given";
      StringBuffer tmp = new StringBuffer();
      if (s_docClazz != null) {
        if (s_docClazz.size() > 1) {
          s_log.warn("Multiple documentation classes found for function " + tok.getSimpleName() + " results may be strange!");
        }
        argumentValues = new String[] {"" };
        returnValueDescription = "";
        Iterator<Doc> it = s_docClazz.iterator();
        Doc next = null;
        while (it.hasNext()) {
          next = it.next();
          if (next.argDescriptions().length > 1) {
            throw new MathsExceptionConfigProblem("Docs supplied for class " + tok.getSimpleName() + " declare more argument descriptions than there are actual arguments.");
          }
          argumentValues[0] = next.argDescriptions()[0];
          returnValueDescription += next.returnDescription();
        }
      }

      tmp.append(" * @param arg0 " + argumentValues[0] + "\n");
      tmp.append(" * <p>\n");
      tmp.append(" * @return " + returnValueDescription + "\n");
      tmp.append(" */\n\n");

      return tmp.toString();
    }
  }

  /**
   * Specific formatting for the Unary function classes
   */
  private static class VoidUnarySpecificFormatting implements SpecificFormatter {
    public String evalFormat(FullToken tok) {
      String[] argumentValues = {"Docs Missing - No description given" };
      StringBuffer tmp = new StringBuffer();
      if (s_docClazz != null) {
        if (s_docClazz.size() > 1) {
          s_log.warn("Multiple documentation classes found for function " + tok.getSimpleName() + " results may be strange!");
        }
        argumentValues = new String[] {"" };
        Iterator<Doc> it = s_docClazz.iterator();
        Doc next = null;
        while (it.hasNext()) {
          next = it.next();
          if (next.argDescriptions().length > 1) {
            throw new MathsExceptionConfigProblem("Docs supplied for class " + tok.getSimpleName() + " declare more argument descriptions than there are actual arguments.");
          }
          argumentValues[0] = next.argDescriptions()[0];
          if (!next.returnDescription().equalsIgnoreCase("")) {
            throw new MathsExceptionConfigProblem("Docs supplied for class " + tok.getSimpleName() + " declare return for a function that is void by definition.");
          }
        }
      }

      tmp.append(" * @param arg0 " + argumentValues[0] + "\n");
      tmp.append(" * <p>\n");
      tmp.append(" */\n\n");

      return tmp.toString();
    }
  }

  /**
   * Specific formatting for the Arbitrary function classes
   */
  private static class ArbitrarySpecificFormatting implements SpecificFormatter {
    public String evalFormat(FullToken tok) {
      return "";
    }
  }

  /**
   * Specific formatting for the Infix classes
   */
  private static class InfixSpecificFormatting implements SpecificFormatter {
    public String evalFormat(FullToken tok) {
      String[] argumentValues = {"Docs Missing - No description given", "Docs Missing - No description given" };
      String returnValueDescription = "Docs Missing - No description given";
      StringBuffer tmp = new StringBuffer();
      if (s_docClazz != null) {
        if (s_docClazz.size() > 1) {
          s_log.warn("Multiple documentation classes found for function " + tok.getSimpleName() + " results may be strange!");
        }
        argumentValues = new String[] {"", "" };
        returnValueDescription = "";
        Iterator<Doc> it = s_docClazz.iterator();
        Doc next = null;
        while (it.hasNext()) {
          next = it.next();
          if (next.argDescriptions().length > 2) {
            throw new MathsExceptionConfigProblem("Docs supplied for class " + tok.getSimpleName() + " declare more argument descriptions than there are actual arguments.");
          }
          for (int i = 0; i < next.argDescriptions().length; i++) {
            argumentValues[i] = next.argDescriptions()[i];
          }
          returnValueDescription += next.returnDescription();
        }
      }
      tmp.append(" * @param arg0  " + argumentValues[0] + "\n");
      tmp.append(" * @param arg1  " + argumentValues[1] + "\n");
      tmp.append(" * \n");
      tmp.append(" * @return " + returnValueDescription + "\n");
      tmp.append(" */\n\n");
      return tmp.toString();
    }
  }

  /* wraps lines to width chars */
  private static String lineWrap(String inStr, int width) {
    StringBuffer strb = new StringBuffer(inStr);
    int strlen = strb.length();
    int currentPos = 0;
    int spaceIdx = 0;
    int widthPlusStar = width - 5;
    while (currentPos + widthPlusStar < strlen) {
      spaceIdx = strb.lastIndexOf(" ", currentPos + widthPlusStar);
      if (spaceIdx > 0) {
        strb.replace(spaceIdx, spaceIdx + 1, "\n * ");
        currentPos += widthPlusStar;
      } // no need to break, inc and while terminates
    }
    return strb.toString();
  }

  /* inserts and wraps code on instances of ";" */
  private static String codeLineWrap(String inStr, int width) {
    StringBuffer strb = new StringBuffer(inStr);
    Pattern regex = Pattern.compile(";");
    Matcher match = regex.matcher(strb);
    while (match.find()) { // no jmp needed, the shuffling of the underlying data seems to be picked up
      strb.replace(match.start(), match.start() + 1, ";\n * ");
    }
    return strb.toString();
  }
}
