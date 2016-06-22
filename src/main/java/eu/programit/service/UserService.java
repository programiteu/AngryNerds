package eu.programit.service;


import eu.programit.domain.User;
import eu.programit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected UserRepository userRepository;
    @Override
    public User saveUser(User user) {
         return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        User temp = userRepository.findOne(id);
       if(temp!=null){
           userRepository.delete(id);
           return true;
       }
        return false;
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return (Collection<User>) userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
//
//    @Override
//    public Collection<User> findByName(String name) {
//        return (Collection<User>) userRepository.findByName(name);
//    }
}
