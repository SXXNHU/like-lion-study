package like_lion.week3.service;

import like_lion.week3.dto.UserRequestDTO;
import like_lion.week3.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //사용자 리스트
    private final List<UserResponseDTO> userList = new ArrayList<>();

    // 사용자 저장
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {

        if(userRequestDTO.getName() == null || userRequestDTO.getName().length() <= 1) {
            throw new RuntimeException("이름은 공백이거나 1자 이하일 수 없습니다");
        }
        int age = getAge(userRequestDTO.getId());
        String gender = getGender(userRequestDTO.getId());

        UserResponseDTO userResponseDTO = new UserResponseDTO(userRequestDTO.getName(), age, gender);
        userList.add(userResponseDTO);
        return new UserResponseDTO(userResponseDTO.getName(), age, gender);
    }

    // 전체 사용자 조회
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> responseDTOS =new ArrayList<>();
        for (UserResponseDTO userResponseDTO : userList) {
            responseDTOS.add(new UserResponseDTO(userResponseDTO.getName(), userResponseDTO.getAge(), userResponseDTO.getGender()));
        }
        return responseDTOS;
    }

    //출생년도 계산
    public static int getBirthYear(String id) {

        String preId = id.substring(0, 6);
        int birthYear;
        char gender = id.charAt(7);
        int birth = Integer.parseInt(id.substring(0, 2));

        if(id.length() != 14 || id.charAt(6) != '-') {
            throw new IllegalArgumentException("유효하지 않은 주민등록번호입니다. '-' 를 포함하여 작성해주세요.");
        }

        if (!isValidDate(preId)) {
            throw new IllegalArgumentException("유효하지 않은 생년월일입니다.");
        }

        if(gender == '1' || gender == '2' ) {
            birthYear = 1900 + birth;
        } else if (gender == '3' || gender == '4') {
            if (birth > 25) {
                throw new IllegalArgumentException("유효하지 않은 주민등록번호입니다.");
            }
            birthYear = 2000 + birth;
        } else {
            throw new IllegalArgumentException("유효하지 않은 성별입니다.");
        }

        return birthYear;
    }

    //날짜 유효성 검사
    public static boolean isValidDate(String date) {
        try {
            // 'yyMMdd' 형식의 날짜를 파싱
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyMMdd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //사용자 나이 계산
    public static int getAge(String id) {
        int birth = getBirthYear(id);
        int currentYear = LocalDate.now().getYear();
        return currentYear - birth;
    }

    //성별 추출 : id -> gender
    public static String getGender(String id) {
        char gender = id.charAt(7);

        switch (gender) {
            case '1': case '3':
                return "male";
            case '2': case '4':
                return "female";
            default:
                throw new IllegalArgumentException("Invalid gender");
        }
    }
}
