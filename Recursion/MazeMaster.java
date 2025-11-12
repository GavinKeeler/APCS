// Name:
// Date:
import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      //Maze m = new Maze(sc.next()+".txt");   //append the .txt here
      Maze m = new Maze();    //extension
      m.display();    
            System.out.println(m);

      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, return false.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, return false.");
      System.out.println("6: Mark only the correct path and list the steps.\n\tIf no path exists, return false.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
    * Do not comment it out.  Do not delete it.
	 */
   public Maze()
   {
   int numR=(int)Math.random()*10;
   int numC=(int)Math.random()*10;
   maze=new char[numR][numC];
   int rowS=(int)Math.random()*(numR-1)+1;
   int rowE=(int)Math.random()*(numR-1)+1;
   for(int r =0;r<numR;r++)
       for(int c = 0;c<numC;c++){
           if(r==rowS&&c==0){
               maze[r][c]='S';
           }else if(r==rowE&&c==numC-1){
               maze[r][c]='E';
           }else if(r==0||r==numR-1||c==0){
                maze[r][c]='W';
            }else if(c==numC){
                maze[r][c]='W';
            }else{
                if(Math.random()>.5)
                    maze[r][c]='W';
                else
                    maze[r][c]='.';
            }
   }
   startRow=rowS;
   startCol=0;
    }
	
	/** 
	 * Constructor.
    * Creates a "deep copy" of the array.
    * The autograder uses this constructor.
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)    //location of start location
            {
               startRow = r;
               startCol = c;
               
            }
         }
      }
   } 
	
 	/* 
	 * Write this one-arg constructor.
    * the filename already has ".txt"
    * Use a try-catch block.
	 * Use next(), not nextLine() 
    * Search the maze and save the location of 'S' 
	 */
   public Maze(String filename)    
   {
         Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         System.exit(1);
      }
      maze=new char[Integer.parseInt(infile.next())][Integer.parseInt(infile.next())];
      while(infile.hasNext())
          for(int r=0;r<maze.length;r++){
              maze[r]=infile.next().toCharArray();
    }
    for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)    //location of start location
            {
               startRow = r;
               startCol = c;
               
            }
         }
      }

    }
   
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         System.out.println('n');
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            markAll(startRow, startCol);
            break;
         case 2:
            int count = markAllAndCountRecursions(startRow, startCol);
            System.out.println("Number of recursions = " + count);
            break;
         case 3:
         case 4: 
            if( markTheCorrectPath(startRow, startCol) )
               System.out.println("Path found!"); 
            else           //use mazeNoPath
               System.out.println("No path exists."); 
            break;
         case 5:
            if( markCorrectPathAndCountSteps(startRow, startCol, 0) )
               System.out.println("Path found!"); 
            else           //use mazeNoPath
               System.out.println("No path exists."); 
            break;
         case 6: 
            if( markCorrectPathAndListSteps(startRow, startCol, "") )
               System.out.println("Path found!"); 
            else        //use mazeNoPath 
               System.out.println("No path exists.");
            break;
         default:
            System.out.println("File not found");  
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
       //System.out.println(r);
       //System.out.println(c);
        if(r>=0&&r<maze.length&&c>=0&&c<maze[0].length){
            if(maze[r][c]==START){
                markAll(r,c-1);
                markAll(r+1,c);
                markAll(r,c+1);
                markAll(r-1,c);
            }
            if(maze[r][c]==DOT){
            maze[r][c]='*';
            markAll(r,c-1);
            markAll(r+1,c);
            markAll(r,c+1);
            markAll(r-1,c);
            }
        }
   }

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
       if(r>=0&&r<maze.length&&c>=0&&c<maze[0].length){
        if(maze[r][c]==START)
                return(1+markAllAndCountRecursions(r,c-1)+markAllAndCountRecursions(r+1,c)+markAllAndCountRecursions(r,c+1)+markAllAndCountRecursions(r-1,c));
        if(maze[r][c]=='.'){
            maze[r][c]='*';
                return(1+markAllAndCountRecursions(r,c-1)+markAllAndCountRecursions(r+1,c)+markAllAndCountRecursions(r,c+1)+markAllAndCountRecursions(r-1,c));
        }
        if(maze[r][c]=='E')
            return 1;
       }
       return 1;
   }


/* 
 * From handout, #3 and #4
 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
 *  Recur until you find E, then mark the path, return true.
 *  If no path is found, return false.
 */	
   public boolean markTheCorrectPath(int r, int c)
   {
      if(r<maze.length&&c<maze[0].length&&r>=0&&c>=0){
          if(maze[r][c]=='E'){
          return true;
          }
          else if(maze[r][c]==START)
            return(markTheCorrectPath(r,c-1)||markTheCorrectPath(r+1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r-1,c));
          else if(maze[r][c]=='.'){
              maze[r][c]='*';
            return(markTheCorrectPath(r,c-1)||markTheCorrectPath(r+1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r-1,c));
          }
          
      }
        return false;
    }
   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, return false.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if(r<maze.length&&c<maze[0].length&&r>=0&&c>=0){
          if(maze[r][c]=='E'){
          return true;
          }
          else if(maze[r][c]==START)
            return(markCorrectPathAndCountSteps(r,c-1,count+1)||markCorrectPathAndCountSteps(r+1,c,count+1)||markCorrectPathAndCountSteps(r,c+1,count+1)||markCorrectPathAndCountSteps(r-1,c,count+1));
          else if(maze[r][c]=='.'){
              maze[r][c]='*';
            return(markCorrectPathAndCountSteps(r,c-1,count+1)||markCorrectPathAndCountSteps(r+1,c,count+1)||markCorrectPathAndCountSteps(r,c+1,count+1)||markCorrectPathAndCountSteps(r-1,c,count+1));
          }
          
      }
        return false;

   }
   
 /*   #6.	Solve the maze, mark the path, list the (r,c) steps.
    */
   public boolean markCorrectPathAndListSteps(int r, int c, String steps)
   {
       String rc="("+r+","+c+")";
       if(r<maze.length&&c<maze[0].length&&r>=0&&c>=0){
          if(maze[r][c]=='E'){
          return true;
          }
          else if(maze[r][c]==START)
            return(markCorrectPathAndListSteps(r,c-1,steps+rc)||markCorrectPathAndListSteps(r+1,c,steps+rc)||markCorrectPathAndListSteps(r,c+1,steps+rc)||markCorrectPathAndListSteps(r-1,c,steps+rc));
          else if(maze[r][c]=='.'){
              maze[r][c]='*';
            return(markCorrectPathAndListSteps(r,c-1,steps+rc)||markCorrectPathAndListSteps(r+1,c,steps+rc)||markCorrectPathAndListSteps(r,c+1,steps+rc)||markCorrectPathAndListSteps(r-1,c,steps+rc));
          }
          
      }
        return false;
   }
}

   /*****************************************
 
      ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 3
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 5
 Number of steps = 14
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, return false.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, return false.
 6: Mark only the correct path and list the steps.
 	If no path exists, return false.
 Please make a selection: 6
 (5,0) (5,1) (4,1) (3,1) (3,2) (3,3) (4,3) (5,3) (6,3) (6,4) (6,5) (6,6) (5,6) (4,6) (4,7)
 Path found!
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 **************************************/






