package com.csis3275.loan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.Credit.model.Credit_group3;
import com.csis3275.loan.model.Loan_group3;
import com.csis3275.loan.repositories.ILoanRepository;

@Service
public class LoanServiceImpl {

	@Autowired
	ILoanRepository loanService;
	
	
	//CREATE
	public Loan_group3 createLoans(Loan_group3 newLoan)	{
		return loanService.save(newLoan);
	}
	
	//READ
	public List<Loan_group3> readLoans()	{
		return (List<Loan_group3>)loanService.findAll();
	}
	
	
	//READ BY ID
	public List<Loan_group3> readLoansById(Long Id) {
		return (List<Loan_group3>) loanService.findAllById(Id);
	}
	
	//READ Single loan
		public Loan_group3 readSingleLoanAccount(Long id) {
			return loanService.findById(id).get();
		}
	
	//GET ALL BALANCES 
	
	public List<Float> getAllAccountBalances() {
        List<Loan_group3> accounts = (List<Loan_group3>) loanService.findAll();
        List<Float> accountBalances = accounts.stream()
                .map(Loan_group3::getTotalToBePaid) // Extract the balance of each account
                .toList();
        return accountBalances;
    }
	
	//GET ALL BALANCES BY ID
	
	public List<Float> getAllAccountBalancesById(Long Id) {
        List<Loan_group3> accounts = (List<Loan_group3>) loanService.findAllById(Id);
        List<Float> accountBalances = accounts.stream()
                .map(Loan_group3::getTotalToBePaid) // Extract the balance of each account
                .toList();
        return accountBalances;
    }
}
