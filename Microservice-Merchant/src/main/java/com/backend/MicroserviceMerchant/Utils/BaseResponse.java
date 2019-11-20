package com.backend.MicroserviceMerchant.Utils;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private T content;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "content=" + content +
                ", errorMessage='" + errorMessage + '\'' +
                ", success=" + success +
                ", status=" + status +
                '}';
    }

    private String errorMessage;
    private Boolean success;
    private HttpStatus status;

    public BaseResponse(T content, String errorMessage, Boolean success, HttpStatus status) {
        this.content = content;
        this.errorMessage = errorMessage;
        this.success = success;
        this.status = status;
    }

    public BaseResponse(T content, String errorMessage, Boolean success) {
        this.content = content;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public BaseResponse(String errorMessage, Boolean success) {
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
