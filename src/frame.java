import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.util.*;
import java.util.Timer;
import java.io.*;

public class frame extends JFrame implements Runnable, KeyListener{
	
	public Ghost blinky = new Ghost(8*28, 10*28, "blinky");
	public Ghost pinky = new Ghost(8*28, 12*28, "pinky");
	public Ghost inky = new Ghost(10*28, 10*28, "inky");
	public Ghost clyde = new Ghost(10*28, 12*28, "clyde");
	public Ghost blink_theat = new Ghost(-84, 480, "blin");
	public Ghost pink_theat = new Ghost(-115, 480, "pin");
	public Ghost ink_theat = new Ghost(-146, 480, "in");
	public Ghost clyde_theat = new Ghost(-177,480,"cly");
	public Ghost pacman_theat = new Ghost(-28, 480,"pac");
	
	public int flash = 0;
	public int jaw = 0;
	public Logic lol;
	public String dir = null;  //blinky's direction
	public String fdir = null; //blinky's direction before fright
	public String dirp = null; // pinky's direction
	public String fdirp = null;// pinky's direction before fright
	public String diri = null; // inky's direction
	public String fdiri = null;// inky's direction before fright
	public String dirc = null; //clyde's direction 
	public String fdirc = null;//clyde's direction before fright
	public BufferedImage blink;
	public BufferedImage blink_right;
	public BufferedImage blink_up;
	public BufferedImage blink_down;
	public BufferedImage pink;
	public BufferedImage pink_right;
	public BufferedImage pink_up;
	public BufferedImage pink_down;
	public BufferedImage ink;
	public BufferedImage ink_right;
	public BufferedImage ink_up;
	public BufferedImage ink_down;
	public BufferedImage clyd;
	public BufferedImage clyd_right;
	public BufferedImage clyd_up;
	public BufferedImage clyd_down;
	public BufferedImage pacma_left;
	public BufferedImage pacma_right;
	public BufferedImage pacma_up;
	public BufferedImage pacma_down;
	public BufferedImage vulny;
	public BufferedImage vulny_flash;
	public BufferedImage dead_left;
	public BufferedImage dead_right;
	public BufferedImage dead_up;
	public BufferedImage dead_down;
	public BufferedImage cherry;
	public BufferedImage strawberry;
	public BufferedImage peach;
	public BufferedImage apple;
	public BufferedImage grapes;
	public BufferedImage galaxian;
	public BufferedImage bell;
	public BufferedImage key;
	public pac pacman = new pac(9*28,18*28);
	public pac invisible = new pac(pacman.getX(), pacman.getY());
	public String direction = "left";
	public String buf = "left";
	public static int[][] map = new int[23][19];
	public long frighttime;
	public int level = 1;
	public int pebbles = 0;
	public long score = 0;
	public int lives = 4;
	public int ghostPoints = 0;
	public int mode = 0;
	public int mode2 = 0;
	public int mode3 = 0;
	public int mode4 = 0;
	public int ghostFlash = 0;
	public boolean menu = true;
	public int select = 0;
	public int startMenu = 0;
	public long readyTime;
	public int ready = 1;
	public int rdyTimeStart = 1;
	public long bonustime;
	public int bonus = 0;
	public int stop = 0;
	public long menutime;
	public boolean highscoremenu = false;
	public static int hssize = 0;
	public static String[] name = new String[10];
	public static String[] highscore = new String[10];
	public int submit = 0;
	public int letter = 0;
	public String subName = "";
	public int nameLength = 0;
	
	public void loadGhost() throws IOException{
		blink = ImageIO.read(new File("images/blinky.bmp"));
		blink_right = ImageIO.read(new File("images/blinky_right.bmp"));
		blink_up = ImageIO.read(new File("images/blinky_up.bmp"));
		blink_down = ImageIO.read(new File("images/blinky_down.bmp"));
		pink = ImageIO.read(new File("images/pinky.bmp"));
		pink_up = ImageIO.read(new File("images/pinky_up.bmp"));
		pink_right = ImageIO.read(new File("images/pinky_right.bmp"));
		pink_down = ImageIO.read(new File("images/pinky_down.bmp"));
		ink_right = ImageIO.read(new File("images/inky_right.bmp"));
		ink_up = ImageIO.read(new File("images/inky_up.bmp"));
		ink_down = ImageIO.read(new File("images/inky_down.bmp"));
		ink = ImageIO.read(new File("images/inky.bmp"));
		clyd = ImageIO.read(new File("images/clyde.bmp"));
		clyd_right = ImageIO.read(new File("images/clyde_right.bmp"));
		clyd_up = ImageIO.read(new File("images/clyde_up.bmp"));
		clyd_down = ImageIO.read(new File("images/clyde_down.bmp"));
		pacma_left = ImageIO.read(new File("images/pacman_left.bmp"));
		pacma_right = ImageIO.read(new File("images/pacman_right.bmp"));
		pacma_up = ImageIO.read(new File("images/pacman_up.bmp"));
		pacma_down = ImageIO.read(new File("images/pacman_down.bmp"));
		vulny = ImageIO.read(new File("images/vulny(flash).bmp"));
		vulny_flash = ImageIO.read(new File("images/vulny.bmp"));
		dead_left = ImageIO.read(new File("images/dead_left.bmp"));
		dead_right = ImageIO.read(new File("images/dead_right.bmp"));
		dead_up = ImageIO.read(new File("images/dead_up.bmp"));
		dead_down = ImageIO.read(new File("images/dead_down.bmp"));
		cherry = ImageIO.read(new File("images/cherry.bmp"));
		strawberry = ImageIO.read(new File("images/strawberry.bmp"));
		peach = ImageIO.read(new File("images/peach.bmp"));
		apple = ImageIO.read(new File("images/apple.bmp"));
		grapes = ImageIO.read(new File("images/grapes.bmp"));
		galaxian = ImageIO.read(new File("images/galaxian.bmp"));
		bell = ImageIO.read(new File("images/bell.bmp"));
		key = ImageIO.read(new File("images/key.bmp"));
	}
	
