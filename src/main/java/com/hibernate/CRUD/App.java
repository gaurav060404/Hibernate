package com.hibernate.CRUD;
import org.hibernate.*;
import java.util.*;

class EmpHandler {
	private int empid;
	private String fname,lname,email;
	
	private Transaction tran = null;
	private Session session;
	private Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("deprecation")
	public void addemp() {
		try {
		System.out.println("--------------------------");
		System.out.println("Add record");
		System.out.println("--------------------------");
		session = HibernateUtil.getSessionFactory().openSession();
		tran = session.beginTransaction();

		 System.out.print("Enter the empid :");
		 empid = sc.nextInt();
		 System.out.print("Enter the first name :");
		 fname = sc.next();
		 System.out.print("Enter the last name :");
		 lname = sc.next();
		 System.out.print("Enter the email :");
		 email = sc.next();

		 Employee emp = new Employee(empid,fname,lname,email);
		 session.save(emp);
		 tran.commit();
		 System.out.println("Record added successfully!");
		 }
		 catch (Exception e) {
		 System.out.println("Error :" + e.getMessage());
		 }
		 session.close();
		}
	
	@SuppressWarnings("unchecked")
	public void getallemps() {
	    session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        System.out.println("--------------------------");
	        System.out.println("Show all records");
	        System.out.println("--------------------------");
	        List<Employee> lstemps = session.createQuery("from Employee").list();
	        System.out.println("-----------------------------------");
	        System.out.println("Empid\tFirst Name\tLast Name\tEmail");
	        System.out.println("-----------------------------------");
	        for (Employee emp : lstemps) {
	            System.out.println(emp.getId() + "\t" + emp.getFirstName() + "\t" + emp.getLastName() + "\t\t" + emp.getEmail());
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        session.close();
	    }
	}

	
	public void getEmpID() {
	    session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        System.out.println("--------------------------");
	        System.out.println("Search record by ID");
	        System.out.println("--------------------------");
	        System.out.print("Enter the ID to search: ");
	        empid = sc.nextInt();
	        Employee tempemp = (Employee) session.get(Employee.class, empid);
	        if (tempemp != null) {
	            System.out.println("Emp Id :" + tempemp.getId());
	            System.out.println("Employee's First Name: " + tempemp.getFirstName());
	            System.out.println("Employee's Last Name: " + tempemp.getLastName());
	            System.out.println("Email: " + tempemp.getEmail());
	        } else {
	            System.out.println("Record does not exist!");
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        session.close();
	    }
	}

	
	public void updateemp() {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
		System.out.println("--------------------------");
		System.out.println("Update record");
		System.out.println("--------------------------");
		 tran = session.beginTransaction();
		 System.out.print("Enter the Empid to be update:");
		 empid = sc.nextInt();
		 Employee tempemp = (Employee) session.get(Employee.class, empid);
		 if(tempemp != null) {
		 System.out.println("Old Data");
		 System.out.println("EmpId :" + tempemp.getId());
		 System.out.println("First Name :" + tempemp.getFirstName());
		 System.out.println("Last Name :" + tempemp.getLastName());
		 System.out.println("Email :" + tempemp.getEmail());
		 System.out.println("New Data");
		 System.out.print("Enter the first name :");
		 fname = sc.next();
		 System.out.print("Enter the last name :");
		 lname = sc.next();
		 System.out.print("Enter the email :");
		 email = sc.next();

		 tempemp.setId(empid);
		 tempemp.setFirstName(fname);
		 tempemp.setLasttName(lname);
		 tempemp.setEmail(email);

		 session.persist(tempemp);
		 tran.commit();
		 System.out.println("Record updated successfully!");
		 }
		 else
		 System.out.println("Record does not exists!");
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		}
		session.close();
		}
	
	@SuppressWarnings("deprecation")
	public void deleteemp() {
		session = HibernateUtil.getSessionFactory().openSession();
		try {
		System.out.println("--------------------------");
		System.out.println("Delete record");
		System.out.println("--------------------------");
		 tran = session.beginTransaction();
		 System.out.print("Enter the rollno to be delete:");
		 empid = sc.nextInt();
		 Employee tempemp = (Employee) session.get(Employee.class,empid);
		 if(tempemp != null) {
		 session.delete(tempemp);
		 tran.commit();
		 System.out.println("Record deleted successfully!");
		 }
		 else
		 System.out.println("Record does not exists!");
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		}
		session.close();
		}
}

public class App
{
 public static void main( String[] args )
 {
 EmpHandler emp = new EmpHandler();
 Scanner sc = new Scanner(System.in);
 int choice = 0;

 while(choice != 6) {
 System.out.println("1...Insert Record");
 System.out.println("2...Edit Record");
 System.out.println("3...Delete Record");
 System.out.println("4...Show All");
 System.out.println("5...Search by EmpID");
 System.out.println("6...Exit");

 System.out.print("Enter the valid choice :");
 choice = sc.nextInt();

 switch(choice) {
 case 1:
 emp.addemp();
 break;
 case 2:
 emp.updateemp();
 break;
 case 3:
 emp.deleteemp();
 break;
 case 4:
 emp.getallemps();
 break;
 case 5:
 emp.getEmpID();
 break;
 case 6:
 break;
 default:
	 System.out.println("Invalid choice !");
	 break;
}
}
}
}
