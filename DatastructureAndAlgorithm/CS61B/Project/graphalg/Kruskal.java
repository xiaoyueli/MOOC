/* Kruskal.java */

package graphalg;

import graph.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   *
   * @param g The weighted, undirected graph whose MST we want to compute.
   * @return A newly constructed WUGraph representing the MST of g.
   */
  public static WUGraph minSpanTree(WUGraph g){
	  WUGraph mst = new WUGraph();
	  Object[] vers = g.getVertices();
	  HashTable vTable = new HashTable(vers.length);
	  DisjointSets vSet = new DisjointSets(vTable.getBuckets());
	  
	  int edgeCnt = 0;
	  for(int idx = 0; idx < vers.length; idx ++){
		  mst.addVertex(vers[idx]);
		  vTable.addItem(vers[idx]);
		  edgeCnt += g.degree(vers[idx]);
	  }
	  
	  // Get all the edges including counterpart one in g and store them in a miniheap eList;
	  EdgeList eList = new EdgeList(edgeCnt);
	  for(int idx = 0; idx < vers.length; idx ++){
		  Object ver = vers[idx];
		  Neighbors nbs = g.getNeighbors(ver);
		  for(int nbIdx = 0; nbIdx < nbs.neighborList.length; nbIdx ++){
			  EdgeNode eNode = new EdgeNode(ver, nbs.neighborList[nbIdx], nbs.weightList[nbIdx]);
			  eList.insertItem(eNode);
			 
		  }
	  }
	  
	  // Get the smallest weighted edge by order, add it to minSpaningTree if the two vertices are not
	  // connected and they are not in the same set.
	  while(!eList.isEmpty()){
		  EdgeNode eNode = eList.deleteMin(1);
		  Object ver1 = eNode.ver1();
		  Object ver2 = eNode.ver2();
		  if(!mst.isEdge(ver1, ver2)){
			  int root1 = vSet.find(vTable.returnTCode(ver1));
			  int root2 = vSet.find(vTable.returnTCode(ver2));
			  if(root1 != root2){
				  mst.addEdge(ver1, ver2, eNode.getWeight());
				  vSet.union(root1, root2);
				  
			  }
			  

		  }		  
	  }
	  

	  return mst;
  }
  

}