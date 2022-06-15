package net.codeJava.atozMain.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.codeJava.atozEntity.BankTransaction;
import net.codeJava.atozEntity.Person;
import net.codeJava.atozMain.constants.PathEnum;
import net.codeJava.atozMain.service.BankTransactionService;
import net.codeJava.atozMain.service.PersonService;

@Controller
public class BankController {
	
	@Autowired
	private BankTransactionService bankTransactionService;

	private Logger log = Logger.getLogger(BankController.class);
	
	@GetMapping(value={"/bank_select"})
    public String getOptionBank(Model model){
		log.info("getOptionBank");
		
//		Person person = personService.findById(5L);
//		if(person != null) {
//			System.out.println("person.getId():"+person.getId());
//			System.out.println("address id:"+person.getAddresses().get(0).getId());
//			System.out.println("zipCode:"+person.getAddresses().get(0).getZipCode());
//		}

		List<BankTransaction> bankTransactions = bankTransactionService.findAvailableBanks();
		
		if(bankTransactions != null && !bankTransactions.isEmpty()) {
//			System.out.println("bankTransactions size:"+bankTransactions.size());
//			for(BankTransaction bankTransaction:bankTransactions) {
//				System.out.println("id:"+bankTransaction.getId());
//				System.out.println("currentTransaction:"+bankTransaction.getCurrentTransaction());
//				System.out.println("bank name:"+bankTransaction.getBankName());
//			}
		}
		
		BankTransaction bankTransaction = bankTransactionService.findAvailableBank();
		model.addAttribute("bankTransactionObj",bankTransaction);
		model.addAttribute("bankTransactions",bankTransactions);
		
		List<Person> persons = personService.findAll();
		if(persons != null) {
			for(Person person:persons) {
//				System.out.println("name:"+person.getName());
			}
			
		}
		model.addAttribute("allPersons",persons);
		
        return  PathEnum.BANK_OPTION_LIST.getValue();
    }
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value="/proceedPayment/{id}/{id1}/{value}")
    public String proceedPayment(@PathVariable("id") Long id, @PathVariable("id1") Long id1, @PathVariable("value") Long value, RedirectAttributes ra,Model model){
		
		log.info("id:"+id);
		log.info("id1:"+id1);
		log.info("value:"+value);
		
//		Person person = new Person();
//		Address address = new Address();
//		address.setPerson(person);
//		address.setHouseNumber(1);
//		address.setZipCode(72100);
//		person.setAddresses(Arrays.asList(address));
//		person.setName("test 123");
//		personService.savePerson(person);
		
		ra.addFlashAttribute("successMsg","Submit Successfully.");
		
        return ("redirect:"+"/bank_select");
    }
	
	@GetMapping("/barChart")
	public String barChart(Model model) {
		
		Map<String, Integer> data = new LinkedHashMap<>();
		data.put("Perodua", 30);
		data.put("Proton", 10);
		data.put("Mazda", 50);
		data.put("Tesla", 100);
		data.put("Honda", 70);
		data.put("Toyota", 90);
		
		model.addAttribute("keySet", data.keySet());
		model.addAttribute("values", data.values());
		
		return "highCharts/barChart";
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public String processForm(@ModelAttribute(value="text1") String text) {
		System.out.println("text1:"+text);
		return ("redirect:"+"/bank_select");
	}
	
	@PostMapping(value = "/processForm1")
	public String processForm1(@RequestParam(value="persons") String[] text) {
		if(text.length >0) {
			System.out.println("text1:"+ArrayUtils.toString(text));
		}
		return ("redirect:"+"/bank_select");
	}
}
