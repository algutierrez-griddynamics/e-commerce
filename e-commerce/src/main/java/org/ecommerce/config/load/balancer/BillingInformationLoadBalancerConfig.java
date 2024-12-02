package org.ecommerce.config.load.balancer;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClient(name = "BILLING-INFORMATION-SERVICE", configuration = RoundRobinLoadBalancerConfig.class)
public class BillingInformationLoadBalancerConfig {
}
