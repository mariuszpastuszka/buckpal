package com.mpas.buckpal.application.port.out;

import com.mpas.buckpal.application.domain.model.Account;

public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
