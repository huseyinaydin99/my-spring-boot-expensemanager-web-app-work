package tr.com.huseyinaydin.expensemanager.controller;

import java.text.ParseException;
import java.util.List;

import tr.com.huseyinaydin.expensemanager.dto.ExpenseFilterDTO;
import tr.com.huseyinaydin.expensemanager.util.DateTimeUtil;
import tr.com.huseyinaydin.expensemanager.validator.ExpenseValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import tr.com.huseyinaydin.expensemanager.dto.ExpenseDTO;
import tr.com.huseyinaydin.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Controller
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public String showExpenseList(Model model) {
		List<ExpenseDTO> list = expenseService.getAllExpenses();
		model.addAttribute("expenses", list);
		model.addAttribute("filter", new ExpenseFilterDTO(DateTimeUtil.getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
		String totalExpenses = expenseService.totalExpenses(list);
		model.addAttribute("totalExpenses", totalExpenses);
		return "expenses-list";
	}
	@GetMapping("/createExpense")
	public String showExpenseForm(Model model) {
		model.addAttribute("expense", new ExpenseDTO());
		return "expense-form";
	}

	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO expneseDTO,
											 BindingResult result) throws ParseException {
		System.out.println("Harcama DTO'su yazdırıldı: "+expneseDTO);

		new ExpenseValidator().validate(expneseDTO, result);

		if (result.hasErrors()) {
			return "expense-form";
		}
		expenseService.saveExpenseDetails(expneseDTO);
		return "redirect:/expenses";
	}

	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id) {
		System.out.println("Harcama ID'si yazdırıldı: "+id);
		expenseService.deleteExpense(id);
		return "redirect:/expenses";
	}

	@GetMapping("/updateExpense")
	public String updateExpense(@RequestParam String id, Model model) {
		System.out.println("Güncellenecek harcama kaydının ID'si yazdırıldı: "+id);
		ExpenseDTO expense = expenseService.getExpenseById(id);
		model.addAttribute("expense", expense);
		return "expense-form";
	}
}






















