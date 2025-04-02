package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepositorio: JpaRepository<Usuario, Int> {

//    @Transactional
//    @Modifying
//    @Query()
//
//    fun atualizarSenha(email: String, senha: String): Int
//    fun findByEmailContains(email: String): List<Usuario>
//    fun findBySenhaContains(senha: String): List<Usuario>
//    fun countByEmail(email: String): Int
}