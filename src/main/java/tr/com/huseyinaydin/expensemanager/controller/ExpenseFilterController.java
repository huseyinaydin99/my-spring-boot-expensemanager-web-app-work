package tr.com.huseyinaydin.expensemanager.controller;

import tr.com.huseyinaydin.expensemanager.dto.ExpenseDTO;
import tr.com.huseyinaydin.expensemanager.dto.ExpenseFilterDTO;
import tr.com.huseyinaydin.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO, Model model) throws ParseException {
        System.out.println("Filtre edilen DTO yazdırıldı: "+expenseFilterDTO);
        List<ExpenseDTO> list = expenseService.getFilteredExpenses(expenseFilterDTO);
        model.addAttribute("expenses", list);
        String totalExpenses = expenseService.totalExpenses(list);
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses-list";
    }
}
