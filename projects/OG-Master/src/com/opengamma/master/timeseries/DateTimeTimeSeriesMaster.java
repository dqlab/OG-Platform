/**
 * Copyright (C) 2009 - Present by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.master.timeseries;

import java.util.Date;

/**
 * A time-series master that uses {@code java.util.Date}.
 * <p>
 * This master provides information at resolutions up to millisecond.
 */
public interface DateTimeTimeSeriesMaster extends TimeSeriesMaster<Date> {

}
