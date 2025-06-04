package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.lucas
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/lucas")
class ControllerLucas(
    val repositorioUsuario: UsuarioRepositorio
) {
    @GetMapping()
    fun buscarUsuarios(): ResponseEntity<List<Usuario>>{
        val usuariosEncontrados = repositorioUsuario.findAll()

        if(usuariosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(200).body(usuariosEncontrados)
    }

    @PostMapping()
    fun criarUsuario(novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {
        val usuario = Usuario(
            nome = novoUsuario.nome,
            senha = novoUsuario.senha,
            tipoUsuario = novoUsuario.tipoUsuario,
        )

        val usuarioSalvo = repositorioUsuario.save(usuario)
        return ResponseEntity.status(201).body(usuarioSalvo)
    }

    @PutMapping("/{id}")
    fun atualizarUsuarioPeloId(@PathVariable id: Int, novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {
        val usuarioExistente = repositorioUsuario.findById(id)

        if (!usuarioExistente.isPresent) {
            return ResponseEntity.status(404).build()
        }

        val usuarioAtualizado = usuarioExistente.get().apply {
            nome = novoUsuario.nome
            senha = novoUsuario.senha
            tipoUsuario = novoUsuario.tipoUsuario
        }

        repositorioUsuario.save(usuarioAtualizado)
        return ResponseEntity.status(200).body(usuarioAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletarUsuarioPeloId(@PathVariable id: Int): ResponseEntity<Void> {
        if (!repositorioUsuario.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        repositorioUsuario.deleteById(id)
        return ResponseEntity.status(204).build()
    }
}