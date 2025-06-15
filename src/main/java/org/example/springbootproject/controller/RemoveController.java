package org.example.springbootproject.controller;

import org.example.springbootproject.errorcode.UserNotFound;
import org.example.springbootproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/remove")
public class RemoveController {

    private final UserService userService;

    //konstruktor
    public RemoveController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        //tar bort användaren och returnerar true om den fanns
        if (userService.removeUser(id)) {
            return ResponseEntity.ok("Remove successful");
        }

        //annars
        UserNotFound usernotfound = new UserNotFound(id);
        throw usernotfound;
    }

}
