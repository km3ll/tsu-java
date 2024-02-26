package pod.tsu.java.datadog;

// Create or update service definition using schema v2 returns "CREATED" response

import com.datadog.api.client.ApiClient;
import com.datadog.api.client.ApiException;
import com.datadog.api.client.v2.api.ServiceDefinitionApi;
import com.datadog.api.client.v2.model.ServiceDefinitionCreateResponse;
import com.datadog.api.client.v2.model.ServiceDefinitionV2;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Contact;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Doc;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Email;
import com.datadog.api.client.v2.model.ServiceDefinitionV2EmailType;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Integrations;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Link;
import com.datadog.api.client.v2.model.ServiceDefinitionV2LinkType;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Opsgenie;
import com.datadog.api.client.v2.model.ServiceDefinitionV2OpsgenieRegion;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Repo;
import com.datadog.api.client.v2.model.ServiceDefinitionV2Version;
import com.datadog.api.client.v2.model.ServiceDefinitionsCreateRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class PostDefinition {
    public static void main(String[] args) {
        ApiClient defaultClient = ApiClient.getDefaultApiClient();
        ServiceDefinitionApi apiInstance = new ServiceDefinitionApi(defaultClient);

        ServiceDefinitionsCreateRequest body =
                new ServiceDefinitionsCreateRequest(
                        new ServiceDefinitionV2()
                            .repos(
                                Collections.singletonList(
                                    new ServiceDefinitionV2Repo()
                                            .name("Source Code")
                                            .provider("GitHub")
                                            .url("https://scm.starbucks.com/scpprd/ucp-product-catalog"))
                            )
                            .ddService("ucp-product-catalog-synch")
                            .schemaVersion(ServiceDefinitionV2Version.V2)
                            .team("ucp-commerce"));


        try {
            ServiceDefinitionCreateResponse result = apiInstance.createOrUpdateServiceDefinitions(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println(
                    "Exception when calling ServiceDefinitionApi#createOrUpdateServiceDefinitions");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
