package br.com.cabral.basic_api.domain.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public abstract  class UserRequestBase {

    @Schema(description = "Nome do usuário",example = "john armless")
    @NotBlank(message ="O nome deve ser preenchido")
    public String Name;
    @Schema(description = "Email do usuário", example = "j.armless@email.com")
    @NotBlank(message ="O email deve ser preenchido")
    @Email(message = "favor informar um email valido")
    public String Email;
}
