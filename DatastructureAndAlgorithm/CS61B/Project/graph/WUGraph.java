/* WUGraph.java */

package graph;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
	HashTable vTable;
	HashTable eTable;
	List vList;
	List eList;

  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
  public WUGraph(){
	  vTable = new HashTable();
	  eTable = new HashTable();
	  vList = new List();
	  eList = new List();
	  
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
	  return vList.getSize();
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   *
   * Running time:  O(1).
   */
  public int edgeCount(){
	  return eList.getSize();
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
	  Object[] vertex = new Object[vTable.getAmount()];
	  ListNode node = vList.front();
	  for(int idx = 0; idx < vTable.getAmount(); idx ++){
		  vertex[idx] = node.item;
		  node = node.next;
	  }
	  
	  return vertex;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){

	  if(!vTable.isExist(vertex)){
		  vTable.addItem(vertex);
		  ListNode node = vTable.getNode(vertex);
		  vList.addNode(node);
		  vList = vTable.reHashTable(vList);
	  }

  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
	  if(isVertex(vertex)){
		  ListNode ver = vTable.getNode(vertex);
		  List children = ver.getChildren();
		  
		  if(children != null){

			  ListNode node = children.front();
			  while(node.isValid(children)){
				  VertexPair vp = (VertexPair)(node.item);
				  removeEdge(vp.object1, vp.object2);
				  node = node.next;
			  }
		  }

		  vList.deleteNode(ver);
		  vTable.deleteItem(vertex);
		  vList = vTable.reHashTable(vList);
	  }
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
	  return vTable.isExist(vertex);
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex){
	  if(isVertex(vertex)){
		  return vTable.getNode(vertex).childNum();
	  }
	  return 0;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex){
	  
	  if(isVertex(vertex)){
		  Neighbors nb = new Neighbors();
		  ListNode ver = vTable.getNode(vertex);
		  List nbs = ver.getChildren();
		  if(nbs != null){
			  int size = nbs.getSize();
			  ListNode nbNode = nbs.front();
			  nb.neighborList = new Object[size];
			  nb.weightList = new int[size];
			  for(int idx = 0; idx < size; idx ++){
				  Object ver1 = ((VertexPair)(nbNode.item)).object1;
				  Object ver2 = ((VertexPair)(nbNode.item)).object2;
				  if(vertex.equals(ver1)){
					  nb.neighborList[idx] = ver2;
				  }	  
				  else{
					  nb.neighborList[idx] = ver1;
				  }
				  nb.weightList[idx] = ((VertexPair)(nbNode.item)).getWeight();
				  nbNode = nbNode.next;
			  }
			  return nb;
		  }
		  
	  }
	  
	  return null;
  }

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   *
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight){
	  if(vTable.isExist(v) && vTable.isExist(u)){
		  VertexPair edge = new VertexPair(u,v,weight);
		  if(eTable.isExist(edge)){
			  ((VertexPair)(eTable.getNode(edge).item)).updateWeight(weight);
		  }
		  else{
			  eTable.addItem(edge);
			  ListNode eNode = eTable.getNode(edge);
			  eList.addNode(eNode);
			  if(u.equals(v)){
				  ListNode ver = vTable.getNode(u);
				  eNode.buildCopy(1);
				  ver.addChild(eNode.getCopy1());
	
			  }
			  else{
				  
				  ListNode ver1 = vTable.getNode(u);
				  ListNode ver2 = vTable.getNode(v);
				  eNode.buildCopy(2);
				  ver1.addChild(eNode.getCopy1());
				  ver2.addChild(eNode.getCopy2());
  
			  }
			  
			  
			  
		  }
	  }
  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v){

	  VertexPair edge = new VertexPair(u, v , 0);
	  if(eTable.isExist(edge)){
	  
		  ListNode eNode = eTable.getNode(edge);

		  
		  if(u.equals(v))
		  {
			  ListNode ver = vTable.getNode(v);
			  ver.removeChild(eNode.getCopy1());
		  }
		  else{
			  ListNode ver1 = vTable.getNode(u);
			  ListNode ver2 = vTable.getNode(v);
			  
			  ver1.removeChild(eNode.getCopy1());
			  ver1.removeChild(eNode.getCopy2());
			  ver2.removeChild(eNode.getCopy1());
			  ver2.removeChild(eNode.getCopy2());

		  }	
		  
		  eList.deleteNode(eNode);
		  eTable.deleteItem(edge);
	  }
  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v){
	  VertexPair edge = new VertexPair(u, v, 0);
	  return eTable.isExist(edge);
  }

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v){
	  VertexPair edge = new VertexPair(u, v , 0);
	  if(eTable.isExist(edge)){
		  edge = (VertexPair)(eTable.getNode(edge).item);
		  return edge.getWeight();
	  }
	  return 0;
  }
  
  public String toString(){
	  StringBuffer result = new StringBuffer("[    ");
	  for(int idx = 0; idx < vertexCount(); idx ++){
		  result.append(" "+ idx + " ");
	  }
	  result.append(" ]\n");
	  Object[] list = getVertices(); 
	  for(int ori = 0; ori < vertexCount(); ori ++){
		  result.append("[ " + ori + ": ");
		  Object vOri= list[ori]; 
		  for(int dest = 0; dest < vertexCount(); dest ++){
			  Object vDest = list[dest];
			  if(isEdge(vOri, vDest)){
				  result.append(" " + weight(vOri, vDest)+ " ");
			  }
			  else{
				  result.append(" * ");
			  }
			  
		  }
		  result.append(" ]\n");
	  }
	  return result.toString();
  }

}
