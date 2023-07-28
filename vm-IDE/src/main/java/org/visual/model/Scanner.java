package org.visual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

@Getter
@AllArgsConstructor
public enum Scanner {
	INSTANCE(new Reflections(new ConfigurationBuilder().forPackages(VisualModelIDE.class.getPackageName())));

	private final Reflections scanner;
}
