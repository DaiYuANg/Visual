package org.visual.shared.mapper;

import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.visual.shared.pojo.BaseManifest;

@Mapper
public interface ManifestMapper {
  ManifestMapper INSTANCE = Mappers.getMapper(ManifestMapper.class);

  @Mapping(target = "version", source = "Version")
  @Mapping(target = "lastTag", source = "Last-Tag")
  @Mapping(target = "gitHash", source = "Git-Hash")
  @Mapping(target = "branch", source = "Branch")
  BaseManifest mapToManifest(Map<String, String> mapManifest);
}
