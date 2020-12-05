package dy.mebius.sample.sc.zuul.controller;

import dy.mebius.sample.sc.common.response.ResResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResResult<Void> error() {
        return ResResult.fail("Zuul异常返回", null);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
