package br.com.brunazorzanello.CrudEtestesUnitarios.resourses;

import br.com.brunazorzanello.CrudEtestesUnitarios.domain.Dto.UserDto;
import br.com.brunazorzanello.CrudEtestesUnitarios.domain.User;
import br.com.brunazorzanello.CrudEtestesUnitarios.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourseTest {
    public static final Integer ID = 1;
    public static final String NAME = "Bruna";
    public static final String EMAIL = "bruna@gmail.com";
    public static final String PASSWORD = "1234";
    @InjectMocks
    private UserResourse resourse;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserServiceImpl service;

    private User user = new User();
    private UserDto userDto = new UserDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(),any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = resourse.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDto() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(),any())).thenReturn(userDto);

        ResponseEntity<List<UserDto>> response = resourse.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDto.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(NAME, response.getBody().get(0).getName());
        assertEquals(EMAIL, response.getBody().get(0).getEmail());
        assertEquals(PASSWORD, response.getBody().get(0).getPassword());
    }
    @Test
    void WhenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(user);
        ResponseEntity<UserDto> response = resourse.create(userDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void wheUpdateThenReturnSuccess() {
        when(service.update(userDto)).thenReturn(user);
        when(mapper.map(any(),any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = resourse.update(ID, userDto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
    }
    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<UserDto> response = resourse.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(anyInt());

    }
    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}