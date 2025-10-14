package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import java.time.LocalDateTime

data class FiltroTransacaoDto(
    val fkProduto: Int? = null,
    val fkCategoria: categoriaEnum? = null,
    val fkFornecedor: Int? = null,
    val fkCliente: Int? = null,
    val fkTipoParceiroComercial: tipoComercialEnum? = null,
    val tipoOperacao: tipoOperacaoEnum? = null,
    val dataInicio: LocalDateTime? = null,
    val dataFim: LocalDateTime? = null,
    val horarioInicio: String? = null,
    val horarioFim: String? = null,
    val pesoMinimo: Double? = null,
    val pesoMaximo: Double? = null,
    val fkTipoProduto: Int? = null
)