package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto

import java.time.LocalDateTime

data class FiltroTransacaoDto(
    val fkProduto: Int? = null,
    val fkCategoria: Int? = null,
    val fkParceiroComercial: Int? = null,
    val tipoOperacao: Int? = null,
    val dataInicio: LocalDateTime? = null,
    val dataFim: LocalDateTime? = null,
    val pesoMinimo: Double? = null,
    val pesoMaximo: Double? = null,
    val tipoProdutoId: Int? = null
)