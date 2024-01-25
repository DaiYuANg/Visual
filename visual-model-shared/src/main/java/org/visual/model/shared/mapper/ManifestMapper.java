package org.visual.model.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManifestMapper {
    ManifestMapper INSTANCE = Mappers.getMapper(ManifestMapper.class);
}
