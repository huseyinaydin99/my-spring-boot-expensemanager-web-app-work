package tr.com.huseyinaydin.expensemanager.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Entity
@Table(name = "tbl_expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String expenseId;
	
	private String name;
	
	private String description;
	
	private BigDecimal amount;
	
	private Date date;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}