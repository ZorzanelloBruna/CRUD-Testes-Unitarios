package br.com.brunazorzanello.CrudEtestesUnitarios.services.impl;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.Dto.UserDto;
import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;
import br.com.brunazorzanello.CrudEtestesUnitarios.repositorys.UserRepository;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.exception.DataIntegrateViolationException;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Bruna";
    public static final String EMAIL = "bruna@gmail.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_ENCONTRADO = "Id não encontrado no banco de dados!";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDto userDto;
    Optional<User> userOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(userOptional);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    public void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnListOfThen() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCriateThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User responde = service.create(userDto);

        assertNotNull(responde);
        assertEquals(User.class, responde.getClass());
        assertEquals(ID, responde.getId());
        assertEquals(NAME, responde.getName());
        assertEquals(EMAIL, responde.getEmail());
        assertEquals(PASSWORD, responde.getPassword());
    }

    @Test
    void whenCriateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            service.create(userDto);
        } catch (Exception e) {
            assertEquals(DataIntegrateViolationException.class, e.getClass());
            assertEquals("E-mail ja cadastrado em nosso sistema", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User responde = service.update(userDto);

        assertNotNull(responde);
        assertEquals(User.class, responde.getClass());
        assertEquals(ID, responde.getId());
        assertEquals(NAME, responde.getName());
        assertEquals(EMAIL, responde.getEmail());
        assertEquals(PASSWORD, responde.getPassword());
    }

    @Test
    void whenUpdadeThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            service.create(userDto);
        } catch (Exception e) {
            assertEquals(DataIntegrateViolationException.class, e.getClass());
            assertEquals("E-mail ja cadastrado em nosso sistema", e.getMessage());
        }
    }

    @Test
    void deleteWithSucess() {
        when(repository.findById(anyInt())).thenReturn(userOptional);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }
    @Test
    void deleteObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}