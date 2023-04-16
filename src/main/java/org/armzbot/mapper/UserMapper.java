package org.armzbot.mapper;

import org.armzbot.dto.RegisterResponse;
import org.armzbot.dto.UserProfile;
import org.armzbot.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponse toRegisterResponse(User user);

    UserProfile toUserProfile(User user);

}
