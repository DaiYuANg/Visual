/*
 * Scenic View,
 * Copyright (C) 2013 Jonathan Giles, Ander Ruiz, Amy Fowler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.debugger.model.attach;

import static org.visual.shared.singleton.OS.OS;

import com.sun.tools.attach.VirtualMachine;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.SystemUtils;
import org.visual.debugger.api.AttachHandler;
import org.visual.debugger.utils.Utils;

/** */
@Slf4j
public class AttachHandlerFactory {

  private static Properties properties;
  private static AttachHandler attachHandler;

  public static void initAttachAPI(final Stage stage) {
    val vms = VirtualMachine.list();
    val jdkHome = SystemUtils.JAVA_HOME;
    AttachHandler attachHandler = getAttachHandler();
    //        // first we check if the classes are already on the classpath
    //        boolean isAttachAPIAvailable = AttachHandlerFactory.isAttachAvailable();
    //        if (isAttachAPIAvailable) return;
    //
    //        // we read the properties file to find previous entries
    //        properties = PropertiesUtils.getProperties();
    //
    ////        String jdkHome = "";
    //        JDKToolsJarPair jdkHome = null;
    //
    //        boolean needAttachAPI = true;
    //
    //        {
    //
    //            // firstly we try the properties reference
    //            String jdpPathPropertyValue = properties.getProperty(JDK_PATH_KEY);
    //            jdkHome = jdpPathPropertyValue == null ? null : new
    // JDKToolsJarPair(jdpPathPropertyValue);
    //            needAttachAPI = jdkHome == null ||
    // !Utils.checkPath(jdkHome.getJdkPath().getAbsolutePath());
    //            if (needAttachAPI) {
    //                // If we can't get it from the properties file, we try to
    //                // find it on the users operating system
    //                List<JDKToolsJarPair> jdkPaths = new ArrayList<>();
    //                attachHandler.getOrderedJDKPaths(jdkPaths);
    //                //System.out.println("found jdks: " + jdkPaths);
    //
    //                // TODO we should handle this better!
    //                if (!jdkPaths.isEmpty()) {
    //                    JDKToolsJarPair jdkPathFile = jdkPaths.getFirst();
    //                    if (jdkPathFile != null) {
    //                        jdkHome = jdkPathFile;
    //                        needAttachAPI =
    // !Utils.checkPath(jdkHome.getJdkPath().getAbsolutePath());
    //                    }
    //                }
    //            }
    //
    //            if (!needAttachAPI) {
    //                addToolsJarToClasspath(jdkHome);
    //            }
    //        }
    //
    //        if (needAttachAPI) {
    //            /**
    //             * This needs to be improved, in this situation we already have
    //             * attachAPI but not because it was saved in the file, try to
    //             * fill the path by finding it
    //             */
    //            if (!needAttachAPI && jdkHome == null) {
    //                List<JDKToolsJarPair> jdkPaths = new ArrayList<>();
    //                attachHandler.getOrderedJDKPaths(jdkPaths);
    //
    //                // TODO we should handle this better!
    //                if (!jdkPaths.isEmpty()) {
    //                    JDKToolsJarPair jdkPathFile = jdkPaths.get(0);
    //                    if (jdkPathFile != null) {
    //                        jdkHome = jdkPathFile;
    //                    }
    //                }
    //            }
    //
    ////            final String _attachPath = jdkHome;
    //            if (platform == Platform.MAC) {
    //                System.setProperty("javafx.macosx.embedded", "true");
    //            }
    //
    //            DirectoryChooser jdkChooser = new DirectoryChooser();
    //            jdkChooser.setTitle("Please find an installed JDK");
    //
    //            File initialDirectory = new File("/");
    //            jdkChooser.setInitialDirectory(initialDirectory);
    //            final File jdkPathFile = jdkChooser.showDialog(stage);
    //            if (jdkPathFile != null) {
    //                String jdkPath = jdkPathFile.getAbsolutePath();
    //                if (jdkPath.isEmpty()) {
    //                    javafx.application.Platform.exit();
    //                }
    //                jdkHome = new JDKToolsJarPair(jdkPath);
    //                addToolsJarToClasspath(jdkHome);
    //                properties.setProperty(VisualModelDebugger.JDK_PATH_KEY, jdkPath);
    //                PropertiesUtils.saveProperties();
    //            }
    //        }
    //
    //        patchAttachLibrary(Objects.requireNonNull(jdkHome));

  }

