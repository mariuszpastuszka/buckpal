package com.mpas.buckpal.adapter.out.persistence;

import com.mpas.buckpal.application.port.out.AccountLock;
import com.mpas.buckpal.application.domain.model.Account.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {

	@Override
	public void lockAccount(AccountId accountId) {
		// do nothing
	}

	@Override
	public void releaseAccount(AccountId accountId) {
		// do nothing
	}

}
