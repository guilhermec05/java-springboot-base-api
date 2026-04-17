package br.com.cabral.basic_api.domain.dto.users;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({ "id", "name", "email" })
public class UserResponse {


    @Schema(description = "id do usuário",example = "1")
    @JsonProperty("id")
    public Long Id;
    @Schema(description = "Nome do usuário",example = "john armless")
    @JsonProperty("name")
    public String Name;
    @Schema(description = "Email do usuário", example = "j.armless@email.com")
    @JsonProperty("email")
    public String Email;
}
