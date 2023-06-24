import java.io.*;
import java.awt.*;
import java.awt.geom.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.util.*;

/**
 * @author Benjamin Banh
 * @assignment Trees-A5: Tree MiniProjekt
 * 
 * Reflection:
 * For this assignment, I finished an expression tree and the evaluate method. This creates
 * an expression tree which handles one digit calculations. This is somewhat efficient and 
 * only works when the string expression is in postfix notation. In the making of the 
 * expression tree, I utilized code from my stack calculator. I think that the evaluation way
 * is very good but think the stack evaluation of a calculator would be better.
 */

//A container for useful static methods that operate on TreeNode objects.
public class TreeUtilities_Banh_v2{
	//the random object used by this class
	private static java.util.Random random = new java.util.Random();

	//used to prompt for command line input
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // tester code for TreeUtils 
    public static void main (String[] args) {
        TreeNode tree = createRandom(4);
        TreeDisplay display = new TreeDisplay();
        display.displayTree(tree);
        display.displayTree(copy(tree));

        System.out.println("leftmost: " + leftmost(tree));
        System.out.println("rightmost: " + rightmost(tree));
        System.out.println("maxdepth: " + maxDepth(tree));
        System.out.println("countNodes: " + countNodes(tree));
        System.out.println("countLeaves: " + countLeaves(tree));
        System.out.println("sum: " + sum(tree));

        preOrder(tree, display);
        System.out.println();
        inOrder(tree, display);
        System.out.println();
        postOrder(tree, display);
        System.out.println();

        TreeNode expTree = createExpressionTree("12+2*9-3/4+");
        System.out.println("Tree Created");
        display.displayTree(expTree);
        System.out.print( "Evauluate Tree: " + eval(expTree) );
    }

	//precondition:  t is non-empty
	//postcondition: returns the value in the leftmost node of t.
	public static Object leftmost(TreeNode t)
	{
		//Implement using a while loop (no recursion)
        if(t == null) return null; 
        
        while (t.getLeft() != null){
            t = t.getLeft();
        }
        return t.getValue();
	}

	//precondition:  t is non-empty
	//postcondition: returns the value in the rightmost node of t.
	public static Object rightmost(TreeNode t)
	{
		if(t == null) return null;
        //Implement using recursion (no loops)
        if(t.getRight() == null) 
            return t.getValue();
        return rightmost(t.getRight());
	}

	//postcondition: returns the maximum depth of t, where an empty tree
	//               has depth 0, a tree with one node has depth 1, etc
	public static int maxDepth(TreeNode t)
	{
        if(t==null) 
            return 0;
        int leftHeight = maxDepth(t.getLeft());
        int rightHeight = maxDepth(t.getRight());
        if(leftHeight > rightHeight)
            return leftHeight+1;
        else
            return rightHeight+1;
	}

