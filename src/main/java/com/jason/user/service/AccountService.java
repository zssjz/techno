package com.jason.user.service;

import com.jason.user.model.AccountDO;
import com.jason.module.MessageDTO;

/**
 * Created by BNC on 2019/7/10.
 */
public interface AccountService {

    /**
     * 保存或编辑账号
     * @param accountDO
     * @return
     */
    MessageDTO modifyAccount(AccountDO accountDO);

    /**
     * 删除账号
     * @param accountId
     * @return
     */
    MessageDTO deleteAccount(String accountId);

    /**
     * 查询账号
     * @param accountId
     * @return
     */
    MessageDTO queryAccount(String accountId);

    /**
     * 查询账号
     * @param accountDO
     * @return
     */
    MessageDTO queryAccountList(AccountDO accountDO);
}
