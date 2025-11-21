package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import java.time.LocalDateTime

data class FiltroTransacaoDto(
    val fkProduto: List<Int>? = null,
    val fkCategoria: List<categoriaEnum>? = null,
    val fkFornecedor: List<Int>? = null,
    val fkCliente: List<Int>? = null,
    val fkTipoParceiroComercial: List<tipoComercialEnum>? = null,
    val tipoOperacao: List<tipoOperacaoEnum>? = null,
    val dataInicio: String? = null,
    val dataFim: String? = null,
    val pesoMinimo: Double? = null,
    val pesoMaximo: Double? = null,
    val fkTipoProduto: List<Int>? = null
)