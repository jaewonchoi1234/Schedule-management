package com.sparta.schedulemanagement.Service;

import com.sparta.schedulemanagement.Dto.SignupRequestDto;
import com.sparta.schedulemanagement.Entity.User;
import com.sparta.schedulemanagement.Entity.UserRoleEnum;
import com.sparta.schedulemanagement.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(SignupRequestDto requestDto) {
        //사용자 Role 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
            role = UserRoleEnum.ADMIN;
        }
        //사용자 등록
        User user = new User(requestDto, role);
        userRepository.save(user);

        return "회원가입 성공";
    }
}
