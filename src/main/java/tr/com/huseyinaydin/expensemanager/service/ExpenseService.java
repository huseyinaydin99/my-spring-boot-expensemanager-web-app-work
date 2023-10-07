package tr.com.huseyinaydin.expensemanager.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ibm.icu.text.NumberFormat;
import tr.com.huseyinaydin.expensemanager.dto.ExpenseFilterDTO;
import tr.com.huseyinaydin.expensemanager.entity.User;
import tr.com.huseyinaydin.expensemanager.exception.ExpenseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.expensemanager.dto.ExpenseDTO;
import tr.com.huseyinaydin.expensemanager.entity.Expense;
import tr.com.huseyinaydin.expensemanager.repository.ExpenseRepository;
import tr.com.huseyinaydin.expensemanager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Service
@RequiredArgsConstructor
public class ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	
	public List<ExpenseDTO> getAllExpenses() {
		User user = userService.getLoggedInUser();
		List<Expense> list = expenseRepository.findByDateBetweenAndUserId(
				Date.valueOf(LocalDate.now().withDayOfMonth(1)),
				Date.valueOf(LocalDate.now()),
				user.getId());
		List<ExpenseDTO> expenseList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
		return expenseList;
	}
	
	private ExpenseDTO mapToDTO(Expense expense) {
		ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
		expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));
		return expenseDTO;
	}

	public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
		//map the DTO to entity
		Expense expense = mapToEntity(expenseDTO);
		//add the logged in user to the expense entity
		expense.setUser(userService.getLoggedInUser());
		//Save the entity to database
		expense = expenseRepository.save(expense);
		//map the entity to DTO
		return mapToDTO(expense);
	}

	private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
		//map the DTO to entity
		Expense expense = modelMapper.map(expenseDTO, Expense.class);
		//TODO: expense id'yi üretir. Yani benzersiz bir String(Hash) üretir. UUID(Universal Unique Identifier).
		if (expense.getId() == null) {
			expense.setExpenseId(UUID.randomUUID().toString());
		}
		//TODO: Harcama tarhisini String olarak takar(Set eder).
		expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
		//return the expense entity
		return expense;
	}

	public void deleteExpense(String id) {
		Expense exitingExpense = getExpense(id);
		expenseRepository.delete(exitingExpense);
	}

	public ExpenseDTO getExpenseById(String id) {
		Expense exitingExpense = getExpense(id);
		return mapToDTO(exitingExpense);
	}

	public Expense getExpense(String id) {
		return expenseRepository.findByExpenseId(id).orElseThrow(() -> new ExpenseNotFoundException("İlgili ID'li harcama bulunamadı: "+id));
	}

	public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException {
		String keyword = expenseFilterDTO.getKeyword();
		String sortBy = expenseFilterDTO.getSortBy();
		String startDateString = expenseFilterDTO.getStartDate();
		String endDateString =expenseFilterDTO.getEndDate();

		Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
		Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());
		User user = userService.getLoggedInUser();
		List<Expense> list = expenseRepository.findByNameContainingAndDateBetweenAndUserId(
				keyword,
				startDate,
				endDate,
				user.getId());
		List<ExpenseDTO> filteredList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
		if (sortBy.equals("date")) {
			//sort it by expense date
			filteredList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
		}else {
			//sort it by expense amount
			filteredList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
		}
		return filteredList;
	}

	public String totalExpenses(List<ExpenseDTO> expenses) {
		//BigDecimal sum = new BigDecimal(0);
		String total = "";
		double totalPrice = 0.0;
		/*BigDecimal total = expenses.stream().map(x -> x.getAmount().add(sum))
				.reduce(BigDecimal.ZERO, BigDecimal::add);*/
		for (int i = 0; i < expenses.size(); i++){
			totalPrice += Double.parseDouble(expenses.get(i).getAmount().toString());
			total = String.valueOf(totalPrice);
		}
		//NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("tr", "TR"));
		return total;
	}
}