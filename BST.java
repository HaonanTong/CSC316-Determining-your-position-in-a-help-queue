import java.util.HashMap;
import java.util.Map;
public class BST{
	private Node root; // root of BST
	private HashMap<Integer, Node> idList = new HashMap<Integer, Node>();
	private int id_current = 0;//remember the biggest id in current queue 
	
	public BST(){}
	
	/**
	 * Checks if tree is empty
	 */
	public boolean isEmpty(){
		if(root != null){
			return false;
		}
		else
			return true;
	}
	/**
	 * Get the maximum id
	 */
	public int getMaxId(){
		return id_current;
	}
	
	/**
	 * Get the idList
	 */
	public HashMap<Integer, Node> getIdList() {
		return idList;
	}
	
	/**
	 * Set the idList
	 */
	public void setIdList(HashMap<Integer, Node> idList) {
		this.idList = idList;
	}
	
	/**
	 * Get root
	 */
	public Node getRoot() {
		return root;
	}
	
	/**
	 * Set root
	 */
	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * Count of root
	 */
	public int size() {
		return size(root);
	}

	/**
	 * Count of node
	 */
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.count;
	}
	
	/**
	 * Helper function insert node based on priority
	 */
	public Node Insert(Node x , int id, int priority) throws Warning{//insert without the count
		Node tmp = new Node(id , priority, 0);
		//insert to the idList
		idList.put(id, tmp);
		if ( x == null ){
			tmp.count = 1;
			return tmp;			
		}
		else{
			/**if( x.priority == priority ) {
				throw new Warning();
			}*/
			if ( x.priority > priority)//go left
				x.leftchild = Insert(x.leftchild , id , priority );
			else if(x.priority < priority) //go right
				x.rightchild = Insert( x.rightchild, id, priority );
		}
		if( x.leftchild != null && x.rightchild != null)
			x.count = x.leftchild.count + x.rightchild.count + 1 ;
		else if(x.leftchild != null && x.rightchild == null)
			x.count = x.leftchild.count + 1 ;
		else if(x.leftchild == null && x.rightchild != null)
			x.count = x.rightchild.count + 1;
		else
			x.count = 1;
		return x;
	}
	
	/**
	 * Main function insert node based on priority
	 */
	public int Insert( int priority ) throws Warning{
		//id = root.count + 1;
		//count?		//need to get id and count
		int tmpid;
		if(findNode(priority) != null) {
			throw new Warning();
		}
		
		if( root != null )
			tmpid = id_current + 1;//
		else
			tmpid = 1;
		id_current ++;
		root = Insert( root , tmpid , priority );
		return tmpid;
	}
	
	/**
	 * (Unneeded) Main Function traverse through idList for testing.
	 */
	public void idListTraverse(){
		Integer id;
		Node x;
		for(Map.Entry<Integer, Node> entry : idList.entrySet()) {
			id = entry.getKey();
			x = entry.getValue();
			System.out.println("Id: " + id + " / Priority: " + x.priority);
		}
		System.out.println("");
	}
	
	/**
	 * (Unneeded) Main Function traverse through the tree for testing.
	 */
	public String preorder_traverse(){
		return preorder_traverse( root );
	}
	
	/**
	 * (Unneeded) Helper Function traverse through the tree for testing.
	 */
	public String preorder_traverse( Node x ){
		String preOrder = "";
		if( x == null )
			return preOrder;
		preOrder += x.id + " " + x.priority + " " + x.count + "\n" ;
		//System.out.println( x.id + " " + x.priority + " " + x.count);
		preOrder = ( preOrder + preorder_traverse(x.leftchild));
		preOrder = ( preOrder + preorder_traverse(x.rightchild));
		return preOrder;
	}

	/**
	 * Main function removes the max node from the tree
	 */
	public Node removeMax() throws Warning{
		//node x = FindMaxNode();
		//removebypriority(x)
		Node x = removeMaxHelper( root );// Node is the biggest
		Node x_cpy = new Node(x.id, x.priority, x.count);
		//System.out.println(x.priority);
		if (x.id == root.id && x.leftchild == null && x.rightchild == null ){
			root = null;
		}else{
			removebypriority( x.priority );	
		}
		//System.out.println(x_cpy.priority);
		return x_cpy;
	}
	
	/**
	 * Helper function find the max node
	 */
	public Node removeMaxHelper(Node x) throws Warning{
		if(x == null) {
			throw new Warning();
		}
		if (x.rightchild == null) // remove x;
		{
			return x;
		} 
		else {
			return removeMaxHelper( x.rightchild );
		} 
	}

	/**
	 * Main function removes node by id
	 */
	public int removebyid(int id) throws Warning// remove by the information of #id
	{
		//translate id --> priority
			//function tranlation
		if(idList.get(id) == null)
			throw new Warning();
				
		int x = idList.get(id).getPriority();
		int weight = idList.get( id ).priority;
		removebypriority( weight );
		return x;
		
		//search the location for the priority
			//func removethepriority
	}
	
	/**
	 * Finds the node given priority
	 */
	public Node findNode(int priority){
		//find the node
		Node x_pre = root;
		Node x = root;
		if(x == null) {
			return x;
		}
		while(x.priority != priority){
			if( x.priority < priority && x.rightchild != null ) // go right;
				{
					x_pre = x;
					x = x.rightchild;
				}
			else if( x.priority > priority && x.leftchild != null ) // go left;
				{
					x_pre = x;
					x = x.leftchild;
				}
			else {
					return null;
				}
		}
		return x;
	}
	
	/**
	 * Helper function removes node with priority from tree
	 */
	public void removebypriority(int priority)
	{
		//find the node
		Node x_pre = root;
		Node x = root;
		if( root == null || root.leftchild == null || root.rightchild == null){
			// problem
		}
		root.count--;
		while(x.priority != priority){
			if( x.priority < priority && x.rightchild != null ) // go right;
				{
					x_pre = x;
					x = x.rightchild;
					x_pre.rightchild.count--;
				}
			else if( x.priority > priority && x.leftchild != null ) // go left;
				{
					x_pre = x;
					x = x.leftchild;
					x_pre.leftchild.count--;
				}
		}
		
		// x is the node we wanna delete.
		
		// delete the node
		idList.remove(x.getid());
		
		Node tmp = x;
		Node tmp_pre = x;
		//If right child exists
		if (x.rightchild != null) {
			x.rightchild.count--;////////
			tmp = x.rightchild;
			if (tmp.leftchild != null) {
				// while left child exists
				while (tmp.leftchild != null)// go left
				{	
					tmp_pre = tmp;
					tmp_pre.leftchild.count--;
					tmp = tmp.leftchild;
				}
				x.setValue(tmp.id, tmp.priority, x.count);
				tmp_pre.leftchild = tmp.rightchild;
			}
			else{
				x.setValue(tmp.id, tmp.priority, x.count);
				x.rightchild = tmp.rightchild;
			}
		}
		
		//If right child !exists but left child exists;
		else if( x.leftchild != null ){// deleting node do not have right tree, but have a left tree
			x.leftchild.count--;///////
			tmp = x.leftchild;
			if(tmp.rightchild != null )//x.leftchild has right tree.
			{
				while( tmp.rightchild != null ){
					tmp_pre = tmp;
					tmp_pre.rightchild.count--;
					tmp = tmp.rightchild;	
				}
				x.setValue(tmp.id, tmp.priority, x.count);
				tmp_pre.rightchild = tmp.leftchild;
			}// most big one in left tree of deleting node x;
			else{
				x.setValue( tmp.id, tmp.priority, x.count );
				x.leftchild = tmp.leftchild;
				//x.count--;
			}			
		}
		
		else{	//x is the leaf node;
			if( x_pre.leftchild != null && x_pre.leftchild.getid() == x.getid() )//x is the left child of the node x_pre
			{
				x_pre.leftchild = null;
			}
			else
				x_pre.rightchild = null;
		}
	}
	
	/**
	 * (Unneeded) Get position based on priority
	 */
	private int getPosition( int priority ){
		return root.count - getPosition( root, 0 , priority );
	}
	
	/**
	 * Helper function to get position
	 */
	private int getPosition(Node x, int pos , int priority)//initial x = root, pos = 1;
	{
		if (x.priority == priority) {
			if( x.leftchild != null ){
				return pos = ( pos + x.leftchild.count );			
			}
			else{
				return pos;				
			}
		}
		
		else if( x.priority > priority )
			return getPosition( x.leftchild , pos , priority );
		
		else
		{
			if( x.leftchild != null )
				pos = pos + x.leftchild.count + 1;
			else
				pos = pos + 1;
			return getPosition( x.rightchild, pos , priority );
		}
	}
	
	/**
	 * Main function to get position
	 */
	public int Getposition( int id ) throws Warning{
		if(idList.get(id) == null )
		{
			throw new Warning();
		}
		int priority_tmp = idList.get(id).priority;
		return getPosition(priority_tmp);
	}
}