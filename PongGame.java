import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
   @author Benjamin Banh
   @author Minh Dinh
*/
class PongGame {
   private static GameFrame frame;
   
   public static void main(String[] args) {
      frame = new GameFrame();
      System.out.println(frame);
   }

   public static GameFrame getFrame() {
      return frame;
   }
}

/**
   creates a new game panel and adds the background and prints toString in log
*/
class GameFrame extends JFrame {
   public GameFrame() {
      GamePanel panel = new GamePanel();
      this.add(panel);
      this.setTitle("Ben's and Minh's Pong Game");
      this.setResizable(false);
      this.setBackground(Color.black);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);
      this.setLocationRelativeTo(null);
   }

   public String toString() {
      return """
             Started the game!
             Player 1 use the W and S keys.
             Player 2 use the up and down arrow keys.
             """;
   }
}

/**
   This class creates the game panel that shows the pong game that implements an interface that is executed by a thread
*/
class GamePanel extends JPanel implements Runnable {
   private static final int GAME_WIDTH = 1000;
   private static final int GAME_HEIGHT = 500;
   private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
   private static final int BALL_DIAMETER = 15;
   private static final int PADDLE_WIDTH = 15;
   private static final int PADDLE_HEIGHT = 100;
   private final double GRAVITY_CONSTANT = 0.98;
   
   private final boolean GRAVITY = false;
   private final boolean AI = false;
   private final boolean SIMULATION = false;

   private Paddle paddle1;
   private Paddle paddle2;
   private Ball ball;
   private final Score score;

/** 
  This constructor creates the game panel 
*/
   public GamePanel(){
	   newBall();
	   checkAI();
      score = new Score(GAME_WIDTH, GAME_HEIGHT);
      this.setFocusable(true);
      this.addKeyListener(new AL());
      this.setPreferredSize(SCREEN_SIZE);

      Thread gameThread = new Thread(this);
      gameThread.start();
   }
   /** 
     creates a new Ball when a point is scored and sets the Ball to a random ball to a set size but random position in the middle of the game panel
   */
   public void newBall() {
	   Random random = new Random();
	   ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER,GRAVITY);
   }
 
   /**
      creates 2 paddles which are the paddles which the player controls
   */
   public void newPaddles() {
      paddle1 = new Player(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
      paddle2 = new Player(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
   }

   public void newAI() {
      paddle1 = new Player(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
      paddle2 = new AI(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,ball);
   }
   
   public void onlyAI() {
      paddle1 = new AI(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,ball);
      paddle2 = new AI(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,ball);
   }

   public void checkAI(){
      if(SIMULATION) {
         onlyAI();
      }
      else if(AI) {
         newAI();
      }
      else{ 
         newPaddles();
      }
   }

   /**
     @param g is the Graphics panel in java.awt
     This is the method that generates 
  */
   public void paint(Graphics g) {
      Image image = createImage(getWidth(), getHeight());
      Graphics graphics = image.getGraphics();
      draw(graphics);
      g.drawImage(image,0,0,this);
   }

  /**
     This method overrides the draw method in Graphics from java.awt and generates the paddles, balls, score, and synchronises the toolkits graphic state
  */
   public void draw(Graphics g) {
      paddle1.draw(g);
      paddle2.draw(g);
      ball.draw(g);
      score.draw(g);
      Toolkit.getDefaultToolkit().sync();
   }
   
   /**
     This moves the ball and paddles
   */
   public void move() {
      paddle1.move();
      paddle2.move();
      ball.move();
   }
   /**
     This method checks collision to see if the ball collides with either the top or bottom of 
     the Game panel or with the paddle and then changes the velocity every collision
   */
   public void checkCollision() {
      //is ball collides with the top
      if(ball.y <=0) {
         ball.setYDirection(-ball.getYSpeed());
         ball.y = 0;
      }
      //is ball collides with the bottom
      if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
         ball.setYDirection(-ball.getYSpeed());
         
         //gives ball gravity to bounce
         if(GRAVITY){
            int num = (int)(ball.getYSpeed() * GRAVITY_CONSTANT);
            ball.setYDirection(num);
            ball.y = GAME_HEIGHT-BALL_DIAMETER;
         }

      }
      
      //if ball collides with paddle1
      if(ball.intersects(paddle1)) {
         ball.setXDirection( Math.abs(ball.getXSpeed()) +1 );
         
         //ensures that the ball will always move either up or down to stop infinite loops
         if(ball.getYSpeed()>0)
            ball.setYDirection( ball.getYSpeed()+1);
         else
            ball.setYDirection( ball.getYSpeed()-1);;
            
         ball.setXDirection(ball.getXSpeed());
         ball.setYDirection(ball.getYSpeed());
      }
      //if ball collides with paddle2
      if(ball.intersects(paddle2)) {
         ball.setXDirection( Math.abs(ball.getXSpeed()) +1 );

         //ensures that the ball will always move either up or down to stop infinite loops
         if(ball.getYSpeed()>0)
            ball.setYDirection( ball.getYSpeed()+1);
         else
            ball.setYDirection( ball.getYSpeed()-1);;
         
         ball.setXDirection(-ball.getXSpeed());
         ball.setYDirection(ball.getYSpeed());
      }
      
      //checks if the paddle is colliding with the top or bottom of the panel
      if(paddle1.y<=0)
         paddle1.y=0;
      if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
         paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
         
      if(paddle2.y<=0)
         paddle2.y=0;
      if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
         paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
         

      //checks if paddle is colliding with the middle of the panel
      if(paddle1.x >= (GAME_WIDTH/2)-PADDLE_WIDTH)
         paddle1.x = (GAME_WIDTH/2)-PADDLE_WIDTH;
      if(paddle2.x <= GAME_WIDTH/2)
         paddle2.x = GAME_WIDTH/2;

      
      //checks paddle collisions with the sides of the panel 
      if(paddle1.x <= 0)
         paddle1.x = 0;
      if(paddle2.x >= GAME_WIDTH-PADDLE_WIDTH)
         paddle2.x = GAME_WIDTH-PADDLE_WIDTH;

      //scoring on the left side
      if(ball.x <=0) {
         score.score2();
         newBall();
		   checkAI();
      }
      
      //scoring on the right side
      if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
         score.score1();
         newBall();
		   checkAI();
      }
   }

   /**
      Runs the game 
   */
   public void run() {
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      while(true) {
         long now = System.nanoTime();
         delta += (now -lastTime)/ns;
         lastTime = now;
         if(delta >=1) {
            move();
            checkCollision();
            repaint();
            delta--;  
            //stops the game if someone wins
            if(score.win()){
               GameFrame oldFrame = PongGame.getFrame();
               oldFrame.setVisible(false);
               oldFrame.dispose();

               PongGame.main(null);
               break;
            }
         }
      }
   }
   
   /**
   This checks if a key is pressed and calls the keyPressed method in the Paddle class
   */
  public class AL extends KeyAdapter{
	//action listener
	public void keyPressed(KeyEvent e) {
	   ((Player) paddle1).keyPressed(e);
	   if(paddle2 instanceof Player)
		  ((Player) paddle2).keyPressed(e);
	}
	//key released
	public void keyReleased(KeyEvent e) {
	   ((Player) paddle1).keyReleased(e);
	   if(paddle2 instanceof Player)
		  ((Player) paddle2).keyReleased(e);
	}
 }
}

