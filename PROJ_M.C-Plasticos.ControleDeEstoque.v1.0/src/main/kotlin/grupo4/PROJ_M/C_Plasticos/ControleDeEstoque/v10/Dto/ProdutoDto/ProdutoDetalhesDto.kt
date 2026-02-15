package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.produtoEnum.ProdutoPrioridade

data class ProdutoDetalhesDto(
    val nomeProduto: String?,
    val tipoProduto: String?,
    val precoMaximo: Double?,
    val precoMinimo: Double?,
    val prioridade: ProdutoPrioridade?,
    val nomeParceiroComercial: String?,
    val fotoProduto: ByteArray?,
)