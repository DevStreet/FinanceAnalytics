/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.maths.lowlevelapi.indexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.lowlevelapi.indexing.MissileXParser.parse_all_return;

/**
 * Parses Midx expressions to index objects
 */
public class ParseMissileX2IndexObjects {

  private boolean _debug;

  @SuppressWarnings("unused")
  private void printList(List<?> list, String listname) {
    Iterator<?> it = list.iterator();
    System.out.println("LIST: " + listname);
    System.out.println("size = " + list.size());
    while (it.hasNext()) {
      System.out.println(it.next().toString());
    }
  }

  private List<IndexItem> processSetUpToList(String request) {
    MissileXLexer lex = null;
    CharStream chars;
    CommonTokenStream tokens;
    MissileXParser parser;
    chars = new ANTLRStringStream(request);
    lex = new MissileXLexer(chars);
    tokens = new CommonTokenStream(lex);
    parser = new MissileXParser(tokens);
    parser.setTreeAdaptor(s_adaptor);
    List<IndexItem> theIndex;
    theIndex = new ArrayList<>();
    return theIndex;
  }

  /**
   * Processes an index string in the context of assignment
   * @param request the requested indices
   * @return the folded index
   */
  public OneDIndex process(String request) {
    OneDIndex returnIndex = null;
    List<IndexItem> theIndex = processSetUpToList(request);
    returnIndex = new OneDIndex(indexGenerator(theIndex, request));
    return returnIndex;
  }

  private List<IndexItem> indexGenerator(List<IndexItem> idx, String str) {
    if (_debug) {
      System.out.println("Generating object based on: \"" + str + "\"");
    }
    MissileXLexer lex = null;
    CharStream chars;
    CommonTokenStream tokens;
    MissileXParser parser;
    CommonTree tree = null;
    chars = new ANTLRStringStream(str);
    lex = new MissileXLexer(chars);
    tokens = new CommonTokenStream(lex);
    parser = new MissileXParser(tokens);
    parser.setTreeAdaptor(s_adaptor);

    parse_all_return ret = null;
    try {
      ret = parser.parse_all();
      tree = (CommonTree) ret.getTree();
    } catch (RecognitionException e) {
      e.printStackTrace();
      throw new MathsExceptionGeneric("Unable to parse string: \"" + str + "\"");
    }
    if (_debug) {
      String type = typenum2str(ret.value);
      System.out.println("Identified:" + type);
    }
    List<?> items = null;
    Iterator<?> it = null;
    StringBuffer buf;
    String tokstr;
    List<IndexItem> localidx;
    switch (ret.value) {
      case 1: // compound_embedded
        throw new RuntimeException("Parser cannot currently handle compound embedded index items, try sticking brackets around it!");
      case 2: // embedded_range
        items = tree.getChildren();
        // strip [ ]
        items.remove(0);
        items.remove(items.size() - 1);
        it = items.iterator();
        buf = new StringBuffer();

        localidx = new ArrayList<>();
        while (it.hasNext()) {
          localidx.clear();
          tokstr = it.next().toString();
          if (!tokstr.equalsIgnoreCase(",")) {
            buf.append(tokstr);
          } else {
            idx.addAll(indexGenerator(localidx, buf.toString()));
            buf = new StringBuffer();
          }
        }
        localidx.clear();
        idx.addAll(indexGenerator(localidx, buf.toString()));
        break;
      case 3: // bracketed_induced_range
        if (_debug) {
          System.out.println("Adding bracked induced range");
        }
        items = tree.getChildren();
        if (items.size() == 5) {
          idx.add(new LinearIndex(Integer.valueOf(items.get(1).toString()), Integer.valueOf(items.get(3).toString())));
        } else {
          idx.add(new LinearIndex(Integer.valueOf(items.get(1).toString()), Integer.valueOf(items.get(3).toString()), Integer.valueOf(items.get(5).toString())));
        }
        break;
      case 4: // induced_range
      case 5: // stepped range
        if (_debug) {
          System.out.println("Adding induced OR stepped range");
        }
        items = tree.getChildren();
        if (items.size() == 3) {
          idx.add(new LinearIndex(Integer.valueOf(items.get(0).toString()), Integer.valueOf(items.get(2).toString())));
        } else {
          idx.add(new LinearIndex(Integer.valueOf(items.get(0).toString()), Integer.valueOf(items.get(2).toString()), Integer.valueOf(items.get(4).toString())));
        }
        break;
      case 6: // linear range
        if (_debug) {
          System.out.println("Adding linear range");
        }
        items = tree.getChildren();
        break;
      case 7: // idx = number
        idx.add(new GeneralIndex(new int[] {Integer.valueOf(tree.toString()) }));
        break;
      case 8: // colon
        idx.add(new ColonIndex());
        break;
      case 9: // end()
        if (_debug) {
          System.out.println("Adding end range");
        }
        items = tree.getChildren();
        if (items.size() == 3) { // n:end
          idx.add(new EndIndex(Integer.valueOf(items.get(0).toString())));
        } else if (items.size() == 5) { // n:m:end
          idx.add(new EndIndex(Integer.valueOf(items.get(0).toString()), Integer.valueOf(items.get(2).toString())));
        } else if (items.size() == 4) { // n : end -m
          idx.add(new EndIndex(Integer.valueOf(items.get(0).toString()), 1, Integer.valueOf(items.get(3).toString())));
        } else {  // n:m:end - p
          idx.add(new EndIndex(Integer.valueOf(items.get(0).toString()), Integer.valueOf(items.get(2).toString()), Integer.valueOf(items.get(5).toString())));
        }
        break;
      default:
        throw new RuntimeException("Unknown value returned from parser. Cannot comprehend \"" + str + "\"");
    }
    return idx;
  }

  static final TreeAdaptor s_adaptor = new CommonTreeAdaptor() {
    public Object create(Token theToken) {
      return new CommonTree(theToken);
    }
  };

  private String typenum2str(int num) {
    switch (num) {
      case 1:
        return "2D index";
      case 2:
        return "embedded_range";
      case 3:
        return "bracketed_induced_range";
      case 4:
        return "induced_range";
      case 5:
        return "stepped_range";
      case 6:
        return "linear_range";
      case 7:
        return "single_index";
      case 8:
        return "colon";
      case 9:
        return "end()";
      default:
        throw new RuntimeException("Unknown type.");
    }
  };

}