/**
   This class creates a paddle that creates the paddles the controllable paddles
*/
class Paddle extends Rectangle {
	final private int PADDLE_SPEED = 7;
 
   /**
	  @param x: initial x location
	  @param y: initial y location
	  @param PADDLE_WIDTH: PADDLE_WIDTH is final variable called for width
	  @param PADDLE_HEIGHT: PADDLE_HEIGHT is final variable called for height
	  
	  Paddle constructor which instantiates the initial position, and size. Extends the Graphics Rectangle Class
   */
	public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
	   super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
	}
	public void move() {
	   super.y -= PADDLE_SPEED;
	   super.x -= PADDLE_SPEED;
	   
	}
	public int getSpeed(){
	   return PADDLE_SPEED;
	}
	/**
	  @param g of the Graphics class
	*/
	public void draw(Graphics g) {
	   g.setColor(Color.white);
	   g.fillRect(x, y, width, height);
	}
 }

/**
 * This class 
 */
class Player extends Paddle{
	private int xVelocity;
	private int yVelocity;
   private int id;
	
   /**
     @param x: initial x location
	  @param y: initial y location
	  @param PADDLE_WIDTH: PADDLE_WIDTH is final variable called for width
	  @param PADDLE_HEIGHT: PADDLE_HEIGHT is final variable called for height
	  @param id in order to keep track of player action listeners
    */
	public Player(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT,int id){
	   super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
      this.id = id;
	}
	
