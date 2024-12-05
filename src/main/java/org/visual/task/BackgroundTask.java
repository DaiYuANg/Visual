package org.visual.task;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class BackgroundTask extends AbstractExecutionThreadService {
  @Override
  protected void run() throws Exception {
  }
}
