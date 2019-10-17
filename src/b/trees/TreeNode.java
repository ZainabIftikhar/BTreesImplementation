
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b.trees;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import linkedlist.doublyLinkedList;
import linkedlist.doublyLinkedList.Node;

/**
 *
 * @author Acer
 */
public class TreeNode {
    TreeNode parent;
    Vector keyDataPair;
    Vector childPointers;
    boolean leaf; int t;
    
    
    private class index{
        int key;
        Node ptr;
        
        index(int key, Node ptr){
            this.key = key;
            this.ptr = ptr;
        }
    }
    
    public TreeNode(int degree, boolean l,TreeNode pat){
        t = degree; leaf = l; parent = pat;
        
        keyDataPair = new Vector(2*t-1);
        childPointers = new Vector(2*t);
    }
    
    
    public void addPairKey(Node ptr, int key){
        keyDataPair.add(new index(key, ptr));
    }
    
    public boolean insertKey(Node ptr, int key){
        int i = -1;
        if (keyDataPair.size() == 2*t-1){
           i = split(key);
        }
        if (leaf && i == -1){
            int k = findIndex(key);
            keyDataPair.add(k, new index(key, ptr));
            return true;
        }
       
        TreeNode s;
        if (i!= -1) //node has splited
            return ((TreeNode) parent.childPointers.elementAt(i)).insertKey(ptr,key);
        
        else 
            return ((TreeNode)childPointers.elementAt((findIndex(key)))).insertKey(ptr,key);
    }
    
    
    
    public boolean hasCapacity(){
        return (keyDataPair.size() < 2*t - 1);
    }
    
    public int split(int key){
        TreeNode z = new TreeNode(t,leaf,parent);
        
        for (int j = t; j < keyDataPair.size(); j++) // copy keys in new node
            z.keyDataPair.add(keyDataPair.get(j));
        
        if (leaf == false){ // copy child pointers in new node
            int i = 0;
            for (int j = t; j < childPointers.size(); j++, i++){
                z.childPointers.add(childPointers.get(j));
                ((TreeNode) z.childPointers.get(i)).parent = z;
            }
        }
        
        index value = (index) (keyDataPair.get(t-1));
        for (int j = t - 1; j < 2*t - 1; j++) 
            keyDataPair.remove(t-1);
        if (leaf == false){
            for (int j = t ; j < 2*t ; j++) 
            childPointers.remove(t);
        }
        if (parent == null)  {
            splitRoot(value,z);
            return (parent.findIndex(key));
        }
        
        int i = parent.findIndex(key); //split at middle key
        parent.keyDataPair.add(i, value);
        parent.childPointers.add(i+1, z);
        
        int x = ((index)parent.keyDataPair.get(i)).key;
        
        if (key < x)
            return i;
        else 
            return i+1;
            
    }
    
    private boolean splitRoot(index value, TreeNode z){
        TreeNode temp = new TreeNode(t,false,null);
        temp.keyDataPair.add(value);
        temp.childPointers.add(this);
        temp.childPointers.add(z);
        Tree.root = temp;
        this.parent = temp;
        z.parent = temp;
        
        return true;
    }
    
    private int findIndex(int value){
        int i = 0;
        for (; i < keyDataPair.size() ; i++){
            int key = ((index)keyDataPair.get(i)).key;
            if (key > value)
                break;
        }
        return i;
    }
    
    void traverse(){
        int i;
        for (i = 0; i < keyDataPair.size(); i++){
            if (leaf == false){
                ((TreeNode) childPointers.elementAt(i)).traverse();
            }
            ((index)keyDataPair.elementAt(i)).ptr.element.printRecord();
        }
        if (leaf == false){
            ((TreeNode) childPointers.elementAt(i)).traverse();
        }   
    }
    
    void traverseRange(int low, int high){
        int i;
        for (i = 0; i < keyDataPair.size(); i++){
            if (leaf == false){
                int k = ((index)keyDataPair.elementAt(i)).key;
               ((TreeNode) childPointers.elementAt(i)).traverseRange(low,high);
            }
            
            int k = ((index)keyDataPair.elementAt(i)).key;
            if ((k == low || k > low) && (k == high || k < high))
            ((index)keyDataPair.elementAt(i)).ptr.element.printRecord();
            else
                return;
        }
        if (leaf == false){
            ((TreeNode) childPointers.elementAt(i)).traverseRange(low,high);
        }   
    }
    
    
    Node search(int key){
        int i = 0;
        while (i < keyDataPair.size() && key > ((index)keyDataPair.get(i)).key)
            i++;
        if (((index)keyDataPair.get(i)).key == key) {
            ((index)keyDataPair.elementAt(i)).ptr.element.printRecord();
            return ((index)keyDataPair.elementAt(i)).ptr;
        }
        if (leaf == true){
            System.out.println("Student not found");
            return null;
        }
        return ((TreeNode)childPointers.get(i)).search(key);
    }
    
    boolean update(int key) throws IOException{
        Node s = search(key);
        if (s == null) 
               return false;
            System.out.println("Which field to update?");
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);  
            String field = br.readLine();
            String temp1,temp2;
            switch (field){
                case "Name":
                    System.out.println("Change name to?");
                    temp1 = br.readLine();
                    s.element.setName(temp1);
                    break;
                case "Course":
                    System.out.println("Change course name from?");
                    temp1 = br.readLine();
                    System.out.println("Change course name to?");
                    temp2 = br.readLine();
                    s.element.setCourse(temp1, temp2);
                    break;
                case "Grade":
                    System.out.println("Change grade of?");
                    temp1 = br.readLine();
                    System.out.println("Change grade to");
                    temp2 = br.readLine();
                    s.element.setGrade(temp1, temp2);
                    break;
            }
        return true;
    }
    
    void remove(int key, doublyLinkedList list){
        int k = findIndex(key);
        
        if (leaf){
            if (k > 0 && (int)(((index)keyDataPair.elementAt(k-1)).key) == key){ //case 1
                list.deleteAtPos(((index)keyDataPair.elementAt(k-1)).ptr);
                keyDataPair.removeElementAt(k-1);
            }
            
            else if (k == 0 && (int)(((index)keyDataPair.elementAt(k)).key) == key){
                list.deleteAtPos(((index)keyDataPair.elementAt(k)).ptr);
                keyDataPair.removeElementAt(k);
            }
            else 
                System.out.println("Key Not Present");
        }
        
        else {
            if (k > 0 && (int)(((index)keyDataPair.elementAt(k-1)).key) == key){ //case 2
                //RemoveInternal(k);
            }
            else {  //case 3
                if (((TreeNode)childPointers.elementAt(k)).keyDataPair.size() == t){
                   // Redistribute(k);
                }
            } 
        }
    }
}
