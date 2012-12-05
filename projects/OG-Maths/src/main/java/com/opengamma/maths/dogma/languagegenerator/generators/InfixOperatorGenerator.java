/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.dogma.languagegenerator.DogmaLangTokenToCodeGenerator;
import com.opengamma.maths.dogma.languagegenerator.FullToken;
import com.opengamma.maths.dogma.languagegenerator.docs.DocFormatter;

/**
 * Generates code for classes that are masking as infix operations
 */
public class InfixOperatorGenerator implements DogmaLangTokenToCodeGenerator {
  InfixOperatorGenerator() {
  }

  private static Logger s_log = LoggerFactory.getLogger(InfixOperatorGenerator.class);

  private static InfixOperatorGenerator s_instance = new InfixOperatorGenerator();

  public static InfixOperatorGenerator getInstance() {
    return s_instance;
  }

  private static String s_autogenPath = "com.opengamma.maths.dogma.autogen.";
  private static String s_indent = "  ";

  @Override
  public String generateMethodCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    String lname = f.getSimpleName().toLowerCase();
    tmp.append("\n");
    tmp.append(s_indent + "public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0, OGArray<? extends Number> arg1) {\n");
    tmp.append(s_indent + s_indent + "Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append(s_indent + s_indent + "Catchers.catchNullFromArgList(arg1, 2);\n");
    tmp.append(s_indent + s_indent + "int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0.getClass());\n");
    tmp.append(s_indent + s_indent + "int type2 = MatrixTypeToIndexMap.getIndexFromClass(arg1.getClass());\n");
    tmp.append(s_indent + s_indent + "OGArray<? extends Number> tmp = s_infixOpChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1][type2], arg0, arg1);\n");
    tmp.append(s_indent + s_indent + "return tmp;\n");
    tmp.append(s_indent + "}\n\n");

    tmp.append("public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(Number arg0, OGArray<? extends Number> arg1) {\n");
    tmp.append("  Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("  Catchers.catchNullFromArgList(arg1, 2);\n");
    tmp.append("OGArray<? extends Number> arg0rewrite;\n");
    tmp.append("if (arg0.getClass() == ComplexType.class) {\n");
    tmp.append("arg0rewrite = new OGComplexScalar(arg0);\n");
    tmp.append("} else {\n");
    tmp.append("arg0rewrite = new OGRealScalar(arg0.doubleValue());\n");
    tmp.append("}\n");
    tmp.append("int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0rewrite.getClass());\n");
    tmp.append("int type2 = MatrixTypeToIndexMap.getIndexFromClass(arg1.getClass());\n");
    tmp.append("  OGArray<? extends Number> tmp = s_infixOpChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1][type2], arg0rewrite, arg1);\n");
    tmp.append("  return tmp;\n");
    tmp.append("}\n\n");

    tmp.append("public static OGArray<? extends Number>");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0, Number arg1) {\n");
    tmp.append("  Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("  Catchers.catchNullFromArgList(arg1, 2);\n");
    tmp.append("OGArray<? extends Number> arg1rewrite;\n");
    tmp.append("if (arg1.getClass() == ComplexType.class) {\n");
    tmp.append("arg1rewrite = new OGComplexScalar(arg1);\n");
    tmp.append("} else {\n");
    tmp.append(" arg1rewrite = new OGRealScalar(arg1.doubleValue());\n");
    tmp.append(" }\n");
    tmp.append(" int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0.getClass());\n");
    tmp.append(" int type2 = MatrixTypeToIndexMap.getIndexFromClass(arg1rewrite.getClass());\n");
    tmp.append("  OGArray<? extends Number> tmp = s_infixOpChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1][type2], arg0, arg1rewrite);\n");
    tmp.append("  return tmp;\n");
    tmp.append("}\n\n");

    tmp.append("public static Number ");
    tmp.append(lname);
    tmp.append(" (Number arg0, Number arg1) {\n");
    tmp.append("  Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("  Catchers.catchNullFromArgList(arg1, 2);\n");
    tmp.append("OGArray<? extends Number> arg0rewrite;\n");
    tmp.append("if (arg0.getClass() == ComplexType.class) {\n");
    tmp.append("arg0rewrite = new OGComplexScalar(arg0);\n");
    tmp.append("} else {\n");
    tmp.append("arg0rewrite = new OGRealScalar(arg0.doubleValue());\n");
    tmp.append("}\n");
    tmp.append("OGArray<? extends Number> arg1rewrite;\n");
    tmp.append("if (arg1.getClass() == ComplexType.class) {\n");
    tmp.append("arg1rewrite = new OGComplexScalar(arg1);\n");
    tmp.append("} else {\n");
    tmp.append(" arg1rewrite = new OGRealScalar(arg1.doubleValue());\n");
    tmp.append(" }\n");
    tmp.append(" int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0rewrite.getClass());\n");
    tmp.append(" int type2 = MatrixTypeToIndexMap.getIndexFromClass(arg1rewrite.getClass());\n");
    tmp.append("  OGArray<? extends Number> tmp = s_infixOpChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1][type2], arg0rewrite, arg1rewrite);\n");
    tmp.append("  return tmp.getEntry(0,0);\n");
    tmp.append("}\n\n");
    return tmp.toString();
  }

  @Override
  public String generateEntryPointsCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    StringBuffer docStr = new StringBuffer();
    String lname = f.getSimpleName().toLowerCase();
    String callStr = s_autogenPath + "DOGMA" + f.getSimpleName() + "." + lname + "(arg0, arg1);";

    DocFormatter d = new DocFormatter(f);
    docStr.append(d.getDocs());

    tmp.append(docStr);
    tmp.append(s_indent + "public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0, OGArray<? extends Number> arg1) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");

    tmp.append(docStr);
    tmp.append(s_indent + "public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(Number arg0, OGArray<? extends Number> arg1) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");

    tmp.append(docStr);
    tmp.append(s_indent + "public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0, Number arg1) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");

    tmp.append(docStr);
    tmp.append(s_indent + "public static Number ");
    tmp.append(lname);
    tmp.append("(Number arg0, Number arg1) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append("InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] ");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable");
    tmp.append(" = MethodScraperForInfixOperators.availableMethodsForInfixOp(operatorDictInfix.getOperationsMap(),");
    tmp.append(f.getSimpleName());
    tmp.append(".class, s_verbose);\n");
    tmp.append("s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions = MethodScraperForInfixOperators.computeFunctions(ConversionCostAdjacencyMatrixStore.getWeightedAdjacencyMatrix(),");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable, defaultInfixFunctionEvalCostsMatrix);\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCodeVariables(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append(s_indent + "private static InfixOpChain[][] s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions;");
    tmp.append("\n");
    return tmp.toString();
  }

}
