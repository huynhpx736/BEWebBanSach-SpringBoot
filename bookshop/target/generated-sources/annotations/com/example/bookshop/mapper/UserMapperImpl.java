package com.example.bookshop.mapper;

import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T08:54:13+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRole( user.getRole() );
        userDTO.setAvatar( user.getAvatar() );
        userDTO.setFullname( user.getFullname() );
        userDTO.setPhone( user.getPhone() );
        userDTO.setClassification( user.getClassification() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setRole( userDTO.getRole() );
        user.setAvatar( userDTO.getAvatar() );
        user.setFullname( userDTO.getFullname() );
        user.setPhone( userDTO.getPhone() );
        user.setClassification( userDTO.getClassification() );

        return user;
    }
}
