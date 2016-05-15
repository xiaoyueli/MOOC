package homework8;

/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 100000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
	  LinkedQueue newQueue = new LinkedQueue();
	  try{
		  while(!q.isEmpty()){
			  LinkedQueue temp = new LinkedQueue();
			  temp.enqueue(q.dequeue());
			  newQueue.enqueue(temp);
		  }  
	  }
	  catch (QueueEmptyException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in makeQueueOfQueues.");
	  }
    return newQueue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
	  LinkedQueue q3 = new LinkedQueue();
	  try{
		  while(!q1.isEmpty()&&!q2.isEmpty()){
			  if(((Comparable)(q1.front())).compareTo((Comparable)(q2.front())) <= 0){
				  q3.enqueue(q1.dequeue());;
			  }
			  else{
				  q3.enqueue(q2.dequeue());
			  }
		  }
		  while(!q1.isEmpty()){
			  q3.enqueue(q1.dequeue());;
		  }
		  while(!q2.isEmpty()){
			  q3.enqueue(q2.dequeue());
		  }
	  }
	  catch(QueueEmptyException e){
		  System.out.println("Error in mergeSortedQueues.");
	  }
    return q3;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
	  try{
		  while(!qIn.isEmpty()){ 
			  if(pivot.compareTo((Comparable)(qIn.front())) == 0){
				  qEquals.enqueue(qIn.dequeue());
			  }
			  else if(pivot.compareTo((Comparable)(qIn.front())) < 0){
				  qLarge.enqueue(qIn.dequeue());
			  }
			  else{
				  qSmall.enqueue(qIn.dequeue());
			  }
		  }
	  }
	  catch(QueueEmptyException e){
		  
	  }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
	  try{
		  LinkedQueue temp = makeQueueOfQueues(q);
		  
		  while(temp.size() > 1){
			  LinkedQueue q1 = (LinkedQueue)temp.dequeue();
			  LinkedQueue q2 = (LinkedQueue)temp.dequeue();
			  LinkedQueue q3 = mergeSortedQueues(q1, q2);
			  temp.enqueue(q3);;
			  
		  }
		  q.append((LinkedQueue)temp.dequeue());;
	  }
	  catch(QueueEmptyException e){
		  System.out.println("Error in mergeSort");
	  }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
	  if(q != null){
		  int idxPivot = (int)Math.round(q.size());
		  if(q.size() > 1){
			  LinkedQueue small = new LinkedQueue();
			  LinkedQueue large = new LinkedQueue();
			  LinkedQueue equal = new LinkedQueue();
			  partition(q, (Comparable)q.nth(idxPivot), small, equal, large);
			  quickSort(small);
			  quickSort(large);
			  q.append(small);
			  q.append(equal);
			  q.append(large);
			  
		  }
	  }
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III. */
    Timer stopWatch = new Timer();
    q = makeRandom(100);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + 100 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(100);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + 100 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
    q = makeRandom(1000);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + 1000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(1000);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + 1000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
    q = makeRandom(10000);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + 10000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(10000);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + 10000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
    q = makeRandom(100000);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + 100000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(100000);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + 100000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
    q = makeRandom(1000000);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + 1000000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(1000000);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + 1000000 + " Integers:  " +
                       stopWatch.elapsed() + " msec.");


  }

}
