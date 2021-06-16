/** Various functions dealing with vectors and matrices. */
class LinearAlgebra {


    /**
     * Returns the magnitude of the vector v (which may be of any length).
     * This is found by adding up the squares of all of the elements of v
     * and taking the square root of the total.
     */
    static double magnitude(double[] v) {
        double squares = 0;
        for(int i=0; i<v.length; i++)
        {
            squares += v[i] * v[i];
        }
        double magnitude = Math.sqrt(squares);
        return magnitude;
    }

    /**
     * Returns the sum of vectors v and w. This is a vector of the same
     * length, each of whose elements is the sum of the corresponding
     * elements in v and w.
     */
    static double[] sum(double[] v, double[] w) {
        double[] sum = new double[v.length];
        for(int i=0; i<v.length; i++)
        {
            sum[i] = v[i] + w[i];
        }
        return sum;
    }

    /**
     * Returns the difference between vectors v and w. This is a vector
     * of the same length, each of whose elements is the difference
     * between the corresponding elements in v and w.
     */
    static double[] difference(double[] v, double[] w) {
        double[] difference = new double[v.length];
        for(int i=0; i<v.length; i++)
        {
            difference[i] = v[i] - w[i];
        }
        return difference;
    }

    /**
     * Returns the element-wise between vectors v and w. This is a vector
     * of the same length, each of whose elements is the product of the
     * corresponding elements in v and w.
     */
    static double[] elementwiseProduct(double[] v, double[] w) {
        double[] elementwiseProduct = new double[v.length];
        for(int i=0; i<v.length; i++)
        {
            elementwiseProduct[i] = v[i] * w[i];
        }
        return elementwiseProduct;
    }

    /**
     * Returns the dot product of vectors v and w. This is the sum of
     * the products of the corresponding elements.
     */
    static double dotProduct(double[] v, double[] w) {
        double dotProduct=0;
        for(int i=0; i<v.length; i++)
        {
            dotProduct += v[i] * w[i];
        }
        return dotProduct;
    }

    /**
     * Returns, as an array of two elements, the dimensions of matrix m.
     */
    static int[] dimensions(double[][] m) {
        int[] dimensions = new int [2];
        dimensions[0] = m.length;
        dimensions[1] = m[0].length;
        return dimensions;
    }

    /**
     * Returns the element-wise sum of matrices m and n.
     */
    //comments are love letters to your future self // /* /** */  /** */
    static double[][] sum(double[][] m, double[][] n) {
        double[][] sum = new double[m.length][m[0].length];
        for(int i=0; i<m.length; i++)
        {
            for(int j=0; j<m[0].length; j++)
            {
                sum[i][j] = m[i][j] + n[i][j];
            }
        }
        return sum;
    }

    /**
     * Returns the element-wise product of matrices m and n.
     */
    static double[][] elementwiseProduct(double[][] m, double[][] n) {
        double[][] elementwiseProduct = new double[m.length][m[0].length];
        for(int i=0; i<m.length; i++)
        {
            for(int j=0; j<m.length; j++)
            {
                elementwiseProduct[i][j] = m[i][j] * n[i][j];
            }
        }
        return elementwiseProduct;
    }

    /**
     * Returns the transpose of m, that is, a matrix where element
     * i, j is element j, i from m.
     */
    static double[][] transpose(double[][] m) {
        double[][] transpose = new double[m[0].length][m.length];
        for(int i=0; i<m.length; i++)
        {
            for(int j=0; j<m[0].length; j++)
            {
                transpose[j][i] = m[i][j];
            }
        }
        return transpose;
    }

    /**
     * Returns the matrix product of m and n. (Search the web for a
     * definition.)
     */
    static double[][] product(double[][] m, double[][] n) {
        double[][] product = new double[m.length][n[0].length];
            for (int i = 0; i < m.length; i++)
            {
                for (int j = 0; j < n[0].length; j++)
                {
                    for (int count = 0; count < m[0].length; count++)
                    {
                        product[i][j] += m[i][count] * n[count][j];
                    }
                }
            }
        return product;
    }

}
