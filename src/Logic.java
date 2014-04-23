import java.io.FileNotFoundException;


public class Logic {
	
	public int[][] map;
	String left = "left", right = "right", up = "up", down = "down";
	public String bld, pld, ild, cld;
	public Logic() throws FileNotFoundException{
		map = frame.getMap("txt/pacmap.txt");
	}
	
	public String move(Ghost b, String dir, int desx, int desy, String prevDir){
		double dxindex = ((b.getY()-28.0)/ 28.0);
		int xindex = (int) dxindex;
		double dyindex =(b.getX() / 28.0);
		int yindex = (int) dyindex;
		int xmid = (xindex+1)*28;
		int ymid = yindex*28;
		
		if (prevDir != dir && prevDir != null){
			if (!atMid(b,xmid,ymid)){
				return prevDir;
			}
		}
		
		if (dir.equals(left)){
			if (yindex-1 < 0)return right;
			if (map[xindex-1][yindex] == 0 && map[xindex+1][yindex] == 0 && map[xindex][yindex-1] !=0) return left;
			if (map[xindex][yindex-1] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex-1][yindex] == 0 && map[xindex+1][yindex] != 0 && desy > b.getY()) {
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
				else if(map[xindex+1][yindex] == 0 && map[xindex-1][yindex] != 0 && desx < b.getY()) return up;
				else if (map[xindex-1][yindex] != 0 && map[xindex+1][yindex] != 0){
					if (desy > b.getY()){
						if (map[xindex+1][yindex] == 4 && !b.dead) return right;
						else return down;
					}else return up;
				}
			} else if (atMid(b, xmid, ymid) && ((desy + 10 > b.getY() && desy - 10 > b.getY()) || (desy+10 < b.getY() && desy-10 < b.getY()))){
				if (desy > b.getY() && map[xindex+1][yindex] != 0){
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
					else if (desy < b.getY() && map[xindex-1][yindex] != 0) return up;}
			if(map[xindex][yindex-1] != 0 || !atMid(b, xmid, ymid))	return left;
			else if (map[xindex][yindex+1] != 0) return right;
			else return left;
		}
			
		else if (dir.equals(right)){
			if (yindex+1 > 18) return left;
			if (map[xindex-1][yindex] == 0 && map[xindex+1][yindex] == 0 && map[xindex][yindex+1] != 0) return right;
			if (map[xindex][yindex+1] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex-1][yindex] == 0 && map[xindex+1][yindex] != 0) {
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
					
				}
				else if(map[xindex+1][yindex] == 0 && map[xindex-1][yindex] != 0) return up;
				else if(map[xindex-1][yindex] != 0 && map[xindex+1][yindex] != 0) {
					if (desy > b.getY()){
						if (map[xindex+1][yindex] == 4 && !b.dead) return right;
						else return down;
						
					}else return up;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desy + 10 > b.getY() && desy - 10 > b.getY()) || (desy+10 < b.getY() && desy-10 < b.getY()))){
				if (desy > b.getY() && map[xindex+1][yindex] != 0){
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
					
				}
					else if (desy < b.getY() && map[xindex-1][yindex] != 0) return up;}
			if(map[xindex][yindex+1] != 0 || !atMid(b, xmid, ymid))	return right;
			else if (map[xindex][yindex-1] != 0) return left;
			else return right;
			}
		else if (dir.equals(up)){
			try{
				if (map[xindex][yindex-1] == 0 && map[xindex][yindex+1] == 0 && map[xindex-1][yindex] != 0) return up;
			} catch (ArrayIndexOutOfBoundsException e){ return down;}
			if (map[xindex-1][yindex] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex][yindex-1] == 0 && map[xindex][yindex+1] != 0) return right;
				else if(map[xindex][yindex+1] == 0 && map[xindex][yindex-1] != 0) return left;
				else if (map[xindex][yindex-1] != 0 && map[xindex][yindex+1] != 0) {
					if (desx > b.getX()){
						return right;
					}else return left;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desx + 10 > b.getX() && desx - 10 > b.getX()) || (desx+10 < b.getX() && desx-10 < b.getX()))){
				if (desx > b.getX() && map[xindex][yindex+1] != 0)return right;
					else if (desx < b.getX() && map[xindex][yindex-1] != 0) return left;}
			if(map[xindex-1][yindex] != 0 || !atMid(b, xmid, ymid))	return up;
			else if (map[xindex+1][yindex] != 0){
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			else return up;
			}
		else /*if (dir.equals(down))*/{
			try{
				if (map[xindex][yindex-1] == 0 && map[xindex][yindex+1] == 0 && map[xindex+1][yindex] != 0) {
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
			} catch(ArrayIndexOutOfBoundsException e) {return up;}
			if (map[xindex+1][yindex] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex][yindex-1] == 0 && map[xindex][yindex+1] != 0) return right;
				else if(map[xindex][yindex+1] == 0 && map[xindex][yindex-1] != 0) return left;
				else if (map[xindex][yindex-1] != 0 && map[xindex][yindex+1] != 0) {
					if (desx > b.getX()){
						return right;
					}else return left;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desx + 10 > b.getX() && desx - 10 > b.getX()) || (desx+10 < b.getX() && desx-10 < b.getX()))){
				if (desx > b.getX() && map[xindex][yindex+1] != 0)return right;
					else if (desx < b.getX() && map[xindex][yindex-1] != 0) return left;}
			if(map[xindex+1][yindex] != 0 || !atMid(b, xmid, ymid))	{
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			else if (map[xindex-1][yindex] != 0) return up;
			else {
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			}
	}
	
	public String moveAway(Ghost b, String dir, int desx, int desy, String prevDir){
		double dxindex = ((b.getY()-28.0)/ 28.0);
		
		int xindex = (int) dxindex;
		double dyindex =(b.getX() / 28.0);
		int yindex = (int) dyindex;
		int xmid = (xindex+1)*28;
		int ymid = yindex*28;
		
		if (prevDir != dir && prevDir != null){
			if (!atMid(b,xmid,ymid)){
				return prevDir;
			}
		}
		
		if (dir.equals(left)){
			if (yindex-1 < 0)return right;
			if (map[xindex-1][yindex] == 0 && map[xindex+1][yindex] == 0 && map[xindex][yindex-1] !=0) return left;
			if (map[xindex][yindex-1] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex-1][yindex] == 0 && map[xindex+1][yindex] != 0 && desy < b.getY()) {
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
				else if(map[xindex+1][yindex] == 0 && map[xindex-1][yindex] != 0 && desx > b.getY()) return up;
				else if (map[xindex-1][yindex] != 0 && map[xindex+1][yindex] != 0){
					if (desy < b.getY()){
						if (map[xindex+1][yindex] == 4 && !b.dead) return right;
						else return down;
					}else return up;
				}
			} else if (atMid(b, xmid, ymid) && ((desy + 10 < b.getY() && desy - 10 < b.getY()) || (desy+10 > b.getY() && desy-10 > b.getY()))){
				if (desy < b.getY() && map[xindex+1][yindex] != 0){
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
					else if (desy > b.getY() && map[xindex-1][yindex] != 0) return up;}
			if(map[xindex][yindex-1] != 0 || !atMid(b, xmid, ymid))	return left;
			else return right;
		}
			
		else if (dir.equals(right)){
			if (yindex+1 > 18) return left;
			if (map[xindex-1][yindex] == 0 && map[xindex+1][yindex] == 0 && map[xindex][yindex+1] != 0) return right;
			if (map[xindex][yindex+1] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex-1][yindex] == 0 && map[xindex+1][yindex] != 0) {
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
				else if(map[xindex+1][yindex] == 0 && map[xindex-1][yindex] != 0) return up;
				else if(map[xindex-1][yindex] != 0 && map[xindex+1][yindex] != 0) {
					if (desy < b.getY()){
						if (map[xindex+1][yindex] == 4 && !b.dead) return right;
						else return down;
					}else return up;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desy + 10 < b.getY() && desy - 10 < b.getY()) || (desy+10 > b.getY() && desy-10 > b.getY()))){
				if (desy < b.getY() && map[xindex+1][yindex] != 0){
					if (map[xindex+1][yindex] == 4 && !b.dead) return right;
					else return down;
				}
					else if (desy > b.getY() && map[xindex-1][yindex] != 0) return up;}
			if(map[xindex][yindex+1] != 0 || !atMid(b, xmid, ymid))	return right;
			else if (map[xindex][yindex-1] != 0) return left;
			else return right;
			}
		else if (dir.equals(up)){
			if (map[xindex][yindex-1] == 0 && map[xindex][yindex+1] == 0 && map[xindex-1][yindex] != 0) return up;
			if (map[xindex-1][yindex] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex][yindex-1] == 0 && map[xindex][yindex+1] != 0) return right;
				else if(map[xindex][yindex+1] == 0 && map[xindex][yindex-1] != 0) return left;
				else if (map[xindex][yindex-1] != 0 && map[xindex][yindex+1] != 0) {
					if (desx < b.getX()){
						return right;
					}else return left;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desx + 10 > b.getX() && desx - 10 > b.getX()) || (desx+10 < b.getX() && desx-10 < b.getX()))){
				if (desx < b.getX() && map[xindex][yindex+1] != 0)return right;
					else if (desx > b.getX() && map[xindex][yindex-1] != 0) return left;}
			if(map[xindex-1][yindex] != 0 || !atMid(b, xmid, ymid))	return up;
			else if (map[xindex+1][yindex] != 0) {
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			else return up;
			}
		else /*if (dir.equals(down))*/{
			if (map[xindex][yindex-1] == 0 && map[xindex][yindex+1] == 0 && map[xindex+1][yindex] != 0) {
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			if (map[xindex+1][yindex] == 0 && atMid(b, xmid, ymid)){
				if(map[xindex][yindex-1] == 0 && map[xindex][yindex+1] != 0) return right;
				else if(map[xindex][yindex+1] == 0 && map[xindex][yindex-1] != 0) return left;
				else if (map[xindex][yindex-1] != 0 && map[xindex][yindex+1] != 0) {
					if (desx < b.getX()){
						return right;
					}else return left;
				}
			}
			else if (atMid(b, xmid, ymid) && ((desx + 10 > b.getX() && desx - 10 > b.getX()) || (desx+10 < b.getX() && desx-10 < b.getX()))){
				if (desx < b.getX() && map[xindex][yindex+1] != 0)return right;
					else if (desx > b.getX() && map[xindex][yindex-1] != 0) return left;}
			if(map[xindex+1][yindex] != 0 || !atMid(b, xmid, ymid))	{
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			else if (map[xindex-1][yindex] != 0) return up;
			else {
				if (map[xindex+1][yindex] == 4 && !b.dead) return right;
				else return down;
			}
			}
	}
	
	public String scatter(Ghost b, String lastdir, int x, int y){
		
		String next, prev = b.last;
		if (lastdir == null){
			if (b.getX() > x) lastdir = left;
			else if (b.getX() < x) lastdir = right;
			else if (b.getY() > y) lastdir = up;
			else if (b.getY() < y) lastdir = down;
			else {
				b.setX(y);
				b.setY(x);
				if (b.getX() > x) lastdir = left;
				else if (b.getX() < x) lastdir = right;
				else if (b.getY() > y) lastdir = up;
				else if (b.getY() < y) lastdir = down;
			}
		}
		if (prev == null) prev = lastdir;
		if (!b.fright){
		if (lastdir.equals(left)){
			next = move(b, left, x, y, prev);
		}else if( lastdir.equals(right)){
			next = move(b, right,x ,y, prev);
		}
		else if (lastdir.equals(up)){
			next = move(b, up, x, y, prev);
		}else{
			next = move(b, down, x, y, prev);
		}}
		else {
			if (lastdir.equals(left)){
				next = moveAway(b, left, x, y, prev);
			}else if( lastdir.equals(right)){
				next = moveAway(b, right,x ,y, prev);
			}
			else if (lastdir.equals(up)){
				next = moveAway(b, up, x, y, prev);
			}else{
				next = moveAway(b, down, x, y, prev);
			}
		}
		
		if (b.getX() == x && b.getY() == y && !b.fright){
			return "chase";
		}
		b.last = next;
		return next;
	}
	
	public String chase(Ghost b, pac p, String lastdir, int blinkyX, int blinkyY){
		
		int desx = p.getX();
		int desy = p.getY();
		if (b.getName().equals("pinky")){
			try{
				if (p.dir.equals(up)){
					desx -= (28*4);
					desy -= (28*4);
				}
				else if (p.dir.equals(down)) desy += (28*4);
				else if (p.dir.equals(right)) desx += (28*4);
				else desx -= (28*4);
			} catch (NullPointerException e) { desx -= (28*4);}
		}
		if (b.getName().equals("inky")){
			int offsetx = desx;
			int offsety = desy;
			try{
				if (p.dir.equals(up)){
					offsetx = (offsetx - (28*2) - blinkyX)*2;
					offsety = (offsety - (28*2) - blinkyY)*2;
					desx = blinkyX + offsetx;
					desy = blinkyY + offsety;
				}
				else if (p.dir.equals(down)){
					offsetx = (offsetx - blinkyX)*2;
					offsety = (offsety + (28*2) - blinkyY)*2;
					desx = blinkyX + offsetx;
					desy = blinkyY + offsety;
				}
				else if (p.dir.equals(right)){
					offsetx = (offsetx + (28*2) - blinkyX)*2;
					offsety = (offsety - blinkyY)*2;
					desx = blinkyX + offsetx;
					desy = blinkyY + offsety;
				}
				else {
					offsetx = (offsetx - (28*2) - blinkyX)*2;
					offsety = (offsety - blinkyY)*2;
					desx = blinkyX + offsetx;
					desy = blinkyY + offsety;
				}
			}catch (NullPointerException e){
				offsetx = (offsetx - (28*2) - blinkyX)*2;
				offsety = (offsety - blinkyY)*2;
				desx = blinkyX + offsetx;
				desy = blinkyY + offsety;
			}
		}
		if (b.getName().equals("clyde")){
			int x2 = desx - b.getX();
			int y2 = desy - b.getY();
			double dis = Math.sqrt((x2 * x2)+(y2*y2));
			if (dis < (28.0 * 8.0)){
				desx = 476;
				desy = 616;
			}
		}
		
		String next, prev = b.last;
		if (lastdir == null){
			if (b.getX() > desx) lastdir = left;
			else if (b.getX() < desx) lastdir = right;
			else if (b.getY() > desy) lastdir = up;
			else if (b.getY() < desy) lastdir = down;
		}
		
		if (!b.fright){
			try{
				if (lastdir.equals(left)){
					next = move(b, left, desx, desy, prev);
				}else if( lastdir.equals(right)){
					next = move(b, right,desx ,desy, prev);
				}
				else if (lastdir.equals(up)){
					next = move(b, up, desx, desy, prev);
				}else{
					next = move(b, down, desx, desy, prev);
			}} catch (NullPointerException e){
				next = move(b, down, desx, desy, prev);
			}}
		else {
			if (lastdir.equals(left)){
				next = moveAway(b, left, desx, desy, prev);
			}else if( lastdir.equals(right)){
				next = moveAway(b, right,desx ,desy, prev);
			}
			else if (lastdir.equals(up)){
				next = moveAway(b, up, desx, desy, prev);
			}else{
				next = moveAway(b, down, desx, desy, prev);
			}
		}
		b.last = next;
		return next;
	}
	
	public boolean contact(pac p, Ghost b){
		double dxindex = ((b.getY()-28.0)/ 28.0);		
		int xindex = (int) dxindex;
		double dyindex =(b.getX() / 28.0);
		int yindex = (int) dyindex;
		
		if (p.getXindex() == xindex && p.getYindex() == yindex){
			return true;
		}
		return false;
	}
	

	public boolean atMid(pac b, int x, int y){
		if (b.getX() == y && b.getY() == x)return true;
		else return false;
	}
	
	public boolean atMid(Ghost b, int x, int y){
		if (b.getX() == y && b.getY() == x)return true;
		else return false;
	}
	
}
