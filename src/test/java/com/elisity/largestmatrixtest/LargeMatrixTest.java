package com.elisity.largestmatrixtest;

import com.elisity.largestmatrix.LargeMatrix;
import com.elisity.largestmatrix.SubMatrix;
import org.junit.Assert;
import org.junit.Test;

public class LargeMatrixTest {



    @Test
    public void validMatrixTest() {

        int[][] m = {
                {1, -2},
                {3, 8}
        };
        try {
            LargeMatrix matrix = new LargeMatrix(m);
            int numRows = matrix.getRows();
            int numCols = matrix.getCols();

            Assert.assertEquals(2, numRows);
            Assert.assertEquals(2, numCols);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void inValidMatrixTest1() throws Exception {

        int[][] m = {
                {},
                {}
        };

        LargeMatrix matrix = new LargeMatrix(m);

    }

    @Test(expected = Exception.class)
    public void inValidMatrixTest2() throws Exception {

        int[][] m = {
                {2, 5},
                {1}
        };

        LargeMatrix matrix = new LargeMatrix(m);

    }


    @Test
    public void numOfUnitMatricesTest() {
        int[][] m = {
                {1, -2},
                {3, 8}
        };
        try {
            LargeMatrix matrix = new LargeMatrix(m);
            Assert.assertEquals(4, matrix.numOfSubMatrices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLargestSubMatrixTest() {
        int[][] m = {
                {10, -2},
                {3, 8}
        };
        try {
            LargeMatrix matrix = new LargeMatrix(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(10, largestSubMatrix.getSum());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
