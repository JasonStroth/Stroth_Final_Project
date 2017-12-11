/** 
 *Built the node class similar to 234 Tree node with help from https://www.unf.edu/~wkloster/3540/234tree.java
 * 
 * 
 * 
*                                      @Author Jeffrey Jason Stroth
 */
public class Node 
{
  
   private static final int ORDER = 3;   
   private int numItems = 0;                                 // keep count of full nodes
   public int partialFull = 0;                               //keep count of partially full nodes
   private Node parent;
   private Node childArray[] = new Node[ORDER];             // allow split of parent node to lo, mid, hi
   private DataItem itemArray[] = new DataItem[ORDER-1];    //allows each node to hold 2 variables
   private DataItem boolArray[] = new DataItem[ORDER-1];    // boolean flags for variables in nodes

   public Node getLow(int childNum)
      { return childArray[childNum]; }
   public Node getMed(int childNum)
      { return childArray[childNum]; }
   public Node getHi(int childNum)
      { return childArray[childNum]; }
   public Node getChild(int childNum)
      { return childArray[childNum]; }
   public Node getParent()
      { return parent; }
   public boolean isLeaf()
      { return (childArray[0]==null) ? true : false; }
   public int getNumItems()
     { return numItems; }
   public DataItem getItem(int index)   
   { return itemArray[index]; }

   //The following two functions will help count Full Nodes and Partially full Nodes
   public boolean isFull()
      { return (numItems==ORDER-1) ? true : false; }
   
   public boolean isPartialFull()
      { return (numItems==ORDER-2) ? true : false; }

   public int findItem(int key)         // return index of item
   {   
      {                                    
      for(int j=0; j<ORDER-1; j++)         
         {                                 
         if(itemArray[j] == null)          
            break;
         else if(itemArray[j].dData == key)
            return j;
         }
      return -1;
      } 
   }
public int insertItem(DataItem newItem)
{
   numItems++;
   
      int newKey = newItem.dData;
     
      for(int j=ORDER-2; j>=0; j--)       
         {                                 
         if(itemArray[j] == null)          
            continue;                      
         else                              
            {                              
            int itsKey = itemArray[j].dData;
            if(newKey < itsKey)
            {
               itemArray[j+1] = itemArray[j];
               boolArray[j+1] = boolArray[j];
            }
            else
               {
               itemArray[j+1] = newItem;
               boolArray[j+1] = newItem;
               return j+1;              
               }                         
            }  
         }  
      itemArray[0] = newItem;              // insert new item
      boolArray[0] = newItem;              // insert item into flag Array for deletion purposes
    return 0;
}   
   public DataItem removeItem()        
      {      
      DataItem temp = itemArray[numItems-1];  
      itemArray[numItems-1] = null;           
      numItems--;                           
      return temp;                            
      }

   public void deleteItem(int key)        // place a flag in Node to "hide"
      {
       //BoolArray holds values like ItemArray. Make Bool Array unique to find "deleted values"
       //boolArray[key] = -77;
       numItems--;                         // make one less item
      }

   public void displayNode()        
      {
      for(int j=0; j<numItems; j++)
      {
     //    if(boolArray[j] != -77)          //*****Need to fix BoolArray to flag deletes from being displayed.
         itemArray[j].displayItem();   
      }
      System.out.println(" "); 
      }
      
   public void connectChild(int childNum, Node child)
      {
      childArray[childNum] = child;
      if(child != null)
         child.parent = this;
      }

   // disconnect child from this node, return it
   public Node disconnectChild(int childNum)
      {
      Node tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
      }
}
