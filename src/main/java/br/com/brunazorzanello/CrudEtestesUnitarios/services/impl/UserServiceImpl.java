package br.com.brunazorzanello.CrudEtestesUnitarios.services.impl;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;
import br.com.brunazorzanello.CrudEtestesUnitarios.repositorys.UserRepository;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.UserService;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado no banco de dados!"));
    }
}
