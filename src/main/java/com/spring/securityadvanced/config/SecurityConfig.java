package com.spring.securityadvanced.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.spring.securityadvanced.dao.SubscriberRepo;
import com.spring.securityadvanced.service.CustomUserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SubscriberRepo subscriberRepo;

    @Autowired
    public SecurityConfig(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(subscriberRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests((requests) -> {
            (requests.anyRequest()).authenticated();
        });
        http.formLogin();
        http.httpBasic();*/
                /*http.authorizeRequests().anyRequest().permitAll()
                .and().formLogin().and().httpBasic();*/

       /* http.authorizeRequests().anyRequest().denyAll()   // denyAll() : refuser any request
                .and().formLogin().and().httpBasic();*/

/*
 si vous utilisez autre app cote fron ( angular..    )cela est necessaire :

        http.cors().configurationSource(new CorsConfigurationSource() {

        /*
        si vous utiliser framwork front (angular...)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
        /*


            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(2500L);
                return config;
            }
        }).and()

        + token pour crsf :
                }).and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                */

        /*
        si vous voulez ignorer une methode comme : .antMatchers("/other/*").permitAll()
            }).and().csrf().ignoringAntMatchers("/other/*").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()

            si vous voulez rajouter filter :
            .and().addFilterBefore(new FilterBefore(), BasicAuthenticationFilter.class)

         */

        /*
        filter jwt
        }).and().csrf().disable()
                .addFilterBefore(new FilterBefore(), BasicAuthenticationFilter.class)
                .addFilterAfter(new FilterAfter(),BasicAuthenticationFilter.class)
                .addFilterAt(new FilterAt(),BasicAuthenticationFilter.class)
                 .addFilterBefore(new JWTValidationFilter(),BasicAuthenticationFilter.class)
                */

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/football/*").authenticated()
                .antMatchers("/basketball/*").authenticated()
                .antMatchers("/swimming/*").authenticated()
                .antMatchers("/subscribers/*").authenticated()

                // authorities
//                .antMatchers("/football/*").hasAuthority("WRITE")
//                .antMatchers("/basketball/*").hasAuthority("READ")
//                .antMatchers("/swimming/*").hasAnyAuthority("LISTEN","READ")
//                .antMatchers("/subscribers/*").hasAuthority("DELETE")

                //****** or role

//                .antMatchers("/football/*").hasRole("USER")
//                .antMatchers("/basketball/*").hasRole("ADMIN")
//                .antMatchers("/swimming/*").hasRole("MANAGER")
//                .antMatchers("/subscribers/*").hasAnyRole("ADMIN","MANAGER")

                .antMatchers("/about/*").permitAll()
                .antMatchers("/connect/*").permitAll()
                //.antMatchers("/other/*").permitAll()
                .and().formLogin().and().httpBasic();

    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("islam").password("12345").authorities("admin")
                .and().withUser("ahmed").password("00000").authorities("player")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails userDetails_1 = User.withUsername("islam").password("12345").authorities("admin").build();
//        UserDetails userDetails_2 = User.withUsername("ahmed").password("00000").authorities("player").build();
//        inMemoryUserDetailsManager.createUser(userDetails_1);
//        inMemoryUserDetailsManager.createUser(userDetails_2);
//        auth.userDetailsService(inMemoryUserDetailsManager);
//    }

    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
