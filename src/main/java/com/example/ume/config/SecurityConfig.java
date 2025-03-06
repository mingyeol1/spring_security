package com.example.ume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


                    //authorizeHttpRequests : 특정한 경로에 요청을 허용하거나 거부하는 메서드
                    //requestMatchers ~~경로에 대해 특정한 작업을 하고싶다. 설정을 하는 메서드
                    //permitAll : 이 경로는 모든 사용자가 이용가능.   //특정한 권한을 주는 메서드
                    //hasRole : 특정한 권한이 있어야 경로에 접근 가능. ex) ADMIN, USER 등
                    //authenticated : 로그인만 진행하면 누구든 입장이 가능
                    //denyAll : 모든 사용자의 접근을 막는 메서드
                    //hasAnyRole : 여러가지의 Role을 설정할 수 있음.
                    /* !! 중요 !! 메서드의 권한을 나눌 때 순서가 중요함.
                    * .anyRequest().permitAll() 이걸 맨위에 넣으면 그 밑에 접근 권한들이 이미 permitAll로 설정이 되어서
                    * 자동으로 밑들도 아무나 접근이 가능하도록 설정이 됨. 순서를 잘 지키자.
                    * */
                    //anyRequest : 위에서 처리하지못한 경로를 자동으로 처리.
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll() //누구나 입장가능
                        .requestMatchers("admin").hasRole("ADMIN")  // 특정한 Role을 가진 사용자만 접근가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")  // 위랑 비슷하지만 많은 Role을 줄 수 있음.
                        .anyRequest().authenticated()       // 위에서 처리하지 못한 경로들은 로그인 한 사용자만 입장이 가능.
                );



        return http.build();
    }
}
