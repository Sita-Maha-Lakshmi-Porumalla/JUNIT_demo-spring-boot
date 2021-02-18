package com.rame.demospringboot;


import com.rame.demospringboot.model.Customer;
import com.rame.demospringboot.model.Student;
import com.rame.demospringboot.repository.CustomerRepository;
import com.rame.demospringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringBootApplication {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private StudentRepository studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}


	@Bean
	public CommandLineRunner insertRecords() {
		return (args) -> {
			// insert records
			custRepo.save(new Customer("Sita", "Maha"));
			custRepo.save(new Customer("Lakshmi", "Porumalla"));
			custRepo.save(new Customer("Bill", "Gates"));
			custRepo.save(new Customer("Rob", "Johnson"));
			custRepo.save(new Customer("Jeff", "Bezos"));


			//Student records
			studentRepo.save(new Student("Sita", "Porumalla", "Sita123@gmail.com", 20));
			studentRepo.save(new Student("Maha", "Lakshmi", "Maha789@gmail.com", 20));
			studentRepo.save(new Student("Jhansi", "Shoe", "Jhansi456@gmail.com", 21));
			studentRepo.save(new Student("Sweety", "Shetty", "Swetty852@gmail.com", 22));


		};
	}
}
