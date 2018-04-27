cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})
add_library(hellondk-lib
             SHARED
             src/main/jni/hellondk.c)

target_link_libraries( # Specifies the target library.
                       hellondk-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
