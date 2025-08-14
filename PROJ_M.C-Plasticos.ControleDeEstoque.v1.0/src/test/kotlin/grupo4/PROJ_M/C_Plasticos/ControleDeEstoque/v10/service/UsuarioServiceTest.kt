package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.CriarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.usuarioDto.EditarUsuarioDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.usuarioEnum.tipoUsuarioEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import java.util.*

class UsuarioServiceTest {

    private lateinit var repositorio: UsuarioRepositorio
    private lateinit var service: UsuarioService
    
    @BeforeEach
    fun setup() {
        repositorio = mock(UsuarioRepositorio::class.java)
        service = UsuarioService(repositorio)
    }
    
    @Test
    fun `deve criar usuario com sucesso`() {
        val novoUsuario = CriarUsuarioDto(nome = "Lucas", senha = "123456", tipoUsuario = tipoUsuarioEnum.Admin)
        val usuarioCriado = Usuario(nome = novoUsuario.nome, senha = novoUsuario.senha, tipoUsuario = novoUsuario.tipoUsuario)

        `when`(repositorio.save(any())).thenReturn(usuarioCriado)

        val response = service.criarUsuario(novoUsuario)

        assertEquals(201, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(novoUsuario.nome, response.body?.nome)
        assertEquals(novoUsuario.tipoUsuario, response.body?.tipoUsuario)
    }

    @Test
    fun `deve listar todos os usuarios com sucesso`() {
        val usuarios = listOf(
            Usuario(nome = "Lucas", senha = "123456", tipoUsuario = tipoUsuarioEnum.Admin),
            Usuario(nome = "Maria", senha = "654321", tipoUsuario = tipoUsuarioEnum.Estoquista)
        )

        `when`(repositorio.findAll()).thenReturn(usuarios)

        val response = service.listarTodosUsuarios()

        assertEquals(200, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(2, response.body?.size)
    }

    @Test
    fun `deve retornar 204 quando nao houver usuarios`() {
        `when`(repositorio.findAll()).thenReturn(emptyList())

        val response = service.listarTodosUsuarios()

        assertEquals(204, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve listar usuario por ID com sucesso`() {
        val idUsuario = 1
        val usuario = Usuario(
            codigoFuncionario = idUsuario, nome = "Lucas", senha = "123456",
            tipoUsuario = tipoUsuarioEnum.Admin)

        `when`(repositorio.existsById(idUsuario)).thenReturn(true)
        `when`(repositorio.findById(idUsuario)).thenReturn(Optional.of(usuario))

        val response = service.listarUsuarioPorId(idUsuario)

        assertEquals(200, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(usuario.nome, response.body?.nome)
    }

    @Test
    fun `deve editar nome do usuario com sucesso`() {
        val idUsuario = 1
        val dto = EditarUsuarioDto(nome = "Lucas Atualizado")
        val usuarioAtualizado = Usuario(
            codigoFuncionario = idUsuario, nome = dto.nome, senha = "123456",
            tipoUsuario = tipoUsuarioEnum.Admin)

        `when`(repositorio.existsById(idUsuario)).thenReturn(true)
        `when`(repositorio.findById(idUsuario)).thenReturn(Optional.of(usuarioAtualizado))
        `when`(repositorio.atualizarNome(idUsuario, dto.nome)).thenReturn(1)

        val response = service.editarNomeUsuario(idUsuario, dto)

        assertEquals(200, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(dto.nome, response.body?.nome)
    }

    @Test
    fun `deve excluir usuario com sucesso`() {
        val idUsuario = 1

        `when`(repositorio.existsById(idUsuario)).thenReturn(true)

        val response = service.excluirUsuario(idUsuario)

        assertEquals(204, response.statusCodeValue)
    }

    @Test
    fun `deve retornar 400 ao criar usuario com nome vazio`() {
        val novoUsuario = CriarUsuarioDto(nome = "", senha = "123456", tipoUsuario = tipoUsuarioEnum.Admin)

        val response = service.criarUsuario(novoUsuario)

        assertEquals(400, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve retornar 404 ao listar usuario por ID inexistente`() {
        val idUsuario = 999

        `when`(repositorio.existsById(idUsuario)).thenReturn(false)

        val response = service.listarUsuarioPorId(idUsuario)

        assertEquals(204, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve retornar 400 ao editar nome do usuario com nome vazio`() {
        val idUsuario = 1
        val dto = EditarUsuarioDto(nome = "")

        `when`(repositorio.existsById(idUsuario)).thenReturn(true)

        val response = service.editarNomeUsuario(idUsuario, dto)

        assertEquals(400, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve retornar 404 ao editar nome de usuario inexistente`() {
        val idUsuario = 999
        val dto = EditarUsuarioDto(nome = "Novo Nome")

        `when`(repositorio.existsById(idUsuario)).thenReturn(false)

        val response = service.editarNomeUsuario(idUsuario, dto)

        assertEquals(404, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve retornar 404 ao excluir usuario inexistente`() {
        val idUsuario = 999

        `when`(repositorio.existsById(idUsuario)).thenReturn(false)

        val response = service.excluirUsuario(idUsuario)

        assertEquals(404, response.statusCodeValue)
    }
}