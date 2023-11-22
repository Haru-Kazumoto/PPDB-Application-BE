package dev.pack.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static dev.pack.modules.enums.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final LogoutHandler logoutHandler;
  private final ApplicationConfig config;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
            .authorizeHttpRequests(
                    (request) -> {
                        request.requestMatchers(String.format("/api/v%d/auth/**",config.APP_VERSION)).permitAll();
                        request.requestMatchers(String.format("/api/v%d/**",config.APP_VERSION)).fullyAuthenticated();

                        //Public path
                        request.requestMatchers("/api/public/**").permitAll();

                        //Role admin
                        request.requestMatchers(String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());

                        //Admin authority
                        request.requestMatchers(GET, String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());
                        request.requestMatchers(POST, String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());
                        request.requestMatchers(PUT, String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());
                        request.requestMatchers(PATCH, String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());
                        request.requestMatchers(DELETE, String.format("/api/v%d/admin/**", config.APP_VERSION)).hasRole(ADMIN.name());

                        //Role user
                        request.requestMatchers(String.format("/api/v%d/user/**",config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());
                        //User authority
                        request.requestMatchers(GET, String.format("/api/v%d/user/**", config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());
                        request.requestMatchers(POST, String.format("/api/v%d/user/**", config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());
                        request.requestMatchers(PUT, String.format("/api/v%d/user/**", config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());
                        request.requestMatchers(PATCH, String.format("/api/v%d/user/**", config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());
                        request.requestMatchers(DELETE, String.format("/api/v%d/user/**", config.APP_VERSION)).hasAnyRole(USER.name(), ADMIN.name());

                        request.anyRequest().permitAll();
                    }
            );
        http.exceptionHandling((ex) -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        http.sessionManagement(
                        (sessionManagementConfigurer) -> {
                            sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        }
                );
        http.logout(
                (form) -> {
                    form.logoutUrl(String.format("/api/v%d/auth/logout", config.APP_VERSION));
                    form.addLogoutHandler(logoutHandler);
                    form.logoutSuccessUrl(String.format("/api/v%d/auth/logout?logout", config.APP_VERSION));
                    form.invalidateHttpSession(true);
                    form.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                }
        );
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new SpaWebFilter(), BasicAuthenticationFilter.class);

        return http.build();
  }
}