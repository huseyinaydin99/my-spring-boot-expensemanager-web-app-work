package tr.com.huseyinaydin.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class UserDTO {

    @NotBlank(message = "Kullanıcı adı boş geçilemez.")
    private String name;

    @Email(message = "Geçersiz formatta e-posta adresi.")
    private String email;

    @NotBlank(message = "Şifre alanı boş geçilemez.")
    @Size(min = 5, message = "Şifre minimum 5 karakter olmalıdır.")
    private String password;

    private String confirmpassword;
}