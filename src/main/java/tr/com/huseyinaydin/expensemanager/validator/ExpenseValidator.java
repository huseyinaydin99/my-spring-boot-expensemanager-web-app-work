package tr.com.huseyinaydin.expensemanager.validator;

import tr.com.huseyinaydin.expensemanager.dto.ExpenseDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public class ExpenseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExpenseDTO expenseDTO = (ExpenseDTO) target;
        if (expenseDTO.getDateString().equals("")
                || expenseDTO.getDateString().isEmpty()
                || expenseDTO.getDateString() == null) {
            errors.rejectValue("dateString",
                    null,
                    "Harcama tarhini boş geçemezsiniz.");
        }
    }
}