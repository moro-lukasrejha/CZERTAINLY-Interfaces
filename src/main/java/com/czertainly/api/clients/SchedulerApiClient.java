package com.czertainly.api.clients;

import com.czertainly.api.exception.ConnectorException;
import com.czertainly.api.model.scheduler.SchedulerRequestDto;
import com.czertainly.api.model.scheduler.SchedulerResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@NoArgsConstructor
public class SchedulerApiClient extends CzertainlyBaseApiClient {

    @Value("${scheduler.base-url}")
    private String schedulerBaseUrl;

    private static final String SCHEDULER_CREATE = "/v1/scheduler/create";

    private static final String SCHEDULER_DELETE = "/v1/scheduler/delete";

    private static final String SCHEDULER_ENABLE = "/v1/scheduler/enable";

    private static final String SCHEDULER_DISABLE = "/v1/scheduler/disable";

    public SchedulerResponseDto schedulerCreate(final SchedulerRequestDto schedulerDto) throws ConnectorException {
        final WebClient.RequestBodyUriSpec request = prepareRequest(HttpMethod.POST);
        return processRequest(r -> r
                        .uri(schedulerBaseUrl + SCHEDULER_CREATE)
                        .body(Mono.just(schedulerDto), SchedulerRequestDto.class)
                        .retrieve()
                        .toEntity(SchedulerResponseDto.class)
                        .block().getBody(),
                request);
    }

    public void deleteScheduledJob(final String jobName) throws ConnectorException {
        final WebClient.RequestBodyUriSpec request = prepareRequest(HttpMethod.DELETE);
        processRequest(r -> r
                        .uri(schedulerBaseUrl + SCHEDULER_DELETE, jobName)
                        .retrieve()
                        .toEntity(Void.class)
                        .block().getBody(),
                request);
    }

    public void enableScheduledJob(final String jobName) throws ConnectorException {
        final WebClient.RequestBodyUriSpec request = prepareRequest(HttpMethod.GET);
        processRequest(r -> r
                        .uri(schedulerBaseUrl + SCHEDULER_ENABLE, jobName)
                        .retrieve()
                        .toEntity(Void.class)
                        .block().getBody(),
                request);
    }

    public void disableScheduledJob(final String jobName) throws ConnectorException {
        final WebClient.RequestBodyUriSpec request = prepareRequest(HttpMethod.GET);
        processRequest(r -> r
                        .uri(schedulerBaseUrl + SCHEDULER_DISABLE, jobName)
                        .retrieve()
                        .toEntity(Void.class)
                        .block().getBody(),
                request);
    }


    @Override
    protected String getServiceUrl() {
        return schedulerBaseUrl;
    }
}
