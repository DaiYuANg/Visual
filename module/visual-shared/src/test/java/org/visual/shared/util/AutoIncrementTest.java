package org.visual.shared.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.val;
import org.junit.jupiter.api.Test;

class AutoIncrementTest {

  @Test
  void next() {
    val max = 100000;
    val result =
        IntStream.range(0, max)
            .parallel()
            .mapToLong(i -> AutoIncrement.next())
            .boxed() // Convert long to Long
            .collect(Collectors.toSet());

    assertEquals(max, result.size());
  }
}
