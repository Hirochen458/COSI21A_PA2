package main;
/**
 * This class is mainly used to create a data structure of AVL tree that used to store players' data, together with some methods that can find the data in the tree and restore the tree.
 * Known Bugs: <Explanation of known bugs or “None”>
 * @author Zhijian Chen
 * <Chen5340@brandeis.edu>
 * <Apr 1th, 2022>
 * COSI 21A PA2
*/

/**
 * Your code goes in this file
 * fill in the empty methods to allow for the required
 * operations. You can add any fields or methods you want
 * to help in your implementations.
 */

public class AVLPlayerNode{
    private Player data;
    private double value;
    private AVLPlayerNode parent;
    private AVLPlayerNode leftChild;
    private AVLPlayerNode rightChild;
    private int rightWeight;
    private int balanceFactor;
    private AVLPlayerNode root;
    private AVLPlayerNode y;
    private AVLPlayerNode w;
    private AVLPlayerNode z;
    private String string;
    private String board;
    AVLPlayerNode newNode;
    
    
    
    
    /**
     * This method is mainly used to construct a node instance.
     * @param data data is the player data.
     * @param value value is the player value.
     * the running time is constant.
     */
    public AVLPlayerNode(Player data,double value){
        this.data = data;
        this.value = value;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        rightWeight = 0;
        balanceFactor = 0;
        root = null;
        y =  null;
        w = null;
        z = null;
        string = "";
        board = "";
        newNode = null;
        
        
    }
    
    
    //This should return the new root of the tree
    //make sure to update the balance factor and right weight
    //and use rotations to maintain AVL condition
    /**
     * This method is mainly used to insert a node into tree and balance the tree.
     * @param newGuy the newGuy player object.
     * @param value the value that stored in player object.
     * @return return the root of the tree.
     * The running time is O(logn).
     */
    public AVLPlayerNode insert(Player newGuy,double value){
    	if(value > this.value) {
    		if(this.rightChild == null) {
    			newNode = new AVLPlayerNode(newGuy, value);
    			this.rightChild = newNode;
    			newNode.parent = this;
    			newNode.rightWeight = rightW(newNode.rightChild);
    		}
    		else {
    			this.rightChild.insert(newGuy, value);
    		}
    	}else if(value == this.value){
    		return this;
    	}else {
    		if(this.leftChild == null) {
    			newNode = new AVLPlayerNode(newGuy, value);
    			this.leftChild = newNode;
    			newNode.parent = this;
    			newNode.rightWeight = rightW(newNode.rightChild);
    		}else {
    			this.leftChild.insert(newGuy, value);
    		}
    	}
    	root = this;
    	
    	
    	if (balanceF(root) < -1) {                //tree right heavy
    		if (balanceF(root.rightChild) > 1) {//right subtree left heavy
    			root.rightChild.rotateRight();
    			root.rotateLeft();
    		}else {                             //right subtree right heavy
    			root.rotateLeft();
    		}
    	}else if(balanceF(root) > 1) {           //tree left heavy
   			if (balanceF(root.leftChild) < -1) { // left subtree right heavy
    			root.leftChild.rotateLeft();
    			root.rotateRight();
    		}else {                             //left subtree left heavy
    			root.rotateRight();
    		}
    	}
    	
    	//newNode.rightWeight = rightW(newNode.rightChild);
    	//inOrderRWUpdate(root);
    	
    	
    	
    	return root;
    }
    
