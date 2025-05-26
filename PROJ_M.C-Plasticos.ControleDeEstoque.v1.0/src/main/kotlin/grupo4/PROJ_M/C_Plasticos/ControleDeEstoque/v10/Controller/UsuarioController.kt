package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Dto.UsuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Dto.UsuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.TipoUsuarioRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.UsuarioRepositorio
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
@RestController
@RequestMapping("/usuario")
class UsuarioController(
    val repositorio: UsuarioRepositorio,
    val tipoUsuarioRepository: TipoUsuarioRepositorio,
){
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário com base nos dados fornecidos.")
    @PostMapping("/criar")
    fun postCriarUsuario(@RequestBody @Valid novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {

        val tipoUsuario = tipoUsuarioRepository.findById(novoUsuario.tipoUsuario)
            .orElseThrow { RuntimeException("Tipo de usuário não encontrado") }

        val usuario = Usuario(
            nome = novoUsuario.nome,
            senha = novoUsuario.senha,
            tipoUsuario = tipoUsuario
        )

        repositorio.save(usuario)
        return ResponseEntity.status(201).body(usuario)
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @GetMapping("/listar")
    fun listarTodosUsuarios(): ResponseEntity<List<Usuario>> {
        val usuarios = repositorio.findAll()
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(usuarios)
    }

    @Operation(summary = "Listar usuário por ID", description = "Retorna os dados de um usuário específico com base no código do funcionário.")
    @GetMapping("/listar/{codigoFuncionario}")
    fun listarUsuarioId(@PathVariable(required = true) codigoFuncionario: Int): ResponseEntity<Usuario> {

        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(204).build()
        }
        val usuarioEncontrado = repositorio.findById(codigoFuncionario)
        return ResponseEntity.of(usuarioEncontrado)
    }

    @Operation(summary = "Editar nome do usuário", description = "Atualiza o nome de um usuário específico.")
    @PatchMapping("/editar/nome")
    fun patchEditarNomeUsuario(
        @Valid @RequestBody dto: EditarUsuarioDto,
        @RequestParam codigoFuncionario: Int
    ): ResponseEntity<Usuario> {

        if (!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        if( dto.nome.isNullOrBlank()) {
            return ResponseEntity.status(400).build()
        }

        val nomeUsuario = dto.nome
        repositorio.atualizarNome(codigoFuncionario, nomeUsuario)

        val nomeAtualizado = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(nomeAtualizado)
    }

    @Operation(summary = "Editar senha do usuário", description = "Atualiza a senha de um usuário específico.")
    @PatchMapping("/editar/senha")
    fun patchEditarSenhaUsuario(@Valid @RequestBody senha: EditarUsuarioDto, @RequestParam codigoFuncionario: Int): ResponseEntity<Usuario> {

        if(!repositorio.existsById(codigoFuncionario)) {
            return ResponseEntity.status(404).build()
        }

        if(senha.senha.isNullOrBlank()) {
            return ResponseEntity.status(400).body(null)
        }
        val senhaUsuario = senha.senha
        repositorio.atualizarSenha(codigoFuncionario, senhaUsuario)

        val senhaAtualizada = repositorio.findById(codigoFuncionario).get()
        return ResponseEntity.status(200).body(senhaAtualizada)
    }

    @Operation(summary = "Excluir usuário", description = "Exclui um usuário com base no código do funcionário.")
    @DeleteMapping("/excluir/{codigoFuncionario}")
    fun deleteExcluirUsuario(@PathVariable codigoFuncionario: Int): ResponseEntity<Usuario> {

        if (repositorio.existsById(codigoFuncionario)) {
            repositorio.deleteByCodigoFuncionario(codigoFuncionario)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()
    }

    @Operation(summary = "Login do usuário", description = "Realiza o login de um usuário com base no código do funcionário e senha.")
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

    @Operation(summary = "Logoff do usuário", description = "Realiza o logoff de um usuário com base no código do funcionário.")
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

