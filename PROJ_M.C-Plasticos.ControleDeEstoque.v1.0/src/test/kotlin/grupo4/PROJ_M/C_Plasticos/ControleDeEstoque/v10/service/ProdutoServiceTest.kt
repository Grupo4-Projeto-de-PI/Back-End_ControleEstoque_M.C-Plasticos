package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.CriarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.usuarioEnum.tipoUsuarioEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.ProdutoHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import java.util.*


class ProdutoServiceTest {


    private lateinit var repositorio: ProdutoRepositorio
    private lateinit var service: ProdutoService
    private lateinit var produtoHelper: ProdutoHelper
    private lateinit var tipoProdutoRepositorio: TipoProdutoRepositorio
    private lateinit var usuarioRepositorio: UsuarioRepositorio

    @BeforeEach
    fun setup() {
        produtoHelper = mock(ProdutoHelper::class.java)
        tipoProdutoRepositorio = mock(TipoProdutoRepositorio::class.java)
        usuarioRepositorio = mock(UsuarioRepositorio::class.java)
        repositorio = mock(ProdutoRepositorio::class.java)
        service = ProdutoService(repositorio,tipoProdutoRepositorio, usuarioRepositorio, produtoHelper)
    }

    @Test
    fun `listarTodosProdutos deve retornar 204 quando lista estiver vazia`() {
        `when`(repositorio.findAll()).thenReturn(emptyList())

        val response = service.listarTodosProdutos()

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `listarTodosProdutos deve retornar 200 com lista de produtos`() {
        val produto = Produto(
            id = 1,
            nome = "Ferro",
            tipo = null,
            fkUsuario = null,
            preco = 50.0
        )

        `when`(repositorio.findAll()).thenReturn(listOf(produto))

        val response = service.listarTodosProdutos()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `getProdutoPorId deve retornar produto quando encontrado`() {
        val produto = Produto(
            id = 1,
            nome = "Ferro",
            tipo = null,
            fkUsuario = null,
            preco = 50.0
        )
        `when`(repositorio.findById(1)).thenReturn(Optional.of(produto))

        val response = service.getProdutoPorId(1)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(produto, response.body)
    }

    @Test
    fun `getProdutoPorId deve retornar 404 quando não encontrado`() {
        val idInexistente = 999

        `when`(repositorio.findById(idInexistente)).thenReturn(Optional.empty())

        val response = service.getProdutoPorId(idInexistente)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `criarProduto deve retornar 201 com produto salvo`() {

        val tipoProduto = TipoProduto(id = 1, tipo = "Metal")
        val fkUsuario = Usuario(
            codigoFuncionario = 1,
            nome = "Usuario Teste",
            senha = "senha123",
            tipoUsuario = tipoUsuarioEnum.Admin,
        )

        val produtoDto = CriarProdutoDto(
            nome = "Ferro",
            tipo = 1,
            fkUsuario = 1,
            meta = 100.0,
        )

        val produtoSalvo = Produto(
            nome = produtoDto.nome,
            tipo = tipoProduto,
            fkUsuario = fkUsuario,
            preco = 0.0
        )

        `when`(tipoProdutoRepositorio.findById(produtoDto.tipo)).thenReturn(Optional.of(tipoProduto))
        `when`(usuarioRepositorio.findById(produtoDto.fkUsuario)).thenReturn(Optional.of(fkUsuario))

        val response = service.criarProduto(produtoDto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(produtoSalvo.nome, response.body?.nome)
        assertEquals(produtoSalvo.tipo?.id, response.body?.tipo?.id)
        assertEquals(produtoSalvo.fkUsuario?.codigoFuncionario, response.body?.fkUsuario?.codigoFuncionario)
    }

    @Test
    fun `deletarProduto deve retornar 204 quando produto existir`() {
        val id = 1
        `when`(repositorio.existsById(id)).thenReturn(true)

        val response = service.deletarProduto(id)

        verify(repositorio).deleteById(id)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `deletarProduto deve retornar 404 quando produto não existir`() {
        val id = 1
        `when`(repositorio.existsById(id)).thenReturn(false)

        val response = service.deletarProduto(id)

        verify(repositorio, never()).deleteById(id)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `atualizarProduto deve retornar 404 quando produto não existir`() {
        val id = 1
        val produtoAtualizado = AtualizarProdutoDto(
            nome = "Ferro",
            tipo = null,
        )

        `when`(repositorio.existsById(id)).thenReturn(false)

        val response = service.atualizarProduto(id, produtoAtualizado)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `atualizarProduto deve atualizar e retornar produto quando existir`() {
        val id = 1
        val tipoProduto = TipoProduto(id = 1, tipo = "Metal")

        val produtoAtualizado = AtualizarProdutoDto(
            nome = "Alumínio",
            tipo = 1,
        )

        val produtoExistente = Produto(
            id = id,
            nome = "Ferro",
            tipo = tipoProduto,
            fkUsuario = null,
            preco = 50.0
        )

        val produtoAtualizadoEsperado = Produto(
            id = id,
            nome = produtoAtualizado.nome,
            tipo = tipoProduto,
            fkUsuario = null,
            preco = 50.0
        )

        `when`(tipoProdutoRepositorio.findById(produtoAtualizado.tipo!!)).thenReturn(Optional.of(tipoProduto))
        `when`(repositorio.existsById(id)).thenReturn(true)
        `when`(repositorio.findById(id)).thenReturn(Optional.of(produtoExistente))
        `when`(repositorio.save(any(Produto::class.java))).thenReturn(produtoAtualizadoEsperado)
        doAnswer { invocation ->
            val produto = invocation.arguments[1] as Produto
            produto.nome = produtoAtualizado.nome
            produto.tipo = tipoProduto
            null
        }.`when`(produtoHelper).atualizarProduto(produtoAtualizado, produtoExistente)

        val response = service.atualizarProduto(1, produtoAtualizado)

        assertEquals(HttpStatus.OK, response.statusCode)

        val body = response.body!!

        assertEquals(produtoAtualizado.nome, body.nome)
        assertEquals(produtoAtualizado.tipo, body.tipo?.id)
    }
}