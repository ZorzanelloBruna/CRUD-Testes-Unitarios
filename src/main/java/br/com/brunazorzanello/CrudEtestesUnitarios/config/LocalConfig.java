package br.com.brunazorzanello.CrudEtestesUnitarios.config;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;
import br.com.brunazorzanello.CrudEtestesUnitarios.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;
    @Bean
    public void startDB(){
        User u1 = new User(null, "Bruna", "bruna@gmail.com","1234");
        User u2 = new User(null, "Pedro", "pedro@gmail.com","123");

        repository.saveAll(List.of(u1, u2));
    }
}
