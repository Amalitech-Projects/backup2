package com.example.TravelAppService.TravelServices;

import com.example.TravelAppService.RequestParam.AirportParam;
import com.example.TravelAppService.RequestParam.CarRentalRequest;
import com.example.TravelAppService.RequestParam.GeneralQueryParams;
import com.example.TravelAppService.RequestParam.HotelParamsByCity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class HotelsRapidApi {

    private final WebClient.Builder webClientBuilder;

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Value("${rapidapi.host}")
    private String rapidApiHost;

    private WebClient webClient;

    @PostConstruct
    private void init() {
        webClient = webClientBuilder.baseUrl("https://tripadvisor16.p.rapidapi.com/api/v1/")
                .defaultHeader("X-RapidAPI-Key", rapidApiKey)
                .defaultHeader("X-RapidAPI-Host", rapidApiHost)
                .build();

    }

    public Mono<String> searchHotelsOffer(GeneralQueryParams data) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("hotels/searchLocation")
                        .queryParam("query", data.getQuery())
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }



    public Mono<String> searchHotelOffer(HotelParamsByCity data) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("hotels/searchHotels")
                        .queryParam("geoId", data.getGeoId())
                        .queryParam("checkIn", data.getCheckIn())
                        .queryParam("checkOut", data.getCheckOut())
                        .queryParam("pageNumber", data.getPageNumber())
                        .queryParam("currencyCode", "USD")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> searchCarLocation(GeneralQueryParams data) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("rentals/searchLocation")
                        .queryParam("query", data.getQuery())
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> searchCarRentals(CarRentalRequest data) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("cars/searchCarsSameDropOff")
                        .queryParam("pickUpPlaceId", data.getPickUpPlaceId())
                        .queryParam("pickUpLocationType", data.getPickUpLocationType())
                        .queryParam("pickUpDate", data.getPickUpDate())
                        .queryParam("dropOffDate", data.getDropOffDate())
                        .queryParam("pickUpTime", data.getPickUpTime())
                        .queryParam("dropOffTime", data.getDropOffTime())
                        .queryParam("order", data.getOrder())
                        .queryParam("page", "PRICE_DESC")
                        .queryParam("currencyCode", "USD")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }



    public Mono<String> searchAirport(AirportParam query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/searchAirport")
                        .queryParam("query", query.getQuery())
                        .build())
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", rapidApiHost)
                .retrieve()
                .bodyToMono(String.class);
    }


}
