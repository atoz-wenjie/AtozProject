package net.codeJava.atozMain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.BankTransaction;
import net.codeJava.atozMain.repository.BankTransactionRepository;

@Service
public class BankTransactionService {

	@Autowired
	private BankTransactionRepository bankTransactionRepository;
	
	public BankTransaction findAvailableBank() {
		return bankTransactionRepository.findAvailableBanks().get(0);
	}
	
	public List<BankTransaction> findAvailableBanks() {
		return bankTransactionRepository.findAvailableBanks();
	}
	
}
