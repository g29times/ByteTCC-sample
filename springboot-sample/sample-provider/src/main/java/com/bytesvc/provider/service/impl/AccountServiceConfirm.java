package com.bytesvc.provider.service.impl;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;
import com.bytesvc.provider.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountServiceConfirm")
public class AccountServiceConfirm implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    @Transactional
    public void increaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findById(acctId);
//        account.setAmount(account.getAmount() + amount);
        account.setFrozen(account.getFrozen() + amount);
        this.accountDao.update(account);
        System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional
    public void decreaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findById(acctId);
//        account.setAmount(account.getAmount() - amount);
        account.setFrozen(account.getFrozen() - amount);
        this.accountDao.update(account);
        System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

}
