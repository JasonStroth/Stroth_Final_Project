/*
                                      @Author Jeffrey Jason Stroth
 */
public class TrilinkTree 
{
    private Node root = new Node();            

   public int find(int key)  // finds integer and returns results
      {
      int results;
      Node curNode = root;
      int childNumber;
      while(true)
         {
         if(( childNumber = curNode.findItem(key)) != -1)
         {
             results = 0;
             return results;
         }
         else if(curNode.isLeaf())
         {
             results = 1;
             return results;
         }
         else                                 
            curNode = getNextChild(curNode, key);
         }  // end while
      }

   public void insert(int dValue) //inserts new value
      {
      Node curNode = root;
      DataItem tempItem = new DataItem(dValue);
      
      while(true)
         {
         if(curNode.isFull())             
            {
            split(curNode);                   
            curNode = curNode.getParent();                                                
            curNode = getNextChild(curNode, dValue);
            }  
         else if( curNode.isLeaf())          
            break;                            
        
         else
            curNode = getNextChild(curNode, dValue);
         } 

      curNode.insertItem(tempItem);       // insert new DataItem
      }  

   public void split(Node thisNode)     // split the node
      {
      DataItem itemB, itemC;
      Node parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();   
      itemB = thisNode.removeItem();    
      child2 = thisNode.disconnectChild(2); 
      child3 = thisNode.disconnectChild(3); 

      Node newRight = new Node();      

      if(thisNode==root)                
         {
         root = new Node();             
         parent = root;                   
         root.connectChild(0, thisNode);   
         }
      else                             
         parent = thisNode.getParent();    

      
      itemIndex = parent.insertItem(itemB); 
      int n = parent.getNumItems();         

      for(int j=n-1; j>itemIndex; j--)          
         {                                      
         Node temp = parent.disconnectChild(j); 
         parent.connectChild(j+1, temp);        
         }
                                  
      parent.connectChild(itemIndex+1, newRight);

      
      newRight.insertItem(itemC);       
      newRight.connectChild(0, child2); 
      newRight.connectChild(1, child3); 
      }  
   
   public Node getNextChild(Node theNode, int theValue) // gets Low Medium or High Node
      {
      int n;      
      int numItems = theNode.getNumItems();
      for(n=0; n<numItems; n++)         
         {                             
         if( theValue < theNode.getItem(n).dData )
            return theNode.getLow(n);               // return Low Node
         if( theValue > theNode.getItem(n).dData)
            return theNode.getHi(theValue);         //return High Node
         }                    
      return theNode.getMed(n);                     // return Medium Node
      }

   public void displayTree()
      {
      recDisplayTree(root, 0, 0);
      }

   private void recDisplayTree(Node thisNode, int level,int childNumber)
      {
      System.out.print("level="+level+" child="+childNumber+" ");
      thisNode.displayNode();               // display this node

      // call ourselves for each child of this node
      int numItems = thisNode.getNumItems();
      for(int j=0; j<numItems+1; j++)
         {
         Node nextNode = thisNode.getChild(j);
         if(nextNode != null)
            recDisplayTree(nextNode, level+1, j);
         else
            return;
         }
      } 
}
