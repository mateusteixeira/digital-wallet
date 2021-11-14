package br.com.dwallet.api;

import br.com.dwallet.model.dto.UserDTO;
import br.com.dwallet.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = this.userService.createUser(userDTO);
        URI location = getUriToHeader(savedUserDTO);
        return ResponseEntity.created(location).body(savedUserDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int pageSize,
                                                  @RequestParam(defaultValue = "id,desc") String[] sort) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sort));
        return ResponseEntity.ok(userService.getAllUsers(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") String idUser) {
        return ResponseEntity.ok(userService.getUserById(idUser));
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody UserDTO userDTO, @PathVariable(name = "id") String idUser) {
        userService.updateUser(userDTO, idUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable(name = "id") String idUser) {
        userService.deleteUser(idUser);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    private URI getUriToHeader(UserDTO userDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();
    }

}
