package org.visual.model.language.lsp;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageClient;

@Slf4j
public class Main {
	@SneakyThrows
	public static void main(String[] args) {
		log.info("start server");
		startServer(System.in, System.out);
	}

	@SneakyThrows
	public static void startServer(InputStream in, OutputStream out) {
		VisualModelLanguageServer server = new VisualModelLanguageServer();
		Launcher<LanguageClient> launcher = Launcher.createLauncher(server, LanguageClient.class, in, out);
		LanguageClient client = launcher.getRemoteProxy();
		server.connect(client);
		Future<?> startListening = launcher.startListening();
		startListening.get();
	}
}
