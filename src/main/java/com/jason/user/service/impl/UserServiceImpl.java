package com.jason.user.service.impl;

import com.jason.user.model.UserDO;
import com.jason.user.repository.UserRepository;
import com.jason.user.service.UserService;
import com.jason.module.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by BNC on 2019/7/10.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageDTO modifyUser(UserDO userDO) {
        UserDO result = userRepository.save(userDO);
        return new MessageDTO(1, HttpStatus.OK, "OK", result);
    }

    @Override
    public MessageDTO deleteUser(String userId) {
        return null;
    }

    @Override
    public MessageDTO queryUser(String userId) {
        Optional<UserDO> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            return new MessageDTO(1, HttpStatus.OK, "OK", optional.get());
        }
        return new MessageDTO(1, HttpStatus.NO_CONTENT, "无数据");
    }

    @Override
    public MessageDTO queryUserList(UserDO userDO) {
        Pageable pageable = PageRequest.of(userDO.getPageSize(), userDO.getPageNum());
        Example<UserDO> example = Example.of(userDO);
        Page<UserDO> page = userRepository.findAll(example, pageable);
        return new MessageDTO(1, HttpStatus.OK, "OK", page);
    }

    @Override
    public MessageDTO queryUserList() {
        List<UserDO> list = userRepository.findAll();
        return new MessageDTO(1, HttpStatus.OK, "OK", list);
    }
}
