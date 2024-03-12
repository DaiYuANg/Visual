package org.visual.shared.util;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AutoIncrement {

  private final AtomicLong counter = new AtomicLong(0);

  private final ReentrantLock locker = new ReentrantLock();

  public long next() {
    return next(1);
  }

  public long next(long space) {
    try {
      locker.lock();
      return counter.getAndAdd(space);
    } finally {
      locker.unlock();
    }
  }
}
