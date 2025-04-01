package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.PacoteUsuarios

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.UsuarioRepositorio
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioJPAController (val repositorio: UsuarioRepositorio){


    @PostMapping("/criar")
    fun postCriarUsuario(@RequestBody @Valid novoUsuario: Usuario): ResponseEntity<Usuario>{
        val usuarioSalvo = repositorio.save(novoUsuario)
        return ResponseEntity.status(201).body(usuarioSalvo)
    }


    @GetMapping("/listar")
    fun getListarUsuarios(@RequestParam(required = false) email: String?): ResponseEntity<List<Usuario>> {
        val usuarios = repositorio.findAll()
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(usuarios)
    }

    @PatchMapping("/editar/{email}/{novaSenha}")
    fun patchEditarUsuarios(@PathVariable email: String, @PathVariable novaSenha: String): ResponseEntity<Usuario> {
        val usuarioEncontrado = repositorio.findById(email.toInt())
        if (usuarioEncontrado.isPresent) {
            val usuario = usuarioEncontrado.get()
            usuario.senha = novaSenha
            val usuarioSalvo = repositorio.save(usuario)
            return ResponseEntity.status(200).body(usuarioSalvo)
        }
        return ResponseEntity.status(404).build()
    }


    @DeleteMapping("/excluir/{email}")
    fun deleteExcluirUsuario(@PathVariable email: String): ResponseEntity<Usuario> {
        val usuarioEncontrado = repositorio.findById(email.toInt())
        if (usuarioEncontrado.isPresent) {
            repositorio.delete(usuarioEncontrado.get())
            return ResponseEntity.status(200).build()
        }
        return ResponseEntity.status(404).build()
    }



}