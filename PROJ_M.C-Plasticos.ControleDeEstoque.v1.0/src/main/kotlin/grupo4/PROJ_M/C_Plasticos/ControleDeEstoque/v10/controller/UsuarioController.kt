package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.UsuarioService
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário com base nos dados fornecidos.")
    @PostMapping
    fun postCriarUsuario(@RequestBody @Valid novoUsuario: CriarUsuarioDto): ResponseEntity<Usuario> {
        return usuarioService.criarUsuario(novoUsuario)
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @GetMapping
    fun listarTodosUsuarios(): ResponseEntity<List<Usuario>> {
        return usuarioService.listarTodosUsuarios()
    }

    @Operation(summary = "Listar usuário por ID", description = "Retorna os dados de um usuário específico com base no código do funcionário.")
    @GetMapping("{codigoFuncionario}")
    fun listarUsuarioId(@PathVariable(required = true) codigoFuncionario: Int): ResponseEntity<Usuario> {
        return usuarioService.listarUsuarioPorId(codigoFuncionario)
    }

    @Operation(summary = "Editar nome do usuário", description = "Atualiza o nome de um usuário específico.")
    @PatchMapping("/nome")
    fun patchEditarNomeUsuario(
        @Valid @RequestBody dto: EditarUsuarioDto,
        @RequestParam codigoFuncionario: Int
    ): ResponseEntity<Usuario> {
        return usuarioService.editarNomeUsuario(codigoFuncionario, dto)
    }

    @Operation(summary = "Editar senha do usuário", description = "Atualiza a senha de um usuário específico.")
    @PatchMapping("/senha")
    fun patchEditarSenhaUsuario(
        @Valid @RequestBody senha: EditarUsuarioDto,
        @RequestParam codigoFuncionario: Int
    ): ResponseEntity<Usuario> {
        return usuarioService.editarSenhaUsuario(codigoFuncionario, senha)
    }

    @Operation(summary = "Excluir usuário", description = "Exclui um usuário com base no código do funcionário.")
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