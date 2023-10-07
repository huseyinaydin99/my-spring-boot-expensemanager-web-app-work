package tr.com.huseyinaydin.expensemanager.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
	
	private Long id;
	
	private String expenseId;

	@NotBlank(message = "Harcama adı boş geçilemez.")
	@Size(min = 3, message = "Harcama adı alanı en az 3 karakter olmalıdır.")
	private String name;
	
	private String description;

	@NotNull(message = "Harcama miktarı boş geçilemez.")
	@Min(value = 1, message = "Harcama miktarı en az 1 olmalıdır.")
	private BigDecimal amount;
	
	private Date date;
	
	private String dateString; 

}
