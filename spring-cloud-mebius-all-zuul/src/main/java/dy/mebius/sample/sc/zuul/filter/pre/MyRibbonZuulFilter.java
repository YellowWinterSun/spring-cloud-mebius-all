package dy.mebius.sample.sc.zuul.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 实现动态路由
 *
 */
@Component
public class MyRibbonZuulFilter extends ZuulFilter {



    @Override
    public String filterType() {
        // pre过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文获取HttpServletRequest
        HttpServletRequest request = ctx.getRequest();

        // URL: 完整的链接 ip:port/uri
        // URI: 不包含ip:port
        String requestURI = request.getRequestURI();
        System.out.println("uri: " + request.getRequestURI());
        System.out.println("url: " + request.getRequestURL());

        if ("/dyapi/myUrlHello".indexOf(requestURI) == 0) {
            System.out.println("自定义路由 myUrlHello，映射到 /hello-service/hello");
            ctx.set(FilterConstants.REQUEST_URI_KEY, "/hello");
            ctx.set(FilterConstants.SERVICE_ID_KEY, "hello-service");
        }
        else if ("/dyapi/no".indexOf(requestURI) == 0) {
            ctx.setSendZuulResponse(false);
            ctx.set("sendForwardFilter.ran", true);
            ctx.setResponseBody("/dyapi/no bye");
        }
        else if (requestURI.startsWith("/dyapi/")) {
            /**
             * 像一般，有个RBAC模块。配置url -> 服务/接口 的映射规则，并且含有启用、禁用的逻辑。
             * 如：
             *      配置映射关系表。这样做的好处是，对外暴露的url可以自定义，提高安全性，不用对外直接暴露服务名，接口名
             *      /qms/xxControler/xx    ->   t8t-crm-qms        /xxController/xx
             *
             * 然后API网关接到 /qms/xxController/xx 的请求后，去映射关系表中查出对应的服务名、接口名。做如下转发即可
             * 同时我们还能兼顾灰度发布、接口启用禁用，接口权限校验等逻辑。
             *
             * 类比到这里的做法可以是：
             *      /demo1/helloApi/hello  ->   springboot-demo-1     /helloController/hello
             *
             * 网关收到 /dyApi/demo1/helloApi/hello 请求后，剔除/dyApi。然后通过 /hello1/helloApi/hello去映射表
             * 取出服务名和接口名
             */
            String requestURIWithoutPrefix = requestURI.substring("/dyapi".length());
            String serviceId = requestURIWithoutPrefix.substring(
                    1, requestURIWithoutPrefix.indexOf("/", 1)
            );
            String uriKey = requestURIWithoutPrefix.substring(
                    requestURIWithoutPrefix.indexOf("/", 1)
            );

            ctx.set(FilterConstants.SERVICE_ID_KEY, serviceId);
            ctx.set(FilterConstants.REQUEST_URI_KEY, uriKey);
        } else {
            throw new RuntimeException("不符合规范的api请求");
        }


        return null;
    }
}
