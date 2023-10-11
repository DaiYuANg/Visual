package org.visual.model.language.lsp;

import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;

public class VisualModelLanguageLauncher {
	public static Launcher<LanguageClient> createServerLauncher(LanguageServer server, InputStream in,
			OutputStream out) {
		return new Launcher.Builder<LanguageClient>().setLocalService(server).setRemoteInterface(LanguageClient.class)
				.setInput(in).setOutput(out).create();
	}
}
