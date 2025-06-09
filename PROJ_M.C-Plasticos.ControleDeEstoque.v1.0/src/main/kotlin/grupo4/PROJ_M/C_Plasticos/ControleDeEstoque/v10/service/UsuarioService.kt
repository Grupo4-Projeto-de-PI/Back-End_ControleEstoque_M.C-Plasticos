package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    val repositorio: UsuarioRepositorio
) {

    fun criarUsuario(novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {

        if(novoUsuario.nome.isBlank() || novoUsuario.senha.isBlank()) {
            return ResponseEntity.status(400).build()
        }

        val usuario = Usuario(
            nome = novoUsuario.nome,
            senha = novoUsuario.senha,
            tipoUsuario = novoUsuario.tipoUsuario
        )

        repositorio.save(usuario)
        return ResponseEntity.status(201).body(usuario)
    }

    fun listarTodosUsuarios(): ResponseEntity<List<Usuario>> {
        val usuarios = repositorio.findAll()
        return if (usuarios.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(usuarios)
        }
    }

    fun listarUsuarioPorId(codigoFuncionario: Int): ResponseEntity<Usuario> {
        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(204).build()
        }
        val usuarioEncontrado = repositorio.findById(codigoFuncionario)
        return ResponseEntity.of(usuarioEncontrado)
    }

    fun editarNomeUsuario(codigoFuncionario: Int, dto: EditarUsuarioDto): ResponseEntity<Usuario> {
        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        if (dto.nome.isNullOrBlank()) {
            return ResponseEntity.status(400).build()
        }

        repositorio.atualizarNome(codigoFuncionario, dto.nome)
        val nomeAtualizado = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(nomeAtualizado)
    }

    fun editarSenhaUsuario(codigoFuncionario: Int, senha: EditarUsuarioDto): ResponseEntity<Usuario> {
        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        if (senha.senha.isNullOrBlank()) {
            return ResponseEntity.status(400).body(null)
        }

        repositorio.atualizarSenha(codigoFuncionario, senha.senha)
        val senhaAtualizada = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(senhaAtualizada)
    }

    fun excluirUsuario(codigoFuncionario: Int): ResponseEntity<Usuario> {
        if (repositorio.existsById(codigoFuncionario)) {
            repositorio.deleteByCodigoFuncionario(codigoFuncionario)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }

    fun loginUsuario(loginUsuario: Usuario): ResponseEntity<String> {
        val login = repositorio.findByCodigoFuncionarioAndSenha(
            loginUsuario.codigoFuncionario,
            loginUsuario.senha
        )

        return if (login.isNotEmpty()) {
            repositorio.atualizarOnline(loginUsuario.codigoFuncionario, true)
            ResponseEntity.status(200).body("Usuario logado com sucesso!")
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun logoffUsuario(nomeEntrada: Usuario): ResponseEntity<String> {
        val login = repositorio.findByCodigoFuncionario(nomeEntrada.codigoFuncionario)

        return if (login.isNotEmpty()) {
            repositorio.atualizarOnline(nomeEntrada.codigoFuncionario, false)
            ResponseEntity.status(200).body("Usuario desconectado!")
        } else {
            ResponseEntity.status(404).build()
        }
    }
}