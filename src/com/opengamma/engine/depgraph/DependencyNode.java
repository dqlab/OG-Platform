/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.depgraph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.analytics.AggregatePositionAnalyticFunctionDefinition;
import com.opengamma.engine.analytics.AnalyticFunctionDefinition;
import com.opengamma.engine.analytics.AnalyticValueDefinition;
import com.opengamma.engine.analytics.AnalyticValueDefinitionComparator;
import com.opengamma.engine.analytics.PositionAnalyticFunctionDefinition;
import com.opengamma.engine.analytics.PrimitiveAnalyticFunctionDefinition;
import com.opengamma.engine.analytics.SecurityAnalyticFunctionDefinition;
import com.opengamma.engine.position.Position;
import com.opengamma.engine.security.Security;
import com.opengamma.util.ArgumentChecker;

/**
 * An individual node in any dependency graph.
 *
 * @author kirk
 */
public class DependencyNode {
  private final ComputationTargetType _computationTargetType;
  private final Object _computationTarget;
  private final AnalyticFunctionDefinition _function;
  private final Set<AnalyticValueDefinition<?>> _outputValues =
    new HashSet<AnalyticValueDefinition<?>>();
  private final Set<AnalyticValueDefinition<?>> _inputValues =
    new HashSet<AnalyticValueDefinition<?>>();
  private final Set<DependencyNode> _inputNodes =
    new HashSet<DependencyNode>();
  private final Map<AnalyticValueDefinition<?>, AnalyticValueDefinition<?>> _resolvedInputs =
    new HashMap<AnalyticValueDefinition<?>, AnalyticValueDefinition<?>>();
  
  public DependencyNode(PrimitiveAnalyticFunctionDefinition function) {
    ArgumentChecker.checkNotNull(function, "Analytic Function Definition");
    _computationTargetType = ComputationTargetType.PRIMITIVE;
    _computationTarget = null;
    _function = function;
    _outputValues.addAll(function.getPossibleResults());
    _inputValues.addAll(function.getInputs());
  }
  
  public DependencyNode(SecurityAnalyticFunctionDefinition function, Security security) {
    ArgumentChecker.checkNotNull(function, "Analytic Function Definition");
    _computationTargetType = ComputationTargetType.SECURITY;
    _computationTarget = security;
    _function = function;
    _outputValues.addAll(function.getPossibleResults(security));
    _inputValues.addAll(function.getInputs(security));
  }
  
  public DependencyNode(PositionAnalyticFunctionDefinition function, Position position) {
    ArgumentChecker.checkNotNull(function, "Analytic Function Definition");
    _computationTargetType = ComputationTargetType.POSITION;
    _computationTarget = position;
    _function = function;
    _outputValues.addAll(function.getPossibleResults(position));
    _inputValues.addAll(function.getInputs(position));
  }

  public DependencyNode(AggregatePositionAnalyticFunctionDefinition function, Collection<Position> positions) {
    ArgumentChecker.checkNotNull(function, "Analytic Function Definition");
    _computationTargetType = ComputationTargetType.MULTIPLE_POSITIONS;
    _computationTarget = positions;
    _function = function;
    _outputValues.addAll(function.getPossibleResults(positions));
    _inputValues.addAll(function.getInputs(positions));
  }
  
  /**
   * @return the outputValues
   */
  public Set<AnalyticValueDefinition<?>> getOutputValues() {
    return _outputValues;
  }
  /**
   * @return the inputValues
   */
  public Set<AnalyticValueDefinition<?>> getInputValues() {
    return _inputValues;
  }
  /**
   * @return the inputNodes
   */
  public Set<DependencyNode> getInputNodes() {
    return _inputNodes;
  }
  
  /**
   * @return the function
   */
  public AnalyticFunctionDefinition getFunction() {
    return _function;
  }

  /**
   * @return the computationTargetType
   */
  public ComputationTargetType getComputationTargetType() {
    return _computationTargetType;
  }

  /**
   * @return the computationTarget
   */
  public Object getComputationTarget() {
    return _computationTarget;
  }

  public void addOutputValues(Collection<AnalyticValueDefinition<?>> outputValues) {
    if(outputValues == null) {
      return;
    }
    _outputValues.addAll(outputValues);
  }

  public void addInputValues(Collection<AnalyticValueDefinition<?>> inputValues) {
    if(inputValues == null) {
      return;
    }
    _inputValues.addAll(inputValues);
  }
  
  public Map<AnalyticValueDefinition<?>, AnalyticValueDefinition<?>> getResolvedInputs() {
    return Collections.unmodifiableMap(_resolvedInputs);
  }
  
  public AnalyticValueDefinition<?> getResolvedInput(AnalyticValueDefinition<?> requiredInput) {
    return _resolvedInputs.get(requiredInput);
  }
  
  public AnalyticValueDefinition<?> getBestOutput(AnalyticValueDefinition<?> input) {
    for(AnalyticValueDefinition<?> outputValue: getOutputValues()) {
      if(AnalyticValueDefinitionComparator.matches(input, outputValue)) {
        return outputValue;
      }
    }
    return null;
  }
  
  public void addInputNode(AnalyticValueDefinition<?> satisfyingInput, DependencyNode inputNode) {
    if(satisfyingInput == null) {
      throw new NullPointerException("All input nodes must satisfy an input value required");
    }
    if(inputNode == null) {
      throw new NullPointerException("Must specify a function to produce the input.");
    }
    _inputNodes.add(inputNode);
    _resolvedInputs.put(satisfyingInput, inputNode.getBestOutput(satisfyingInput));
  }
}
