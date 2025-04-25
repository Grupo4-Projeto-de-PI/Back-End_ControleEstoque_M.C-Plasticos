package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    fun findByTipo(tipo: String): List<Produto>
}
