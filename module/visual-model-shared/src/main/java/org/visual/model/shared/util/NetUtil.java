package org.visual.model.shared.util;

import java.io.IOException;
import java.net.ServerSocket;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NetUtil {

    public static int randomPort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            return -1;
        }
    }
}
