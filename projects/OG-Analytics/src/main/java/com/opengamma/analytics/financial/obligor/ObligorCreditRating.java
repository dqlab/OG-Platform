/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.obligor;

import java.util.Set;

import com.opengamma.util.ArgumentChecker;

/**
 * Gets a particular type of credit rating of an {@link Obligor}.
 */
public class ObligorCreditRating implements ObligorMeta<Obligor, Set<CreditRating>> {

  @Override
  public int compare(final Obligor o1, final Obligor o2) {
    return o1.getSector().getName().compareTo(o2.getSector().getName());
  }

  @Override
  public Set<CreditRating> getMetaData(final Obligor obligor) {
    ArgumentChecker.notNull(obligor, "obligor");
    return obligor.getCreditRatings();
  }

  @Override
  public Set<CreditRating> getMetaData(final Obligor obligor, final Set<CreditRating> element) {
    ArgumentChecker.notNull(obligor, "obligor");
    return obligor.getCreditRatings();
  }

}
