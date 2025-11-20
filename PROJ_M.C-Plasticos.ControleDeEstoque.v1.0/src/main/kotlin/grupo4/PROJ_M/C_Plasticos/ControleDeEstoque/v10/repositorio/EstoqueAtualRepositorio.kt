package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EstoqueAtualRepositorio : JpaRepository<Produto, Int> {

    @Query(value = "SELECT nome, fk_produto, estoqueAtual FROM estoque_atual", nativeQuery = true)
    fun buscarEstoqueAtual(): List<Map<String, Any>>

    @Query(value = "SELECT nome FROM produto WHERE nome LIKE %:nome%", nativeQuery = true)
    fun buscarProdutoPorNome(nome: String): List<Map<String, Any>>

    @Query(value = "SELECT * FROM informacoes_estoque_atual  WHERE fk_produto = :id", nativeQuery = true)
    fun valorEstoqueProduto(id: Int): List<Map<String, Any>>
}