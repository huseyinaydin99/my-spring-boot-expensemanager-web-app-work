package tr.com.huseyinaydin.expensemanager.exception;

import lombok.Getter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Getter
public class ExpenseNotFoundException extends RuntimeException{
    private String message;

    public ExpenseNotFoundException(String message) {
        this.message = message;
    }
}