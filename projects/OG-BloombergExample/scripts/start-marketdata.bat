@echo off

if "%JAVA_HOME%" == "" echo Warning: JAVA_HOME is not set
set JAVACMD=java.exe

set RUN_MODE=%1
if "%RUN_MODE%" == "" set RUN_MODE=shareddev

"%JAVACMD%" -jar lib\org.eclipse-jetty-jetty-start-7.0.1.v20091125.jar ^
  -Xms1024m -Xmx1024m -XX:MaxPermSize=256M -XX:+UseConcMarkSweepGC ^
  -XX:+CMSIncrementalMode -XX:+CMSIncrementalPacing ^
  -Djetty.port=8090 ^
  -Dlogback.configurationFile=marketdata-logback.xml ^
  start.class=com.opengamma.production.startup.MarketDataServer ^
  config\marketdata-spring.xml ^
  "path=config;london.jar" "lib=lib"
