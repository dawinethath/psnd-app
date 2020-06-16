cmake_minimum_required(VERSION 3.4.1)

add_library(internal-jni SHARED  internal-jni.c)

# Include libraries needed for internal-jni lib
target_link_libraries(internal-jni  android  log)