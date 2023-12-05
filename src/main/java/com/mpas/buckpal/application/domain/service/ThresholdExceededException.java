package com.mpas.buckpal.application.domain.service;

import com.mpas.buckpal.application.domain.model.Money;

public class ThresholdExceededException extends RuntimeException {

  public ThresholdExceededException(Money threshold, Money actual) {
    super(String.format("Maximum threshold for transferring money exceeded: tried to transfer %s but threshold is %s!", actual, threshold));
  }

}
