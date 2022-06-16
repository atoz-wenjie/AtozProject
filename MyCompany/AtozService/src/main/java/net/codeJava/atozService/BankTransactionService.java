package net.codeJava.atozService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.BankTransaction;
import net.codeJava.atozRepository.BankTransactionRepository;

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
