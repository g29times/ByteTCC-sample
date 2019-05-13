package com.bytesvc.provider.service.impl;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;
import com.bytesvc.provider.service.IAccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * . _________         .__   _____   __
 * ./   _____/__  _  __|__|_/ ____\_/  |_
 * .\_____  \ \ \/ \/ /|  |\   __\ \   __\
 * ./        \ \     / |  | |  |    |  |
 * /_______  /  \/\_/  |__| |__|    |__|
 * .       \/
 *
 * @author li tong
 * @date 2019/5/13 9:50
 * @see Object
 * @since 1.0
 */
@Compensable(interfaceClass = IAccountService.class,
        confirmableKey = "accountServiceConfirm",
        cancellableKey = "accountServiceCancel")
@Service("accountServiceTry")
public class AccountServiceTry implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    private void someERROR() {
        throw new RuntimeException("Some error");
    }

    @Override
    @Transactional
    public void increaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findById(acctId);
        account.setAmount(account.getAmount() + amount);
        account.setFrozen(account.getFrozen() - amount);
        this.accountDao.update(account);
        // 1 执行完后抛异常 本地回滚OK TODO 将异常转移到下游服务测试分布式异常
        if(amount > 1) {
            someERROR();
        }
        System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional
    public void decreaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findById(acctId);
        account.setAmount(account.getAmount() - amount);
        account.setFrozen(account.getFrozen() + amount);
        this.accountDao.update(account);
        System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

}
