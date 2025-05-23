package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.TipoProduto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    fun findByTipo(tipo: TipoProduto): List<Produto>

//    @Transactional
//    @Query("SELECT p.id AS id, p.nome AS nome, p.tipo AS tipo, p.fkUsuario AS cadastrante, p.dataCadastro AS dataCadastro FROM Produto p")
//    fun findBySemPreco(novoProduto: Produto): Produto

}
