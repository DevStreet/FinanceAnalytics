/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.generators;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.dogma.languagegenerator.DogmaLangTokenToCodeGenerator;
import com.opengamma.maths.dogma.languagegenerator.FullToken;
import com.opengamma.maths.dogma.languagegenerator.docs.Doc;
import com.opengamma.maths.dogma.languagegenerator.docs.DocFormatter;
import com.opengamma.maths.dogma.languagegenerator.docs.DocsDictionaryPopulator;

/**
 * 
 */
public class VoidUnaryFunctionGenerator implements DogmaLangTokenToCodeGenerator {
  VoidUnaryFunctionGenerator() {
  }

  private static Logger s_log = LoggerFactory.getLogger(VoidUnaryFunctionGenerator.class);

  private static VoidUnaryFunctionGenerator s_instance = new VoidUnaryFunctionGenerator();

  public static VoidUnaryFunctionGenerator getInstance() {
    return s_instance;
  }

  private static String s_autogenPath = "com.opengamma.maths.dogma.autogen.";
  private static String s_indent = "  ";

  private static DocsDictionaryPopulator<Doc> s_docsDict = new DocsDictionaryPopulator<Doc>();
  private static Map<Class<?>, Set<Doc>> s_classToDocsMap = s_docsDict.getOperationsMap();

  @Override
  public String generateMethodCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    String lname = f.getSimpleName().toLowerCase();
    tmp.append("\n");
    tmp.append("public static void ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0) {\n");
    tmp.append("Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0.getClass());\n");
    tmp.append(" s_voidUnaryFunctionChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1], arg0);\n");
    tmp.append("}\n\n");

    tmp.append("public static void ");
    tmp.append(lname);
    tmp.append("(Number arg0) {");
    tmp.append("Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("OGArray<? extends Number> arg0rewrite;\n");
    tmp.append("if (arg0.getClass() == ComplexType.class) {\n");
    tmp.append("arg0rewrite = new OGComplexScalar(arg0);\n");
    tmp.append("} else {\n");
    tmp.append("arg0rewrite = new OGRealScalar(arg0.doubleValue());\n");
    tmp.append("}\n");
    tmp.append("int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0rewrite.getClass());\n");
    tmp.append("s_voidUnaryFunctionChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1], arg0rewrite);\n");
    tmp.append("}\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append("VoidUnaryFunction<OGArray<? extends Number>>[] ");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable");
    tmp.append(" = MethodScraperForVoidUnaryFunctions.availableMethodsForVoidUnaryFunctions(operatorDictVoidUnary.getOperationsMap(),");
    tmp.append(f.getSimpleName());
    tmp.append(".class);\n");
    tmp.append("s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions = MethodScraperForVoidUnaryFunctions.computeFunctions(ConversionCostAdjacencyMatrixStore.getWeightedAdjacencyMatrix(),");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable, defaultUnaryFunctionEvalCostsMatrix); // same eval cost matrix is fine as default\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCodeVariables(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append(s_indent + "private static VoidUnaryFunctionChain[] s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions;");
    tmp.append("\n");
    return tmp.toString();
  }

  @Override
  public String generateEntryPointsCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    String lname = f.getSimpleName().toLowerCase();
    StringBuffer docStr = new StringBuffer();
    DocFormatter d = new DocFormatter(f);
    docStr.append(d.getDocs());

    // the code
    String callStr = s_autogenPath + "DOGMA" + f.getSimpleName() + "." + lname + "(arg0);";

    tmp.append("\n");
    tmp.append(docStr);
    tmp.append(s_indent + "public static void ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0) {\n");
    tmp.append(s_indent + s_indent);
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");

    tmp.append(docStr);
    tmp.append(s_indent + "public static void ");
    tmp.append(lname);
    tmp.append("(Number arg0) {\n");
    tmp.append(s_indent + s_indent);
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");
    return tmp.toString();
  }
}
