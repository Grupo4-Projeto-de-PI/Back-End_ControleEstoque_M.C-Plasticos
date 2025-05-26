package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    fun findByTipo(tipo: TipoProduto): List<Produto>

//    @Transactional
//    @Query("SELECT p.id AS id, p.nome AS nome, p.tipo AS tipo, p.fkUsuario AS cadastrante, p.dataCadastro AS dataCadastro FROM Produto p")
//    fun findBySemPreco(novoProduto: Produto): Produto

}
