package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.UsuarioDto

import jakarta.validation.constraints.Size

data class EditarUsuarioDto (
    val nome: String? = null,
    @field:Size(max = 8, message = "A senha deve ter no máximo 8 dígitos")
    val senha: String? = null
)