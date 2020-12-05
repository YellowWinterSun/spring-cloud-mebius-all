package dy.mebius.sample.sc.hello.service.conf;

import dy.mebius.sample.sc.common.response.ResResult;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局Controller抛出异常统一处理器
 * 主要目的是，将意外抛出的各种异常，转化成统一的返回JSON格式。
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResResult<?> handle(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        logger.error("GlobalControllerExceptionHandler =====>", e);

        // 定义自己的抛出异常方式，能够解析成ResResult的状态码，异常信息等
//        if (e instanceof AppException) {
//            AppException exception = (AppException) e;
//            return ...
//        }

        // 最后是通用的报错，不应该暴露给外面，统一对外表示“系统异常”即可
        return ResResult.fail("系统异常", null);
    }
}
