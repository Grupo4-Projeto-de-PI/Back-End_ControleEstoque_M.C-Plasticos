package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import java.time.LocalDateTime

data class FiltroTransacaoDto(
    val fkProduto: Int? = null,
    val categoria: categoriaEnum? = null,
    val fkParceiroComercial: Int? = null,
    val tipoOperacao: tipoOperacaoEnum? = null,
    val dataInicio: LocalDateTime? = null,
    val dataFim: LocalDateTime? = null,
    val pesoMinimo: Double? = null,
    val pesoMaximo: Double? = null,
    val tipoProdutoId: Int? = null
)