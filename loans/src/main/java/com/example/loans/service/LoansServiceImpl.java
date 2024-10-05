package com.example.loans.service;

import com.example.loans.constants.LoansConstants;
import com.example.loans.dto.LoansDTO;
import com.example.loans.entity.Loans;
import com.example.loans.exception.LoanAlreadyExistsException;
import com.example.loans.exception.ResourceNotFoundException;
import com.example.loans.mapper.LoansMapper;
import com.example.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService{
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if (loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans loan = new Loans();

        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loan;
    }

    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));

        return LoansMapper.mapToLoansDTO(loan,new LoansDTO());
    }

    @Override
    public boolean updateLoan(LoansDTO loansDTO) {
        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        return false;
    }
}
