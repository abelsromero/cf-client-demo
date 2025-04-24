package com.example.demo;

import io.pivotal.spring.cloud.servicebroker.platform.ServiceInstanceLister;
import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v3.organizations.ListOrganizationsRequest;
import org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse;
import org.cloudfoundry.client.v3.organizations.OrganizationResource;
import org.cloudfoundry.client.v3.spaces.ListSpacesRequest;
import org.cloudfoundry.client.v3.spaces.ListSpacesResponse;
import org.cloudfoundry.client.v3.spaces.SpaceResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CfController {

    private final CloudFoundryClient client;
    private final ServiceInstanceLister serviceInstanceLister;

    static {
//        Hooks.onOperatorDebug();
    }

    public CfController(CloudFoundryClient cloudFoundryClient,
                        ServiceInstanceLister serviceInstanceLister) {
        this.client = cloudFoundryClient;
        this.serviceInstanceLister = serviceInstanceLister;
    }

    @GetMapping("/orgs/{name}")
    public Mono<List<OrganizationResource>> organizations(@PathVariable(required = false) String name) {

        var request = name == null
                ? ListOrganizationsRequest.builder().build()
                : ListOrganizationsRequest.builder().name(name).build();

        return client.organizationsV3()
                .list(request)
                .map(ListOrganizationsResponse::getResources);
    }

    @GetMapping("/spaces/{name}")
    public Mono<List<SpaceResource>> spaces(@PathVariable(required = false) String name) {

        var request = name == null
                ? ListSpacesRequest.builder().build()
                : ListSpacesRequest.builder().name(name).build();

        return client.spacesV3()
                .list(request)
                .map(ListSpacesResponse::getResources);
    }

    @GetMapping("/openapi/{org}")
    public Flux<ServiceInstanceLister.ServiceInstance> openapi(@PathVariable(required = false) String org) {
        return serviceInstanceLister.findServiceInstances(org);
    }

}
