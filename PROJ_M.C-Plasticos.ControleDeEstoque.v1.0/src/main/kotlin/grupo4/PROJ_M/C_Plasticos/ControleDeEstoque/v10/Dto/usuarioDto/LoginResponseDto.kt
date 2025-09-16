package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario

data class LoginResponseDto(
    val success: Boolean,
    val message: String,
    val statusText: String,
    val usuario: Usuario? = null
)