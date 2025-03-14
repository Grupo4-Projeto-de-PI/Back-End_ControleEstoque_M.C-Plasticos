package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.PacoteUsuarios

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/usuarios")
class   UsuarioController {

    val usuarios = mutableListOf<Usuario>()

    //CREATE
    @PostMapping("/criar")
    fun criarUsuario(@RequestBody novoUsuario: Usuario): ResponseEntity<Usuario> {
        usuarios.add(novoUsuario)
        return ResponseEntity.status(201).body(novoUsuario)
    }

    //READ
    @GetMapping("/listar")
    fun listarUsuarios(@PathVariable (required = false) email: String?): ResponseEntity<List<Usuario>> {

        if (email == null) {
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(204).build()
            }
            return ResponseEntity.status(200).body(usuarios)
        }

        val usuarioEncontrado = usuarios.find { it.email == email }
        return if (usuarioEncontrado != null) {
            ResponseEntity.status(200).body(listOf(usuarioEncontrado))
        } else ResponseEntity.status(204).build()
    }

    //UPDATE
    @PatchMapping("/editar/{email}/{novaSenha}")
    fun editarUsuarios(@PathVariable email: String, @PathVariable novaSenha: String): ResponseEntity<Usuario> {

//      Procura se existe algum usuário com o campo "e-mail" igual ao e-mail passado no caminho e guarda na variável
        val usuarioEncontrado = usuarios.find { it.email == email }

        if (usuarioEncontrado != null) {
            usuarioEncontrado.senha = novaSenha
            return ResponseEntity.status(200).body(usuarioEncontrado)
        }
        return ResponseEntity.status(404).build()

    }

    //DELETE
    @DeleteMapping("/excluir/{email}")
    fun excluirUsuario(@PathVariable email: String): ResponseEntity<Usuario> {
        val usuarioEncontrado = usuarios.find { it.email == email }
        val index = usuarios.indexOf(usuarioEncontrado)

        if (usuarioEncontrado != null) {
            usuarios.removeAt(index)
            return ResponseEntity.status(200).body(usuarioEncontrado)
        }
        return ResponseEntity.status(404).build()

    }


}