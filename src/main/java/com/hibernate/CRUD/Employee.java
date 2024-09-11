package com.hibernate.CRUD;
import jakarta.persistence.*;

@Entity
@Table (name = "Emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	@Column(name = "empfname", nullable = false)
	 private String firstName;
	@Column(name = "emplname", nullable = false)
	 private String lastName;
	@Column(name = "empemail", nullable = false)
	private String email;

	 public Employee() {}
	 public Employee(int id, String firstName, String lastName , String email) {
	 this.id = id;
	 this.firstName = firstName;
	 this.lastName = lastName;
	 this.email = email;
	 }
	 
	 public int getId() { return id;}
	 public void setId(int id) { this.id = id;}
	 public String getFirstName() { return firstName; }
	 public void setFirstName(String firstName) { this.firstName = firstName; }
	 public String getLastName() { return lastName; }
	 public void setLasttName(String lastName) { this.lastName = lastName; }
	 public String getEmail() { return email; }
	 public void setEmail(String email) { this.email = email; }
	
}
