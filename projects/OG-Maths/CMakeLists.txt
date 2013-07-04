#
# Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#

cmake_minimum_required (VERSION 2.8.6)

project (OG-Maths)

set(OG-Maths_VERSION_MAJOR 0)
set(OG-Maths_VERSION_MINOR 0)
set(OG-Maths_VERSION_PATCH 1)
set(OG-Maths_VERSION
  ${OG-Maths_VERSION_MAJOR}.${OG-Maths_VERSION_MINOR}.${OG-Maths_VERSION_PATCH})

# Append local module folder to module path
set(CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/cmake/modules ${CMAKE_MODULE_PATH})

# Add Java
find_package(Java)
include(UseJava)

# Always include debug info in Java, since it does no harm and we don't have multiple
# build types for it in CMake as far as I am aware.
set(CMAKE_Java_COMPILE_FLAGS "${CMAKE_Java_COMPILE_FLAGS} -g")

set(CMAKE_JAVA_INCLUDE_PATH /home/graham/.ivy2/cache/org.slf4j/slf4j/jars/slf4j-api-1.7.5.jar /home/graham/.ivy2/cache/org.testng/testng/jars/testng-6.3.1.jar /home/graham/src/OG-MathsWrappers-0.0.1.jar /home/graham/.ivy2/cache/org.fudgemsg/fudge-java/jars/fudge-java-0.3.9.8.jar)

add_subdirectory(src)

message("Build type: " ${CMAKE_BUILD_TYPE})

message("
  Package " ${CMAKE_PROJECT_NAME} " version " ${OG-Maths_VERSION} "
  Java Compiler............: " ${Java_JAVAC_EXECUTABLE} "
  Java Compiler flags......: " ${CMAKE_Java_COMPILE_FLAGS} "
  Jar Tool.................: " ${Java_JAR_EXECUTABLE} "
")
