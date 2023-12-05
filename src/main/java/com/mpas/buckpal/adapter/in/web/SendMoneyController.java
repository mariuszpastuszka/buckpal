package com.mpas.buckpal.adapter.in.web;

import com.mpas.buckpal.application.port.in.SendMoneyUseCase;
import com.mpas.buckpal.application.port.in.SendMoneyCommand;
import com.mpas.buckpal.common.WebAdapter;
import com.mpas.buckpal.application.domain.model.Account.AccountId;
import com.mpas.buckpal.application.domain.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
class SendMoneyController {

	private final SendMoneyUseCase sendMoneyUseCase;

	@PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
	void sendMoney(
			@PathVariable("sourceAccountId") Long sourceAccountId,
			@PathVariable("targetAccountId") Long targetAccountId,
			@PathVariable("amount") Long amount) {

		SendMoneyCommand command = new SendMoneyCommand(
				new AccountId(sourceAccountId),
				new AccountId(targetAccountId),
				Money.of(amount));

		sendMoneyUseCase.sendMoney(command);
	}

}
