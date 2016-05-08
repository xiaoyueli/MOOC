/* HashTableChained.java */

package dict;

import list.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	private Object[] buckets;
	private int prime;
	private int bucketNumber;
	private int coeA;
	private int coeB;
	private double loadFactor;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.

	  bucketNumber = NextPrime((int)(sizeEstimate * 1.5));
	  buckets = new Object[bucketNumber];
	  prime = InitialPrime(bucketNumber, 5);
	  InitialCoeOfCompFunc(prime);
	  loadFactor = sizeEstimate * 1.0 / bucketNumber;
	  

	  
  }
  
  public double getLoadFactor(){
	  return loadFactor;
  }
  
  
  /**
   * @param number
   * @return return true if the number is prime, false otherwise.
   */
  private boolean IsPrime(int number){
	  
	  for(int divisor = 2; divisor < number; divisor ++ ){
		  if(number % divisor == 0){
			  return false;
		  }
	  }         
	  return true;
  }
  
  /**
   * @param curNumber
   * @return the next prime number of curNumber.
   */
  
  private int NextPrime(int curNumber){
	  
	  do{
		curNumber ++;  
	  }while(!IsPrime(curNumber));
	  return curNumber;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  bucketNumber = NextPrime(100);
	  buckets = new Object[bucketNumber];
	  prime = InitialPrime(bucketNumber, 5);
	  InitialCoeOfCompFunc(prime);
	  loadFactor = 100.0 / bucketNumber;

  }
  
  /**
   * Compute the nth prime after num to guarantee the prime is substantially bigger than num.
   * @param num bucketNumber.
   * @param n how many primes after bucketNumber. 
   * @return the nth prime after bucketNumber.
   */
  
  protected int InitialPrime(int num , int n){
	  do{
		 num = NextPrime(num); 
		 n --;
	  }while(n > 0);
	  return num;
  }
  
  /**
   *  Initialize the coefficient needed by function compFunction, 
   *  where coeA and CoeB are integer between 0 and num - 1, and coeA is not 0.
   *  Initialize them randomly corresponding to the rules above.
   * @param num the prime used in compFunction.
   */

  protected void InitialCoeOfCompFunc(int num){
	  do{
		  coeA = (int)(Math.random()* (num - 1));
	  }while(coeA == 0);
	  coeB = (int)(Math.random()* (num - 1));
  }
  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	  int bucketIdx = Math.abs((coeA * code + coeB) % prime % bucketNumber);
	  
    return bucketIdx;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
	  int sum = 0;
	  for(List l: (List[])buckets){
		  if(l != null){
			  sum += l.length();
		  }
	  }
    return sum;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	  if(size() != 0){
		  return false;
	  }
    return true;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  int hashcode = key.hashCode();
	  int idx = compFunction(hashcode);
	  List l = (DList)buckets[idx];
	  Entry entry = new Entry();
	  entry.key = key;
	  entry.value = value;
	  if(buckets[idx] == null){
		  l = new DList();
		  buckets[idx] = l;
	  }
	  l.insertBack(entry);
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	  Entry targetEntry = null;
	  int hashcode = key.hashCode();
	  int idx = compFunction(hashcode);
	  List l = (List)buckets[idx];
	  if(l != null){
		  ListNode node = l.back();
		  try{
			  while(node.isValidNode() && !((Entry)(node.item())).key.equals(key)){
				node = node.next();  
			  }
			  if(node.isValidNode()){
				  targetEntry = (Entry)(node.item());
			  }
		  }
		  catch(InvalidNodeException e){
			  System.out.println("Invalid Node in find().");
		  }
	  }

    return targetEntry;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  Entry targetEntry = null;
	  int hashcode = key.hashCode();
	  int idx = compFunction(hashcode);
	  List l = (List)buckets[idx];
	  if(buckets[idx] != null){
		  ListNode node = l.front();
		  try{
			  while(node.isValidNode() && !((Entry)(node.item())).key.equals(key)){
				  node = node.next();
			  }
			  if(node.isValidNode()){
				  targetEntry = (Entry)(node.item());
				  node.remove();
			  }
		  }
		  catch(InvalidNodeException e){
			  System.out.println("Invalid Node in remove().");
		  }

		  
	  }
    return targetEntry;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.

	  for(int idx = 0; idx < buckets.length; idx ++){
		  if(buckets[idx] != null){
			  List l = (List)buckets[idx];
			  ListNode node = l.front();
			  try{
				  while(node.isValidNode()){
					  ListNode temp = node.next();
					  node.remove();
					  node = temp;
				  }
				  buckets[idx] = null;
			  }
			  catch(InvalidNodeException e){
				  System.out.println("Invalid Node in makeEmpty().");
			  }

		  }
	  }
  }
  
  public void distribution(){
	  int cnt = 0;
	  while( cnt < bucketNumber){
		  
		  if(buckets[cnt] != null){
			  System.out.print("[" + ((List)buckets[cnt]).length() + "]");
		  }
		  else{
			  System.out.print("[0]");
		  }
		  cnt ++;
		  if(cnt % 15 == 0){
			  System.out.println();
		  } 
		  
	  }
  }
  
  public int computeCollisions()
  {
	  int sum = 0;
	  for(int idx = 0; idx < bucketNumber; idx ++){
		  if(buckets[idx] != null && ((List)buckets[idx]).length() > 1){
			  sum += ((List)buckets[idx]).length() - 1;
		  }
	  }
	  return sum;
  }
  
  public int expectedCollisions(int sizeEstimate){
	  return sizeEstimate - bucketNumber + (int)(bucketNumber * Math.pow(1 - 1.0 / bucketNumber, sizeEstimate));
  }

}