/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.convention.daycount;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.util.ArgumentChecker;

/**
 * Convention for calculating the day count.
 */
public abstract class DayCount {

  /**
   * Gets the day count between the specified dates.
   * <p>
   * Given two dates, this method returns the fraction of a year between these dates
   * according to the convention.
   * 
   * @param firstDate  the earlier date, not null
   * @param secondDate  the later date, not null
   * @return the day count fraction
   */
  public abstract double getDayCountFraction(final LocalDate firstDate, final LocalDate secondDate);

  /**
   * Gets the day count between the specified dates using the supplied calendar to provide business days
   * <p>
   * Given two dates, this method returns the fraction of a year between these dates
   * according to the convention.
   * 
   * @param firstDate  the earlier date, not null
   * @param secondDate  the later date, not null
   * @param calendar  a calendar
   * @return the day count fraction
   */
  public double getDayCountFraction(final LocalDate firstDate, final LocalDate secondDate, final Calendar calendar) {
    return getDayCountFraction(firstDate, secondDate);
  }

  /**
   * Gets the day count between the specified dates.
   * <p>
   * Given two dates, this method returns the fraction of a year between these dates
   * according to the convention.
   * 
   * @param firstDate  the earlier date, not null
   * @param secondDate  the later date, not null
   * @return the day count fraction
   */
  public double getDayCountFraction(final ZonedDateTime firstDate, final ZonedDateTime secondDate) {
    ArgumentChecker.notNull(firstDate, "first date");
    ArgumentChecker.notNull(secondDate, "second date");
    return getDayCountFraction(firstDate.getDate(), secondDate.getDate());
  }

  /**
   * Gets the day count between the specified dates using the supplied calendar to provide business days
   * <p>
   * Given two dates, this method returns the fraction of a year between these dates
   * according to the convention.
   * 
   * @param firstDate  the earlier date, not null
   * @param secondDate  the later date, not null
   * @param calendar  a calendar
   * @return the day count fraction
   */
  public double getDayCountFraction(final ZonedDateTime firstDate, final ZonedDateTime secondDate, final Calendar calendar) {
    ArgumentChecker.notNull(firstDate, "first date");
    ArgumentChecker.notNull(secondDate, "second date");
    return getDayCountFraction(firstDate.getDate(), secondDate.getDate());
  }

  /**
   * Calculates the accrued interest for the coupon according to the convention.
   * 
   * @param previousCouponDate  the previous coupon date, not null
   * @param date  the evaluated coupon date, not null
   * @param nextCouponDate  the next coupon date, not null
   * @param coupon  the coupon value
   * @param paymentsPerYear  the number of payments per year, one, two, three, four, six or twelve
   * @return the accrued interest
   */
  public abstract double getAccruedInterest(final LocalDate previousCouponDate, final LocalDate date, final LocalDate nextCouponDate, final double coupon, final double paymentsPerYear);

  /**
   * Calculates the accrued interest for the coupon according to the convention.
   * 
   * @param previousCouponDate  the previous coupon date, not null
   * @param date  the evaluated coupon date, not null
   * @param nextCouponDate  the next coupon date, not null
   * @param coupon  the coupon value
   * @param paymentsPerYear  the number of payments per year, one, two, three, four, six or twelve
   * @return the accrued interest
   */
  public double getAccruedInterest(final ZonedDateTime previousCouponDate, final ZonedDateTime date, final ZonedDateTime nextCouponDate, final double coupon, final double paymentsPerYear) {
    ArgumentChecker.notNull(previousCouponDate, "previous coupon date");
    ArgumentChecker.notNull(date, "date");
    ArgumentChecker.notNull(nextCouponDate, "next coupon date");
    return getAccruedInterest(previousCouponDate.getDate(), date.getDate(), nextCouponDate.getDate(), coupon, paymentsPerYear);
  }

  /**
   * Gets the name of the convention.
   * 
   * @return the name, not null
   */
  public abstract String getConventionName();

}
