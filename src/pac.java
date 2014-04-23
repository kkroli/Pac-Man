import java.io.FileNotFoundException;


public class pac {

	private int x;
	private int y;
	public int[][] map;
	public double dxindex ;
	public int xindex ;
	public double dyindex ;
	public int yindex ;
	public int xmid;
	public int ymid;
	public int xbot;
	public int ybot;
	public int xright;
	public int yright;
	public int tele = 0;
	public String dir = null;
	public Logic log = new Logic();

	public pac (int xx, int yy) throws FileNotFoundException {
		x = xx;
		y = yy;
		map = frame.getMap("txt/pacmap.txt");
	}

	public void update(){
		dxindex = (y-28.0)/ 28.0;
		xindex = (int) dxindex;
		dyindex =(x / 28.0);
		yindex = (int) dyindex;
		xmid = (xindex+1)*28;
		ymid = yindex*28;
		xbot = (int) ((y-2.0)/28.0);
		ybot = (int) ((x+26.0)/28.0);
		if ((yindex < 0 || yindex > 18)&& xindex == 10){
			if (tele == 0) teleport();
			else tele = 1;
		}
	}
	
	public void teleport(){
		if (x <= 0 )x = 19*28;
		else x = 0;
		y = 11*28+1;
		tele = 1;
		update();
	}
	
	public boolean moveRight(){
		update();
		tele = 0;
		try{
		if(map[xindex][yindex+1] != 0 && map[xbot][ybot+1] != 0){
			x += 2;
			return true;
		}
		else if(yindex != ybot){
			x += 2;
			return true;
		}}catch (ArrayIndexOutOfBoundsException e){
			x += 2;
			return true;}
		return false;
	}
	public boolean moveLeft(){
		update();
		tele = 0;
		try {
		if(map[xindex][yindex-1] != 0 && map[xbot][ybot-1] != 0){
			x -= 2;
			return true;
		}
		else if (yindex != ybot) {
			x -= 2;
			return true;
		}}catch (ArrayIndexOutOfBoundsException e){
			x -= 2;
			return true;
		}
		return false;
	}
	
	public boolean moveUp(){
		update();
		tele = 0;
		if (yindex < 0 || yindex > 18) return false;
		if (map[xindex-1][yindex] != 0 && map[xbot-1][ybot] != 0){
			y -= 2;
			return true;
		}
		else if (xindex != xbot){
			y -= 2;
			return true;
		}
		return false;
	}
	public boolean moveDown(){
		update();
		tele = 0;
		if (yindex < 0 || yindex > 18) return false;
		if (map[xindex+1][yindex] != 0 && map[xbot+1][ybot] != 0){
			y += 2;
			return true;
		}
		else if (xindex != xbot){
			y += 2;
			return true;
		}
		return false;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXindex(){
		return xindex;
	}
	public int getYindex(){
		return yindex;
	}
	public boolean eat(){
		try{
		if (map[xindex][yindex] == 1) return true;
		else return false;
		} catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	public boolean energize(){
		try{
			if (map[xindex][yindex] == 2) return true;
			else return false;
		} catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	public void adjustUp() {y -= 2;}
	public void adjustDown() {y += 2;}
	public void adjustLeft() {x -= 2;}
	public void adjustRight() {x += 2;}
	
	
}
