package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.LoginResponseDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.LoginUsuarioDto
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

    fun editarTipoUsuario(codigoFuncionario: Int, tipoUsuario: EditarUsuarioDto): ResponseEntity<Usuario> {
        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        if (tipoUsuario.tipoUsuario == null) {
            return ResponseEntity.status(400).body(null)
        }

        repositorio.atualizarTipoUsuario(codigoFuncionario, tipoUsuario.tipoUsuario)
        val usuarioAtualizado = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(usuarioAtualizado)
    }

    fun excluirUsuario(codigoFuncionario: Int): ResponseEntity<Usuario> {
        if (repositorio.existsById(codigoFuncionario)) {
            repositorio.deleteByCodigoFuncionario(codigoFuncionario)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }

    fun loginUsuario(loginUsuario: LoginUsuarioDto): ResponseEntity<LoginResponseDto> {
        val usuarioEncontrado = repositorio.findByCodigoFuncionario(loginUsuario.codigoFuncionario)

        return if (usuarioEncontrado.isNotEmpty()) {
            val usuario = usuarioEncontrado.first()

            // Verifica se a senha fornecida corresponde à senha armazenada
            if (usuario.senha == loginUsuario.senhaLog) {
                repositorio.atualizarOnline(loginUsuario.codigoFuncionario, true)

                ResponseEntity.status(200).body(
                    LoginResponseDto(
                        success = true,
                        message = "Usuario logado com sucesso!",
                        statusText = "OK",
                        usuario = usuario
                    )
                )
            } else {
                ResponseEntity.status(401).body(
                    LoginResponseDto(
                        success = false,
                        message = "Credenciais inválidas",
                        statusText = "Unauthorized"
                    )
                )
            }
        } else {
            ResponseEntity.status(404).body(
                LoginResponseDto(
                    success = false,
                    message = "Usuário não encontrado",
                    statusText = "Not Found"
                )
            )
        }
    }
}