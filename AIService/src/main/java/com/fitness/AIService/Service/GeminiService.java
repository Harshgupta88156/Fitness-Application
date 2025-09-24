
package com.fitness.AIService.Service;
import java.util.List;
import java.util.Map;





import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeminiService {

    @Value("${gemini.api.url}")
    private String geminiUrl;

    @Value("${gemini.api.key}")
    private String geminiAPIKey;

    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getRecommendations(String details) {
        // Build the request body in correct format
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", details)
                                )
                        )
                )
        );

        String url = geminiUrl + "?key=" + geminiAPIKey;

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        s -> s.isError(),
                        resp -> resp.bodyToMono(String.class).flatMap(body -> {
                            // log the actual error body
                            System.err.println("Gemini error " + resp.statusCode() + " body=" + body);
                            return Mono.error(new RuntimeException("Gemini call failed"));
                        })
                )
                .bodyToMono(String.class)
                .block();
    }
}
