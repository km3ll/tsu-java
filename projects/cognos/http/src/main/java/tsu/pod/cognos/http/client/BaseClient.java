package tsu.pod.cognos.http.client;

import tsu.pod.cognos.http.client.dto.ApiResponse;

public interface BaseClient {

    ApiResponse getBalance(String accountId, String currency) throws Exception;

    ApiResponse debit(String accountId, Double amount) throws Exception;
}
