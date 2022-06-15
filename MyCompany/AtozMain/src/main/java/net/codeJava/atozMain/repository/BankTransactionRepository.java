package net.codeJava.atozMain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import net.codeJava.atozEntity.BankTransaction;

@Transactional
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{
	
//	@Query("SELECT t1.id FROM BankTransaction t1 where t1.currentTransaction="
//			+ "(SELECT MIN(t1.currentTransaction) FROM BankTransaction t1 "
//			+ "ORDER BY RAND()")
//	List<BankTransaction> findAvailableBanks();
	
	@Query("SELECT t1 FROM BankTransaction t1 where t1.currentTransaction=\r\n"
			+ "(SELECT MIN(t1.currentTransaction) FROM BankTransaction t1) ORDER BY RAND ()")
	List<BankTransaction> findAvailableBanks();

}
