package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

interface TransacaoRepositorio: JpaRepository <Transacao, Int> {

    @Query(
        value = """
    SELECT t.*
    FROM transacao t
    LEFT JOIN produto p ON t.fk_produto = p.id
    LEFT JOIN tipo_produto tp ON p.tipo_produto = tp.id
    LEFT JOIN parceiro_comercial pc ON t.fk_parceiro_comercial = pc.id
    WHERE
        (COALESCE(:fkProduto) IS NULL OR t.fk_produto IN (:fkProduto))
    AND (COALESCE(:fkCategoria) IS NULL OR t.categoria IN (:fkCategoria))
    AND (COALESCE(:fkCliente) IS NULL OR pc.id IN (:fkCliente))
    AND (COALESCE(:fkFornecedor) IS NULL OR pc.id IN (:fkFornecedor))
    AND (COALESCE(:fkTipoParceiroComercial) IS NULL OR pc.tipo_comercial IN (:fkTipoParceiroComercial))
    AND (COALESCE(:tipoOperacao) IS NULL OR t.tipo_operacao IN (:tipoOperacao))
    AND (
         (:dataInicio IS NULL AND :dataFim IS NULL)
         OR (t.data BETWEEN :dataInicio AND :dataFim)
    )
    AND (
         (:pesoMinimo IS NULL AND :pesoMaximo IS NULL)
         OR (t.peso BETWEEN :pesoMinimo AND :pesoMaximo)
    )
    AND (COALESCE(:fkTipoProduto) IS NULL OR tp.id IN (:fkTipoProduto))
""",
        nativeQuery = true
    )
    fun findByDynamicFiltersNative(
        @Param("fkProduto") fkProduto: List<Int>?,
        @Param("fkCategoria") fkCategoria: List<Int>?,
        @Param("fkCliente") fkCliente: List<Int>?,
        @Param("fkFornecedor") fkFornecedor: List<Int>?,
        @Param("fkTipoParceiroComercial") fkTipoParceiroComercial: List<Int>?,
        @Param("tipoOperacao") tipoOperacao: List<Int>?,
        @Param("dataInicio") dataInicio: LocalDate?,
        @Param("dataFim") dataFim: LocalDate?,
        @Param("pesoMinimo") pesoMinimo: Double?,
        @Param("pesoMaximo") pesoMaximo: Double?,
        @Param("fkTipoProduto") fkTipoProduto: List<Int>?
    ): List<Transacao>
}