	public frame() throws FileNotFoundException{
		super("Pacman");
		setSize(532,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		 lol = new Logic();
	}
	
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		if (!menu && lives >= 0){
			if (code == KeyEvent.VK_UP) buf = "up";
			if (code == KeyEvent.VK_DOWN) buf = "down";
			if (code == KeyEvent.VK_LEFT) buf = "left";
			if (code == KeyEvent.VK_RIGHT) buf = "right";
			if (code == KeyEvent.VK_SPACE) startMenu++;
			if (code == KeyEvent.VK_X && startMenu % 2 == 1) {
				menu = true;
				startMenu = 0;
				score = 0;
				lives = 4;
				try {
					reset();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		} else if (!menu && lives < 0){
			if (hssize == 10 && score >= Integer.parseInt(highscore[hssize-1])){		
				if (code == KeyEvent.VK_UP) letter++;
				if (code == KeyEvent.VK_DOWN) letter--;
				if (code == KeyEvent.VK_ENTER) {
					if (nameLength < 3){
						if (letter % 26 == 0)subName = subName + "A";
						else if (letter % 26 == 1)subName = subName + "B";
						else if (letter % 26 == 2)subName = subName + "C";
						else if (letter % 26 == 3)subName = subName + "D";
						else if (letter % 26 == 4)subName = subName + "E";
						else if (letter % 26 == 5)subName = subName + "F";
						else if (letter % 26 == 6)subName = subName + "G";
						else if (letter % 26 == 7)subName = subName + "H";
						else if (letter % 26 == 8)subName = subName + "I";
						else if (letter % 26 == 9)subName = subName + "J";
						else if (letter % 26 == 10)subName = subName + "K";
						else if (letter % 26 == 11)subName = subName + "L";
						else if (letter % 26 == 12)subName = subName + "M";
						else if (letter % 26 == 13)subName = subName + "N";
						else if (letter % 26 == 14)subName = subName + "O";
						else if (letter % 26 == 15)subName = subName + "P";
						else if (letter % 26 == 16)subName = subName + "Q";
						else if (letter % 26 == 17)subName = subName + "R";
						else if (letter % 26 == 18)subName = subName + "S";
						else if (letter % 26 == 19)subName = subName + "T";
						else if (letter % 26 == 20)subName = subName + "U";
						else if (letter % 26 == 21)subName = subName + "V";
						else if (letter % 26 == 22)subName = subName + "W";
						else if (letter % 26 == 23)subName = subName + "X";
						else if (letter % 26 == 24)subName = subName + "Y";
						else subName = subName + "Z";
						nameLength++;	
					}	
				}
			}else {
				submit = 1;
			}
		}
		else if (highscoremenu){
			if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_X) highscoremenu = false;
		}
		else{
			if (code == KeyEvent.VK_UP) select--;
			if (code == KeyEvent.VK_DOWN) select++;
			if (code == KeyEvent.VK_ENTER && select % 3 == 0) menu = false;
			if (code == KeyEvent.VK_ENTER && select % 3 == 1) highscoremenu = true;
			if (code == KeyEvent.VK_ENTER && select % 3 == 2) System.exit(0);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public void reset() throws FileNotFoundException{
		map = getMap("txt/pacmap.txt");
		pacman = new pac(9*28,18*28);
		invisible = new pac(pacman.getX(), pacman.getY());
		blinky = new Ghost(8*28, 10*28, "blinky");
		pinky = new Ghost(8*28, 12*28, "pinky");
		inky = new Ghost(10*28, 10*28, "inky");
		clyde = new Ghost(10*28, 12*28, "clyde");
		direction = "left";
		buf = "left";
		flash = 0;
		jaw = 0;
		dir = null;  
		fdir = null; 
		dirp = null;
		fdirp = null;
		diri = null; 
		fdiri = null;
		dirc = null; 
		fdirc = null;
		mode = 0;
		mode2 = 0;
		mode3 = 0;
		mode4 = 0;
		ghostFlash = 0;
		pebbles = 0;
		ready = 1;
		rdyTimeStart = 1;
		bonus = 0;
	}
	
	public static int[][] getMap(String filename) throws FileNotFoundException{
		File f = new File(filename);
		
		Scanner sc = new Scanner(new FileReader(f));
		int i = 0;
		try{
			while(sc.hasNextLine()){
				int j = 0;
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				while( st.hasMoreTokens()){
					map[i][j] = Integer.parseInt(st.nextToken());
					j++;
				}
				i++;
			}
		}
		finally{
			sc.close();
		}
		return map;
	}
	
	public static void getHighScores(){
		File f = new File("txt/highscore.txt");
		Scanner sc = null;
		int i = 0;
		try{
			sc = new Scanner(new FileReader(f));
			while(sc.hasNextLine()){
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				while( st.hasMoreTokens()){
					name[i] = st.nextToken();
					highscore[i] = st.nextToken();
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			sc.close();
		}
		hssize = i;
	}
	
	public void start()
	{
		Thread th = new Thread(this);
		th.start();
	}
	
	public void paint(Graphics g){
		
		try {
			g.drawImage(getFrame(), 0, 0, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Image getFrame() throws IOException {
		BufferedImage img = new BufferedImage(532, 750, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = img.getGraphics();
		if (!menu){
		for (int i = 0; i < 23; i++){
			for (int j = 0; j < 19; j++){
				if (map[i][j] == 0){
					g.setColor(Color.blue);
					g.fillRect(j*28, (i+1)*28, 28, 28);
				}
				if (map[i][j] == 1){
					g.setColor(Color.white);
					g.fillOval(j*28+10, (i+1)*28 +11, 7, 7);
				}
				if (map[i][j] == 2){
					if(flash < 20){
					g.setColor(Color.pink);
					g.fillOval(j*28 + 7, (i+1)*28 +10,13,13);}   
					else {
						g.setColor(Color.white);
						g.fillOval(j*28 + 7, (i+1)*28 +10,13,13);
					}
				}
				if (map[i][j] == 4){
					g.setColor(Color.white);
					g.fillRect(j*28, (i+1)*28 + 10, 28, 2);
					
				}
			}
		}
		
		if(bonus == 1 && Math.abs(bonustime - System.currentTimeMillis())<= 9333){
			
			if (level == 1) {
				g.drawImage(cherry,252,392,this);
			} else if (level == 2){
				g.drawImage(strawberry,252,392,this);
			} else if (level == 3 || level == 4){
				g.drawImage(peach,252,392,this);
			} else if (level == 5 || level == 6){
				g.drawImage(apple,252,392,this);
			} else if (level == 7 || level == 8){
				g.drawImage(grapes,252,392,this);
			} else if (level == 9 || level == 10){
				g.drawImage(galaxian,252,392,this);
			} else if (level == 11 || level == 12){
				g.drawImage(bell,252,392,this);
			} else if (level >= 13){
				g.drawImage(key,252,392,this);
			}
		} else if (Math.abs(bonustime - System.currentTimeMillis()) > 9333){
			bonus = 0;
		}
		
		String scor = Long.toString(score);
		g.setColor(Color.white);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
		g.drawString(scor, 400, 695);
		int x = 10;
		for (int i = 0; i < lives; i++){
			g.drawImage(pacma_left, x, 675 , this);
			x += 28;
		}
		String lvl = Integer.toString(level);
		g.drawString(lvl, 280,695);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
		g.drawString("Level", 210,695);

		if(flash != 39) flash ++;
		else flash = 0;
		//Blinky's faces
		if (mode == 2){
			if (Math.abs(System.currentTimeMillis() - frighttime) < 4501) g.drawImage(vulny,blinky.getX(),blinky.getY(), this);
			else {
				if (ghostFlash % 10 >= 1 && ghostFlash % 10 <= 4) g.drawImage(vulny_flash,blinky.getX(),blinky.getY(), this);
				else g.drawImage(vulny,blinky.getX(),blinky.getY(), this);
			}
		}
		else if (mode == 5){
			try{
				if (dir.isEmpty() || dir.equals("left")) g.drawImage(dead_left, blinky.getX(), blinky.getY(), this);
				else if (dir.equals("right")) g.drawImage(dead_right, blinky.getX(), blinky.getY(), this);
				else if (dir.equals("up")) g.drawImage(dead_up, blinky.getX(), blinky.getY(), this);
				else if (dir.equals("down")) g.drawImage(dead_down, blinky.getX(), blinky.getY(), this);
			} catch (NullPointerException e){}
		}
		else{
			try{
			if (dir.equals("left")) g.drawImage(blink, blinky.getX(), blinky.getY(), this);
			else if (dir.equals("right")) g.drawImage(blink_right, blinky.getX(), blinky.getY(), this);
			else if (dir.equals("up")) g.drawImage(blink_up, blinky.getX(), blinky.getY(), this);
			else if (dir.equals("down")) g.drawImage(blink_down, blinky.getX(), blinky.getY(), this);
			} catch (NullPointerException e){
				g.drawImage(blink, blinky.getX(), blinky.getY(), this);
			}}
		//Pinky's faces
		if (mode2 == 2){
			if (Math.abs(System.currentTimeMillis() - frighttime) < 4501) g.drawImage(vulny, pinky.getX(), pinky.getY(), this);
			else {
				if (ghostFlash % 10 >= 1 && ghostFlash % 10 <= 4) g.drawImage(vulny_flash, pinky.getX(), pinky.getY(), this);
				else g.drawImage(vulny, pinky.getX(), pinky.getY(), this);
			}
		}
		else if (mode2 == 5){
			try{
				if (dirp.isEmpty() || dirp.equals("left")) g.drawImage(dead_left, pinky.getX(), pinky.getY(), this);
				else if (dirp.equals("right")) g.drawImage(dead_right, pinky.getX(), pinky.getY(), this);
				else if (dirp.equals("up")) g.drawImage(dead_up, pinky.getX(), pinky.getY(), this);
				else if (dirp.equals("down")) g.drawImage(dead_down, pinky.getX(), pinky.getY(), this);
			} catch (NullPointerException e){}
		}
		
		else{
			try{
			if (dirp.equals("left")) g.drawImage(pink, pinky.getX(), pinky.getY(), this);
			else if (dirp.equals("right")) g.drawImage(pink_right, pinky.getX(), pinky.getY(), this);
			else if (dirp.equals("up")) g.drawImage(pink_up, pinky.getX(), pinky.getY(), this);
			else if (dirp.equals("down")) g.drawImage(pink_down, pinky.getX(), pinky.getY(), this);
			}catch (NullPointerException e){
				g.drawImage(pink_right, pinky.getX(), pinky.getY(), this);
			}}
		//inky's faces
		if (mode3 == 2){
			if (Math.abs(System.currentTimeMillis() - frighttime) < 4501) g.drawImage(vulny, inky.getX(), inky.getY(), this);
			else {
				if (ghostFlash % 10 >= 1 && ghostFlash % 10 <= 4) g.drawImage(vulny_flash, inky.getX(), inky.getY(), this);
				else g.drawImage(vulny, inky.getX(), inky.getY(), this);
			}
		}
		else if (mode3 == 5){
			try{
				if (diri.isEmpty() || diri.equals("left")) g.drawImage(dead_left, inky.getX(), inky.getY(), this);
				else if (diri.equals("right")) g.drawImage(dead_right, inky.getX(), inky.getY(), this);
				else if (diri.equals("up")) g.drawImage(dead_up, inky.getX(), inky.getY(), this);
				else if (diri.equals("down")) g.drawImage(dead_down, inky.getX(), inky.getY(), this);
			} catch (NullPointerException e){}
		}
		
		else{
			try{
				if (diri.equals("left")) g.drawImage(ink, inky.getX(), inky.getY(), this);
				else if (diri.equals("right")) g.drawImage(ink_right, inky.getX(), inky.getY(), this);
				else if (diri.equals("up")) g.drawImage(ink_up, inky.getX(), inky.getY(), this);
				else if (diri.equals("down")) g.drawImage(ink_down, inky.getX(), inky.getY(), this);
			} catch (NullPointerException e){
				g.drawImage(ink_down, inky.getX(), inky.getY(), this);
			}
		}
		
		//clyde's faces
		if (mode4 == 2){
			if (Math.abs(System.currentTimeMillis() - frighttime) < 4501) g.drawImage(vulny, clyde.getX(), clyde.getY(), this);
			else {
				if (ghostFlash % 10 >= 1 && ghostFlash % 10 <= 4) g.drawImage(vulny_flash, clyde.getX(), clyde.getY(), this);
				else g.drawImage(vulny, clyde.getX(), clyde.getY(), this);
			}
		}
		else if (mode4 == 5){
			try{
				if (dirc.isEmpty() || dirc.equals("left")) g.drawImage(dead_left, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("right")) g.drawImage(dead_right, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("up")) g.drawImage(dead_up, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("down")) g.drawImage(dead_down, clyde.getX(), clyde.getY(), this);
			} catch (NullPointerException e){}
		}
		
		else{
			try{
				if (dirc.equals("left")) g.drawImage(clyd, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("right")) g.drawImage(clyd_right, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("up")) g.drawImage(clyd_up, clyde.getX(), clyde.getY(), this);
				else if (dirc.equals("down")) g.drawImage(clyd_down, clyde.getX(), clyde.getY(), this);
			} catch (NullPointerException e) {
				g.drawImage(clyd_down, clyde.getX(), clyde.getY(), this);
			}
		}

		//pacman's faces
		if (direction.equals("left")) {
			if (jaw < 4) g.drawImage(pacma_left, pacman.getX(), pacman.getY(), this);
			else if (jaw < 8) g.drawImage(ImageIO.read(new File("images/pacman_left_half.bmp")), pacman.getX(), pacman.getY(),this);
			else g.drawImage(ImageIO.read(new File("images/pacman_left_closed.bmp")), pacman.getX(), pacman.getY(),this);
		}
		else if (direction.equals("right")){
			if (jaw < 4)g.drawImage(pacma_right, pacman.getX(), pacman.getY(), this);
			else if (jaw < 8) g.drawImage(ImageIO.read(new File("images/pacman_right_half.bmp")), pacman.getX(), pacman.getY(),this);
			else g.drawImage(ImageIO.read(new File("images/pacman_right_closed.bmp")), pacman.getX(), pacman.getY(),this);
		}
		else if (direction.equals("up")) {
			if (jaw < 4) g.drawImage(pacma_up, pacman.getX(), pacman.getY(), this);
			else if (jaw < 8) g.drawImage(ImageIO.read(new File("images/pacman_up_half.bmp")), pacman.getX(), pacman.getY(),this);
			else g.drawImage(ImageIO.read(new File("images/pacman_up_closed.bmp")), pacman.getX(), pacman.getY(),this);
		}
		else if (direction.equals("down")){
			if (jaw < 4) g.drawImage(pacma_down, pacman.getX(), pacman.getY(), this);
			else if (jaw < 8) g.drawImage(ImageIO.read(new File("images/pacman_down_half.bmp")), pacman.getX(), pacman.getY(),this);
			else g.drawImage(ImageIO.read(new File("images/pacman_down_closed.bmp")), pacman.getX(), pacman.getY(),this);
		}
		if (jaw != 11 && startMenu % 2 == 0 && ready == 0 && lives >= 0) jaw++;
		else jaw = 0;
		
		if (ready == 1){
			if (rdyTimeStart == 1){
				readyTime = System.currentTimeMillis();
				rdyTimeStart = 0;
			}
			try{
				if (Math.abs(readyTime - System.currentTimeMillis()) < 1500){
					g.setColor(Color.red);
					g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 55));
					g.drawString("Ready", 195, 345);
				}
				if (Math.abs(readyTime - System.currentTimeMillis()) > 1500){
					g.setColor(Color.red);
					g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 55));
					g.drawString("GO!", 195, 345);
				}
				if (Math.abs(readyTime - System.currentTimeMillis()) > 2000){
					ready = 0;
				}
			} catch (NullPointerException e){}
		}
		
		if(startMenu % 2 == 1){
			g.setColor(Color.black);
			g.fillRect(140, 300, 250, 100);
			g.setColor(Color.blue);
			g.drawRect(140, 300, 250, 100);
			g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 45));
			g.drawString("PAUSE", 195, 345);
			g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 17));
			g.drawString("Press X to exit to main menu.", 150, 375);
		}
		if (submit == 0 && lives < 0){
			g.setColor(Color.black);
			g.fillRect(140, 300, 250, 100);
			g.setColor(Color.blue);
			g.drawRect(140, 300, 250, 100);
			g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
			g.drawString("Submit your name", 170, 320);
			g.setColor(Color.red);
			g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 45));
			for (int q = 0; q < nameLength; q++){
				g.drawString(Character.toString(subName.charAt(q)), 150 +(30*q), 375);
			}
			if (letter % 26 == 0)g.drawString("A", 150 + (30*nameLength), 375);
			else if (letter % 26 == 1)g.drawString("B", 150 + (30*nameLength), 375);
			else if (letter % 26 == 2)g.drawString("C", 150 + (30*nameLength), 375);
			else if (letter % 26 == 3)g.drawString("D", 150 + (30*nameLength), 375);
			else if (letter % 26 == 4)g.drawString("E", 150 + (30*nameLength), 375);
			else if (letter % 26 == 5)g.drawString("F", 150 + (30*nameLength), 375);
			else if (letter % 26 == 6)g.drawString("G", 150 + (30*nameLength), 375);
			else if (letter % 26 == 7)g.drawString("H", 150 + (30*nameLength), 375);
			else if (letter % 26 == 8)g.drawString("I", 150 + (30*nameLength), 375);
			else if (letter % 26 == 9)g.drawString("J", 150 + (30*nameLength), 375);
			else if (letter % 26 == 10)g.drawString("K", 150 + (30*nameLength), 375);
			else if (letter % 26 == 11)g.drawString("L", 150 + (30*nameLength), 375);
			else if (letter % 26 == 12)g.drawString("M", 150 + (30*nameLength), 375);
			else if (letter % 26 == 13)g.drawString("N", 150 + (30*nameLength), 375);
			else if (letter % 26 == 14)g.drawString("O", 150 + (30*nameLength), 375);
			else if (letter % 26 == 15)g.drawString("P", 150 + (30*nameLength), 375);
			else if (letter % 26 == 16)g.drawString("Q", 150 + (30*nameLength), 375);
			else if (letter % 26 == 17)g.drawString("R", 150 + (30*nameLength), 375);
			else if (letter % 26 == 18)g.drawString("S", 150 + (30*nameLength), 375);
			else if (letter % 26 == 19)g.drawString("T", 150 + (30*nameLength), 375);
			else if (letter % 26 == 20)g.drawString("U", 150 + (30*nameLength), 375);
			else if (letter % 26 == 21)g.drawString("V", 150 + (30*nameLength), 375);
			else if (letter % 26 == 22)g.drawString("W", 150 + (30*nameLength), 375);
			else if (letter % 26 == 23)g.drawString("X", 150 + (30*nameLength), 375);
			else if (letter % 26 == 24)g.drawString("Y", 150 + (30*nameLength), 375);
			else g.drawString("Z", 150 + (30*nameLength), 375);
		}
		
	} else if (highscoremenu){
		g.setColor(Color.yellow);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 80));
		g.drawString("HIGHSCORES",28, 100);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 50));
		g.drawString("1.", 50, 170);
		g.drawString("2.", 50, 210);
		g.drawString("3.", 50, 250);
		g.drawString("4.", 50, 290);
		g.drawString("5.", 50, 330);
		g.drawString("6.", 50, 370);
		g.drawString("7.", 50, 410);
		g.drawString("8.", 50, 450);
		g.drawString("9.", 50, 490);
		g.drawString("10.", 50, 530);
		g.setColor(Color.red);
		for (int uu = 0; uu < hssize; uu++){
			g.drawString(name[uu], 110, 170 + (uu*40));
			g.drawString(highscore[uu], 220, 170 + (uu * 40));
		}
	}
		else{
		g.setColor(Color.yellow);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 80));
		g.drawString("Pacman", 115, 250);
		g.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		if (select % 3 == 0){
			g.setColor(Color.red);
			g.drawString("Start", 115, 350);
			g.setColor(Color.white);
			g.drawString("High Scores", 115, 380);
			g.drawString("Exit", 115, 410);
		}else if (select % 3 == 1){
			g.setColor(Color.white);
			g.drawString("Start", 115, 350);
			g.drawString("Exit", 115, 410);
			g.setColor(Color.red);
			g.drawString("High Scores", 115, 380);
		} else{
			g.setColor(Color.white);
			g.drawString("Start", 115, 350);
			g.drawString("High Scores", 115, 380);
			g.setColor(Color.red);
			g.drawString("Exit", 115, 410);
		}
		if (stop == 0){
			g.setColor(Color.white);
		g.drawImage(blink_right,blink_theat.getX(), blink_theat.getY(),this);
		g.drawImage(pink_right, pink_theat.getX(), pink_theat.getY(), this);
		g.drawImage(ink_right, ink_theat.getX(), ink_theat.getY(), this);
		g.drawImage(clyd_right,clyde_theat.getX(), clyde_theat.getY(),this);
		g.drawImage(pacma_right, pacman_theat.getX(), pacman_theat.getY(),this);
		if (pacman_theat.getX() >= 300)g.drawString("AAAAAAHHH!!!", 300, 470);
		else g.drawString("AAAAAAHHH!!!", pacman_theat.getX(), 470);
	} else if (stop == 1){
		g.setColor(Color.white);
		g.drawImage(vulny,blink_theat.getX(), blink_theat.getY(),this);
		g.drawImage(vulny, pink_theat.getX(), pink_theat.getY(), this);
		g.drawImage(vulny, ink_theat.getX(), ink_theat.getY(), this);
		g.drawImage(vulny,clyde_theat.getX(), clyde_theat.getY(),this);
		g.drawImage(pacma_left, pacman_theat.getX(), pacman_theat.getY(),this);
		g.drawString("HAHAHAHAHA!!!", pacman_theat.getX(),470);
	} else{
		g.setColor(Color.white);
		g.drawImage(blink,blink_theat.getX(), blink_theat.getY(),this);
		g.drawImage(pink, pink_theat.getX(), pink_theat.getY(), this);
		g.drawImage(ink, ink_theat.getX(), ink_theat.getY(), this);
		g.drawImage(clyd,clyde_theat.getX(), clyde_theat.getY(),this);
		g.drawImage(pacma_left, pacman_theat.getX(), pacman_theat.getY(),this);
		g.drawString("?!", (pacman_theat.getX() + 20), 470);
	}
		}
		g.dispose();
		return img;
	}
	
	public void run(){
		while(true){
			if (menu && !highscoremenu){
				if (stop == 0){
					blink_theat.moveRight();
					pink_theat.moveRight();
					ink_theat.moveRight();
					clyde_theat.moveRight();
					pacman_theat.moveRight();
					blink_theat.moveRight();
					pink_theat.moveRight();
					ink_theat.moveRight();
					clyde_theat.moveRight();
					pacman_theat.moveRight();
					if (clyde_theat.getX() >= 560) stop = 1;
				} else if (stop == 1){
					blink_theat.moveLeft();
					pink_theat.moveLeft();
					ink_theat.moveLeft();
					clyde_theat.moveLeft();
					pacman_theat.moveLeft();
					blink_theat.moveLeft();
					pink_theat.moveLeft();
					ink_theat.moveLeft();
					clyde_theat.moveLeft();
					pacman_theat.moveLeft();
					if (clyde_theat.getX() < 112) {
						stop = 2;
						menutime = System.currentTimeMillis();
					}
				} else if (highscoremenu){
					
				}
				else{
					if (Math.abs(menutime - System.currentTimeMillis()) > 1000){
						stop = 0;
					}
				}
			}
			if (!menu && startMenu % 2 == 0 && ready == 0 && lives >= 0){
			if (blinky.escape == 0){
				dir = lol.scatter(blinky,dir,252,224);
				try{
					if (dir.equals("chase") || blinky.fright){
						blinky.escape = 1;
						dir = null;
					}
				} catch (NullPointerException e){}
			}
			else{
			if (mode == 0){
				dir = lol.scatter(blinky,dir,28,56);
			}
			try{
			if (dir.equals("chase")){
				mode = 1;
				dir = null;
			}} catch (NullPointerException e){}
			
			if (mode == 1) {
				dir = lol.chase(blinky, pacman, dir, blinky.getX(), blinky.getY());
			}
			if (mode == 2){
				if (Math.abs(System.currentTimeMillis() - frighttime) < 6001){
					dir = lol.chase(blinky, pacman, dir, blinky.getX(), blinky.getY());
				}
				else{
					mode = 1;
					blinky.fright = false;
					dir = lol.chase(blinky, pacman, dir, blinky.getX(), blinky.getY());
				}
			}
			if (mode == 5){
				blinky.fright = false;
				dir = lol.scatter(blinky, dir, 224, 280);
				try{
					if (dir.equals("chase")){
						mode = 1;
						dir = null;
						blinky.dead = false;
						blinky.escape = 0;
					}
				} catch (NullPointerException e){}
			}
			}
			try{
			if (dir.equals("right"))	blinky.moveRight();
			else if (dir.equals("left")) blinky.moveLeft();
			else if (dir.equals("up"))	blinky.moveUp();
			else if (dir.equals("down"))	blinky.moveDown();}
			catch (NullPointerException e){}
			
			if (pinky.escape == 0){
				dirp = lol.scatter(pinky,dirp,252,224);
				try{
					if (dirp.equals("chase") || pinky.fright){
						pinky.escape = 1;
						dirp = null;
					}
				} catch (NullPointerException e){}
			}
			else {
			if (mode2 == 0){
				dirp = lol.scatter(pinky,dirp,476,56);
			}
			try{
			if (dirp.equals("chase")){
				mode2 = 1;
				dirp = null;
			}} catch (NullPointerException e){}
			if (mode2 == 1) {
				dirp = lol.chase(pinky, pacman, dirp, blinky.getX(), blinky.getY());
			}
			if (mode2 == 2) {
				if (Math.abs(System.currentTimeMillis() - frighttime) < 6001){
					dirp = lol.chase(pinky, pacman, dirp,blinky.getX(), blinky.getY());
				}
				else{
					mode2 = 1;
					pinky.fright = false;
				}
			}
			if (mode2 == 5){
				pinky.fright = false;
				dirp = lol.scatter(pinky, dirp, 280, 280);
				try{
					if (dirp.equals("chase")){
						mode2 = 1;
						dirp = null;
						pinky.dead = false;
						pinky.escape = 0;
					}
				} catch (NullPointerException e){}
			}
			}
			try{
			if (dirp.equals("right"))	pinky.moveRight();
			else if (dirp.equals("left")) pinky.moveLeft();
			else if (dirp.equals("up"))	pinky.moveUp();
			else if (dirp.equals("down"))	pinky.moveDown();
			}catch (NullPointerException e){}
			
			if (inky.escape == 0 && pebbles >= 30){
				diri = lol.scatter(inky,diri,168,224);
				try{
					if (diri.equals("chase") || inky.fright){
						inky.escape = 1;
						diri = null;
					}
				} catch (NullPointerException e){}
			}
			else{
				if (mode3 == 0){
					diri = lol.scatter(inky,diri,28,616);
				}
				try{
					if (diri.equals("chase")){
						mode3 = 1;
						diri = null;
					}} catch (NullPointerException e){}
				if (mode3 == 1) {
					diri = lol.chase(inky, pacman, diri, blinky.getX(), blinky.getY());
				}
				if (mode3 == 2) {
					if (Math.abs(System.currentTimeMillis() - frighttime) < 6001){
						diri = lol.chase(inky, pacman, diri, blinky.getX(), blinky.getY());
					}
					else{
						mode3 = 1;
						inky.fright = false;
					}
				}
				if (mode3 == 5){
					inky.fright = false;
					diri = lol.scatter(inky, diri, 224, 336);
					try{
						if (diri.equals("chase")){
							mode3 = 1;
							diri = null;
							inky.dead = false;
							inky.escape = 0;
						}
					} catch (NullPointerException e){}
				}
			}
			try{
			if (diri.equals("right"))	inky.moveRight();
			else if (diri.equals("left")) inky.moveLeft();
			else if (diri.equals("up"))	inky.moveUp();
			else if (diri.equals("down")) inky.moveDown();
			}catch (NullPointerException e){}
			
			if (clyde.escape == 0 &&  pebbles >= 60){
				dirc = lol.scatter(clyde, dirc, 336, 224);
				try{
					if(dirc.equals("chase") || clyde.fright){
						clyde.escape = 1;
						dir = null;
					}
				} catch (NullPointerException e){}
			}
			else {
				if (mode4 == 0){
					dirc = lol.scatter(clyde,dirc,476,616);
				}
				try{
					if (dirc.equals("chase")){
						mode4 = 1;
						dirc = null;
					}} catch (NullPointerException e){}
				if (mode4 == 1) {
					dirc = lol.chase(clyde, pacman, dirc, blinky.getX(), blinky.getY());
				}
				if (mode4 == 2) {
					if (Math.abs(System.currentTimeMillis() - frighttime) < 6001){
						dirc = lol.chase(clyde, pacman, dirc, blinky.getX(), blinky.getY());
					}
					else{
						mode4 = 1;
						clyde.fright = false;
					}
				}
				if (mode4 == 5){
					clyde.fright = false;
					dirc = lol.scatter(clyde, dirc, 280, 336);
					try{
						if (dirc.equals("chase")){
							mode4 = 1;
							dirc = null;
							clyde.dead = false;
							clyde.escape = 0;
						}
					} catch (NullPointerException e){}
				}
			}	
			try{
			if (dirc.equals("right"))	clyde.moveRight();
			else if (dirc.equals("left")) clyde.moveLeft();
			else if (dirc.equals("up"))	clyde.moveUp();
			else if (dirc.equals("down"))	clyde.moveDown();
			}catch (NullPointerException e){}
			
			if (!buf.equals(direction)){
				if (buf.equals("up")){
					if (invisible.moveUp()){
						direction = buf;
						invisible.adjustDown();
					}
				}
				if (buf.equals("down")){
					if (invisible.moveDown()){
						direction = buf;
						invisible.adjustUp();
					}
				}
				if (buf.equals("left")){
					if (invisible.moveLeft()){
						direction = buf;
						invisible.adjustRight();
					}
				}
				if (buf.equals("right")){
					if (invisible.moveRight()){
						direction = buf;
						invisible.adjustLeft();
					}
				}
			}
			pacman.dir = direction;
			if (direction != null){
				if (direction.equals("up")){
					pacman.moveUp();
					invisible.moveUp();
				}
				if (direction.equals("down")){
					pacman.moveDown();
					invisible.moveDown();
				}
				if (direction.equals("left")){
					pacman.moveLeft();
					invisible.moveLeft();
				}
				if (direction.equals("right")){
					pacman.moveRight();
					invisible.moveRight();
				}
			}
			
			if (pacman.eat()){
				int xind = pacman.getXindex();
				int yind = pacman.getYindex();
				map[xind][yind] = 3;
				score += 10;
				pebbles++;
			}
			
			if (pacman.getXindex() == 13 && pacman.getYindex() == 9 && bonus == 1){
				bonus = 0;
				if (level == 1){
					score += 100;
				} else if (level == 2){
					score += 300;
				} else if (level == 3 || level == 4){
					score += 500;
				} else if (level == 5 || level == 6){
					score += 700;
				} else if (level == 7 || level == 8){
					score += 1000;
				} else if (level == 9 || level == 10){
					score += 2000;
				} else if (level == 11 || level == 12){
					score += 3000;
				} else if (level >= 13){
					score += 5000;
				}
			}
			
			if (pacman.energize()){
				int xind = pacman.getXindex();
				int yind = pacman.getYindex();
				map[xind][yind] = 3;
				score += 50;
				ghostPoints = 200;
				pebbles++;
				if (!blinky.dead){
					blinky.fright = true;
					mode = 2;
				}
				if (!pinky.dead){
					pinky.fright = true;
					mode2 = 2;
				}
				if (!inky.dead){
					inky.fright = true;
					mode3 = 2;
				}
				if (!clyde.dead){
					clyde.fright = true;
					mode4 = 2;
				}
				frighttime = System.currentTimeMillis();
			}
			
			if (lol.contact(pacman, blinky)){
				if (blinky.fright && !blinky.dead){
					mode = 5;
					blinky.dead = true;
					score += ghostPoints;
					ghostPoints *= 2;
				} else if (!blinky.fright && !blinky.dead){
					lives--;
					try {
						reset();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if (lol.contact(pacman, pinky)){
				if (pinky.fright && !pinky.dead){
					mode2 = 5;
					pinky.dead = true;
					score += ghostPoints;
					ghostPoints *= 2;
				}else if (!pinky.fright && !pinky.dead){
					lives--;
					try {
						reset();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if (lol.contact(pacman, inky)){
				if (inky.fright && !inky.dead){
					mode3 = 5;
					inky.dead = true;
					score += ghostPoints;
					ghostPoints *= 2;
				}else if (!inky.fright && !inky.dead){
					lives--;
					try {
						reset();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if (lol.contact(pacman, clyde)){
				if (clyde.fright && !clyde.dead){
					mode4 = 5;
					clyde.dead = true;
					score += ghostPoints;
					ghostPoints *= 2;
				}else if (!clyde.fright && !clyde.dead){
					lives--;
					try {
						reset();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
			if (mode == 2 || mode2 == 2 || mode3 == 2 || mode4 ==2){
				ghostFlash++;
			}
			
			if (pebbles == 50 || pebbles == 120){
				bonus = 1;
				bonustime = System.currentTimeMillis();		
			}
			
			if (pebbles == 155){
				try {
					level++;
					reset();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}}
			
			if (lives < 0){
				if (submit == 0){
					if (nameLength >= 3){
						highscoreSort();
						subName = "";
						File fold = new File("txt/highscore.txt");
						fold.delete();
						File fnew = new File("txt/highscore.txt");
						try{
							FileWriter F2 = new FileWriter(fnew,false);
							F2.write(name[0] + " " + highscore[0] + "\n");
							F2.close();
							F2 = new FileWriter(fnew,true);
							for (int w = 1; w < hssize; w++){
								F2.write(name[w] + " " + highscore[w] + "\n");
							}
							F2.close();
						} catch (IOException e) {}
						submit = 1;
					}
				}else {
					menu = true;
					score = 0;
					lives = 4;
					nameLength = 0;
					submit = 0;
					getHighScores();
					try {
						reset();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			repaint();
			try
			{
				
				Thread.sleep(12);
			}
			catch (InterruptedException ex)
			{
				//do nothing
			}
		}
	}
	
	public void highscoreSort(){
		String scorebuf;
		String namebuf;
		for (int w = 0; w < hssize; w++){
			if(score >= Integer.parseInt(highscore[w])){
				scorebuf = highscore[w];
				namebuf = name[w];
				highscore[w] = Long.toString(score);
				name[w] = subName;
				score = Integer.parseInt(scorebuf);
				subName = namebuf;
			}
		}
		if (hssize < 10){
			name[hssize] = subName;
			highscore[hssize] = Long.toString(score);
			hssize++;
		}
	}
	
	 public static void main(String[] args) throws IOException{
	        frame f = new frame();
	        map = getMap("txt/pacmap.txt");
	        getHighScores();
	        f.loadGhost();
	        f.start();}
}
