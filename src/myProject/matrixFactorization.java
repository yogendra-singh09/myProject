package myProject;

import Jama.Matrix;

public class matrixFactorization {
	
	
	static double getEIJ(double[]a,double[]b){
		
		double eij=0.0;
		for(int i=0;i<a.length;i++){
			eij+=a[i]*b[i];
		}
		
		return eij;		
	}
	
	//steps=5000, alpha=0.0002, beta=0.02
	/*
	@INPUT:
	    R     : a matrix to be factorized, dimension N x M
	    P     : an initial matrix of dimension N x K
	    Q     : an initial matrix of dimension M x K
	    K     : the number of latent features
	    steps : the maximum number of steps to perform the optimisation
	    alpha : the learning rate
	    beta  : the regularization parameter
	@OUTPUT:
	    the final matrices P and Q */
	
	
	//, double steps, double alpha, double beta
	public static void matrix_factorization(Matrix  R, Matrix P,Matrix Q1, int K){
	
		Matrix Q = Q1.transpose();
	
		int steps =5000;
		double  alpha=0.0002, beta=0.02;
		for(int step=0;step<steps;step++){
			for(int i=0;i<R.getRowDimension();i++){
				for(int j=0;j<R.getColumnDimension();j++){
					 if (R.getArray()[i][j] > 0) // j has rated i
					 {
						 double eij = R.getArray()[i][j]-getEIJ(P.getArrayCopy()[i],Q.transpose().getArray()[j]);
			               for (int k=0;k<2;k++){
		                        P.set(i,k, P.getArray()[i][k] + alpha * (2 * eij * Q.getArray()[k][j] - beta * P.getArray()[i][k]));
		                        Q.set(k,j , Q.getArray()[k][j] + alpha * (2 * eij * P.getArray()[i][k] - beta * Q.getArray()[k][j]));
			               }
			               
					 }
				}
			}
			Matrix eR =  R.times(Q.transpose());
			double e=0;
			for(int i=0;i<R.getRowDimension();i++){
				for(int j=0;j<R.getColumnDimension();j++){
					 if (R.get(i,j) > 0) // j has rated i
					 {
						 //pending start
						 e = e + Math.pow(R.get(i, j) - getEIJ(P.getArrayCopy()[i],Q.transpose().getArray()[j]),2);//numpy.dot(P[i,:],Q[:,j]), 2)
						 //pending end
						 for (int k=0;k<2;k++){
		                    e= e+ (beta/2) * ( Math.pow(P.get(i,k),2) + Math.pow(Q.get(k, j),2));      
							 //P.getArray()[i][k] = P.getArray()[i][k] + alpha * (2 * eij * Q.getArray()[k][j] - beta * P.getArray()[i][k]);
			             }
					 }
				}
			}
			if (e < 0.001)
	            break;
			
		}
		System.out.println(P.toString());
		System.out.println(Q.transpose().toString());
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	 double [][] R1= {
	                   {5,3,0,1},
	                   {4,0,0,1},
	                   {1,1,0,5},
	                   {1,0,0,4},
	                   {0,1,5,4},
	                  };

	 int N = R1.length;
	 int M = R1[0].length;
	 int K=2;
	 
	 double[][] P1 = {{ 0.81731757 , 0.73404687},
			 { 0.66673968 , 0.58751334},
			 { 0.37011864 , 0.70260874},
			 { 0.18583068 , 0.2899891 },
			 { 0.29680928 , 0.86223154}};// of size N x K;
			 
			 
			 double [][]Q1 = {{ 0.76351663 , 0.16536651},
					 { 0.7553445  , 0.63743139},
					 { 0.19173638 , 0.14925955},
					 { 0.58501508 , 0.38253523}}; // of size M x K;
			 
	 Matrix R = new Matrix(R1);
	 Matrix P = new Matrix(P1);
	 Matrix Q = new Matrix(Q1);
	 
	 
	 
	 matrix_factorization(R, P, Q, K);
	 
	 
	 
	}

}
