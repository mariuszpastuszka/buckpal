package com.mpas.buckpal.application.port.out;

import com.mpas.buckpal.application.domain.model.Account;

public interface AccountLock {

	void lockAccount(Account.AccountId accountId);

	void releaseAccount(Account.AccountId accountId);

}
