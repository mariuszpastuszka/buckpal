package com.mpas.buckpal.application.port.in;

import com.mpas.buckpal.application.domain.model.Account.AccountId;
import com.mpas.buckpal.application.domain.model.Money;

public interface GetAccountBalanceUseCase {

	Money getAccountBalance(GetAccountBalanceQuery query);

	record GetAccountBalanceQuery(AccountId accountId) {
	}
}
