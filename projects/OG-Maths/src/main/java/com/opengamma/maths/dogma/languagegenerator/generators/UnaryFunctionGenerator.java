/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.generators;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;
import com.opengamma.maths.dogma.languagegenerator.DogmaLangTokenToCodeGenerator;
import com.opengamma.maths.dogma.languagegenerator.FullToken;
import com.opengamma.maths.dogma.languagegenerator.docs.Doc;
import com.opengamma.maths.dogma.languagegenerator.docs.DocsDictionaryPopulator;

/**
 * 
 */
public class UnaryFunctionGenerator implements DogmaLangTokenToCodeGenerator {
  UnaryFunctionGenerator() {
  }

  private static Logger s_log = LoggerFactory.getLogger(UnaryFunctionGenerator.class);

  private static UnaryFunctionGenerator s_instance = new UnaryFunctionGenerator();

  public static UnaryFunctionGenerator getInstance() {
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
    tmp.append("public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0) {\n");
    tmp.append("Catchers.catchNullFromArgList(arg0, 1);\n");
    tmp.append("int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg0.getClass());\n");
    tmp.append("OGArray<? extends Number> tmp = s_unaryFunctionChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1], arg0);\n");
    tmp.append("return tmp;\n");
    tmp.append("}\n\n");

    tmp.append("public static Number ");
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
    tmp.append("OGArray<? extends Number> tmp = s_unaryFunctionChainRunner.dispatch(s_");
    tmp.append(lname);
    tmp.append("Instructions[type1], arg0rewrite);\n");
    tmp.append("return tmp.getEntry(0, 0);\n");
    tmp.append("}\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append("UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>[] ");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable");
    tmp.append(" = MethodScraperForUnaryFunctions.availableMethodsForUnaryFunctions(operatorDictUnary.getOperationsMap(),");
    tmp.append(f.getSimpleName());
    tmp.append(".class);\n");
    tmp.append("s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions = MethodScraperForUnaryFunctions.computeFunctions(ConversionCostAdjacencyMatrixStore.getWeightedAdjacencyMatrix(),");
    tmp.append(f.getSimpleName());
    tmp.append("FunctionTable, defaultUnaryFunctionEvalCostsMatrix);\n\n");
    return tmp.toString();
  }

  @Override
  public String generateTableCodeVariables(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    tmp.append(s_indent + "private static UnaryFunctionChain[] s_");
    tmp.append(f.getSimpleName().toLowerCase());
    tmp.append("Instructions;");
    tmp.append("\n");
    return tmp.toString();
  }

  @Override
  public String generateEntryPointsCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    String lname = f.getSimpleName().toLowerCase();

    String functionTitle = "DOGMA Function: " + f.getSimpleName() + "( arg0 )";
    String shortDesc = "Docs Missing - No description given";
    String longDesc = "Docs Missing - No description given";
    String example = "Docs Missing - No description given";
    String argumentValues = "Docs Missing - No description given\n";
    String returnValueDescription = "Docs Missing - No description given";
    Set<Doc> docClazz = s_classToDocsMap.get(f.getInterfaceClass());
    if (s_classToDocsMap.get(f.getInterfaceClass()) != null) {
      if (docClazz.size() > 1) {
        s_log.warn("Multiple documentation classes found for function " + f.getSimpleName() + " results may be strange!");
      }
      shortDesc = "";
      longDesc = "";
      example = "";
      argumentValues = "";
      returnValueDescription = "";
      Iterator<Doc> it = docClazz.iterator();
      Doc next = null;
      while (it.hasNext()) {
        next = it.next();
        shortDesc += next.shortDescription();
        longDesc += next.longDescription();
        example += next.exampleInput();
        if (next.argDescriptions().length > 1) {
          throw new MathsExceptionConfigProblem("Docs suppied for class " + f.getSimpleName() + " declare more argument descriptions than there are actual arguments.");
        }
        for (int i = 0; i < next.argDescriptions().length; i++) {
          argumentValues += next.argDescriptions()[i] + "\n";
        }
        returnValueDescription += next.returnDescription();
      }
    }

    tmp.append("/**\n");
    tmp.append(" * ");
    
    tmp.append(functionTitle + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Short Description:\n");
    tmp.append(" * ");
    
    tmp.append(shortDesc + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Full Description:\n");
    tmp.append(" * ");
    tmp.append(longDesc + "\n");
    
    tmp.append(" * <p>\n");
    tmp.append(" * Example(s):\n");
    tmp.append(" * <p>\n");
    tmp.append(" * ");
    
    tmp.append(example + "\n");
    tmp.append(" * <p>\n");
    
    tmp.append(" * @param arg0 " + argumentValues);
    tmp.append(" * <p>\n");
    tmp.append(" * @return " + returnValueDescription + "\n");
    tmp.append(" */");
    
    // the code
    String callStr = s_autogenPath + "DOGMA" + f.getSimpleName() + "." + lname + "(arg0);";
    tmp.append("\n");
    tmp.append(s_indent + "public static OGArray<? extends Number> ");
    tmp.append(lname);
    tmp.append("(OGArray<? extends Number> arg0) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");

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
    tmp.append(longDesc + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * Example(s):\n");
    tmp.append(" * <p>\n");
    tmp.append(" * ");
    tmp.append(example + "\n");
    tmp.append(" * <p>\n");
    tmp.append(" * @param arg0 " + argumentValues);
    tmp.append(" * <p>\n");
    tmp.append(" * @return " + returnValueDescription + "\n");
    tmp.append(" */");
    tmp.append("\n" + s_indent + "public static Number ");
    tmp.append(lname);
    tmp.append("(Number arg0) {\n");
    tmp.append(s_indent + s_indent + "return ");
    tmp.append(callStr);
    tmp.append("\n");
    tmp.append(s_indent + "}\n\n");
    return tmp.toString();
  }
}
