package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.ProdutoDetalhesDto

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    @Query("SELECT p FROM Produto p JOIN p.tipo tp WHERE tp.id = :tipoId")
    fun findByTipoProduto(tipoId: Int?): List<Produto>

    @Transactional
    fun findTopByOrderByIdDesc(): Produto?

    @Transactional
    @Query("""
    SELECT NEW grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.ProdutoDetalhesDto(
        p.nome, tp.tipo, p.precoMaximo, p.precoMinimo, p.prioridade, pc.nome, p.fotoProduto
    )
    FROM Produto p
    JOIN p.tipo tp
    LEFT JOIN Transacao t ON t.fkProduto = p
    LEFT JOIN t.fkParceiroComercial pc
    WHERE p.id = :produtoId
""")
    fun findProdutoComDetalhes(produtoId: Int): List<ProdutoDetalhesDto>

}
