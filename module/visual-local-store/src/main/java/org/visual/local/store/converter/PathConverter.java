package org.visual.local.store.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.nio.file.Path;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class PathConverter implements AttributeConverter<Path, String> {
  @Override
  public String convertToDatabaseColumn(@NonNull Path attribute) {
    return attribute.toAbsolutePath().toString();
  }

  @Override
  public Path convertToEntityAttribute(String dbData) {
    return Path.of(dbData);
  }
}
