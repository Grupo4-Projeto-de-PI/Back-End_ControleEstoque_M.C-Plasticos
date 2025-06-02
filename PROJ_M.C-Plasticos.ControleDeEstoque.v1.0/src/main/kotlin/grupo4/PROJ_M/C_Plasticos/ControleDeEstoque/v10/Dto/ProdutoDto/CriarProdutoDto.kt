package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto
import jakarta.validation.constraints.NotBlank

data class CriarProdutoDto(
    @field: NotBlank
    val nome: String,
    val tipo: Int,
    val fkUsuario: Int,
)