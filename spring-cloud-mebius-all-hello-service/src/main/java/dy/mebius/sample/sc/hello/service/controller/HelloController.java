package dy.mebius.sample.sc.hello.service.controller;

import dy.mebius.sample.sc.common.response.ResResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hello")
    public ResResult<String> hello() {
        String msg = "访问hello-service.hello() " + new SimpleDateFormat("HH:mm:ss").format(new Date()) + " [端口号]:" + serverPort;
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResResult.succeed(msg);
    }

    @RequestMapping("/helloForError")
    public ResResult<String> helloForError() throws Exception {
        String msg = "访问hello-service.hello() " + new SimpleDateFormat("HH:mm:ss").format(new Date()) + " [端口号]:" + serverPort;

        throw new Exception("oh no");

        //return ResResult.succeed(msg);
    }
}
