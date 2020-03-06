package lucky.baijunhan.fileserver.controller;

import lucky.baijunhan.fileserver.model.HttpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public HttpResult<?> exception(Exception exception){
        return HttpResult.FAILED(exception.toString());
    }

}
