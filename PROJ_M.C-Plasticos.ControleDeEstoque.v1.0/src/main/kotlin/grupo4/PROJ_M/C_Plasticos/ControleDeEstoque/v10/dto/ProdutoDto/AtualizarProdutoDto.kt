package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto

import jakarta.validation.constraints.NotBlank

data class AtualizarProdutoDto(
    val nome: String? = null,
    val tipo: Int? = null,
)