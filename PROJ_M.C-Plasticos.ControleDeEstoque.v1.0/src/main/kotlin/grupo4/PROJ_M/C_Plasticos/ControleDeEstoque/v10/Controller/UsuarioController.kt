package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.UsuarioRepositorio
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    val repositorio: UsuarioRepositorio
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
    fun patchEditarNomeUsuario(@Valid @RequestBody nome: Usuario, @RequestParam codigoFuncionario: Int): ResponseEntity<Usuario> {

        if(!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        val nomeUsuario = nome.nome

        if (nomeUsuario != null) {
            repositorio.atualizarNome(codigoFuncionario, nomeUsuario)
        }
        else{
            return ResponseEntity.status(400).build()
        }
        val nomeAtualizado = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(nomeAtualizado)
    }

    @PatchMapping("/editar/senha")
    fun patchEditarSenhaUsuario(@Valid @RequestBody senha: Usuario, @RequestParam codigoFuncionario: Int): ResponseEntity<Usuario> {

        if(!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }
        val senhaUsuario = senha.senha

        if (senhaUsuario != null) {
            repositorio.atualizarSenha(codigoFuncionario, senhaUsuario)
        }
        else{
            return ResponseEntity.status(400).build()
        }
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

    @GetMapping("/login")
    fun login(@RequestBody loginUsuario: Usuario): ResponseEntity<String>{
        val codigoFuncionarioEntrada = loginUsuario.codigoFuncionario
        val senhaEntrada = loginUsuario.senha
        val login = repositorio.findByCodigoFuncionarioAndSenha(codigoFuncionarioEntrada, senhaEntrada)

        if (login.isNotEmpty()) {
            repositorio.atualizarOnline(codigoFuncionarioEntrada, true)
            return ResponseEntity.status(200).body("Usuario logado com sucesso!")
        }

        return ResponseEntity.status(404).build()
    }

    @GetMapping("/logoff")
    fun logoff(@RequestBody nomeEntrada: Usuario): ResponseEntity<String>{
        val codigoFuncionario = nomeEntrada.codigoFuncionario
        val login = repositorio.findByCodigoFuncionario(codigoFuncionario)

        if (login.isNotEmpty()) {
            repositorio.atualizarOnline(codigoFuncionario,false)
            return ResponseEntity.status(200).body("Usuario desconectado!")
        }

        return ResponseEntity.status(404).build()

    }
}

