package org.armzbot.adaptor;

import lombok.extern.log4j.Log4j2;
import org.armzbot.dto.UserProfile;
import org.armzbot.entity.User;
import org.armzbot.exception.UserException;
import org.armzbot.mapper.UserMapper;
import org.armzbot.services.UserService;
import org.armzbot.utils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserAdaptor {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserAdaptor(UserService userService, UserMapper userMapper) {

        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserProfile getUserProfile() throws UserException{
        Optional<String> opt = SecurityUtil.getCurrentUserId();

        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }
        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }

        return userMapper.toUserProfile(optUser.get());

    }


}
