package like_lion.pangjam.Service;

import like_lion.pangjam.Dto.UserDto;
import like_lion.pangjam.Model.User;
import like_lion.pangjam.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto.Response.Create createUser(UserDto.Request.Create userDto) {
        User user = User.builder()
                .nickname(userDto.getNickname())
                .build();

        User savedUser = userRepository.save(user);

        return UserDto.Response.Create.builder()
                .userId(savedUser.getUserId())
                .nickname(savedUser.getNickname())
                .build();
    }

    public UserDto updateUser(UserDto userDto) {
        return userDto;
    }

    public void deleteUser(int userId) {
    }
}