  private static boolean isAttachAvailable() {
    //        // Test if we can load a class from tools.jar
    //        try {
    //
    // Class.forName("com.sun.tools.attach.AttachNotSupportedException").newInstance();
    //            return true;
    //        } catch (final Exception e) {
    //            log.error(e.getLocalizedMessage(), e, "Java Attach API was not found on
    // classpath,
    // will attempt manual lookup...");
    ////            e.printStackTrace();
    //            return false;
    //        }
    //
    ////    	// it seems that, on Windows at least, we _need_ to instantiate the attach
    ////    	// library, even if it is available on the classpath.
    ////    	return false;
    return true;
  }

  private static AttachHandler getAttachHandler() {
    if (attachHandler != null) {
      return attachHandler;
    }
    switch (OS) {
      case WINDOWS -> attachHandler = new WindowsAttachHandler();
      case MAC -> attachHandler = new MacAttachHandler();
      case LINUX -> attachHandler = new LinuxAttachHandler();
      case null, default ->
          // TODO handle alternate operating systems like Linux, etc
          attachHandler = new AttachHandlerBase();
    }
    return attachHandler;
  }

  // TODO there is interesting code at the following URL for enumerating all
  // available AttachProviders:
  // http://stackoverflow.com/questions/11209267/dynamic-library-loading-with-pre-and-post-check-using-serviceloader
  @SneakyThrows
  private static void patchAttachLibrary(final JDKToolsJarPair jdkHome) {
    File jdkHomeFile = jdkHome.getJdkPath();

    try {
      System.loadLibrary("attach");
      log.atTrace().log("Loading attach library from {}", jdkHome);
    } catch (final UnsatisfiedLinkError e) {
      /** Try to set or modify java.library.path */
      final String path =
          jdkHomeFile.getAbsolutePath()
              + File.separator
              + "jre"
              + File.separator
              + "bin;"
              + System.getProperty("java.library.path");
      System.setProperty("java.library.path", path);

      //            try {
      /** This code is need for reevaluating the library path */
      final Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
      fieldSysPath.setAccessible(true);
      fieldSysPath.set(null, null);
      System.loadLibrary("attach");
      //            } catch (final Throwable e2) {
      //                log.atError().log(e2.getMessage(), e, "Error while trying to put
      // attach.dll
      // in path");
      //            }
    }
  }

  @SneakyThrows
  private static void addToolsJarToClasspath(final JDKToolsJarPair jdkPath) {
    final URL url = Utils.toURI(jdkPath.getToolsPath(getAttachHandler()).getAbsolutePath()).toURL();
    log.atTrace().log("Adding to classpath: {}", url);

    final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    final Class<?> sysclass = URLClassLoader.class;

    final Method method = sysclass.getDeclaredMethod("addURL", URL.class);
    method.setAccessible(true);
    method.invoke(sysloader, url);
  }

  static void doBasicJdkSearch(List<JDKToolsJarPair> jdkPaths) {
    //        final String javaHome = System.getProperty("java.home");
    //        if (isJREInsideJDKFolder(javaHome)) {
    //            File jdkHome = new File(javaHome).getParentFile();// + "/../lib/tools.jar");
    //            if (jdkHome.exists()) {
    //                jdkPaths.add(new JDKToolsJarPair(jdkHome));
    //            }
    //        } else if (!isJDKFolder(javaHome)) {
    //            log.atTrace().log("Error: No JDK found on system");
    //            return;
    //        }
    //
    //        // This points to, for example, "C:\Program Files
    //        // (x86)\Java\jdk1.6.0_30\jre"
    //        // This is one level too deep. We want to pop up and then go into the
    //        // lib directory to find tools.jar
    //        log.atInfo().log("JDK found at: {}", javaHome);
    //
    //        val jdkHome = new File(javaHome);// + "/../lib/tools.jar");
    //        if (jdkHome.exists()) {
    //            jdkPaths.add(new JDKToolsJarPair(jdkHome));
    //        }
  }

  static boolean isJDKFolder(String path) {
    if (path == null) return false;
    val f = new File(path);
    return f.exists() && f.isDirectory() && f.getPath().contains("jdk");
  }

  static boolean isJREInsideJDKFolder(String path) {
    if (path == null) {
      return false;
    }
    val f = new File(path);
    return f.exists()
        && f.isDirectory()
        && f.getPath().contains("jdk")
        && f.getName().contains("jre");
  }
}
