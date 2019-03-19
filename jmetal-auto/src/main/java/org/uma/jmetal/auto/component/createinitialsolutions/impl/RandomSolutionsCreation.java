package org.uma.jmetal.auto.component.createinitialsolutions.impl;

import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT1;
import org.uma.jmetal.problem.multiobjective.zdt.ZDT4;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.auto.component.createinitialsolutions.CreateInitialSolutions;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmDefaultOutputData;
import org.uma.jmetal.util.SolutionListUtils;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

public class RandomSolutionsCreation<S extends Solution<?>> implements CreateInitialSolutions<S> {
  private final int numberOfSolutionsToCreate;
  private final Problem<S> problem;

  public RandomSolutionsCreation(Problem<S> problem, int numberOfSolutionsToCreate) {
    this.problem = problem;
    this.numberOfSolutionsToCreate = numberOfSolutionsToCreate;
  }

  public List<S> create() {
    List<S> solutionList = new ArrayList<>(numberOfSolutionsToCreate);
    IntStream.range(0, numberOfSolutionsToCreate)
        .forEach(i -> solutionList.add(problem.createSolution()));

    return solutionList;
  }

  public static void main(String[] args) {
    DoubleProblem problem = new ZDT4(2);
    int numberOfSolutionsToCreate = 100;

    List<DoubleSolution> solutions =
        new RandomSolutionsCreation<>(problem, numberOfSolutionsToCreate).create();
    AlgorithmDefaultOutputData.generateMultiObjectiveAlgorithmOutputData(solutions, 0);
  }
}