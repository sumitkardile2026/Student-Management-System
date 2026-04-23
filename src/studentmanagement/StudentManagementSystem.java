package studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) throws Exception {

        Scanner sc =new Scanner(System.in);
        int choice;

        do{
            System.out.println("-----Student Management-----");
            System.out.println("1.Add Student");
            System.out.println("2.View Student");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit\n");

            System.out.println("Enter Choice:");
            choice= sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Add Student\n");
                    addStudent(sc);
                    break;

                case 2:
                    System.out.println("View Student\n");
                    viewStudent();
                    break;

                case 3:
                    System.out.println("Search Student\n");
                    searchStudent(sc);
                    break;

                case 4:
                    System.out.println("Update Student\n");
                    updateStudent(sc);
                    break;

                case 5:
                    System.out.println("Delete Student\n");
                    deleteStudent(sc);
                    break;

                case 6:
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Invalid Choice\n");
            }

        }
        while (choice!=6);
        }

//        Add Student
       static void addStudent(Scanner sc) throws Exception{

            System.out.println("Enter Student Id:");
            int id= sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Student Name:");
            String name=sc.nextLine();

            System.out.println("Enter Student City:");
            String city=sc.nextLine();

            System.out.println("Enter Student Email:");
            String email=sc.nextLine();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=  DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "******"
            );

           PreparedStatement ps= con.prepareStatement("Insert into student values(?,?,?,?)");
           ps.setInt(1,id);
           ps.setString(2,name);
           ps.setString(3,city);
           ps.setString(4,email);

           ps.executeUpdate();
           System.out.println("Student Added Successfully...\n");
           con.close();
        }

//        ViewStudent
       static void  viewStudent() throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "******"
            );

            PreparedStatement ps = con.prepareStatement("select * from student");
            ResultSet rs = ps.executeQuery();

           System.out.println("Id   Name    City    Email");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String email = rs.getString("email");

                System.out.println(id+" "+name+" "+city+" "+email);
            }
           System.out.println("\n");
            con.close();
        }

//        Search Student
        static void searchStudent(Scanner sc) throws Exception{
            System.out.println("Enter Id to Search:");
            int id= sc.nextInt();

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "********");

            PreparedStatement ps=con.prepareStatement("select * from student where id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String city = rs.getString("city");
                String email = rs.getString("email");

                System.out.println("Student Found");
                System.out.println(id+" "+name+" "+city+" "+email+"\n");
            }
            else{
                System.out.println("Student Not Found.\n");
            }

            con.close();
         }

//         Upadate Student
       static void updateStudent(Scanner sc)throws Exception{

           System.out.println("Enter Id:");
           int id=sc.nextInt();
           sc.nextLine();

           System.out.println("Enter City:");
           String ci= sc.nextLine();

           Connection con = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/studentdb",
                   "root",
                   "******");

           PreparedStatement ps = con.prepareStatement("update student set city = ? where id = ?");
           ps.setString(1,ci);
           ps.setInt(2,id);

           ps.executeUpdate();
           System.out.println("Update Successfully.\n");

           con.close();
         }

//         Delete Student
   static void deleteStudent(Scanner sc)throws Exception{

       System.out.println("Enter Id");
       int id= sc.nextInt();

       Connection con = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/studentdb",
               "root",
               "*****");

       PreparedStatement ps = con.prepareStatement("delete from student where id = ?");
       ps.setInt(1,id);

       ps.executeUpdate();

       con.close();
       System.out.println("Delete Successfully.\n");
   }
}
