package org.visual.model.services.impl;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.services.IOperationSystemService;
import oshi.SystemInfo;

@Slf4j
@Singleton
public class OperationSystemService implements IOperationSystemService {

	private final SystemInfo systemInfo = new SystemInfo();

	public OperationSystemService() {
		log.info("test");
	}

	@Override
	public String getOperatingSystemType() {

		return systemInfo.getOperatingSystem().getFamily();
	}
}
