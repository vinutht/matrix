package com.elisity.largestmatrix;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    //Main for which we are computing the largest-sum sub-matrix
    private final int[][] internalMatrix;

    //List to hold all the sub-matrices
    private Map<SubMatrix, SubMatrix> subMatrices = new HashMap<>();

    private final int rows;
    private final int cols;

    public Main(int[][] internalMatrix) throws Exception {

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

        generateMatrices();
    }

    /**
     * Generating unit matrices.
     * Unit matrix is a matrix with only one element in it and having the dimension 1X1
     *
     * As an example suppose we have the following matrix
     * {{1, 2}, {3, 4}}
     *
     * Then it generates 4 unit matrices
     * {1}, {2}, {3}, {4}
     * */
    private void generateUnitMatrices() {
        for(int ri=0; ri<rows; ri++) {
            for(int ci=0; ci<cols; ci++) {
                SubMatrix subMatrix = new SubMatrix(1, 1, ri, ci);
                subMatrix.setSum(internalMatrix[ri][ci]);
                subMatrices.put(subMatrix, subMatrix);
            }
        }
    }


    /***
     * This api generates row vectors and their sum
     * As an example, if suppose we have the following matrix
     *
     * {{1, 2}, {3, 4}}
     *
     * It generates the following row vectors and their sum
     *
     * {1, 2}, sum=3
     * and
     * {3, 4}, sum=7
     *
     * It uses the cache which holds the precomputed vectors so that it does not compute the sum of the already precomputed row vectors.
     * ***/
    private void generateRowVectors(int vectSize) {
        for(int ri=0; ri<rows; ri++) {
            for(int ci=0; ci<cols; ci++) {
                if(ci+vectSize <= cols) {
                    int vectSum = 0;
                    int nCols = vectSize;
                    for(int cellIndex=0; ((cellIndex<vectSize) && (cellIndex+ci<cols)); ) {
                        SubMatrix subMatrix = new SubMatrix(1, nCols-1, ri, ci+cellIndex);
                        nCols = nCols - 1;
                        //Picking the already computed sub-matrix from the cache.
                        SubMatrix cachedSubMatrix = subMatrices.get(subMatrix);

                        if(cachedSubMatrix == null) {
                            subMatrix = new SubMatrix(1, 1, ri, ci+cellIndex);
                            cachedSubMatrix = subMatrices.get(subMatrix);
                            vectSum = vectSum + cachedSubMatrix.getSum();
                            cellIndex++;
                        }

                        else if(cachedSubMatrix != null) {
                            vectSum = vectSum + cachedSubMatrix.getSum();
                            cellIndex = cellIndex+vectSize-1;
                        }
                    }
                    SubMatrix rowVect = new SubMatrix(1, vectSize, ri, ci);
                    rowVect.setSum(vectSum);
                    subMatrices.put(rowVect, rowVect);
                }
            }
        }
    }


    /***
     * This api generates col vectors and their sum
     * As an example, if suppose we have the following matrix
     *
     * {{1, 2}, {3, 4}}
     *
     * It generates the following col vectors and their sum
     *
     * {1, 3}, sum=4
     * and
     * {2, 4}, sum=6
     *
     * It uses the cache which holds the precomputed vectors so that it does not compute the sum of the already precomputed col vectors.
     * ***/
    private void generateColVectors(int vectSize) {
        for(int ci=0; ci<cols; ci++) {
            for(int ri=0; ri<rows; ri++) {
                if(ri+vectSize <= rows) {
                    int vectSum = 0;
                    int nRows = vectSize;
                    for(int cellIndex=0; ((cellIndex<vectSize) && (cellIndex+ri<rows)); ) {
                        SubMatrix subMatrix = new SubMatrix(nRows-1, 1, ri+cellIndex, ci);
                        nRows = nRows-1;
                        SubMatrix cachedSubMatrix = subMatrices.get(subMatrix);
                        if(cachedSubMatrix == null) {
                            subMatrix = new SubMatrix(1, 1, ri+cellIndex, ci);
                            cachedSubMatrix = subMatrices.get(subMatrix);
                            vectSum = vectSum + cachedSubMatrix.getSum();
                            cellIndex++;
                        }
                        else if(cachedSubMatrix != null) {
                            vectSum = vectSum + cachedSubMatrix.getSum();
                            cellIndex = cellIndex+vectSize-1;
                        }
                    }
                    SubMatrix rowVect = new SubMatrix(vectSize, 1, ri, ci);
                    rowVect.setSum(vectSum);
                    subMatrices.put(rowVect, rowVect);
                }
            }
        }
    }


    /***
     * This api generates both row and column vectors of all sizes.
     *
     * For example, if suppose the given matrix is
     *
     * {{1, 2,3}, {2, 4, 6}, {3, 2, 8}}
     *
     * It generates row vectors of size 2 and 3 and their sum
     * Row vectors = {1, 2};sum=3, {2, 3};sum=5, {1, 2, 3};sum=6, {2, 4};sum=6, {4, 6};sum=10 and so on
     *
     * In the similar way it also generates all the col vectors
     * **/
    private void generateVectors() {

        for(int vectSize=2; vectSize<=cols; vectSize++) {
            generateRowVectors(vectSize);
        }

        for(int vectSize=2; vectSize<=rows; vectSize++) {
            generateColVectors(vectSize);
        }

    }



    private void computeVerticalBoxSum(int startRowIndex, int startColIndex, int numRows, int numCols) {

        int boxSum = 0;

        for(int numRowIndex = 0, ri = startRowIndex; numRowIndex<numRows; numRowIndex++, ri++) {
            SubMatrix rowVector = new SubMatrix(1, numCols, ri, startColIndex);
            SubMatrix cachedVector = subMatrices.get(rowVector);
            if(cachedVector != null) {
                boxSum = boxSum + cachedVector.getSum();
            }
        }
        SubMatrix box = new SubMatrix(numRows, numCols, startRowIndex, startColIndex);
        box.setSum(boxSum);

        subMatrices.put(box, box);
    }

    private void computeHorizontalBoxSum(int startRowIndex, int startColIndex, int numRows, int numCols) {

        int boxSum = 0;

        for(int numColIndex = 0, ci = startColIndex; numColIndex<numCols; numColIndex++, ci++) {
            SubMatrix colVector = new SubMatrix(numRows, 1, startRowIndex, ci);
            SubMatrix cachedVector = subMatrices.get(colVector);
            if(cachedVector != null) {
                boxSum = boxSum + cachedVector.getSum();
            }
        }
        SubMatrix box = new SubMatrix(numRows, numCols, startRowIndex, startColIndex);
        box.setSum(boxSum);

        subMatrices.put(box, box);
    }

    /***
     * This generates all the matrices of all sizes.
     * If for example we have 4x3 matrix then it generates
     *
     * 2x2, 2x3, 3x2, 3x3, 4x2, 4x3 matrices and their sums
     *
     * It uses dynamic programming approach to store all the computed matrix in cache and re-use these precomputed matrix for further computation
     * **/
    private void generateSubMatrices() {

        for(int rI = 0; rI<rows; rI++) {
            for(int numRows=2; numRows<=rows; numRows++)
            if(rI+numRows <= rows) {
                computeVerticalBoxSum(rI, 0, numRows, 2);
            }
        }

        for(int cI = 0; cI<cols; cI++) {
            for(int numCols=2; numCols<=cols; numCols++)
                if(cI+numCols <= cols) {
                    computeVerticalBoxSum(0, cI, 2, numCols);
                }
        }
    }

    /**
     * This api generates all the possible sub-matrices
     *
     * This generates unit matrix, row vectors, col vectors and sub-matrices of all sizes and their corresponding sums.
     * This uses DP technique and stores all the computed matrices into cache so that it can be reused when needed instead of computing it again.
     * **/
    private void generateMatrices() {
        generateUnitMatrices();
        generateVectors();
        generateSubMatrices();
    }


    /***
     * Sort all the generated sub-matrices using their sums and return the sub-matrix with largest sum.
     * **/
    public SubMatrix getLargeSubMatrix() {
        List<SubMatrix> sortedSubMatrices = subMatrices.keySet().stream()
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
