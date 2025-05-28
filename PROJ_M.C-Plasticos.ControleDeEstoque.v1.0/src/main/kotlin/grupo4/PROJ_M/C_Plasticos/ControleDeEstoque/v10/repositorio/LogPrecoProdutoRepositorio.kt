package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogPrecoProduto
import org.springframework.data.jpa.repository.JpaRepository

interface LogPrecoProdutoRepositorio: JpaRepository<LogPrecoProduto, Int>{

}

