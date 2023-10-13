package com.csis3275;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

import com.csis3275.banking.model.BankingServiceImpl;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.Credit_group3;

@SpringBootApplication
public class Csis3275Group3ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Csis3275Group3ProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(UserServiceImpl_group3 repository, BankingServiceImpl Bankingrepository,
			CreditServiceImpl creditRepository) {

		return (args) -> {
			// Use https://www.browserling.com/tools/bcrypt to encrypt your password before
			// adding a test user to database

			// User for testing login and authentication email = admin@fake.com, password =
			// password
			User_group3 user1 = new User_group3("admin@fake.com",
					"$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22", "ADMIN", (long) 1);
			repository.createUser(user1);
			User_group3 user2 = new User_group3("user@fake.com",
					"$2a$10$57VDThG1aa./iRozB98QeuPtzADsVY0vfqcKehoDZ9USKe4pu3b22", "USER", (long) 2);
			repository.createUser(user2);

			Banking_group3 checkingAccount = new Banking_group3("Checking Account", 1000.00f, 1000, "Checking");
			Bankingrepository.createBankingAccounts(checkingAccount);

			Banking_group3 savingsAccount = new Banking_group3("Savings Account", 5000.00f, 5000, "Savings");
			Bankingrepository.createBankingAccounts(savingsAccount);

			Credit_group3 cashbackAccount = new Credit_group3("Cashback Card", 628.25f, 3000, 0.10f, "Cashback");
			creditRepository.createCreditCardAccounts(cashbackAccount);

			Credit_group3 rewardsAccount = new Credit_group3("Rewards Card", 575.0f, 5000, 0.15f, "Rewards");
			creditRepository.createCreditCardAccounts(rewardsAccount);
		};
	}

}
