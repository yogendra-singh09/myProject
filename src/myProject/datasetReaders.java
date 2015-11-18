package myProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Jama.Matrix;

public class datasetReaders {
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		Scanner sC = new Scanner(new File(Config.RATING_FILES));
		//1508 (user-id) 2071(item-id)
		//byte [][] userMatrix =  new byte[10000][5854];

		double[][] ratingMatrix = new double[1508][2071];
		
		
		int a=0,b;
		String[] temp;
		
		float rating;
		while(sC.hasNext()){
			//System.out.println(sC.nextLine());
			temp = sC.nextLine().split(" ");
			a= Integer.parseInt(temp[0]);
			b= Integer.parseInt(temp[1]);
			rating = Float.parseFloat(temp[2]);
			ratingMatrix[a-1][b-1]=rating;
		}
		
		
		double[][] P1= new double[1508][2];
		
		for (int i = 0; i < P1.length; i++) {
		    for (int j = 0; j < P1[i].length; j++) {
		        P1[i][j] = (int)(Math.random()*10);
		    }
		}
		
		
        double[][] Q1= new double[2071][2];
		
		for (int i = 0; i < Q1.length; i++) {
		    for (int j = 0; j < Q1[i].length; j++) {
		        Q1[i][j] = (int)(Math.random()*10);
		    }
		}
		
		Matrix R = new Matrix(ratingMatrix);
		Matrix P = new Matrix(P1);
		Matrix Q = new Matrix(Q1);
//
		matrixFactorization.matrix_factorization(R, P, Q, 2);
//		 R     : a matrix to be factorized, dimension N x M
//		    P     : an initial matrix of dimension N x K
//		    Q     : an initial matrix of dimension M x K
//		    K     : the number of latent features
//		    steps : the maximum number of steps to perform the optimisation
//		    alpha : the learning rate
//		    beta  : the regularization parameter		
		
//		for(int i=0;i<ratingMatrix.length;i++){
//			for(int j=0;j<ratingMatrix[0].length;j++)
//				if(ratingMatrix[i][j]==0.0){
//					System.out.println(i+" ,"+j);
//				}
//		}
		
		
		sC.close();
		
		
		
	}

}
