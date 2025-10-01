package com.harsh.ApiGateway;


import com.harsh.ApiGateway.User.RegisterRequest;
import com.harsh.ApiGateway.User.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.text.ParseException;


@Component
@RequiredArgsConstructor
@Slf4j
public class KeyCloakUserSyncFilter implements WebFilter {

    private final UserService userService;



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String userid = exchange.getRequest().getHeaders().getFirst("X-User-ID");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        RegisterRequest registerRequest = getUserDetails(token);
        if(userid == null){
            userid = registerRequest.getKeyCloakId();
        }
        if(userid != null && token != null){
            String finalUserid = userid;
            return userService.validateUser(userid)
                    .flatMap(exist -> {
                        if(!exist){
                            if(registerRequest != null){
                                return userService.registerUser(registerRequest)
                                        .then(Mono.empty());
                            }
                            else{
                                return Mono.empty();
                            }
                        }
                        else{
                            log.info("User already Exist, Skipping sync");
                            return Mono.empty();
                        }
                    })


                    .then(Mono.defer(()->{
                        ServerHttpRequest mutateRequest = exchange.getRequest().mutate()
                                .header("X-User-ID", finalUserid)
                                .build();
                        return chain.filter(exchange.mutate().request(mutateRequest).build());
                    }));
        }


        return chain.filter(exchange);
    }

    private RegisterRequest getUserDetails(String token) {
        try{
            String tokenWithoutBearer = token.replace("Bearer", "").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();


            RegisterRequest request = new RegisterRequest();
            request.setEmail(jwtClaimsSet.getStringClaim("email"));
            request.setKeyCloakId(jwtClaimsSet.getStringClaim("sub"));
            request.setPassword("dummy@123123");
            request.setFirstName(jwtClaimsSet.getStringClaim("given_name"));
            request.setLastName(jwtClaimsSet.getStringClaim("family_name"));

            return request;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
