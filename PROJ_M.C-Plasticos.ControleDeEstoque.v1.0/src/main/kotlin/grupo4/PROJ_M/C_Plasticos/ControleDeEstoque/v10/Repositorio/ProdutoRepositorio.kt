package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ProdutoRepositorio: JpaRepository<Produto, Int> {

    @Transactional
    fun findByTipo(tipo: String): List<Produto>
}
