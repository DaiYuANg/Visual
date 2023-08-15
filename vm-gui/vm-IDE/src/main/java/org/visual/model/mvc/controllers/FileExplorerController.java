package org.visual.model.mvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.annotations.EventSubscriber;
import org.visual.model.mvc.base.Controller;

@Slf4j
@EventSubscriber
public class FileExplorerController implements Controller {
	@Override
	public void initialize() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
