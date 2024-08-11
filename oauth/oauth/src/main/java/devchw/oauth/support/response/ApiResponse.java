package devchw.oauth.support.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private Result result;
    private T data;

    public static ApiResponse<?> success() {
        return new ApiResponse<>(Result.success(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Result.success(), data);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Result {
        private int code;
        private String msg;

        static Result success() {
            return new Result(200, "Success");
        }
    }

}