	//postcondition: each node in t has been lit up on display
	//               in a pre-order traversal
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		if (t != null) {
            System.out.print(" " + t.getValue());
            preOrder(t.getLeft(),display);
            preOrder(t.getRight(),display);
        }
	}

	//postcondition: each node in t has been lit up on display
	//               in an in-order traversal
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
        if (t != null) {
            inOrder(t.getLeft(),display);
            System.out.print(" " + t.getValue());
            inOrder(t.getRight(),display);
        }
	}

	//postcondition: each node in t has been lit up on display
	//               in a post-order traversal
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
        if (t != null) {
            postOrder(t.getLeft(),display);
            postOrder(t.getRight(),display);
            System.out.print(" " + t.getValue());
        }
	}

	//useful method for building a randomly shaped
	//tree of a given maximum depth
	public static TreeNode createRandom(int depth)
	{
		if (random.nextInt((int)Math.pow(2, depth)) == 0)
			return null;
		return new TreeNode(random.nextInt(10),
			createRandom(depth - 1),
			createRandom(depth - 1));
	}

	//returns the number of nodes in t
	public static int countNodes(TreeNode t)
	{
		if(t==null) 
            return 0;
        else
            return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
	}

	//returns the number of leaves in t
	public static int countLeaves(TreeNode t)
	{
        if (t == null)
            return 0;
        int count = 0;
        if (t.getLeft() == null && t.getRight() == null)
            count++;
        count += ( countLeaves(t.getLeft()) + countLeaves(t.getRight()) );
        return count;
	}

	//precondition:  all values in t are Integer objects
	//postcondition: returns the sum of all values in t
	public static int sum(TreeNode t)
	{
		if (t == null) 
            return 0;
        return ( Integer.parseInt(t.getValue().toString()) + sum(t.getLeft()) + sum(t.getRight()) );
	}

	//postcondition:  returns a new tree, which is a complete copy
	//                of t with all new TreeNode objects pointing
	//                to the same values as t (in the same order, shape, etc)
	public static TreeNode copy(TreeNode t)
	{
		if(t == null)
            return null;
        TreeNode clone = t;
        clone.setLeft(copy(t.getLeft()));
        clone.setRight(copy(t.getRight()));
        return clone;
	}

	//postcondition:  returns true if t1 and t2 have the same
	//                shape (but not necessarily the same values);
	//                otherwise, returns false
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		if(t1 == null && t2 == null){
            return true;
        }
		if(t1 != null && t2 != null){
            return sameShape(t1.getLeft(),t2.getLeft()) && sameShape(t1.getRight(),t2.getRight());
        }
        return false;
	}

	//precondition:  expTree is an expression tree consisting of Integer objects
	//               joined by "+" and "*" operators
	//postcondition: returns the value of the expression tree
	public static int eval(TreeNode expTree)
	{
        char op = (char) expTree.getValue();
        if ( op == '+')
        	return eval(expTree.getLeft()) + eval(expTree.getRight());
        else if ( op == '-')
            return eval(expTree.getLeft()) - eval(expTree.getRight());
        else if ( op == '*')
            return eval(expTree.getLeft()) * eval(expTree.getRight());
        else if ( op == '/')
            return eval(expTree.getLeft()) / eval(expTree.getRight());
        else 
        	return Character.getNumericValue(op);
	}

	//precondition:  exp represents an arithmetic expression,
	//               consisting of "+", "*", paretheses and numbers
	//postcondition: returns an expression tree to represent this arithmetic expression
	public static TreeNode createExpressionTree(String exp)
	{
		if(exp == null || exp.length() == 0)
            return null;
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        for(char c:exp.toCharArray()){
            if(c == '+' || c == '-' || c == '*' || c == '/'){
                TreeNode a = nodeStack.pop();
                TreeNode b = nodeStack.pop();
                TreeNode op = new TreeNode(c);

                op.setLeft(a);
                op.setRight(b);
                nodeStack.push(op);
            }
            else{
                nodeStack.push(new TreeNode(c));
            }
        }
        return nodeStack.peek();
	}

}//end of class


    //a graphical component for displaying the contents of a binary tree.
    //sample usage:
        // TreeDisplay display = new TreeDisplay();
        // display.displayTree(someTree);
        // display.visit(someNode);
    class TreeDisplay extends JComponent
    {
        //number of pixels between text and edge
        private static final int ARC_PAD = 2;
    
        //the tree being displayed
        private TreeNode root = null;
    
        //the node last visited
        private TreeNode visiting = null;
    
        //the set of all nodes visited so far
        private Set visited = new HashSet();
    
        //number of milliseconds to pause when visiting a node
        private int delay = 500;
    
        //creates a frame with a new TreeDisplay component.
        //(constructor returns the TreeDisplay component--not the frame).
        public TreeDisplay()
        {
            //create surrounding frame
            JFrame frame = new JFrame("Tree Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            //add the TreeDisplay component to the frame
            frame.getContentPane().add(this);
    
            //show frame
            frame.pack();
            frame.setVisible(true);
    
            java.util.Timer timer = new java.util.Timer();
            TimerTask task = new TimerTask()
            {
                public void run()
                {
                    TreeDisplay.this.repaint();
                }
            };
            timer.schedule(task, 0, 1000);
        }
    
        //tells the frame the default size of the tree
        public Dimension getPreferredSize()
        {
            return new Dimension(400, 300);
        }
    
        //called whenever the TreeDisplay must be drawn on the screen
        public void paint(Graphics g)
        {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
    
            //draw white background
            g2.setPaint(Color.white);
            g2.fill(new Rectangle2D.Double(0, 0, d.width, d.height));
    
            int depth = TreeUtilities_Banh_v2.maxDepth(root);
    
            if (depth == 0)
                //no tree to draw
                return;
    
            //hack to avoid division by zero, if only one level in tree
            if (depth == 1)
                depth = 2;
    
            //compute the size of the text
               FontMetrics font = g2.getFontMetrics();
            int leftPad = font.stringWidth(
                TreeUtilities_Banh_v2.leftmost(root).toString()) / 2;
            int rightPad = font.stringWidth(
                TreeUtilities_Banh_v2.rightmost(root).toString()) / 2;
            int textHeight = font.getHeight();
    
            //draw the actual tree
            drawTree(g2, root, leftPad + ARC_PAD,
                        d.width - rightPad - ARC_PAD,
                        textHeight / 2 + ARC_PAD,
                        (d.height - textHeight - 2 * ARC_PAD) / (depth - 1));
        }
    
        //draws the tree, starting from the given node, in the region with x values ranging
        //from minX to maxX, with y value beginning at y, and next level at y + yIncr.
        private void drawTree(Graphics2D g2, TreeNode t, int minX, int maxX, int y, int yIncr)
        {
            //skip if empty
            if (t == null)
                return;
    
            //compute useful coordinates
            int x = (minX + maxX) / 2;
            int nextY = y + yIncr;
    
            //draw black lines
            g2.setPaint(Color.black);
            if (t.getLeft() != null)
            {
                int nextX = (minX + x) / 2;
                g2.draw(new Line2D.Double(x, y, nextX, nextY));
            }
            if (t.getRight() != null)
            {
                int nextX = (x + maxX) / 2;
                g2.draw(new Line2D.Double(x, y, nextX, nextY));
            }
    
            //measure text
            FontMetrics font = g2.getFontMetrics();
            String text = t.getValue().toString();
            int textHeight = font.getHeight();
            int textWidth = font.stringWidth(text);
    
            //draw the box around the node
            Rectangle2D.Double box = new Rectangle2D.Double(
                x - textWidth / 2 - ARC_PAD, y - textHeight / 2 - ARC_PAD,
                textWidth + 2 * ARC_PAD, textHeight + 2 * ARC_PAD);//, ARC_PAD, ARC_PAD);
            Color c;
            //color depends on whether we haven't visited, are visiting, or have visited.
            if (t == visiting)
                c = Color.YELLOW;
            else if (visited.contains(t))
                c = Color.ORANGE;
            else
                c = new Color(187, 224, 227);
            g2.setPaint(c);
            g2.fill(box);
            //draw black border
            g2.setPaint(Color.black);
            g2.draw(box);
    
            //draw text
            g2.drawString(text, x - textWidth / 2, y + textHeight / 2);
    
            //draw children
            drawTree(g2, t.getLeft(), minX, x, nextY, yIncr);
            drawTree(g2, t.getRight(), x, maxX, nextY, yIncr);
        }
    
        //tells the component to switch to displaying the given tree
        public void displayTree(TreeNode root)
        {
            this.root = root;
    
            //signal that the display needs to be redrawn
            repaint();
        }
    
        //light up this particular node, indicating we're visiting it.
        public void visit(TreeNode t)
        {
            //if we've already visited it, we assume this is a new traversal,
            //and reset the set of visited nodes.
            if (visited.contains(t))
                visited = new HashSet();
    
            //update visiting and visited
            visiting = t;
            visited.add(t);
    
            //signal that the display needs to be redrawn
            repaint();
    
            //pause, so you can see the traversal
            try
            {
                Thread.sleep(delay);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    
        //change the length of time to pause when visiting a node
        public void setDelay(int delay)
        {
            this.delay = delay;
        }
    }

    class TreeNode{
        private Object value;
        private TreeNode left;
        private TreeNode right;
        
        public TreeNode(Object initValue)
          { value = initValue; left = null; right = null; }
      
        public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
          { value = initValue; left = initLeft; right = initRight; }
      
        public Object getValue() { return value; }
        public TreeNode getLeft() { return left; }
        public TreeNode getRight() { return right; }
      
        public void setValue(Object theNewValue) { value = theNewValue; }
        public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
        public void setRight(TreeNode theNewRight) { right = theNewRight; }
      }