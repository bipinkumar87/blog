package com.example.blogBipin.services.impl;

import com.example.blogBipin.Dao.UserDao;
import com.example.blogBipin.Dto.UserDto;
import com.example.blogBipin.entity.User;
import com.example.blogBipin.exceptions.UserNotFoundException;
import com.example.blogBipin.mapper.EntityMapper;
import com.example.blogBipin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityMapper entityMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(userDto.getPassword()); // Hash the password
        User user = entityMapper.toUserEntity(userDto);
        user = userDao.save(user);
        logger.info("User created with ID: {}", user.getId());
        return entityMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return entityMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();
        return users.stream()
                .map(entityMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());

        // Hash the password if it is updated
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword()); // Hash the password
        }

        userDao.save(existingUser);
        logger.info("User updated with ID: {}", existingUser.getId());
        return entityMapper.toUserDto(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
        logger.info("User deleted with ID: {}", id);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return entityMapper.toUserDto(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
