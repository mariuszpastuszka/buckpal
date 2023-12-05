package com.mpas.buckpal.application.port.out;

import java.time.LocalDateTime;

import com.mpas.buckpal.application.domain.model.Account;
import com.mpas.buckpal.application.domain.model.Account.AccountId;

public interface LoadAccountPort {

	Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