    //This should return the new root of the tree
    //remember to update the right weight
    /**
     * This method is used to delete a node from the tree.
     * @param value the value that used to find the node.
     * @return return the root of tree.
     * The running time is O(logn).
     */
    public AVLPlayerNode delete(double value){
    	AVLPlayerNode d = searchNode(root, value);
    	if (d.leftChild == null || d.rightChild == null) {
    		y = d;
    	}else {
    		y = successor(d);
    	}
    	if (y.leftChild != null) {
    		z = y.leftChild;
    	}else {
    		z = y.rightChild;
    	}
    	if (z != null) {
    		z.parent = y.parent;
    	}
    	if (y.parent == null) {
    		root = z;
    	}else {
    		if(y == y.parent.leftChild) {
    			y.parent.leftChild = z;
    		}else {
    			y.parent.rightChild = z;
    		}
    	}
    	if (y != d) {
    		d.data = y.data;
    	}
    	
    	if (balanceF(root) < -1) {                //tree right heavy
    			if (balanceF(root.rightChild) > 1) {//right subtree left heavy
    				root.rightChild.rotateRight();
    				root.rotateLeft();
    			}else {                             //right subtree right heavy
    				root.rotateLeft();
    			}
    		}else if(balanceF(root) > 1) {           //tree left heavy
    			if (balanceF(root.leftChild) < -1) { // left subtree right heavy
    				root.leftChild.rotateLeft();
    				root.rotateRight();
    			}else {                             //left subtree left heavy
    				root.rotateRight();
    			}
    		}
    	 //inOrderRWUpdate(root);
    		
    	  return root;
    }
    
    
    //remember to maintain rightWeight
    /**
     * This method is mainly used to make a subtree/node rotate right when it's left heavy.
     * The running time is constant.
     */
    private void rotateRight(){
    	y = this.leftChild;
    	this.leftChild = y.rightChild;
    	if (y.rightChild != null) {
    		y.rightChild.parent = this;
    	}
    	y.parent = this.parent;
    	if (this.parent == null) {
    		root = y;
    	}else if (this == this.parent.rightChild) {
    		this.parent.rightChild = y;
    	}else {
    		this.parent.leftChild = y;
    	}
    	y.rightChild = this;
    	this.parent = y;
    	
    	this.rightWeight = rightW(this.rightChild);
    	y.rightWeight = rightW(y.rightChild);
    	
    }

    
    //remember to maintain rightWeight
    /**
     * This method is mainly used to make a subtree/node rotate left when it's right heavy.
     * The running time is constant.
     */
    private void rotateLeft(){
    	y = this.rightChild;
    	this.rightChild = y.leftChild;
    	if (y.leftChild != null) {
    		y.leftChild.parent = this;
    	}
    	y.parent = this.parent;
    	if (this.parent == null) {
    		root = y;
    	}else if( this == this.parent.leftChild) {
    		this.parent.leftChild = y;
    	}else {
    		this.parent.rightChild = y;
    	}
    	y.leftChild = this;
    	this.parent = y;
    	
    	this.rightWeight = rightW(this.rightChild);
    	y.rightWeight = rightW(y.rightChild);
    }
	
    
    //this should return the Player object stored in the node with this.value == value
    /**
     * This method is mainly used to search for the player node in the tree.
     * @param value the value that used to find the player.
     * @return return the player object.
     * The running time is O(logn).
     */
    public Player getPlayer(double value){
    	return searchPlayer(this, value);
    }
    
    
    //this should return the rank of the node with this.value == value
    /**
     * This method is used to get players' rank in the tree.
     * @param value the value that used to find the tree.
     * @return return the rank in the tree.
     * The running time is O(n).
     */
    public int getRank(double value){
    	
    	
    	//AVLPlayerNode A = searchNode(this, value);
    	//return inOrderRWUpdate(root, A);
    	return rank(root, value);

    }

    
    //this should return the tree of names with parentheses separating subtrees
    //eg "((bob)alice(bill))"
    /**
     * This method is mainly used to make the tree into printable string.
     * @return return the string of the tree.
     * The running time is O(n).
     */
    public String treeString(){
    	string = "";
    	return inOrder(this);
    	
    	
    }

    
    //this should return a formatted scoreboard in descending order of value
    //see example printout in the pdf for the command L
    /**
     * This method is used to print a readable ranked board with players' name, ID, and ELO.
     * @return return a string with readable board.
     * The running time is O(n).
     */
    public String scoreboard(){
    	board = "";
    	board += "NAME" + "          " + "ID" + "     "+ "SCORE" +"\n";
    	return inReOrder(this);
    }
    
    
    /**
     * This method is used to calculate the height of the tree.
     * @param n the node/root of the subtree/tree.
     * @return return the height of the tree.
     * The running time is O(n).
     */
    private int height(AVLPlayerNode n) {
    	int leftH = 0;
    	int rightH = 0;
    	if (n == null) {
    		return 0;
    	}else {
    		leftH = height(n.leftChild);
    		rightH = height(n.rightChild);
    		return Math.max(leftH,rightH) + 1;
    	}
    }
    
   
	
