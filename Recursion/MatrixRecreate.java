// Name:   Date:

public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.display(matrix, rowcount, colcount);
      TheMatrix.re_create(rowcount, colcount);
      int[][] new_matrix = TheMatrix.getRecreatedMatrix();
      if( new_matrix == null )
         System.out.println("Did not find a match.");
      else
         TheMatrix.display( new_matrix, rowcount, colcount );
   }
}
class TheMatrix
{
	//do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix = null;
   
   public static int[][] getRecreatedMatrix()
   {
      return recreatedMatrix;
   }
   public static int[][] create()
   {
        int[][] arr= new int[(int)(Math.random()*4)+2][(int)(Math.random()*4)+2];
        for(int r= 0;r<arr.length;r++)
            for(int c=0;c<arr[0].length;c++)
                arr[r][c]=(int)(Math.random()*2);
        return arr;
   }
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
        for(int r=0;r<matrix.length;r++)
            for(int c=0;c<matrix[0].length;c++)
                if(matrix[r][c]==1){
                    rowcount[r]+=1;
                    colcount[c]+=1;
                }
   }
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {
        System.out.print("    ");
        for(int c =0;c<colcount.length;c++)
            System.out.print(colcount[c]+" ");
        System.out.println("");
        for(int r=0;r<matrix.length;r++){
            System.out.print(rowcount[r]+" | ");
            for(int c=0;c<matrix[0].length;c++)
                System.out.print(matrix[r][c]+" ");
            System.out.println("");

        }  
            //if(r==0)
            //for(int c=0;)
   }
   public static void re_create(int[] orig_rowcount, int[] orig_colcount)
   {     
      recreatedMatrix= null;  //delete the previous matrix
      int[][] new_matrix = new int[orig_rowcount.length][orig_colcount.length];	
      recur(new_matrix, orig_rowcount, orig_colcount, 0, 0);
   }
   private static void recur(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount, int row, int col)
   {
      if(compare(new_matrix, orig_rowcount, orig_colcount))    //base case: if new_matrix works, then copy over to recreatedMatrix
      {
      	//copy over from new_matrix to recreatedMatrix (not just references)
         recreatedMatrix = new int[new_matrix.length][];
         for(int i = 0; i < new_matrix.length; i++)
         {
            recreatedMatrix[i] = new int[new_matrix[i].length];
            for (int j = 0; j < new_matrix[i].length; j++)
            {
               recreatedMatrix[i][j] = new_matrix[i][j];
            }
         } 
         return;   //we've found it!  It's a base case.
      }
   	//ENTER YOUR PERMUTATION CODE HERE
   	 if(row<new_matrix.length&&col<new_matrix[0].length)
   	    new_matrix[row][col]=1;
   	 if(col==new_matrix[0].length-1&&row<new_matrix.length)
        recur(new_matrix,orig_rowcount,orig_colcount,row+1,0);
   	 else if(row<new_matrix.length)
   	    recur(new_matrix,orig_rowcount,orig_colcount,row,col+1);
   	if(row<new_matrix.length&&col<new_matrix[0].length)
   	 new_matrix[row][col]=0;
   	 if(col==new_matrix[0].length-1&&row<new_matrix.length)
        recur(new_matrix,orig_rowcount,orig_colcount,row+1,0);
   	 else if(row<new_matrix.length)
   	    recur(new_matrix,orig_rowcount,orig_colcount,row,col+1);
   }
   private static boolean compare(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount)
   {
        int[][] temp = new int[new_matrix.length][new_matrix[0].length];
        int[] rowcount2=new int[new_matrix.length];
        int[] colcount2=new int[new_matrix[0].length];
        for(int r=0;r<new_matrix.length;r++){
            for(int c=0;c<new_matrix[0].length;c++){
            temp[r][c]=new_matrix[r][c];
            if(new_matrix[r][c]==1){
                    rowcount2[r]+=1;
                    colcount2[c]+=1;
            }
            }
            }
        for(int r=0;r<new_matrix.length;r++)
            if(rowcount2[r]!=orig_rowcount[r])
                return false;
        for(int c=0;c<new_matrix[0].length;c++)
            if(colcount2[c]!=orig_colcount[c])
                return false;
        return true;
   }
}



