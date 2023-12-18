package awir.lab2.services;

import awir.lab2.entities.User;
import awir.lab2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    public UserServiceImpl() {
    }

    public User findUserByName(String name) {
        return repository.findUserByName(name);
    }
}
