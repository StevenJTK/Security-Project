package org.example.springbootproject.controller;

import org.example.springbootproject.errorcode.UserNotFound;
import org.example.springbootproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/remove")
public class RemoveController {

    private final UserService userService;

    public RemoveController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping
    public ResponseEntity<String> remove(@RequestParam Long id) {
        if (userService.removeUser(id)) {
            return ResponseEntity.ok("Remove successful");
        }
     //   UserNotFound usernotfound = new UserNotFound("USER_NOT_FOUND", "User " + id + " does not exist", HttpStatus.NOT_FOUND.value());
       UserNotFound usernotfound = new UserNotFound(id);
      //  return new ResponseEntity<>(usernotfound, HttpStatus.NOT_FOUND);
        throw usernotfound;
    }

}
