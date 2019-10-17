/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linkedlist;
import Records.Student;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 *
 * @author Acer
 */
public class doublyLinkedList{
    Node head;
    Node tail;
    int size;
    
    public doublyLinkedList() {
        size = 0;
    }
    
    public class Node {
        public Student element;
        Node next;
        Node prev;
 
        public Node(Student element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    
    public int size() { return size; }
     
    public boolean isEmpty() { return size == 0; }
     
     
    public void insertAtLast(Student element) {
         
        Node tmp = new Node(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
    }

    public void deleteAtPos(Node ptr){
        
        if (ptr.prev!= null){
            ptr.prev.next = ptr.next;
        }
        
        if (ptr.next!= null){
            ptr.next.prev = ptr.prev;
        }
        ptr = null;
        size--;
    }

    public void iterateForward(){
        Node tmp = head;
        while(tmp != null){
            ((Student)tmp.element).printRecord();
            tmp = tmp.next;
        }
    }
    
    public Node getElement(int index){
        Node ptr = head;
        for (int i = 0; i <= size; i++){
            if (i == index){
                return ptr;
            }
            ptr = ptr.next;            
        }
        return null;
    }
    
    public void saveRecords(BufferedWriter bw) throws IOException{
        Node tmp = head;
        while(tmp != null){
            ((Student)tmp.element).saveRecords(bw);
            tmp = tmp.next;
        }
    }
}
