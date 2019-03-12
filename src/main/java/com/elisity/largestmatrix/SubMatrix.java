package com.elisity.largestmatrix;

import java.util.Objects;

public class SubMatrix implements Comparable<SubMatrix> {

    //Number of rows in the sub-matrix
    private final int rows;

    //Number of cols in the sub-matrix
    private final int cols;

    //Index of the row - Start of the sub-matrix(rowIndex, colIndex)
    private final int rowIndex;

    //Index of the col - start of the sub-matrix(rowIndex, colIndex)
    private final int colIndex;


    //Total sum of the sub-matrix represented by this instance.
    private int sum;


    public SubMatrix(int rows, int cols, int rowIndex, int colIndex) {
        this.rows = rows;
        this.cols = cols;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubMatrix subMatrix = (SubMatrix) o;
        return rows == subMatrix.rows &&
                cols == subMatrix.cols &&
                rowIndex == subMatrix.rowIndex &&
                colIndex == subMatrix.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, cols, rowIndex, colIndex);
    }

    @Override
    public int compareTo(SubMatrix o) {
        return this.sum - o.sum;
    }
}
