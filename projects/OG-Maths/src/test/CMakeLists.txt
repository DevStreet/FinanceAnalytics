#
# Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#
set(JARNAME Test-${PROJECT_NAME}-${OG-Maths_VERSION})
set(OGMATHSJARNAME ${PROJECT_NAME}-${OG-Maths_VERSION})

set(OGMATHSJAR ${JARDIR}/${OGMATHSJARNAME}.jar)
set(CMAKE_JAVA_INCLUDE_PATH ${CMAKE_JAVA_INCLUDE_PATH} /home/graham/.ivy2/cache/org.testng/testng/jars/testng-6.3.1.jar ${OGMATHSJAR})

file(GLOB_RECURSE JAR_SOURCES *.java)

add_jar(${JARNAME} ${JAR_SOURCES})
#add_dependencies(${JARNAME})
