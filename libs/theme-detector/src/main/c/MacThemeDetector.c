#include <jni.h>
#include "MacThemeDetector.h"
#include <CoreFoundation/CoreFoundation.h>

JNIEXPORT jint JNICALL Java_MacThemeDetector_getCurrentTheme(JNIEnv *env, jobject obj) {
    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *interfaceStyle = [defaults stringForKey:@"AppleInterfaceStyle"];

    if (interfaceStyle != nil && [interfaceStyle isEqualToString:@"Dark"]) {
        [pool release];
        return 1; // Dark theme
    } else {
        [pool release];
        return 0; // Light theme
    }
}
