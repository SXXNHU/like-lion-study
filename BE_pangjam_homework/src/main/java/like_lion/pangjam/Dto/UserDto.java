package like_lion.pangjam.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    public static class Request {
        @Getter @Setter
        public static class Create{
            private String nickname;
        }
        public static class UpdateProfile{}
    }

    public static class Response {
        @Getter @Builder
        public static class Create{
            private int userId;
            private String nickname;
            //private String createdAt;
            //private String modifiedAt;
        }
        public static class UpdateProfile {}
    }
}