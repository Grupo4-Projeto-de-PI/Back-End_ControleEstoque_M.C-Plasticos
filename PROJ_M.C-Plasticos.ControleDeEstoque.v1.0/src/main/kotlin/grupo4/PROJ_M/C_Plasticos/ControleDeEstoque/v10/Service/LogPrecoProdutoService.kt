package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.LogPrecoProduto
import java.math.BigDecimal

class LogPrecoProdutoService {

//    fun getPrecoAlterado(LogPreco : LogPrecoProduto): BigDecimal {
//        if (LogPreco.precoNovo < 0) {
//            throw IllegalArgumentException()
//        }
//
//        return precoNovo + 1
//
//    }

    fun getPrecoAlterado(precoProduto: LogPrecoProduto): BigDecimal {
        if (precoProduto.precoNovo!! < BigDecimal.ZERO) {
            throw IllegalArgumentException("Preço novo não pode ser negativo")
        }

        return precoProduto.precoNovo!! + BigDecimal.ONE
    }


}