package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Usuario
import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.UsuarioRepositorio
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springdoc.core.service.GenericResponseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    val repositorio: UsuarioRepositorio,
    private val responseBuilder: GenericResponseService
){


    @PostMapping("/criar")
    fun postCriarUsuario(@RequestBody @Valid novoUsuario: Usuario): ResponseEntity<Usuario>{
        val usuarioSalvo = repositorio.save(novoUsuario)
        return ResponseEntity.status(201).body(usuarioSalvo)
    }

    @GetMapping("/listar")
    fun listarTodosUsuarios(): ResponseEntity<List<Usuario>> {
        val usuarios = repositorio.findAll()
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(usuarios)
    }

    @GetMapping("/listar/{codigoFuncionario}")
    fun listarUsuarioId(@PathVariable(required = true) codigoFuncionario: Int): ResponseEntity<Usuario> {

        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(204).build()
        }
        val usuarioEncontrado = repositorio.findById(codigoFuncionario)
        return ResponseEntity.of(usuarioEncontrado)
    }

    @PatchMapping("/editar/nome")
    fun patchEditarNomeUsuario(@RequestParam nome: String, @RequestParam codigoFuncionario: Int): ResponseEntity<Usuario> {

        if(!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        repositorio.atualizarNome(codigoFuncionario, nome)
        val nomeAtualizado = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(nomeAtualizado)
    }

    @PatchMapping("/editar/senha")
    fun patchEditarSenhaUsuario(@RequestParam senha: Int, @RequestParam codigoFuncionario: Int): ResponseEntity<Usuario> {

        if(!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        repositorio.atualizarSenha(codigoFuncionario, senha)
        val senhaAtualizada = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(senhaAtualizada)
    }

    @DeleteMapping("/excluir/{codigoFuncionario}")
    fun deleteExcluirUsuario(@PathVariable codigoFuncionario: Int): ResponseEntity<Usuario> {

        if (repositorio.existsById(codigoFuncionario)) {
            repositorio.deleteByCodigoFuncionario(codigoFuncionario)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()
    }

    @GetMapping("/login/{nome}/{senha}")
    fun login(@PathVariable nomeEntrada: String, @PathVariable senhaEntrada: Int): ResponseEntity<Usuario>{
        val login = repositorio.findByNomeAndSenha(nomeEntrada, senhaEntrada)
        val nome = login?.nome
        val senha = login?.senha
        val status = login?.ativo

        if (nomeEntrada == nome && senhaEntrada == senha && status == true) {
            val statuOnline = true
            repositorio.atualizarOnline(nome, statuOnline)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

        }

    @GetMapping("/login/{nome}")
    fun logoff(@PathVariable nomeEntrada: String): ResponseEntity<Usuario>{
        val login = repositorio.findByNome(nomeEntrada)
        val nome = login?.nome
        val statusOnline = login?.online

        if (nomeEntrada == nome && statusOnline == true) {
            val online = false
            repositorio.atualizarOnline(nome,online)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }
}

