package com.example.mod4.mapper.v2;

import com.example.mod4.model.User;
import com.example.mod4.web.model.UserRequest;
import com.example.mod4.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperV2 {

    UserResponse userToResponse(User user);

    User requestToUser(UserRequest request);

}
