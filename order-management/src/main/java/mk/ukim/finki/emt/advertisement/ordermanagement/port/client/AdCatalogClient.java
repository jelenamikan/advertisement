package mk.ukim.finki.emt.advertisement.ordermanagement.port.client;

import mk.ukim.finki.emt.advertisement.ordermanagement.application.AdCatalog;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.AdId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
class AdCatalogClient implements AdCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdCatalogClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    AdCatalogClient(@Value("${app.ad-catalog.url}") String serverUrl,
                         @Value("${app.ad-catalog.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.ad-catalog.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<Ad> findAll() {
        try {
            return restTemplate.exchange(uri().path("/ads").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Ad>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving ads", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Ad findById(AdId id) {
        try {
            return restTemplate.exchange(uri().path("/ads/"+id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Ad>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving ad by id", ex);
            return null;
        }
    }
}

