package eu.contacts.peoplecontact.service;

import java.util.ArrayList;
import java.util.List;

import eu.contacts.peoplecontact.dto.UserDto;
import eu.contacts.peoplecontact.entity.User;
import eu.contacts.peoplecontact.exception.ResourceNotFoundException;
import eu.contacts.peoplecontact.mapper.UserMapper;
import eu.contacts.peoplecontact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email + "Email not found"));

    }
    public List<UserDto> getAllUsers(){
        List<UserDto> allUsersDto = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            allUsersDto.add(userMapper.ToDto(user));
        }
        return allUsersDto;
    }

    @Override
    public UserDto addUser(UserDto user) {
        return null;
    }

    @Override
    public void deleteUserByEmail(String email) {

    }

    @Override
    public UserDto updateUserByEmail(String email) {
        return null;
    }
}
