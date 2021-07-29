package com.curation.backend.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final WhatTagRepository whatTagRepository;
    private final WithTagRepository withTagRepository;
    private final FollowerFollowingRepository followerFollowingRepository;

    @GetMapping("")
    public void test() {

        List<User> users = new ArrayList<>();
        User user1 = User.builder().name("사용자1").role(Role.USER).email("user1@naver.com").userDescription(null).fileName(null).profileImg(null).build();
        User user2 = User.builder().name("사용자2").role(Role.USER).email("user2@gmail.com").userDescription(null).fileName(null).profileImg(null).build();
        User user3 = User.builder().name("사용자3").role(Role.USER).email("user3@ssafy.com").userDescription(null).fileName(null).profileImg(null).build();
        User user4 = User.builder().name("사용자4").role(Role.USER).email("user4@daum.com").userDescription(null).fileName(null).profileImg(null).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        userRepository.saveAll(users);

        List<WhatTag> whatTags = new ArrayList<>();
        whatTags.add(WhatTag.builder().title("유명 관광지는 필수").build());
        whatTags.add(WhatTag.builder().title("SNS 핫플레스").build());
        whatTags.add(WhatTag.builder().title("여유롭게 힐링").build());
        whatTags.add(WhatTag.builder().title("체험/액티비티").build());
        whatTags.add(WhatTag.builder().title("문화/예술/역사").build());
        whatTags.add(WhatTag.builder().title("자연과 함께").build());
        whatTags.add(WhatTag.builder().title("관광보다 먹방").build());

        whatTagRepository.saveAll(whatTags);

        List<WithTag> withTags = new ArrayList<>();
        withTags.add(WithTag.builder().title("혼자").build());
        withTags.add(WithTag.builder().title("친구와").build());
        withTags.add(WithTag.builder().title("연인과").build());
        withTags.add(WithTag.builder().title("배우자와").build());
        withTags.add(WithTag.builder().title("아이와").build());
        withTags.add(WithTag.builder().title("부모님과").build());
        withTags.add(WithTag.builder().title("기타").build());

        withTagRepository.saveAll(withTags);

        //팔로우-팔로잉 설정
        List<FollowFollowing> followFollowings = new ArrayList<>();
        followFollowings.add(FollowFollowing.builder().follower(user1).following(user3).build());
        followFollowings.add(FollowFollowing.builder().follower(user1).following(user4).build());
        followFollowings.add(FollowFollowing.builder().follower(user2).following(user4).build());
        followFollowings.add(FollowFollowing.builder().follower(user4).following(user1).build());
        followFollowings.add(FollowFollowing.builder().follower(user4).following(user2).build());

        followerFollowingRepository.saveAll(followFollowings);
    }
