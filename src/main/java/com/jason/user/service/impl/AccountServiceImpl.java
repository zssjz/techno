package com.jason.user.service.impl;

import com.jason.user.model.AccountDO;
import com.jason.user.repository.AccountRepository;
import com.jason.user.service.AccountService;
import com.jason.module.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by BNC on 2019/7/10.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public MessageDTO modifyAccount(AccountDO accountDO) {
        AccountDO result = accountRepository.save(accountDO);
        return new MessageDTO(1, HttpStatus.OK, "OK", result);
    }

    @Override
    public MessageDTO deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
        return new MessageDTO(1, HttpStatus.OK, "OK");
    }

    @Override
    public MessageDTO queryAccount(String accountId) {
        Optional<AccountDO> optional = accountRepository.findById(accountId);
        if (optional.isPresent()) {
            return new MessageDTO(1, HttpStatus.OK, "OK", optional.get());
        }
        return new MessageDTO(1, HttpStatus.NO_CONTENT, "无数据");
    }

    @Override
    public MessageDTO queryAccountList(AccountDO accountDO) {
        Pageable pageable = PageRequest.of(accountDO.getPageSize(), accountDO.getPageNum());
        Example<AccountDO> example = Example.of(accountDO);
        Page<AccountDO> page = accountRepository.findAll(example, pageable);
        return new MessageDTO(1, HttpStatus.OK, "OK", page);
    }
}
