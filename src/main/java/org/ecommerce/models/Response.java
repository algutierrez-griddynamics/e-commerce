package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.ecommerce.enums.HttpStatusCode;

@Setter
@Getter
@AllArgsConstructor
public class Response <T> {
    private boolean success;
    private String message;
    private T data;
    private HttpStatusCode statusCode;

    public Response() {}

    public Response(boolean success, String message, HttpStatusCode statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ApiResponse {" +
                "\n\t\tsuccess=" + success +
                ",\n\t\tmessage='" + message + '\'' +
                ",\n\t\tdata=" + data +
                ",\n\t\tstatusCode=" + statusCode +
                "\n\t"+'}';
    }
}
