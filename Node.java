public class Node {
	public int id; //id of the callings;
	public int priority; //priority of the calling.
	public int count;	//the node of the tree.
	public Node leftchild, rightchild; // links to subtrees
	
	//private Node parent;
		public void setValue(int id, int priority, int count){
		this.id = id;
		this.priority = priority;
		this.count = count;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public int getid(){
		return id;
	}
	
	public Node(int id, int priority, int count){
		this.id = id;
		this.priority = priority;
		this.count = count;
	}
}