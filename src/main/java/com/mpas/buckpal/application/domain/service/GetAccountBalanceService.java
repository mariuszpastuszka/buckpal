package com.mpas.buckpal.application.domain.service;

import com.mpas.buckpal.application.domain.model.Money;
import com.mpas.buckpal.application.port.in.GetAccountBalanceUseCase;
import com.mpas.buckpal.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceUseCase {

	private final LoadAccountPort loadAccountPort;

	@Override
	public Money getAccountBalance(GetAccountBalanceQuery query) {
		return loadAccountPort.loadAccount(query.accountId(), LocalDateTime.now())
				.calculateBalance();
	}
}
