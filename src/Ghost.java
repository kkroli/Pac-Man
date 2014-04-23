import java.awt.*;

public class Ghost {
	
	private int x;
	private int y;
	private String name;
	public int escape;
	public boolean dead;
	public boolean fright;
	public String last;
	
	public Ghost (int xx, int yy, String n) {
		x = xx;
		y = yy;
		name = n;
		escape = 0;
		dead = false;
		fright = false;
		last = null;
	}

	public void moveRight(){
		x ++;
	}
	public void moveLeft(){
		x--;
	}
	
	public void moveUp(){
		y--;
	}
	public void moveDown(){
		y++;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setX(int xx){
		x = xx;
	}
	public void setY(int yy){
		y = yy;
	}
	public String getName(){
		return name;
	}
}
