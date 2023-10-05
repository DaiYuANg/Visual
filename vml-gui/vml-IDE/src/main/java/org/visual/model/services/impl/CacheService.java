package org.visual.model.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import dev.dirs.BaseDirectories;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.services.ICacheService;

@Slf4j
@Singleton
public class CacheService implements ICacheService {

    private final String cacheRoot;

    @Inject
    public CacheService(@Named("ApplicationCache") String cacheRoot) {
        this.cacheRoot = cacheRoot;
    }
}
