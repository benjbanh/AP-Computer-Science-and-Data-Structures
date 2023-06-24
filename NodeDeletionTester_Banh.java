//@author Benjamin Banh 
//Trees-A3: removeNode

/**
 * Reflection
 * 
 * I feel that this is a good delete method although I am not 100% sure about efficiency. 
 * Some issues I encountered was trouble with the Comparable interface and trouble 
 * with recursion which I later solved. I still am not sure how to convert objects to 
 * Comparable<E> without "Type Safety Errors". 
 */

/**
 * Testing class for Binary Tree class
 */
public class NodeDeletionTester_Banh {
    public static void main(String[] args) {
        /**
         * tree1- Sub-Tree with no successor nodes (but does have predecessor nodes) 
         *        is handled
         *      - Tree where succcessor node to the deleted node is a leaf node is handled.
         * tree2- Empty tree handled
         * tree3- Tree where successor node to the deleted node has a child is handled.
         * tree4- 1 node tree handled
         * 
         */
        BSTree<String> tree1 = new BSTree<>();  
        BSTree<String> tree2 = new BSTree<>();
        BSTree<String> tree3 = new BSTree<>();
        BSTree<String> tree4 = new BSTree<>();


        /**
         *      b
         *    /  \
         *   a    c
         *       /
         *      e
         *     / \
         *    d   f
         */
        tree1.add("b");
        tree1.add("a");
        tree1.add("c");
        tree1.add("e");
        tree1.add("d");
        tree1.add("f");

        tree1.print(2);
        tree1.removeNode("e");
        tree1.print(2);
        tree1.removeNode("a");
        tree1.print(2);

        /**
         * 
         * 
         */
        tree2.removeNode("Empty Test");
        tree2.print(1);


        /**
         *      one
         *     /  \
         *  four    two
         *   /      /
         *  five  three  
         */
        tree3.add("one");
        tree3.add("two");
        tree3.add("three");
        tree3.add("four");
        tree3.add("five");

        tree3.print(2);
        tree3.removeNode("four");
        tree3.print(2);

        /**
         *      Singular Node
         * 
         * 
         */
        tree4.add("Singular Node");

        tree4.removeNode("N/A");
        tree4.removeNode("Singular Node");
        tree4.print(2);




    }
}

/**
 * A Binary Tree which has an add and print function. The compareTo method was to test the 
 * program. The instance variable root is the base node of the binary tree.
 */
class BSTree<E extends Comparable<E>> {
    private TreeNode root;

    /**
     * This is a method that allows the tester access to the TreeNode root.
     * @param value the value to be inputed into the insert method
     */
    public void add(Comparable<E> value){
        root = insert(root,value);
    }

    /**
     * This is a recursive method which inserts the Object value into the binary tree. 
     * @param comp the instance variable root
     * @param value the value to be inserted
     * @return a leaf which has the value of the Object value
     */
    private TreeNode insert(TreeNode comp, Comparable<E> value){
        //if tree is empty
        if(comp == null){
            return new TreeNode(value);
        }
        //lesser
        else if( value.compareTo((E)comp.getValue()) < 0 ){
            comp.setLeft( insert(comp.getLeft(),value));
        }
        //greater
        else if( value.compareTo((E)comp.getValue()) > 0 ){
            comp.setRight( insert(comp.getRight(), value));
        }
        return comp;
    }
   
    /**
     * Allows tester to access the instance variable root and choosing between the prints
     */
    public void print(int i){
        if(i==1) {
            System.out.print("Preorder: ");
            printPreorder(this.root);  
        }
        else if(i==2) {
            System.out.print("Inorder: ");
            printInorder(this.root);  
        }
        else if(i==3) {
            System.out.print("Postorder: ");
            printPostorder(this.root);  
        }
        else{
            System.out.print("Pre-1, In-2, Post-3");
        }
        System.out.println();
    }

