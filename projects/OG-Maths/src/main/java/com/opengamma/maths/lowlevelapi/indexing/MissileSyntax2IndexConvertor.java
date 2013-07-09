/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionParseError;

/**
 * Parses Missile syntax into Index objects
 */
public class MissileSyntax2IndexConvertor {

  // tree adaptor
  static final TreeAdaptor s_adaptor = new CommonTreeAdaptor() {
    public Object create(Token aToken) {
      return new CommonTree(aToken);
    }
  };

  //parser kit
  private CharStream _input;
  private MissileLexer _lexer;
  private CommonTokenStream _tokens;
  private MissileParser _parser;
  private CommonTreeNodeStream _nodes;
  private MissileTree _ASTWalker; //CSIGNORE

  private static Logger s_logger = LoggerFactory.getLogger(MissileSyntax2IndexConvertor.class);

  // noise
  private boolean _debug = false; //CSIGNORE

  /**
   * Default constructor
   */
  public MissileSyntax2IndexConvertor() {
  }

  /**
   * Switch on debugging statements
   * @param debug true to enable debug statements
   */
  public MissileSyntax2IndexConvertor(boolean debug) {
    _debug = debug;
  }

  public Index eval(String arg) {
    if (_debug == true) {
      s_logger.debug("Attempting to match:\"" + arg + "\"");
    }
    Index idx = null;
    try {
      // convert input string to char *
      _input = new ANTLRStringStream(arg);
      // get a lexer
      _lexer = new MissileLexer(_input);
      // get tokens from lexer
      _tokens = new CommonTokenStream(_lexer);
      // get parser
      _parser = new MissileParser(_tokens);
      // parse tokens to AST
      MissileParser.begin_return tree = _parser.begin();
      if (_debug) {
        s_logger.debug("tree=" + ((Tree) tree.tree).toStringTree());
      }
      _parser.setTreeAdaptor(s_adaptor);

      if (_debug) {
        s_logger.debug("_Tree_:\n");
        printTree((CommonTree) tree.getTree(), 1);
      }

      // get a node stream from tree
      _nodes = new CommonTreeNodeStream((Tree) tree.tree);
      // get an AST walker
      _ASTWalker = new MissileTree(_nodes);
      // walk AST to get Index blobs
      idx = _ASTWalker.walk();

      if (_debug) {
        s_logger.debug("Linear:" + idx.toString());
        s_logger.debug("_Indexing Blob_:\n" + idx.toString());
      }

    } catch (Throwable t) {
      throw new MathsExceptionParseError(t.getMessage());
    }
    return idx;
  }

  /**
   * Gets the debug status.
   * @return the debug status
   */
  public final boolean isDebugOn() {
    return _debug;
  }

  public void printTree(CommonTree tree, int offset) {
    if (tree != null) {
      StringBuffer indenting = new StringBuffer(offset);
      if (tree.getParent() == null) {
        s_logger.debug(indenting.toString() + tree.getText().toString());
      }
      for (int i = 0; i < offset; i++) {
        indenting = indenting.append("=>");
      }
      for (int i = 0; i < tree.getChildCount(); i++) {
        s_logger.debug(indenting.toString() + tree.getChild(i).toString());
        printTree((CommonTree) tree.getChild(i), offset + 1);
      }
    }
  }

}
