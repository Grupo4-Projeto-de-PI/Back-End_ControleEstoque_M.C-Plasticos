package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.usuarioEnum.tipoUsuarioEnum
import jakarta.validation.constraints.*

data class CriarUsuarioDto(
    @field: NotBlank
    val nome: String,

    @field: NotBlank
    @field: Size(max = 8)
    val senha: String,
    val tipoUsuario: tipoUsuarioEnum
)
