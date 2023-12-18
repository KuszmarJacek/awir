package awir.lab2.services;

import awir.lab2.entities.User;

public interface UserService {
    public User findUserByName(String name);
}
