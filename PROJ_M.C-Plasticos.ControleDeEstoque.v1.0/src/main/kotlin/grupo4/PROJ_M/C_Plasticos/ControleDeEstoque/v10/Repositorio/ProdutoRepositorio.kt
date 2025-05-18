package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    fun findByTipo(tipo: String): List<Produto>

    @Transactional
    @Query("SELECT p.id AS id, p.nome AS nome, p.tipo AS tipo, p.cadastrante AS cadastrante, p.dataCadastro AS dataCadastro FROM Produto p")
    fun findBySemPreco(novoProduto: Produto): Produto

}
