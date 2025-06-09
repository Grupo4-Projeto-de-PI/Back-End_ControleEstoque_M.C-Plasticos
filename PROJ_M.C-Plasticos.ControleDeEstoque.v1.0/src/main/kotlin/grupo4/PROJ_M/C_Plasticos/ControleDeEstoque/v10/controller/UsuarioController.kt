package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
@RestController
@RequestMapping("/usuario")
class UsuarioController(
    val usuarioService: UsuarioService
) {

    @Operation(summary = "Cria um novo usuário", description = "Cria um usuário com os dados fornecidos.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                content = [Content(schema = Schema(implementation = Usuario::class))]),
            ApiResponse(responseCode = "400", description = "Dados inválidos")
        ]
    )
    @PostMapping
    fun postCriarUsuario(@RequestBody @Valid novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {
        return usuarioService.criarUsuario(novoUsuario)
    }

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                content = [Content(schema = Schema(implementation = Usuario::class))]),
            ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
        ]
    )
    @GetMapping
    fun listarTodosUsuarios(): ResponseEntity<List<Usuario>> {
        return usuarioService.listarTodosUsuarios()
    }

    @Operation(summary = "Lista usuário por ID", description = "Retorna os dados de um usuário específico pelo ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Usuário encontrado", content = [Content(schema = Schema(implementation = Usuario::class))]),
            ApiResponse(responseCode = "204", description = "Usuário não encontrado")
        ]
    )
    @GetMapping("{codigoFuncionario}")
    fun listarUsuarioId(@PathVariable(required = true) codigoFuncionario: Int): ResponseEntity<Usuario> {
        return usuarioService.listarUsuarioPorId(codigoFuncionario)
    }

    @Operation(summary = "Edita o nome de um usuário", description = "Atualiza o nome de um usuário específico pelo ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Nome do usuário atualizado com sucesso", content = [Content(schema = Schema(implementation = Usuario::class))]),
            ApiResponse(responseCode = "400", description = "Nome inválido"),
            ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        ]
    )
    @PatchMapping("/nome")
    fun patchEditarNomeUsuario(
        @Valid @RequestBody dto: EditarUsuarioDto,
        @RequestParam codigoFuncionario: Int
    ): ResponseEntity<Usuario> {
        return usuarioService.editarNomeUsuario(codigoFuncionario, dto)
    }

    @Operation(summary = "Edita o nome de um usuário", description = "Atualiza a senha de um usuário específico pelo ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Senha do usuário atualizado com sucesso", content = [Content(schema = Schema(implementation = Usuario::class))]),
            ApiResponse(responseCode = "400", description = "Senha inválida, precisa ter no mínimo 8 caracteres."),
            ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        ]
    )
    @PatchMapping("/senha")
    fun patchEditarSenhaUsuario(
        @Valid @RequestBody senha: EditarUsuarioDto,
        @RequestParam codigoFuncionario: Int
    ): ResponseEntity<Usuario> {
        return usuarioService.editarSenhaUsuario(codigoFuncionario, senha)
    }

    @Operation(summary = "Exclui um usuário", description = "Remove um usuário específico pelo ID.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        ]
    )
    @DeleteMapping("/{codigoFuncionario}")
    fun deleteExcluirUsuario(@PathVariable codigoFuncionario: Int): ResponseEntity<Usuario> {
        return usuarioService.excluirUsuario(codigoFuncionario)
    }

    @Operation(summary = "Login do usuário", description = "Realiza o login de um usuário com base no código do funcionário e senha.")
    @GetMapping("/login")
    fun login(@RequestBody loginUsuario: Usuario): ResponseEntity<String> {
        return usuarioService.loginUsuario(loginUsuario)
    }

    @Operation(summary = "Logoff do usuário", description = "Realiza o logoff de um usuário com base no código do funcionário.")
    @GetMapping("/logoff")
    fun logoff(@RequestBody nomeEntrada: Usuario): ResponseEntity<String> {
        return usuarioService.logoffUsuario(nomeEntrada)
    }
}