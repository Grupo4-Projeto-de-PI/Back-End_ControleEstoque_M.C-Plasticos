package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto

import jakarta.validation.constraints.*

data class CriarUsuarioDto(
    @field: NotBlank
    val nome: String,

    @field: NotBlank
    @field: Size(max = 8)
    val senha: String,

    @field: Min(0)
    @field: Max(2)
    val tipoUsuario: Int
)
