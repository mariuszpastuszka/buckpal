package com.mpas.buckpal.adapter.out.persistence;

import com.mpas.buckpal.application.domain.model.Account;
import com.mpas.buckpal.application.domain.model.Account.AccountId;
import com.mpas.buckpal.application.domain.model.Activity;
import com.mpas.buckpal.application.port.out.LoadAccountPort;
import com.mpas.buckpal.application.port.out.UpdateAccountStatePort;
import com.mpas.buckpal.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
class AccountPersistenceAdapter implements
		LoadAccountPort,
		UpdateAccountStatePort {

	private final SpringDataAccountRepository accountRepository;
	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	@Override
	public Account loadAccount(
					AccountId accountId,
					LocalDateTime baselineDate) {

		AccountJpaEntity account =
				accountRepository.findById(accountId.getValue())
						.orElseThrow(EntityNotFoundException::new);

		List<ActivityJpaEntity> activities =
				activityRepository.findByOwnerSince(
						accountId.getValue(),
						baselineDate);

		Long withdrawalBalance = activityRepository
				.getWithdrawalBalanceUntil(
						accountId.getValue(),
						baselineDate)
				.orElse(0L);

		Long depositBalance = activityRepository
				.getDepositBalanceUntil(
						accountId.getValue(),
						baselineDate)
				.orElse(0L);

		return accountMapper.mapToDomainEntity(
				account,
				activities,
				withdrawalBalance,
				depositBalance);

	}

	@Override
	public void updateActivities(Account account) {
		for (Activity activity : account.getActivityWindow().getActivities()) {
			if (activity.getId() == null) {
				activityRepository.save(accountMapper.mapToJpaEntity(activity));
			}
		}
	}

}
