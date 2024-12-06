package org.visual.app.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.visual.app.entity.DatabaseConnection;
import org.visual.database.DBConnection;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface DatabaseConnectConverter {
  @Mapping(target = "updateAt", ignore = true)
  @Mapping(target = "sort", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "databaseType", ignore = true)
  @Mapping(target = "createAt", ignore = true)
  DatabaseConnection create(DBConnection form);
}