    /**
     * Prints out the binary tree starting from the Root, Left, Right
     * @param node the root
     */
    private void printPreorder(TreeNode node){
        if (node != null) {
            System.out.print(" " + node.getValue());
            printPreorder(node.getLeft());
            printPreorder(node.getRight());
        }
    }

    /**
     * Prints out the binary tree starting from the Left, Root, Right
     * @param node the root
     */
    private void printInorder(TreeNode node){
        if (node != null) {
            printInorder(node.getLeft());
            System.out.print(" " + node.getValue());
            printInorder(node.getRight());
        }
    }

    /**
     * Prints out the binary tree starting from the Left, Right, Root
     * @param node the root
     */
    private void printPostorder(TreeNode node){
        if (node != null) {
            printPostorder(node.getLeft());
            printPostorder(node.getRight());
            System.out.print(" " + node.getValue());
        }
    }

    /**
     * Calls upon the remove() method and returns the returned value
     * @param datum the value of the node wanted to be deleted
     * @return the value of a deleted node recieved from remove()
     */
    public Comparable<E> removeNode(Comparable<E> datum){
        return (E) remove(root, datum).getValue();
    }

    /**
     * The remove method which returns the deleted treenode. Called upon by the removeNode()
     * method. 
     * @param comp the root node recursively called
     * @param datum the value of the node which is to be deleted
     * @return the deleted tree node
     */
    private TreeNode remove( TreeNode comp, Comparable<E> datum ){
        TreeNode parent = null;
        TreeNode curr = comp;
 
        //find node and its preceeding node
        while (curr != null && curr.getValue() != datum){
            parent = curr;
 
            // lesser
            if (datum.compareTo( (E) curr.getValue() ) < 0)
                curr = curr.getLeft();
            
            // greater
            else if(datum.compareTo( (E) curr.getValue() ) > 0)
                curr = curr.getRight();
        }
 
        // not found
        if (curr == null) {
            return comp;
        }
 
        // 0 childs
        if (curr.getLeft() == null && curr.getRight() == null){
            //
            if (curr != comp){
                if (parent.getLeft() == curr) {
                    parent.setLeft(null);
                }
                else {
                    parent.setRight(null);
                }
            }
            //curr == comp
            else {
                comp = null;
            }
        }
 
        // 2 childs ren
        else if (curr.getLeft() != null && curr.getRight() != null){
            //set lesser node to root
            TreeNode node = curr.getLeft();
            while(node.getLeft() != null){
                node = node.getLeft();
            }
            
            //continue down to move deleted node's children up the tree
            remove(comp, (E) node.getValue());
            curr.setValue(node.getValue());
        }
 
        // 1 lonely child
        else {
            //set child to non-null node
            TreeNode child = null;
            if(curr.getRight() == null)
                child = curr.getLeft();
            else
                child = curr.getRight();
 
            // set deleted node's parent to its child
            if (curr != comp){
                if(curr == parent.getLeft()) {
                    parent.setLeft(child);
                }
                else if(curr == parent.getRight()) {
                    parent.setRight(child);
                }
            }
 
            //set deleted node to its child
            else {
                comp = child;
            }
        }
        return comp;
    }
}

/** 
 * Pre-given Node class
 */
class TreeNode{
    private Object value;
    private TreeNode left;
    private TreeNode right;

    //constructors
    public TreeNode(Object initValue){ 
        value = initValue; left = null; right = null; 
    } 
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight){ 
        value = initValue; left = initLeft; right = initRight; 
    }   

    //Methods
    public Object getValue() { 
        return value; 
    }
    public TreeNode getLeft() { 
        return left; 
    }
    public TreeNode getRight() { 
        return right; 
    }    
    public void setValue(Object theNewValue) { 
        value = theNewValue; 
    }
    public void setLeft(TreeNode theNewLeft) { 
        left = theNewLeft; 
    }
    public void setRight(TreeNode theNewRight) { 
        right = theNewRight; 
    }
}