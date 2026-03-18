package com.ecom.user.users.api;

import com.ecom.user.users.api.dto.UsersDTO;
import com.ecom.user.users.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
NOTES
- userId temporarily taken from header. it will change on jwt implementation
 */

@RestController
@Tag(name = "users")
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO,
                                               @RequestHeader("x-user-id") UUID userId){
        return new ResponseEntity<>(usersService.createUser(usersDTO, userId), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id,
                                               @RequestBody UsersDTO usersDTO,
                                               @RequestHeader("x-user-id") UUID userId){
        return new ResponseEntity<>(usersService.updateUser(id, usersDTO, userId), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           @RequestHeader("x-user-id") UUID userId){
        usersService.deleteUser(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
