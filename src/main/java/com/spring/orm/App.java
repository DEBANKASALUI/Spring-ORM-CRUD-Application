package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entity.Student;

public class App {
	public static void main(String[] args) {
		System.out.println("Spring ORM started!...");
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");
		StudentDao studentDao = context.getBean("dao", StudentDao.class);

//		INSERT
//		Student stu = new Student(103, "ABC Potter", "London");
//		int icount = studentDao.insert(stu);
//		System.out.println("Number of records inserted: " + icount);
//		System.out.println("Record Inserted successsfully!...");

//		SELECT single object
//		Student stu1 = studentDao.getStudent(100);
//		System.out.println(stu1);

//		SELECT multiple objects
//		List<Student> allStudents = studentDao.getAllStudents();
//		allStudents.forEach(i -> System.out.println(i));

//		UPDATE
//		Student stu2 = new Student();
//		stu2.setId(100);
//		stu2.setCity("Germany");
//		studentDao.change(stu2);
//		System.out.println("Record Updated successsfully!...");		

//		DELETE
//		studentDao.delete(101);
//		System.out.println("Record Deleted successsfully!...");		
		
//ORM APP		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcom to the Spring ORM Application!...");
		
		while(true) {
		System.out.println("PRESS 1 to INSERT new Student");
		System.out.println("PRESS 2 to SELECT all Students");
		System.out.println("PRESS 3 to SELECT 1 Student");
		System.out.println("PRESS 4 to DELETE Student");
		System.out.println("PRESS 5 to UPDTAE Student Details");
		System.out.println("PRESS 6 to EXIT APP");
		
		try {
			int input = Integer.parseInt(br.readLine());
			switch(input) {
			case 1: //INSERT
				System.out.println("Enter User ID: ");
				int uId=Integer.parseInt(br.readLine());
				System.out.println("Enter User Name: ");
				String uName=br.readLine();
				System.out.println("Enter User City: ");
				String uCity=br.readLine();
				
				Student s= new Student(uId,uName,uCity);
				int insertCount = studentDao.insert(s);
				System.out.println("Number of records inserted: " + insertCount);
				System.out.println("Record Inserted successsfully!...");
				break;	
			case 2: //DISPLAY ALL
				List<Student> allStudents = studentDao.getAllStudents();
				allStudents.forEach(i -> System.out.println(i));
				
				break;	
			case 3: //DISPLAY SINGLE
				System.out.println("Enter User ID to be DISPLAYED: ");
				int suserId=Integer.parseInt(br.readLine());
				Student s1 = studentDao.getStudent(suserId);
				System.out.println(s1);
				break;
			case 4: //DELETE
				System.out.println("Enter User ID to be DELETED: ");
				int duserId=Integer.parseInt(br.readLine());
				studentDao.delete(duserId);
				System.out.println("Record Deleted successsfully!...");
				break;
			case 5://UPDATE
				System.out.println("Enter the student id to be updated");
				int updatedId = Integer.parseInt(br.readLine());
				
				System.out.println("Press 1 for update the name");
				System.out.println("Press 2 for update the city");
				System.out.println("Press 3 for update both city and name");
				
				
				System.out.println("Enter your choice for field to update: ");
				int choice = Integer.parseInt(br.readLine());
				
				Student std1 = studentDao.getStudent(updatedId);
				
				String updatedName = std1.getName();
				String updatedCity = std1.getCity();
				
				switch (choice)
				{
				case 1:
					System.out.println("Enter the name to be updated");
					updatedName = br.readLine();
					//setting the student objects value
					std1 = new Student(updatedId, updatedName, updatedCity);
					System.out.println("Name updated successfully");
					studentDao.change(std1);
					System.out.println("Updated successfully\n");
					break;

				case 2:
					System.out.println("Enter the city name to be updated");
					updatedCity = br.readLine();
					//setting the student objects value in another way
						std1.setId(updatedId);
						std1.setName(updatedName);
						std1.setCity(updatedCity);
					System.out.println("City updated successfully");
					studentDao.change(std1);
					System.out.println("Updated successfully\n");
					
					break;
					
				case 3: 
					System.out.println("Enter User ID Again to confirm: ");
					int upId=Integer.parseInt(br.readLine());
					System.out.println("Updated name: ");
					String upName=br.readLine();
					System.out.println("Updated City: ");
					String upCity=br.readLine();
					studentDao.updateStudent(upId, upName, upCity);
					System.out.println(upId+" id Student is updated...!");
				}
				break;
			case 6: 
				System.out.println("Exiting App!..");
				System.exit(0);
				break;
			}
			
		}catch(Exception e) {
			System.out.println("Invalid Input..Try Again!..");
			System.out.println(e.getMessage());
		}
		
		}
		
	}
}
