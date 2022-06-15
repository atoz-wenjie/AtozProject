package net.codeJava.atozEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bankTransaction")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currentTransaction",nullable = false)
    private Long currentTransaction;

    @Column(name="bankName", nullable = false, unique = true, length=45)
    private String bankName;
    
    public BankTransaction() {
    	
    }
    
    public BankTransaction(Long id, String bankName, Long currentTransaction) {
    	this.id=id;
    	this.bankName=bankName;
    	this.currentTransaction=currentTransaction;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Long currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


}
