package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Usuario
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UsuarioRepositorio: JpaRepository<Usuario, Int> {
    @Transactional
    @Modifying
    @Query("update Usuario u set u.nome = ?2 where u.codigoFuncionario = ?1")
    fun atualizarNome(codigoFuncionario: Int, nome: String): Int

    @Transactional
    @Modifying
    @Query("update Usuario u set u.senha = ?2 where u.codigoFuncionario = ?1")
    fun atualizarSenha(codigoFuncionario: Int, senha: Int): Int

    @Transactional
    fun deleteByCodigoFuncionario(codigoFuncionario: Int): Int

    @Query("select * from Usuario u where u.nome = ?1 and u.senha = ?1")
    fun findByNomeAndSenha(nome: String, senha: Int): Usuario?

    @Query("select * from Usuario u where u.nome = ?1")
    fun findByNome(nome: String): Usuario?

    @Modifying
    @Query("update Usuario u set u.online = ?2 where u.nome = ?1")
    fun atualizarOnline(nome: String, online: Boolean): Usuario?

}