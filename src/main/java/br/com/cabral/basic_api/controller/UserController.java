package br.com.cabral.basic_api.controller;


import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.dto.users.UserUpdateRequest;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "Usuários", description = "Operações relacionada a usuários")
public class UserController {

    @Autowired
    private  final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Trazer todos os usuários")
    public ResponseEntity<List<UserResponse>> getUsers(Pageable pageable){

        List<UserResponse> userResponses = userService.listAll(pageable);
        return ResponseEntity.ok(userResponses);

    }

    @GetMapping("{id}")
    @Operation(summary = "Trazer os usuários por id")
    public  ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse user = userService.getById(id);
        if(user == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Criar os usuários")
    public  ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userBody){
        UserResponse user = userService.createUser(userBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar os usuários")
    public  ResponseEntity<UserResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody UserUpdateRequest userBody){
        UserResponse user = userService.updateUser(id,userBody);

        if(user == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return  ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar os usuários")
    public  ResponseEntity<UserResponse> delete(@PathVariable Long id){
        userService.deletedUser(id);

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
