package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response <T> {
    private boolean success;
    private String message;
    private T data;

    public Response() {}

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
