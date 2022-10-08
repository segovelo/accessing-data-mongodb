package com.example.accessingdatamongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

  private static Logger logger = LoggerFactory.getLogger(AccessingDataMongodbApplication.class);
  @Autowired
  private CustomerRepository repository;

  public static void main(String[] args) {
	  try {
	  ApplicationContext ctx = SpringApplication.run(AccessingDataMongodbApplication.class, args);
	  Environment environment = ctx.getEnvironment();
	  
      logger.info("Accessing-Data-MongoDB Application started successfully !");
      logger.info(
          "Application listening on port: {}", environment.getProperty("local.server.port"));
    } catch (Exception ex) {
      logger.error("Accessing-Data-MongoDB Application failed :( *** ", ex);
      throw new RuntimeException();
    }
  }

  @Override
  public void run(String... args) throws Exception {

    repository.deleteAll();

    // save a couple of customers
    repository.save(new Customer("Alice", "Smith"));
    repository.save(new Customer("Bob", "Smith"));
    repository.save(new Customer("Frederick", "Daniels"));
    repository.save(new Customer("Claire", "Boston"));
    repository.save(new Customer("Marion", "Sernerio"));
    repository.save(new Customer("Burton", "Clover"));
    repository.save(new Customer("Gerald", "Smith"));
    repository.save(new Customer("Juliet", "Williams"));
    repository.save(new Customer("Holbert", "Saunders"));
    repository.save(new Customer("Alice", "Williams"));
    // fetch all customers
    System.out.println("\n\nCustomers found with findAll():");
    System.out.println("-------------------------------");
    for (Customer customer : repository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("\nCustomer found with findByFirstName('Alice'):");
    System.out.println("--------------------------------");
    for (Customer customer : repository.findByFirstName("Alice")) {
        System.out.println(customer);
    }

    System.out.println("====================================\n"); 
    System.out.println("Customers found with findByLastName('Smith'):");
    System.out.println("--------------------------------");
    for (Customer customer : repository.findByLastName("Smith")) {
      System.out.println(customer);
    }
    System.out.println("\n\n");
  }

}