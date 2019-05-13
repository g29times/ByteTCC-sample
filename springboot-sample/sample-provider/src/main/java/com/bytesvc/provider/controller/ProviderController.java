package com.bytesvc.provider.controller;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;
import com.bytesvc.provider.service.IAccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//@Compensable(interfaceClass = IAccountService.class, cancellableKey = "accountServiceCancel")
@RestController
public class ProviderController {

	@Autowired
	private IAccountDao accountDao;

	@Autowired
	private IAccountService accountServiceTry;

	@ResponseBody
	@RequestMapping(value = "/increase/{acctId}/{amount}", method = RequestMethod.GET)
//	@Transactional
	public void increaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
//		Account account = this.accountDao.findById(acctId);
//		account.setAmount(account.getAmount() + amount);
//		this.accountDao.update(account);
//		System.out.printf("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);

		accountServiceTry.increaseAmount(acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease/{acctId}/{amount}", method = RequestMethod.GET)
//	@Transactional
	public void decreaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
//		Account account = this.accountDao.findById(acctId);
//		account.setAmount(account.getAmount() - amount);
//		this.accountDao.update(account);
//		System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);

		accountServiceTry.decreaseAmount(acctId, amount);
	}

}
