package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogPrecoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.LogPrecoProdutoService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class LogPrecoProdutoServiceTest {

    val service = LogPrecoProdutoService()

    @Test
    fun `valores menor que 0 não devem ser aceitos`() {

        assertThrows(IllegalArgumentException::class.java) {
            service.getPrecoAlterado(-10.0)
        }

    }

    @Test
    fun `o preço novo deve ser diferente do antigo`() {

        val precoAntigo = BigDecimal("10.00")
        val precoNovo = BigDecimal("11.00")
        val produto = Produto(/* inicialize conforme necessário */)
        val logPreco = LogPrecoProduto(
            precoAntigo = precoAntigo,
            fkProduto = produto,
            precoNovo = precoNovo,
            dataAlteracao = LocalDateTime.now()
        )

        val resultado = service.getPrecoAlterado(logPreco.precoNovo!!.toDouble())

        assert(resultado != precoAntigo)
    }


}