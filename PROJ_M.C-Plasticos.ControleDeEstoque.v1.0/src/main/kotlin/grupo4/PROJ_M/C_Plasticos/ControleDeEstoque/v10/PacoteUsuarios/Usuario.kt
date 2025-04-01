package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.PacoteUsuarios

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


@Entity
class Usuario (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @field: NotBlank @field:Size(min = 1, max = 15) var nome: String? = null,
    @field:Email var email: String? = null,
    @field: Size(min = 2, max = 8) var senha: String? = null
) {
    constructor(): this(null, null, null)

}