package ie.gmit.sw.ai;

import javax.imageio.*;
import java.awt.image.*;

public class Sprite {
	private String name; //The name of this sprite
	private BufferedImage[] frames; //The set of image frames to animate
 	private int index = 0; //Initial starting index in array
 	
 	private int currentRow;
 	private int currentCol;
 	
	public Sprite(String name, String... images) throws Exception{
		this.name = name;
		this.index = 0; //Initialise the starting index to zero
		this.frames = new BufferedImage[images.length]; //Initialise the image frames
		
		for (int i = 0; i < images.length; i++){
			frames[i] = ImageIO.read(new java.io.File(images[i])); //Read in each image as a BufferedImage
		}
	}
	
	public Sprite(String name, int startingRow, int startingCol, String... images) throws Exception {
		this(name, images);
		currentRow = startingRow;
		currentCol = startingCol;
	}
	
	public BufferedImage getNext(){ //Returns the next image frame
		int idx = index;
		if (index < frames.length - 1){
			index++;
		}else{
			index = 0; //Circle back to the start of the array
		}
		return frames[idx]; 
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getCurrentRow(){
		return currentRow;
	}
	
	public int getCurrentCol(){
		return currentCol;
	}
	
	public void setCurrentRow(int row){
		currentRow = row;
	}
	
	public void setCurrentCol(int col){
		currentCol = col;
	}
}
