package com.curation.backend.user.dto;

import com.curation.backend.user.domain.FollowFollowing;
import com.curation.backend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String profileImg;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.profileImg = entity.getProfileImg();
    }

}