	//moving the paddles according to key press and the id that is instantiated in the constructor
	  public void keyPressed(KeyEvent e) {
	   switch (id) {
		  //left panel id #1
		  case 1 -> {
			 if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_A) {
				setXDirection(-super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_D) {
				setXDirection(super.getSpeed());
			 }
		  }
		  //right panel id #2
		  case 2 -> {
			 if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setXDirection(-super.getSpeed());
			 }
			 if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setXDirection(super.getSpeed());
			 }
		  }
	   }
	}
	//if the key is released the paddle stops motion
	public void keyReleased(KeyEvent e) {
	   switch ( id ) {
		  //paddle 1(left)
		  case 1 -> {
			 if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_A) {
				setXDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_D) {
				setXDirection(0);
			 }
		  }
		  //paddle 2(right)
		  case 2 -> {
			 if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setXDirection(0);
			 }
			 if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setXDirection(0);
			 }
		  }
	   }
	}
	/**
	  mutator method to change yVelocity instance variable
	*/
	public void setYDirection(int yDirection) {
	   yVelocity = yDirection;
	}
	/**
	 mutator method to change xVelocity instance variable
	*/
	public void setXDirection(int xDirection) {
	   xVelocity = xDirection;
	}
	
	/**
	  mutator method to change super instance variable of y position
	*/
	public void move() {
	   y = y + yVelocity;
	   x = x + xVelocity;
	}
 }
 
 class AI extends Paddle {
	private Ball ball;
 
	public AI(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT,Ball ball){
	   super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
	   this.ball = ball;
	}
	public void move() {
 
	   if(super.y >= ball.y){
		  super.y -= super.getSpeed();
	   }
	   else{
		  super.y += super.getSpeed();
	   }
		  
	}
 
	public void draw(Graphics g) {
	   g.setColor(Color.red);
	   g.fillRect(x, y, width, height);
	}
 }
 
 
 /**
	This is the class slightly strange since ball(circle) is extended from rectangle
 */
 class Ball extends Rectangle{
	private int xVelocity;
	private int yVelocity;
	private boolean gravity;
	private final int initialSpeed = 5;
	private final int dir = 6;
	
	/**
	   @param x: initial x position
	   @param y: initial y position
	   @param width: width of the ball
	   @param height: height of the ball
	   
	   Constructor which sets random direction to the ball and 
	*/
	public Ball(int x, int y, int width, int height,boolean gravity){
	   super(x,y,width,height);
	   this.gravity = gravity;
	   Random random = new Random();
	   
	   //random x direction
	   int randomXDirection = (int)(random.nextInt(dir+dir)-dir);         //original 2
	   if(randomXDirection == 0)
		  randomXDirection = (int)(random.nextInt(dir)-dir);
	   setXDirection(randomXDirection*initialSpeed);
	   
	   int randomYDirection = (int)(random.nextInt(dir+dir)-dir);         //original 2
	   if(randomYDirection == 0)
		  randomYDirection = (int)(random.nextInt(dir)-dir);
	   setYDirection(randomYDirection*initialSpeed);
 
	}
	/** 
	   mutator method to change xVelocity
	*/
	public void setXDirection(int randomXDirection) {
	   xVelocity = randomXDirection;
	}
	
	/**
	   mutator method to change yVelocity
	*/
	public void setYDirection(int randomYDirection) {
	   yVelocity = randomYDirection;
	}

   	/**
	   mutator method to change xVelocity
	*/
	public int getXSpeed() {
	   return xVelocity;
	}
	
	/**
	   mutator method to change yVelocity
	*/
	public int getYSpeed() {
	   return yVelocity;
	}

	/**
	   Moves the ball according to the x and y velocity
	*/
	public void move() {
	   x += xVelocity;
	   y += yVelocity;
	   //ball increases with height over time
      if(gravity)
		  yVelocity += 1;
	}
	/**
	   @Override draw method of graphics rectangle
	   overrides the draw method in Graphics class
	*/
	public void draw(Graphics g) {
	   g.setColor(Color.red);
	   g.fillOval(x, y, width, height);
	   
	}
 }

/**
   This is the Score class which records the score of game
*/
class Score extends Rectangle{

   private static int GAME_WIDTH;
   private static int GAME_HEIGHT;
   private int player1;
   private int player2;
   private boolean oneWin;
   private boolean twoWin;

   public Score(int GAME_WIDTH, int GAME_HEIGHT){
      Score.GAME_WIDTH = GAME_WIDTH;
      Score.GAME_HEIGHT = GAME_HEIGHT;
      oneWin = false;
      twoWin = false;
   }
   
   public void score1(){
      player1++;
   }

   public void score2(){
      player2++;
   }

   public boolean win(){
      return oneWin || twoWin;
   }

/**
  @Override the draw method in Graphics class
*/
   public void draw(Graphics g) {
      g.setColor(Color.white);
      g.setFont(new Font("SansSerif", Font.PLAIN,50));
   
      g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
      
      //draws the digits individually(first tens digit then ones digit)
      g.drawString(player1 / 10 + String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
      g.drawString(player2 / 10 + String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);

      if(player1 >= 10){
         g.drawString("Player 1 wins!", 0, GAME_HEIGHT-10);
         oneWin = true;
      }
      if(player2 >= 10){
         g.drawString("Player 2 wins!", GAME_WIDTH/2, GAME_HEIGHT-10);
         twoWin = true;
      }
   }
}