    /**
     * This method is used to calculate the balance factor of the tree/subtree
     * @param n the node/root of the subtree/tree.
     * @return return the balance factor.
     * The running time O(2n).
     */
	public int balanceF(AVLPlayerNode n) {
    	return height(n.leftChild) - height(n.rightChild);
    }
    
	
	/**
	 * This method is used to search for node in tree and return the data in node.
	 * @param n the root of tree.
	 * @param value the value that is used to search.
	 * @return return the player object in node.
	 * The running time is O(logn).
	 */ 
    private Player searchPlayer(AVLPlayerNode n, double value) {
    	
    	if (n == null || n.value == value) {
    		return n.data;
    	}
    	if (value < n.value) {
    		return searchPlayer(n.leftChild, value);
    	}else {
    		return searchPlayer(n.rightChild, value);
    	}
    	//return null;
    }
    
    
    /**
     * This method is used to search for the node in tree and return node.
     * @param n the root of tree.
     * @param value the value that is used to search.
     * @return return the node.
     * The running time is O(logn).
     */
    private AVLPlayerNode searchNode(AVLPlayerNode n, double value) {
    	if (n == null || n.value == value) {
    		return n;
    	}
    	if (value < n.value) {
    		return searchNode(n.leftChild,value);
    	}else {
    		return searchNode(n.rightChild, value);
    	}
    }
    
    
    /**
     * This method is used to calculate the number of node in right tree.
     * @param n the node that is used to calculated.
     * @return return the number of nodes in right tree.
     * The runninghtime is O(logn).
     */
    private int rightW(AVLPlayerNode n) {
    	if (n == null) {
    		return 0;	
    	}else {
    		return 1 + rightW(n.leftChild) + rightW(n.rightChild);
    	}
    }
    
    
    /**
     * 
     * @param n
     */
    /*
    private void inOrderRWUpdate(AVLPlayerNode n) {
    	int o = 0;
    	if (n == null) {
    		return ;
    	}else {
    		inOrderRWUpdate(n.rightChild);
    		o++;
    		n.rightWeight += o;
    		inOrderRWUpdate(n.leftChild);
    	    
    	}
    }*/
    
    
    /**
     * This method is used to get the number of nodes larger than given nodes
     * @param n the root
     * @param value the value used to find node
     * @return return the rank of node
     * The running time is O(n)
     */
    public int rank(AVLPlayerNode n, double value) {
    	if(n == null) {
    		return 0;
    	}else {
    		if (n.value == value) {
    			return n.rightWeight + 1;
    		}
    		else if (value > root.value) {
    			return rank(n.rightChild, value);
    		}
    		else {
    			return rank(n.leftChild, value) + n.rightWeight + 1;
    		}
    	}
        
   }
    
    /**
     * This method is used to do the in-order traverse in tree.
     * @param n the node that used to start
     * @return return the string with node.
     * The running time is O(n).
     */
    private String inOrder(AVLPlayerNode n) {
    	
    	
    	if (n == null) {
    		return "";
    	}else {
    		
    		//System.out.print("not null");
    		string += "(";
    		inOrder(n.leftChild);
    		string += (n.data.getName());
    		
    		//string += "(";
    		inOrder(n.rightChild);
    		string += ")";
    		//string += (n.data + ")");
    		return string;
    	}
    	
    }
    
    /**
     * This method is used to do a in-order traverse from large to small in tree.
     * @param n the node that used to start.
     * @return return a String.
     * The running time is O(n).
     */
    private String inReOrder(AVLPlayerNode n) {
    	if (n == null) {
    		return "";
    	}else {
    		inReOrder(n.rightChild);
    		board += n.data.getName() + "          " + n.data.getID() + "     " + n.data.getELO() + "\n";
    		inReOrder(n.leftChild);
    	    return board;
    	}
    }
    
    /**
     * This method is used to find the minimum node in the tree with given node.
     * @param n given as root to find the minimum node in the tree.
     * @return return the minimum node.
     * The running O(logn).
     */
    private AVLPlayerNode minNode(AVLPlayerNode n) {
    	if (n == null) {
    		return n;
    	}else {
    		return minNode(n.leftChild);
    	}
    }
    
    /**
     * This method is used to find the success of a node.
     * @param n the node that used to find the successor.
     * @return return the successor.
     * The running time is O(logn).
     */
    private AVLPlayerNode successor(AVLPlayerNode n) {
    	if (n.rightChild != null) {
    		return minNode(n.rightChild);
    	}else {
    		w = n.parent;
    		while(w != null && n == w.rightChild) {
    			n = w;
    			w = w.parent;
    		}
    		return w;
    	}
    }
	
}
    
	
