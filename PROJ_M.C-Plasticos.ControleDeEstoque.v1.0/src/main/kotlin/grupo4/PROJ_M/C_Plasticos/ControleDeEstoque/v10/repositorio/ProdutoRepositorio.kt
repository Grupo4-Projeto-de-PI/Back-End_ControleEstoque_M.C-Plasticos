package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    @Query("SELECT p FROM Produto p JOIN p.tipo tp WHERE tp.id = :tipoId")
    fun findByTipoProduto(tipoId: Int?): List<Produto>

    @Transactional
    fun findTopByOrderByIdDesc(): Produto?

//    @Transactional
//    @Query("SELECT p.id AS id, p.nome AS nome, p.tipo AS tipo, p.fkUsuario AS cadastrante, p.dataCadastro AS dataCadastro FROM Produto p")
//    fun findBySemPreco(novoProduto: Produto): Produto

}
