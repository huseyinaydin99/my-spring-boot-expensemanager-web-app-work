package tr.com.huseyinaydin.expensemanager.dto;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilterDTO {

    private String keyword;

    private String sortBy;

    private String startDate;

    private String endDate;

    public ExpenseFilterDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}