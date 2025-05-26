package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

interface TransacaoRepositorio: JpaRepository <Transacao, Int> {

    @Transactional
    @Query("""
    SELECT t FROM Transacao t
    JOIN t.fkProduto p
    JOIN p.tipo tp
    WHERE (:fkProduto IS NULL OR t.fkProduto.id = :fkProduto)
      AND (:fkCategoria IS NULL OR t.fkCategoria.id = :fkCategoria)
      AND (:fkParceiroComercial IS NULL OR t.fkParceiroComercial.id = :fkParceiroComercial)
      AND (:tipoOperacao IS NULL OR t.tipoOperacao.id = :tipoOperacao)
      AND (:dataInicio IS NULL OR :dataFim IS NULL OR t.data BETWEEN :dataInicio AND :dataFim)
      AND (:pesoMinimo IS NULL OR :pesoMaximo IS NULL OR t.peso BETWEEN :pesoMinimo AND :pesoMaximo)
      AND (:tipoProdutoId IS NULL OR tp.id = :tipoProdutoId)
""")
    fun findByDynamicFilters(
        @Param("fkProduto") fkProduto: Int? = null,
        @Param("fkCategoria") fkCategoria: Int? = null,
        @Param("fkParceiroComercial") fkParceiroComercial: Int? = null,
        @Param("tipoOperacao") tipoOperacao: Int? = null,
        @Param("dataInicio") dataInicio: LocalDateTime? = null,
        @Param("dataFim") dataFim: LocalDateTime? = null,
        @Param("pesoMinimo") pesoMinimo: Double? = null,
        @Param("pesoMaximo") pesoMaximo: Double? = null,
        @Param("tipoProdutoId") tipoProdutoId: Int? = null
    ): List<Transacao>
}