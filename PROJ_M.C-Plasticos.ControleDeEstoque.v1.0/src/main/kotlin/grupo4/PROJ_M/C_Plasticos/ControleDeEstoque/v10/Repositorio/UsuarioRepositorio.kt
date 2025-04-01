package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.PacoteUsuarios.Usuario
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UsuarioRepositorio: JpaRepository<Usuario, Int> {

    //@Transactional
    //@Modifying
    //@Query()

    //fun atualizarSenha(email: String, senha: String): Int
    //fun findByEmailContains(email: String): List<Usuario>
    //fun findBySenhaContains(senha: String): List<Usuario>
    //fun countByEmail(email: String): Int


}