package com.curation.backend.route.dto;

import com.curation.backend.route.domain.Route;
import com.curation.backend.user.dto.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RouteStorageListResponseDto {

    private Long id;
    private String routeImg;
    private String routeDescription;
    private LocalDateTime modifiedAt;
    private UserResponseDto user;

    private Long likeCnt;
    private Long storageCnt;
    private Long commentCnt;

    public RouteStorageListResponseDto(Route entity) {
        this.id = entity.getId();
        this.routeImg = entity.getRouteImg();
        this.routeDescription = entity.getRouteDescription();
        this.modifiedAt = entity.getModifiedAt();
        this.user = new UserResponseDto(entity.getUser());

        this.likeCnt = Long.valueOf(entity.getRouteLikes().size());
        this.storageCnt = Long.valueOf(entity.getRouteStorages().size());
        this.commentCnt = Long.valueOf(entity.getRouteComments().size());
    }

}
