package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.produtoEnum.ProdutoPrioridade

data class AtualizarProdutoDto(
    val nome: String? = null,
    val tipo: Int? = null,
    val prioridade: ProdutoPrioridade? = null,
)