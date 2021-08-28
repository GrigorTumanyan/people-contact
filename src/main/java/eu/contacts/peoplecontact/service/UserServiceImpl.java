package eu.contacts.peoplecontact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.contacts.peoplecontact.dto.UserDto;
import eu.contacts.peoplecontact.entity.User;
import eu.contacts.peoplecontact.exception.ResourceNotFoundException;
import eu.contacts.peoplecontact.mapper.UserMapper;
import eu.contacts.peoplecontact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.ToDto(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email + " Email not found")));

    }

    public List<UserDto> getAllUsers() {
        List<UserDto> allUsersDto = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            allUsersDto.add(userMapper.ToDto(user));
        }
        return allUsersDto;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException(userDto.getEmail() + " is already exists");
        } else {
            User createdUser = userRepository.save(userMapper.ToEntity(userDto));
            return userMapper.ToDto(createdUser);
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email + " Email not found"));
            userRepository.delete(user);
    }

    @Override
    public UserDto updateUserByEmail(String email, UserDto userDto) {
        Optional<User> findUserByEmail = userRepository.findByEmail(email);
        if (findUserByEmail.isPresent()) {
            UserDto userDtoFromData = userMapper.ToDto(findUserByEmail.get());
            userDtoFromData.setEmail(userDto.getEmail());
            userDtoFromData.setName(userDto.getName());
            User updatedUser = userRepository.save(userMapper.ToEntity(userDtoFromData));
            return userMapper.ToDto(updatedUser);
        }
        throw new ResourceNotFoundException(userDto.getEmail() + " not found");

    }
}
