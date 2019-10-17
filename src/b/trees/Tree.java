/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b.trees;
import java.io.IOException;
import linkedlist.doublyLinkedList;
import linkedlist.doublyLinkedList.Node;
/**
 *
 * @author Acer
 */
public class Tree { 
    static TreeNode root;
    int t; 
    
    Tree(int t){
        this.t = t; root = null;
    }

    Tree() {
        root = null;
        t = 2; //default order
    }
    
    void setOrder(int t){
        this.t = t;
    }
    
    void search(int RollNo){ 
        if (root != null)
            root.search(RollNo);
        else {
            System.out.println("Student not found!");
        }
    }
    
    boolean update(int RollNo) throws IOException{
        if (root != null){
           return(root.update(RollNo));
        }
        return false;
    }
    
    boolean delete(int value){        
        return true;
    }
    
    void traverse(){
        if (root != null) 
            root.traverse(); 
    }
    
    void traverseRange(int low, int high){
        if (root != null) 
            root.traverseRange(low,high); 
    }

    void insert(Node ptr, int key) {
       if (root == null){ //Tree is empty
            root = new TreeNode(t,true,null); //root's parent is null
            root.addPairKey(ptr, key);
        }
        else{
            root.insertKey(ptr,key);
        }
    }
    
    void remove(int key,doublyLinkedList list){
        if (root!=null)
            root.remove(key,list);
        else
            System.out.println("Student not found!");       
    }
}
