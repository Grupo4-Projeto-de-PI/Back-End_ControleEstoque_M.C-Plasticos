package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.ProdutoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import java.time.LocalDate
import java.util.*


class ProdutoServiceTest {


    private lateinit var repositorio: ProdutoRepositorio
    private lateinit var service: ProdutoService

    @BeforeEach
    fun setup() {
        repositorio = mock(ProdutoRepositorio::class.java)
        service = ProdutoService(repositorio)
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

//    @Test
//    fun `getProdutoPorId deve retornar produto quando encontrado`() {
//        val produto = Produto(
//            id = 1,
//            nome = "Ferro",
//            tipo = null,
//            fkUsuario = null,
//            preco = 50.0
//        )
//        `when`(repositorio.findById(1)).thenReturn(Optional.of(produto))
//
//        val response = service.getProdutoPorId(1)
//
//        assertEquals(HttpStatus.OK, response.statusCode)
//        assertEquals(produto, response.body)
//    }

//    @Test
//    fun `getProdutoPorId deve retornar 404 quando não encontrado`() {
//        val idInexistente = 999
//
//        `when`(repositorio.findById(idInexistente)).thenReturn(Optional.empty())
//
//        val response = service.getProdutoPorId(idInexistente)
//
//        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
//    }

    @Test
    fun `criarProduto deve retornar 201 com produto salvo`() {
        val produto = Produto(
            id = 1,
            nome = "Ferro",
            tipo = null,
            fkUsuario = null,
            preco = 50.0
        )
        `when`(repositorio.save(produto)).thenReturn(produto)

        val response = service.criarProduto(produto)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(produto, response.body)
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
        val produtoAtualizado = Produto(
            id = 1,
            nome = "Ferro",
            tipo = null,
            fkUsuario = null,
            preco = 50.0
        )

        `when`(repositorio.existsById(id)).thenReturn(false)

        val response = service.atualizarProduto(id, produtoAtualizado)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `atualizarProduto deve atualizar e retornar produto quando existir`() {
        val id = 1
        val produtoAtualizado = Produto(
            id = id,
            nome = "Alumínio",
            tipo = null,
            fkUsuario = null,
            preco = 60.0,
            dataCadastro = LocalDate.of(2024, 1, 1) // Data fixa para previsibilidade
        )

        `when`(repositorio.existsById(id)).thenReturn(true)
        `when`(repositorio.save(any())).thenReturn(produtoAtualizado)

        val response = service.atualizarProduto(id, produtoAtualizado)

        assertEquals(HttpStatus.OK, response.statusCode)

        val body = response.body!!
        assertEquals(produtoAtualizado.id, body.id)
        assertEquals(produtoAtualizado.nome, body.nome)
        assertEquals(produtoAtualizado.tipo, body.tipo)
        assertEquals(produtoAtualizado.fkUsuario, body.fkUsuario)
        assertEquals(produtoAtualizado.preco, body.preco)
        // Opcionalmente comparar dataCadastro se for previsível
        assertEquals(produtoAtualizado.dataCadastro, body.dataCadastro)
    }

    @Test
    fun `atualizarPrecoProduto deve retornar 404 se produto não existir`() {
        val id = 2
        `when`(repositorio.existsById(id)).thenReturn(false)

        val response = service.atualizarPrecoProduto(id, 199.0)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

}