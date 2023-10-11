package org.visual.model.language.lsp;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.*;

@Slf4j
public class VisualModelLanguageServer implements LanguageServer, LanguageClientAware {
	@Override
	public void connect(LanguageClient client) {
	}

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
		return null;
	}

	@Override
	public void initialized(InitializedParams params) {
		LanguageServer.super.initialized(params);
	}

	@Override
	public CompletableFuture<Object> shutdown() {
		return null;
	}

	@Override
	public void exit() {
	}

	@Override
	public NotebookDocumentService getNotebookDocumentService() {
		return LanguageServer.super.getNotebookDocumentService();
	}

	@Override
	public TextDocumentService getTextDocumentService() {
		return null;
	}

	@Override
	public WorkspaceService getWorkspaceService() {
		return null;
	}

	@Override
	public void cancelProgress(WorkDoneProgressCancelParams params) {
		LanguageServer.super.cancelProgress(params);
	}

	@Override
	public void setTrace(SetTraceParams params) {
		LanguageServer.super.setTrace(params);
	}
}
