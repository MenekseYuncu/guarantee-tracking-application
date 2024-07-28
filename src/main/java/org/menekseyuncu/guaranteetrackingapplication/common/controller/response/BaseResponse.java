package org.menekseyuncu.guaranteetrackingapplication.common.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents a standard HTTP response format.
 *
 * @param <T> the type of the response body
 */
@Getter
@Builder
public class BaseResponse<T> {

    /**
     * The timestamp of when the response was created.
     * Defaults to the current time when the response is built.
     */
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    /**
     * The HTTP status code of the response.
     */
    private HttpStatus httpStatus;

    /**
     * Indicates whether the operation was successful.
     */
    private Boolean isSuccess;

    /**
     * The response payload.
     * This field is included in the response only if it's not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    /**
     * A default success response with HTTP status 200 OK.
     */
    public static final BaseResponse<Void> SUCCESS = BaseResponse.<Void>builder()
            .httpStatus(HttpStatus.OK)
            .isSuccess(true).build();

    /**
     * Creates a success response with the given payload.
     *
     * @param response the response payload
     * @param <T>      the type of the response payload
     * @return a success {@link BaseResponse} with the provided payload
     */
    public static <T> BaseResponse<T> successOf(final T response) {
        return BaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .response(response).build();
    }
}