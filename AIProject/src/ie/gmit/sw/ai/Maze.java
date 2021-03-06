package ie.gmit.sw.ai;

import java.util.Random;

import ie.gmit.sw.ai.mazeAlgos.maze.MazeGenerator;
import ie.gmit.sw.ai.mazeAlgos.maze.MazeGeneratorFactory;
import ie.gmit.sw.ai.mazeAlgos.maze.MazeView;
import ie.gmit.sw.ai.mazeAlgos.maze.Node;
import ie.gmit.sw.ai.mazeAlgos.traversers.AStarTraversator;
import ie.gmit.sw.ai.mazeAlgos.traversers.Traversator;

public class Maze {
	private Node[][] node;
	private Node goal;
	private char[][] maze;
	private MazeView mv;
	private Sprite[] sprites;
	public Maze(int dimension, Sprite[] sprites){
		maze = new char[dimension][dimension];
		this.sprites = sprites;
		init();
		buildMaze();
		setGoalNode();
		
		
		int featureNumber = (int)((dimension * dimension) * 0.01);
		addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
		addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
		addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
		addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a hedge
		
		featureNumber = (int)((dimension * dimension) * 0.01);
		addSpider('\u0036', '0', featureNumber); //6 is a Black Spider, 0 is a hedge
		addSpider('\u0037', '0', featureNumber); //7 is a Blue Spider, 0 is a hedge
		addSpider('\u0038', '0', featureNumber); //8 is a Brown Spider, 0 is a hedge
		addSpider('\u0039', '0', featureNumber); //9 is a Green Spider, 0 is a hedge
		addSpider('\u003A', '0', featureNumber); //: is a Grey Spider, 0 is a hedge
		addSpider('\u003B', '0', featureNumber); //; is a Orange Spider, 0 is a hedge
		addSpider('\u003C', '0', featureNumber); //< is a Red Spider, 0 is a hedge
		addSpider('\u003D', '0', featureNumber); //= is a Yellow Spider, 0 is a hedge
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = '0'; //Index 0 is a hedge...
			}
		}
	}
	
	private void addFeature(char feature, char replace, int number){
		int counter = 0;
		while (counter < feature){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col] == replace){
				maze[row][col] = feature;
				counter++;
			}
		}
	}
	
	private void addSpider(char feature, char replace, int number){
		int counter = 0;
		while (counter < feature){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col] == replace){
				maze[row][col] = feature;
				
				switch(feature)
				{
					case('\u0036'):
						for(Sprite s : sprites){
							if(s.getName().contains("Black Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u0037'):
						for(Sprite s : sprites){
							if(s.getName().contains("Blue Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u0038'):
						for(Sprite s : sprites){
							if(s.getName().contains("Brown Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u0039'):
						for(Sprite s : sprites){
							if(s.getName().contains("Green Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u003A'):
						for(Sprite s : sprites){
							if(s.getName().contains("Grey Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u003B'):
						for(Sprite s : sprites){
							if(s.getName().contains("Orange Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u003C'):
						for(Sprite s : sprites){
							if(s.getName().contains("Red Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
					case('\u003D'):
						for(Sprite s : sprites){
							if(s.getName().contains("Yellow Spider")){
								s.setCurrentCol(col);
								s.setCurrentRow(row);
							}
						}
						break;
				}
				counter++;
			}
		}
	}
	
	private void buildMaze(){ 
		for (int row = 1; row < maze.length - 1; row++){
			for (int col = 1; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num > 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1] = '\u0020'; //\u0020 = 0x20 = 32 (base 10) = SPACE
				}else{
					if (row + 1 < maze.length - 1)maze[row + 1][col] = '\u0020';
				}
			}
		}		
	}
	
	public void setGoalNode(){
		Random generator = new Random();
		int randRow = generator.nextInt(maze.length);
		int randCol = generator.nextInt(maze[0].length);
		
		//goal = node[randRow][randCol];
		maze[randRow][randCol] = '\u003E'; //added this
	}
	
	public Node getGoalNode(){
		return goal;
	}
	
	
	public char[][] getMaze(){
		return this.maze;
	}
	
	public char get(int row, int col){
		return this.maze[row][col];
	}
	
	
	public void set(int row, int col, char c){
		this.maze[row][col] = c;
	}
	
	public int size(){
		return this.maze.length;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}