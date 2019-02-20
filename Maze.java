import java.util.*;

public class Maze{
	// Returns true if it is possible to solve the maze,
	// starting in the top-left and ending in the bottom-right.
	public static boolean winnable(int maze[][]) {
		boolean marked[][] = new boolean[maze.length][maze[0].length];
		return winnable(maze, 0, 0, marked); 
	}
	// Returns true if it is possible to solve the maze,
	// starting in the location maze[startRow][startCol] and ending in the bottom-right.
	// If the maze is winnable, this method modifies the maze array by filling in the winning path with 2s.
	// Otherwise, the maze is not modified.
	public static boolean winnable(int maze[][], int startRow, int startCol, boolean[][] marked) {
		// If you already won, fill in the entry with 2 and return true.
		// Mark the current location as visited.
		// Call winnable recursively on each adjacent location that hasn't been visited yet.
		// (do not visit invalid locations, i.e. locations that contain walls or locations outside the maze)
		// If you can win from any adjacent location, fill in the entry with 2 and return true.
		// Otherwise return false.
		int numRows = maze.length, numCols = maze[0].length;
		marked[startRow][startCol] = true; 
		boolean rightPath = startCol+1 < numCols && maze[startRow][startCol+1]!=1 && !marked[startRow][startCol+1]; 
		boolean leftPath = startCol-1 >= 0 && maze[startRow][startCol-1]!=1 && !marked[startRow][startCol-1]; 
		boolean downPath = startRow+1 < numRows && maze[startRow+1][startCol]!=1 && !marked[startRow+1][startCol]; 
		boolean upPath = startRow-1 >= 0 && maze[startRow-1][startCol]!=1 && !marked[startRow-1][startCol]; 
		boolean won = (startRow==(numRows-1) && startCol==(numCols-1)); 

		if (won){  //Base condition, you won so add 2
			maze[startRow][startCol] = 2; 					
			return won;
		}

		//Checks to see if there is a down Path
		if (downPath) {
			if (winnable(maze, startRow+1, startCol, marked)) {
				maze[startRow][startCol] = 2; //if there is a path then add 2
				return downPath;
			}
		}
		//Checks to see if there is an up Path
		if (upPath) {
			if (winnable(maze, startRow-1, startCol, marked)) { 
				maze[startRow][startCol] = 2;
				return upPath;
			}
		}
		//Checks to see if there is a Path to the right
		if (rightPath) {
			if(winnable(maze, startRow, startCol+1, marked)){
				maze[startRow][startCol] = 2;
				return rightPath;
			}
		}

		//Checks to see if there is a left Path
		if(leftPath){
			if(winnable(maze, startRow, startCol-1, marked)){
				maze[startRow][startCol] = 2;
				return leftPath;
			}
		}

		return false; 
	}

	public static void print(int maze[][]) {
		for (int[] row : maze)
			System.out.println(Arrays.toString(row));
	}

	public static void main(String[] args) {
		int[][] maze1 = {{0, 0, 0, 1, 1},
				{1, 1, 0, 1, 1},
				{0, 0, 0, 1, 1},
				{0, 1, 1, 1, 1},
				{0, 0, 0, 0, 0}};

		int[][] maze2 = {{0, 0, 0, 0, 1},
				{0, 1, 0, 1, 1},
				{0, 1, 1, 0, 0},
				{0, 0, 0, 1, 0},
				{1, 0, 1, 0, 0}};

		int[][] maze3 = {{0, 0, 0, 0, 1},
				{0, 1, 1, 0, 1},
				{0, 1, 1, 0, 1},
				{0, 0, 1, 1, 1},
				{1, 0, 0, 0, 0}};

		int[][] maze4 = {{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0}};

		int[][] maze5 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

		// For each maze, call the winnable method, then print the maze.
		// Winnable mazes should have their winning paths filled in with 2s.

		System.out.println("maze1: " + winnable(maze1)); // true
		print(maze1);

		System.out.println("\nmaze2: " + winnable(maze2)); // false
		print(maze2);

		System.out.println("\nmaze3: " + winnable(maze3)); // true
		print(maze3);

		System.out.println("\nmaze4: " + winnable(maze4)); // false
		print(maze4);

		System.out.println("\nmaze5: " + winnable(maze5)); // true
		print(maze5);
	}
}

