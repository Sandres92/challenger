package com.kor.challenger.config;

import com.kor.challenger.security.jwt.JwtConfigurer;
import com.kor.challenger.security.jwt.JwtTokenProvider;
import com.kor.challenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/pi/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/registration", "/login**", "/js/**", "/error**", "/user/**").permitAll()
                .antMatchers("/", "/registration", "/login**", "/js/**", "/error**", "/user/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));*/

        http.
                httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/registration*").permitAll()
                .antMatchers("/", "/registration", "/error**", "/api/v1/auth/login", "/user/**", LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
*//*        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");*//*

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);

    }*/

    //@Bean
    //public PrincipalExtractor principalExtractor(UserDetailRepo userDetailRepo) {
    //    return map -> {
    //        String id = (String) map.get("sub");
    //        User user = userDetailRepo.findById(id).orElseGet(() -> {
    //            User newUser = new User();
//
    //            newUser.setId(id);
    //            newUser.setName((String) map.get("name"));
    //            newUser.setEmail((String) map.get("email"));
    //            newUser.setGender((String) map.get("gender"));
    //            newUser.setLocale((String) map.get("locale"));
    //            newUser.setUserpic((String) map.get("picture"));
//
    //            return newUser;
    //        });
//
    //        user.setLastVisit(LocalDateTime.now());
//
    //        return userDetailRepo.save(user);
    //    };
    //}
}
