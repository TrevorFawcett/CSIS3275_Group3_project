package com.csis3275;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csis3275.login.model.User_group3;
import com.csis3275.login.service.UserServiceImpl_group3;

import com.csis3275.banking.model.BankingServiceImpl;
import com.csis3275.banking.model.BankingTransServiceImpl;
import com.csis3275.banking.model.Banking_group3;
import com.csis3275.loan.model.LoanServiceImpl;
import com.csis3275.loan.model.Loan_group3;
import com.csis3275.Credit.model.CreditServiceImpl;
import com.csis3275.Credit.model.CreditTransServiceImpl;
import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.banking.model.BankingTrans_group3;
import com.csis3275.Credit.model.CreditTrans_group3;

@SpringBootApplication
public class Csis3275Group3ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Csis3275Group3ProjectApplication.class, args);
		
		
		
	}

	@Bean
	CommandLineRunner demo(UserServiceImpl_group3 repository, BankingServiceImpl Bankingrepository,
			CreditServiceImpl creditRepository, LoanServiceImpl loanRepository, CreditTransServiceImpl creditTransService
			,BankingTransServiceImpl bankingTranService) {

		return (args) -> {
			// Use https://www.browserling.com/tools/bcrypt to encrypt your password before
			// adding a test user to database

			// User for testing login and authentication email = admin@fake.com, password = password
			
			User_group3 user2 = repository.readUsers().get(1);

			Banking_group3 checkingAccount = new Banking_group3("Checking Account", 1000.00f, 1000, "Checking", user2);
			Bankingrepository.createBankingAccounts(checkingAccount);

			Banking_group3 savingsAccount = new Banking_group3("Savings Account", 5000.00f, 5000, "Savings", user2);
			Bankingrepository.createBankingAccounts(savingsAccount);

			Credit_group3 cashbackAccount = new Credit_group3("Cashback Card", 628.25f, 3000, 0.10f, "Cashback", user2);
			creditRepository.createCreditCardAccounts(cashbackAccount);

			Credit_group3 rewardsAccount = new Credit_group3("Rewards Card", 575.0f, 5000, 0.15f, "Rewards", user2);
			creditRepository.createCreditCardAccounts(rewardsAccount);

			Loan_group3 loan1 = new Loan_group3("Personal Loan", 10000.0f, 5.0f, 4, 11000.0f, user2);
			loanRepository.createLoans(loan1);
			
			Loan_group3 loan2 = new Loan_group3("Home Loan", 200000.0f, 3.5f, 7, 250000.0f, user2);
			loanRepository.createLoans(loan2);
			
			BankingTrans_group3 bankingTrans1 = new BankingTrans_group3("Deposit", 100.0f, "Deposit for savings", 0.0f, 100.0f, checkingAccount,false);
			bankingTranService.createBankingTransaction(bankingTrans1);
			
			BankingTrans_group3 bankingTrans2 = new BankingTrans_group3("Withdrawal", 50.0f, "ATM withdrawal", 50.0f, 0.0f, checkingAccount,false);
			bankingTranService.createBankingTransaction(bankingTrans2);
			
			
			CreditTrans_group3 transaction1 = new CreditTrans_group3("Purchase", "Groceries", 50.0f, cashbackAccount, false);
			creditTransService.createCreditTransaction(transaction1);
			
	        CreditTrans_group3 transaction2 = new CreditTrans_group3("Payment", "Monthly bill", -100.0f, rewardsAccount, false);
	        creditTransService.createCreditTransaction(transaction2);
	        
			
<<<<<<< Updated upstream
		};
	}
=======
		}; 
	} 
>>>>>>> Stashed changes

}
