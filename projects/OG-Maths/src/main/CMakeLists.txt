#
# Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#
set(JARNAME ${PROJECT_NAME}-${OG-Maths_VERSION})

file(GLOB_RECURSE JAR_SOURCES *.java)

add_jar(${JARNAME} ${JAR_SOURCES})
