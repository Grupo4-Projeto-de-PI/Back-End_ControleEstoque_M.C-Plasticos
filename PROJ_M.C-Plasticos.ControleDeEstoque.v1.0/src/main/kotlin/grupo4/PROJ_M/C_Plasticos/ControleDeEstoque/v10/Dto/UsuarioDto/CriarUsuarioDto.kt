package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Dto.UsuarioDto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CriarUsuarioDto(
    @field:NotBlank
    @field:Size(min = 1, max = 15)
    val nome: String,

    @field:NotBlank
    @field:Size(max = 8, message = "A senha deve ter no máximo 8 dígitos")
    val senha: String,

    val tipoUsuario: Int
)