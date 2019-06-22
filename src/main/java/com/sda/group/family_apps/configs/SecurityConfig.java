package com.sda.group.family_apps.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //wstrzykujemy beana, którego stworzyliśmy przez adnotacje @Bean
    private PasswordEncoder passwordEncoder;

    @Autowired //dostarczane przez kontener - domyslny konfig bazy danych (z app.properties)
    private DataSource dataSource;

    @Override //fragment konfiguracji do zdefiniowania dostępu do przestrzeni aplikacji
    protected void configure(HttpSecurity http) throws Exception {
//        "/add" - blokowanie dokładnie na adres "/add"
//        "/add/*" - blokowanie adresów jeden liść dalej "/add","/add/sth", "/add/any"
//        "/add/**" - blokowanie adresów do końca gałęzi "/add","/add/sth", "/add/any", "/add/any/any" ,"add/any/any/sth"
        http.authorizeRequests()
                .antMatchers("/register").permitAll() //kolejność jest ważna - elementy wyższe nadpisują niższe
                .antMatchers("/login").permitAll()
                .antMatchers("/profile").permitAll()
                .antMatchers("/css/**").permitAll() //odblokowujemy dostęp do zasobow statycznych
                .antMatchers("/api/**").permitAll() //odblokowujemy dostęp do zasobow statycznych
                .antMatchers("/admin/**").hasAnyAuthority( "ROLE_ADMIN")
                .antMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login") //tutaj mówimy na jakiego urla aplikacja ma nas przekierować kiedy nie jestesmy zalogowani
                .usernameParameter("loginEmail") //nazwa inputa z loginemw htmlu(formularzu) logowania
                .passwordParameter("loginPassword")//nazwa inputa z hasłem htmlu(formularzu) logowania
                .loginProcessingUrl("/processLogin") //na jaki adres ma zostać wysłany formularz logowania
                .failureUrl("/login?error=1")
                .defaultSuccessUrl("/");
    }

    // EFEKT NASZEJ WYOBRAŹNI - to prawdopodobnie utworzy sobie spring
//    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
//    public String log(@RequestParam String loginEmail, @RequestParam String loginPassword) {
//        if(username i hasło pasują){
//            zaloguj()
//                    return "index"
//        }else{
//            return "redirect: /login?error=1"
//        }
//
//    }

    @Override //konfiguracja sposobu odpytania bazy o dane użytkownika
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("superUser")
                .password(passwordEncoder.encode("superPassword"))
                .roles("USER"); //roles dodaje prefix "ROLE_" do wartosci
        auth.jdbcAuthentication()
                .usersByUsernameQuery( //definicja zapytania o hasło użytkownika na podstawie loginu
                        "SELECT u.uSeRname, u.password_hash, 1 " +
                                "FROM Users u " +
                                "WHERE u.username = ?")
                .authoritiesByUsernameQuery( //definicja zapytania o role użytkownika na podstawie loginu
                        "SELECT u.username, r.role_name, 1 " +
                        "FROM users u " +
                        "JOIN users_roles ur ON u.id = ur.user_id " +
                        "JOIN roles r ON ur.roles_id = r.id " +
                        "WHERE u.username = ? ")
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource);

    }
}
