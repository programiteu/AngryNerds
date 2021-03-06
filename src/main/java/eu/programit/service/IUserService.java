package eu.programit.service;


import eu.programit.domain.User;

import java.util.Collection;

public interface IUserService {

    public User saveUser(User user);
    public Boolean deleteUser(Integer id);
    public User editUser(User user);
    public User findUser(Integer id);
    public Collection<User> getAllUsers();
    public User findByName(String name);
    public User findByEmail(String email);
}