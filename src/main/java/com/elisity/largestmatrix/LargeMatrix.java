package com.elisity.largestmatrix;

import java.util.*;
import java.util.stream.Collectors;

public class LargeMatrix {

    //LargeMatrix for which we are computing the largest-sum sub-matrix
    private final int[][] internalMatrix;

    //List to hold all the sub-matrices
    private Set<SubMatrix> subMatrices = new HashSet<>();

    private final int rows;
    private final int cols;

    public LargeMatrix(int[][] internalMatrix) throws Exception {

        Objects.requireNonNull(internalMatrix, "Input matrix is mandatory and it cannot be null");
        this.internalMatrix = internalMatrix;

        rows = internalMatrix.length;
        cols = internalMatrix[0].length;

        if(rows <= 0 || cols <= 0) {
            throw new Exception("The number of rows and cols should be greater than 0");
        }


        for(int ri=0; ri<rows; ri++) {
            if(internalMatrix[ri].length != cols) {
                throw new Exception(String.format("Expectation mismatch. The number of columns should be %s", cols));
            }
        }

        generateSubMatrices();
    }

    /**
     * Generating unit matrices.
     * Unit matrix is a matrix with only one element in it and having the dimension 1X1
     * */
    private void generateUnitMatrices() {
        for(int ri=0; ri<rows; ri++) {
            for(int ci=0; ci<cols; ci++) {
                SubMatrix subMatrix = new SubMatrix(1, 1, ri, ci);
                subMatrix.setSum(internalMatrix[ri][ci]);
                subMatrices.add(subMatrix);
            }
        }
    }


    private void generateRowVectors() {

    }


    private void generateColVectors() {

    }


    private void generateVectors() {

        generateColVectors();
    }

    /**
     * This api generates all the possible sub-matrices
     * **/
    private void generateSubMatrices() {
        generateUnitMatrices();
        generateVectors();
    }


    public SubMatrix getLargeSubMatrix() {
        List<SubMatrix> sortedSubMatrices = subMatrices.stream()
                .sorted(Comparator.comparing(SubMatrix::getSum))
                .collect(Collectors.toList());

        return sortedSubMatrices.get(sortedSubMatrices.size()-1);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int numOfSubMatrices() {
        return subMatrices.size();
    }
}
