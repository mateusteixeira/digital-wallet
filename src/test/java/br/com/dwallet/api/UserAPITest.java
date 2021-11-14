package br.com.dwallet.api;

import br.com.dwallet.model.dto.UserDTO;
import br.com.dwallet.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAPITest {

    private static final String ID_USER = "q1w2e3r4";

    @Mock
    private UserService userService;

    @InjectMocks
    private UserAPI userAPI;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_user_with_location_created() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UserDTO userDTO = mock(UserDTO.class);
        when(userService.createUser(userDTO)).thenReturn(userDTO);
        when(userDTO.getId()).thenReturn(ID_USER);

        ResponseEntity<UserDTO> userDTOResponseEntity = userAPI.createUser(userDTO);
        assertNotNull(userDTOResponseEntity);
        assertEquals(HttpStatus.CREATED, userDTOResponseEntity.getStatusCode());
        assertNotNull(userDTOResponseEntity.getHeaders());
        assertNotNull(userDTOResponseEntity.getHeaders().getLocation());
        assertEquals("/" + ID_USER, userDTOResponseEntity.getHeaders().getLocation().getPath());
        assertNotNull(userDTOResponseEntity.getBody());
        verify(userService).createUser(userDTO);
        verify(userDTO).getId();
    }

    @Test
    public void should_get_all_users_with_ok() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        when(userService.getAllUsers(pageRequest)).thenReturn(Lists.newArrayList(UserDTO.builder().build()));

        ResponseEntity<List<UserDTO>> usersResponseEntity = userAPI.getUsers(pageRequest);
        assertNotNull(usersResponseEntity);
        assertEquals(HttpStatus.OK, usersResponseEntity.getStatusCode());
        assertNotNull(usersResponseEntity.getBody());
        verify(userService).getAllUsers(any(PageRequest.class));
    }

    @Test
    public void should_get_all_user_by_id_with_ok() {
        when(userService.getUserById(ID_USER)).thenReturn(UserDTO.builder().build());

        ResponseEntity<UserDTO> usersResponseEntity = userAPI.getUser(ID_USER);
        assertNotNull(usersResponseEntity);
        assertEquals(HttpStatus.OK, usersResponseEntity.getStatusCode());
        assertNotNull(usersResponseEntity.getBody());
        verify(userService).getUserById(ID_USER);
    }

    @Test
    public void should_update_user_with_no_content() {
        UserDTO userDTO = UserDTO.builder().build();
        ResponseEntity<Object> responseEntity = userAPI.updateUser(userDTO, ID_USER);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(userService).updateUser(eq(userDTO), eq(ID_USER));
    }

    @Test
    public void should_delete_user_by_id_with_ok() {
        ResponseEntity<Object> responseEntity = userAPI.deleteUser(ID_USER);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(userService).deleteUser(ID_USER);
    }

    @Test
    public void should_delete_all_users_with_ok() {
        ResponseEntity<Object> responseEntity = userAPI.deleteAllUsers();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(userService).deleteAllUsers();
    }
}