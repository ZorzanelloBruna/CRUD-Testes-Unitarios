package br.com.brunazorzanello.CrudEtestesUnitarios.services;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;

public interface UserService {
    User findById(Integer id);
}
