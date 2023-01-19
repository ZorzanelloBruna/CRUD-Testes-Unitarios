package br.com.brunazorzanello.CrudEtestesUnitarios.services;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.Dto.UserDto;
import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create (UserDto userDto);
    User update (UserDto userDto);
    void delete(Integer id);
}
