package br.com.brunazorzanello.CrudEtestesUnitarios.resourses;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.Dto.UserDto;
import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user" )
public class UserResourse {

    @Autowired
    private UserService service;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
    return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDto.class));
    //método mapper - primeiro argumento chama as informações de interesse, segundo - retorna conforme a classe que vc quer
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto>listDto = service.findAll().stream().map(x ->
                mapper.map(x, UserDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
