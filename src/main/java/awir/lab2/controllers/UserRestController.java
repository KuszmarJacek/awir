package awir.lab2.controllers;

import awir.lab2.entities.User;
import awir.lab2.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController implements UserApi {
    @Autowired
    UserRepository repository;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/add_user")
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            repository.save(user);
            logger.info("[INFO]: Added user: " +  user.getName() + " " + user.getEmail());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user); // obiekt Result należy zainicjować
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new Error()); // obiekt Error należy zainicjować
        }
    }

    @DeleteMapping("/delete_user/{name}")
    public ResponseEntity deleteUser(@PathVariable String name) {
        try {
            User user = repository.findUserByName(name);
            repository.delete(user);
            logger.info("[INFO]: Deleted user: " +  user.getName() + " " + user.getEmail());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user); // obiekt Result należy zainicjować
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new Error()); // obiekt Error mależy zainicjować
        }
    }

//    @GetMapping("/get_user/{name}")
    public ResponseEntity getUser(@PathVariable String name) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(repository.findUserByName(name));
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new Error()); // obiekt Error mależy zainicjować
        }
    }

    @PutMapping("/update_user/{name}")
    public ResponseEntity updateuser(@PathVariable String name, @RequestBody User user) {
        try {
            User userToBeUpdated = repository.findUserByName(name);
            userToBeUpdated.setName(user.getName());
            userToBeUpdated.setEmail(user.getEmail());

            User updatedUser = repository.save(userToBeUpdated);
            logger.info("[INFO]: Updated user: " +  userToBeUpdated.getName() + " " + userToBeUpdated.getEmail());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new Error()); // obiekt Error mależy zainicjować
        }
    }
}
