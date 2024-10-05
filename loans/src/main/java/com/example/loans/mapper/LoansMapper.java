package com.example.loans.mapper;

import com.example.loans.dto.LoansDTO;
import com.example.loans.entity.Loans;

public class LoansMapper {
    public static LoansDTO mapToLoansDTO(Loans loans,LoansDTO loansDTO){
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDTO;
    }

    public static Loans mapToLoans(LoansDTO loansDTO,Loans loans){
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        return loans;
    }
}
