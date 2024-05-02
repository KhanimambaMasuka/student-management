package com.learning.dto;

import com.learning.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring"
        ,unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class UserDTOMapper {
    public abstract UserDTO toUserDTO(User user);
}
