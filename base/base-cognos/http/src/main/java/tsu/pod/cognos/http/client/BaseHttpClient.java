package tsu.pod.cognos.http.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import tsu.pod.cognos.http.client.dto.ApiResponse;
import tsu.pod.cognos.http.client.dto.DebitRequest;
import tsu.pod.cognos.utils.json.JsonMapper;

public class BaseHttpClient implements BaseClient {

    private final HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();

    @Override
    public ApiResponse getBalance(String accountId, String currency) {

        String url =
                String.format(
                        "https://api.bank.com/accounts/%s/balance?currency=%s",
                        accountId, currency);

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Authorization", "Bearer sample-123")
                        .GET()
                        .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonMapper.MAPPER.readValue(response.body(), ApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get account balance", e);
        }
    }

    @Override
    public ApiResponse debit(String accountId, Double amount) throws Exception {

        String url = String.format("https://api.bank.com/accounts/%s/debit", accountId);

        String body = JsonMapper.MAPPER.writeValueAsString(new DebitRequest(amount));
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer token-123")
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            return JsonMapper.MAPPER.readValue(response.body(), ApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to debit", e);
        }
    }
}
