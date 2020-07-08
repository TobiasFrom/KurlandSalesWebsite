package semesterprojekt.demo.Config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Log
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    //Initialize PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    //Make login credentials for admin - (in memory).
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.headers().frameOptions().disable();


        http.authorizeRequests()
                .antMatchers(
                        "/adminabout",
                        "/admincategory",
                        "/admincontact",
                        "/adminlogin",
                        "/admin",
                        "/adminnavigationbar",
                        "/adminproduct",
                        "/adminreview",
                        "/adminservs",
                        "/adminservsupdate",
                        "/adminupdatecontact",
                        "/adminupdatenavbar",
                        "/adminupdateproduct"
                ).hasRole("ADMIN")
                .antMatchers("/h2/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/adminlogin")
                .permitAll()
                .failureUrl("/login-error")
                .defaultSuccessUrl("/admin")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/adminlogin");
    }
}
