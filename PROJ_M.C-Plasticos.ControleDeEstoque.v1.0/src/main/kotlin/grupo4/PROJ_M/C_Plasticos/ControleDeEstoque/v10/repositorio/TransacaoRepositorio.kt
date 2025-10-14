package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
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
    JOIN t.fkParceiroComercial pc
    WHERE (:fkProduto IS NULL OR t.fkProduto.id = :fkProduto)
      AND (:fkCategoria IS NULL OR t.categoria = :fkCategoria)
      AND (
        :fkCliente IS NULL OR (
            pc.id = :fkCliente
            AND (pc.papelComercial = grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum.CL 
                 OR pc.papelComercial = grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum.CLFN)
        )
      )
      AND (
        :fkFornecedor IS NULL OR (
            pc.id = :fkFornecedor
            AND (pc.papelComercial = grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum.FN 
                 OR pc.papelComercial = grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum.CLFN)
        )
      ) 
      AND (:fkTipoParceiroComercial IS NULL OR pc.tipoComercial = :fkTipoParceiroComercial)
      AND (:tipoOperacao IS NULL OR t.tipoOperacao = :tipoOperacao)
      AND (
        :dataInicio IS NULL 
        OR :dataFim IS NULL 
        OR t.data BETWEEN :dataInicio AND :dataFim
      )
      AND (
        :pesoMinimo IS NULL
        OR :pesoMaximo IS NULL
        OR t.peso BETWEEN :pesoMinimo AND :pesoMaximo
      )
      AND (:fkTipoProduto IS NULL OR tp.id = :fkTipoProduto)
""")
    fun findByDynamicFilters(
        @Param("fkProduto") fkProduto: Int? = null,
        @Param("fkCategoria") categoria: categoriaEnum? = null,
        @Param("fkCliente") fkCliente: Int? = null,
        @Param("fkFornecedor") fkFornecedor: Int? = null,
        @Param("fkTipoParceiroComercial") fkTipoParceiroComercial: tipoComercialEnum? = null,
        @Param("tipoOperacao") tipoOperacao: tipoOperacaoEnum? = null,
        @Param("dataInicio") dataInicio: LocalDateTime? = null,
        @Param("dataFim") dataFim: LocalDateTime? = null,
        @Param("horarioInicio") horarioInicio: String? = null,
        @Param("horarioFim") horarioFim: String? = null,
        @Param("pesoMinimo") pesoMinimo: Double? = null,
        @Param("pesoMaximo") pesoMaximo: Double? = null,
        @Param("fkTipoProduto") fkTipoProduto: Int? = null
    ): List<Transacao>
}