/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b.trees;

import java.io.*;
import Records.Student;
import java.util.Scanner;
import linkedlist.doublyLinkedList;
import linkedlist.doublyLinkedList.Node;

/**
 *
 * @author Acer
 */
public class BTrees {

    static final String fileName = "C:\\Users\\Acer\\Documents\\NetBeansProjects\\B-Trees\\src\\sample.txt";
    static Tree tree = new Tree();
    static doublyLinkedList list = new doublyLinkedList();
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        
        int option = PrintMenu();
        while (option!= 9){
            switch (option){
                case 1:
                    if (CreateRecord())
                        System.out.println("Record has been added!");
                    break;
                case 2:
                    ReviewRecord();
                    break;
                case 3:
                    UpdateRecord();
                    break;
                case 4:
                    DeleteRecord();
                    break;
                case 5:
                    ViewWithinRange();
                    break;
                case 6:
                    ViewAll();
                    break;
                case 7:
                    SaveToFile();
                    break;
                case 8:
                    LoadFromFile();
                    break;
            }
            option = PrintMenu();
                
        }
    }
    
    
    public static int PrintMenu() throws IOException, InterruptedException{  
        System.out.println("Press 1 to Create A Student Record");
        System.out.println("Press 2 to Review A Student Record");
        System.out.println("Press 3 to Update A Student Record");
        System.out.println("Press 4 to Delete A Student Record");
        System.out.println("Some Other Things.....");
        System.out.println("Press 5 to View Records Within Particular Range");
        System.out.println("Press 6 to View All Student's Records");
        System.out.println("Press 7 to Save To File");
        System.out.println("Press 8 to Load From File");
        System.out.println("Press 9 to Quit");
        Scanner sc = new Scanner(System.in);
        return(sc.nextInt());
    }
    
    
    
    public static void LoadFromFile() throws FileNotFoundException, IOException{
        BufferedReader br = null;
        String sCurrentLine;
        br = new BufferedReader(new FileReader(fileName));
        while ((sCurrentLine = br.readLine()) != null) {
            list.insertAtLast(new Student(sCurrentLine));
        }
        br.close();
        BuildIndexTree();
        Scanner s = new Scanner(System.in);
        System.out.println("What order tree you want to build?");
        tree.setOrder(s.nextInt());
    }
    
    public static void SaveToFile() throws FileNotFoundException, IOException{
        FileWriter fwOb = new FileWriter(fileName, false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(fileName,true));
        list.saveRecords(bw);
        bw.close( );
    }
    
    public static void BuildIndexTree(){
        for (int i = 0 ; i < list.size(); i++){
            Node t = (Node)list.getElement(i);
            tree.insert(t,t.element.getRollNo());
        }
    }
    
    public static boolean CreateRecord() throws IOException{
        System.out.println("Enter Record in the form of Roll No,Name,Course1 Grade1,Course2 Grade2,CourseN GradeN");
        System.out.println("Please follow the above format!");
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        String record = br.readLine();
        list.insertAtLast(new Student(record));
        Node t = (Node)list.getElement(list.size()-1);
        tree.insert(t,t.element.getRollNo());
        return true;
    }
    
    public static void ReviewRecord() throws IOException{
        System.out.println("Enter RollNo of the Student");
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        int RollNo =  Integer.valueOf(br.readLine());
        tree.search(RollNo);
    }
    
    public static void UpdateRecord() throws IOException{
        System.out.println("Enter RollNo of the Student");
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        int RollNo =  Integer.valueOf(br.readLine());
        tree.update(RollNo);
    }
    
    public static void ViewWithinRange(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Search from Roll No : ");
        int low = sc.nextInt();
        System.out.print("To Roll No : ");
        int high = sc.nextInt();
        tree.traverseRange(low,high);
    }
    
    public static void ViewAll(){
        tree.traverse();
    }
    
    public static void DeleteRecord() throws IOException{
        System.out.println("Enter RollNo of the Student");
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);  
        int RollNo =  Integer.valueOf(br.readLine());
        tree.remove(RollNo,list);
    }
}
