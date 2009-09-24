package com.opengamma.financial.model.option.definition;

/**
 * @author emcleod
 */

public class Barrier {

  public enum KnockType {
    IN, OUT
  }

  public enum BarrierType {
    DOWN, UP
  }

  private final KnockType _knock;
  private final BarrierType _barrier;
  private final double _value;

  public Barrier(KnockType knock, BarrierType barrier, double value) {
    _knock = knock;
    _barrier = barrier;
    _value = value;
  }

  public KnockType getKnockType() {
    return _knock;
  }

  public BarrierType getBarrierType() {
    return _barrier;
  }

  public double getBarrierValue() {
    return _value;
  }
}
