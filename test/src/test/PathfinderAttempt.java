package test;

import java.io.*;
import java.util.*;

public class PathfinderAttempt {

	static String[][] maze;
	static boolean[][] wasHere;
	static int startX, startY;
	static int endX, endY;
	static int rows, cols;
	
	public PathfinderAttempt() throws IOException{
		Scanner input = new Scanner(new File("maze.txt"));
		
		rows = input.nextInt();
		cols = input.nextInt();
		maze = mazeMaker(rows, cols); //creates String matrix of maze
		wasHere = new boolean[rows][cols];
		for(int i = 0; i < rows; i++){ //sets default values as false, unnecessary
			for(int j = 0; j < cols; j++){
				wasHere[i][j] = false;
			}
		}
		
		input.close();
		
		String[] start = findChar(maze, "s").split(",");
		startX = Integer.parseInt(start[0]);
		startY = Integer.parseInt(start[1]);
		
		String[] end = findChar(maze, "e").split(",");
		endX = Integer.parseInt(end[0]);
		endY = Integer.parseInt(end[1]);
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		PathfinderAttempt p = new PathfinderAttempt();
		
		output(maze); //Outputs array/maze to String
		System.out.print(startX + " "  + startY + " " + endX + " " + endY + " ");
		boolean b = Solve(startX, startY);
		
		System.out.print(b);
		
	}
	
	public static String[][] mazeMaker(int x, int y) throws IOException{
		Scanner mazereader = new Scanner(new File("maze.txt"));
		mazereader.nextLine();
		String[][] maze = new String[x][y];
		String line;
		for(int i = 0; i < y; i++){
			line = mazereader.nextLine();
			for(int j = 0; j < x; j++){
				maze[i][j] = line.substring(j, j+1);
			}
		}
		return maze;
	}
	
	public static void output(String[][] arr){
		for(int i = 0; i < arr[0].length; i++){
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	public static String findChar(String[][] arr, String s){
		for(int r = 0; r < arr.length; r++){
			for(int c = 0; c < arr[0].length; c++){
				if(arr[r][c].equals(s)){
					return r + "," + c;
				}
			}
		}
		return null;
	}
	
	public static boolean Solve(int x, int y){
		if(x == endX && y == endY) return false;
		if(maze[x][y].equals("#") || wasHere[x][y]) return false;
		
		wasHere[x][y] = true;
		
		if(x!=0){ //left edge
			if(Solve(x-1,y)){
				return true;
			}
		}
		if(x!=cols-1){ //right edge
			if(Solve(x+1,y)){
				return true;
			}
		}
		
		if(y!=0){ //top edge
			if(Solve(x,y-1)){
				return true;
			}
		}
		
		if(y!=rows-1){ //bottom edge
			if(Solve(x,y+1)){
				return true;
			}
		}
		
		return false;
		
	}
	
}