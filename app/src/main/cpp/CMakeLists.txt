cmake_minimum_required(VERSION 3.4.1)

add_library(nativeenv SHARED nativeenv.c)

find_library(log-lib log)

target_link_libraries(nativeenv ${log-lib})