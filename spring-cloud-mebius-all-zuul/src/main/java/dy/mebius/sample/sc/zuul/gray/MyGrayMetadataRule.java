package dy.mebius.sample.sc.zuul.gray;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 自定义ribbon IRule规则，实现基于metadata的灰度发布方案
 */
public class MyGrayMetadataRule extends ZoneAvoidanceRule {

    @Override
    public Server choose(Object key) {
        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }

        // todo 此处 过滤灰度版本号
        for (Server server : serverList) {
            if (server instanceof DiscoveryEnabledServer) {
                DiscoveryEnabledServer discoveryEnabledServer = (DiscoveryEnabledServer) server;
                Map<String, String> metadataMap = discoveryEnabledServer.getInstanceInfo().getMetadata();
                String instanceVersion = metadataMap.get("instanceVersion");
                System.out.println("server:" + server.getHostPort() + " metadata-version:" + instanceVersion);
            }
        }

        return serverList.get(ThreadLocalRandom.current().nextInt(serverList.size()));
    }
}
