package org.visual.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.visual.entity.DatabaseConnection;
import org.visual.model.form.DatabaseConnectForm;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface DatabaseConnectConverter {
  @Mapping(target = "updateAt", ignore = true)
  @Mapping(target = "sort", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "databaseType", ignore = true)
  @Mapping(target = "createAt", ignore = true)
  DatabaseConnection create(DatabaseConnectForm form);
}
