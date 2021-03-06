package com.elisity.largestmatrixtest;

import com.elisity.largestmatrix.Main;
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
            Main matrix = new Main(m);
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

        Main matrix = new Main(m);

    }

    @Test(expected = Exception.class)
    public void inValidMatrixTest2() throws Exception {

        int[][] m = {
                {2, 5},
                {1}
        };

        Main matrix = new Main(m);

    }


    @Test
    public void numOfSubMatrices2By2Test() {
        int[][] m = {
                {1, -2},
                {3, 8}
        };
        try {
            Main matrix = new Main(m);
            Assert.assertEquals(9, matrix.numOfSubMatrices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numOfSubMatrices2By3Test() {
        int[][] m = {
                {1, -2, 6},
                {3, 8, 4}
        };
        try {
            Main matrix = new Main(m);
            Assert.assertEquals(18, matrix.numOfSubMatrices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numOfSubMatrices3By2Test() {
        int[][] m = {
                {1, -2},
                {3, 8},
                {6, -4}
        };
        try {
            Main matrix = new Main(m);
            Assert.assertEquals(18, matrix.numOfSubMatrices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLargestSubMatrix2By3Test() {
        int[][] m = {
                {10, -2, 6},
                {3, 8, 4}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(29, largestSubMatrix.getSum());
            Assert.assertEquals(0, largestSubMatrix.getRowIndex());
            Assert.assertEquals(0, largestSubMatrix.getColIndex());
            Assert.assertEquals(2, largestSubMatrix.getRows());
            Assert.assertEquals(3, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLargestSubMatrix3By2Test() {
        int[][] m = {
                {10, -2},
                {3, 8},
                {6, 4}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(29, largestSubMatrix.getSum());
            Assert.assertEquals(0, largestSubMatrix.getRowIndex());
            Assert.assertEquals(0, largestSubMatrix.getColIndex());
            Assert.assertEquals(3, largestSubMatrix.getRows());
            Assert.assertEquals(2, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLargestSubMatrix3By2_2Test() {
        int[][] m = {
                {10, -2},
                {-30, 8},
                {6, 4}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(12, largestSubMatrix.getSum());
            Assert.assertEquals(1, largestSubMatrix.getRowIndex());
            Assert.assertEquals(1, largestSubMatrix.getColIndex());
            Assert.assertEquals(2, largestSubMatrix.getRows());
            Assert.assertEquals(1, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLargestSubMatrix4By2Test() {
        int[][] m = {
                {10, -2},
                {-30, 8},
                {6, 4},
                {26, -6}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(32, largestSubMatrix.getSum());
            Assert.assertEquals(2, largestSubMatrix.getRowIndex());
            Assert.assertEquals(0, largestSubMatrix.getColIndex());
            Assert.assertEquals(2, largestSubMatrix.getRows());
            Assert.assertEquals(1, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getLargestSubMatrix2By2Test() {
        int[][] m = {
                {10, -2},
                {3, 8}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(19, largestSubMatrix.getSum());
            Assert.assertEquals(0, largestSubMatrix.getRowIndex());
            Assert.assertEquals(0, largestSubMatrix.getColIndex());
            Assert.assertEquals(2, largestSubMatrix.getRows());
            Assert.assertEquals(2, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLargestSubMatrix2By2_2Test() {
        int[][] m = {
                {-4, -2},
                {3, 8}
        };
        try {
            Main matrix = new Main(m);
            SubMatrix largestSubMatrix = matrix.getLargeSubMatrix();
            Assert.assertEquals(11, largestSubMatrix.getSum());
            Assert.assertEquals(1, largestSubMatrix.getRowIndex());
            Assert.assertEquals(0, largestSubMatrix.getColIndex());
            Assert.assertEquals(1, largestSubMatrix.getRows());
            Assert.assertEquals(2, largestSubMatrix.getCols());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
