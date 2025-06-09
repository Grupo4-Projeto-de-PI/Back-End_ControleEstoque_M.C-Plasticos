package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.tipoProdutoDto.AtualizarTipoProdutoDTO
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoProdutoRepositorio
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.*

class TipoProdutoServiceTest {

    @Mock
    private lateinit var repositorio: TipoProdutoRepositorio

    @InjectMocks
    private lateinit var service: TipoProdutoService

    @BeforeEach
    fun setup() {
        repositorio = mock(TipoProdutoRepositorio::class.java)
        service = TipoProdutoService(repositorio)
    }

    @Test
    fun `deve listar todos os tipos de produtos com sucesso`() {
        val tiposProdutos = listOf(
            TipoProduto(id = 1, tipo = "Plástico"),
            TipoProduto(id = 2, tipo = "Metal")
        )

        `when`(repositorio.findAll()).thenReturn(tiposProdutos)

        val response = service.listarTodosTiposProdutos()

        assertEquals(200, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(2, response.body?.size)
    }

    @Test
    fun `deve retornar 204 quando nao houver tipos de produtos`() {
        `when`(repositorio.findAll()).thenReturn(emptyList())

        val response = service.listarTodosTiposProdutos()

        assertEquals(204, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve cadastrar tipo de produto com sucesso`() {
        val novoTipoProduto = TipoProduto(id = 1, tipo = "Plástico")
        `when`(repositorio.save(any())).thenReturn(novoTipoProduto)

        val response = service.cadastrarTipoProduto(novoTipoProduto)

        assertEquals(201, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(novoTipoProduto.tipo, response.body?.tipo)
    }

    @Test
    fun `deve atualizar tipo de produto com sucesso`() {
        val idTipoProduto = 1
        val tipoProdutoExistente = TipoProduto(id = idTipoProduto, tipo = "Plástico")
        val dto = AtualizarTipoProdutoDTO(tipo = "Metal")

        `when`(repositorio.findById(idTipoProduto)).thenReturn(Optional.of(tipoProdutoExistente))
        `when`(repositorio.save(any())).thenReturn(tipoProdutoExistente.apply { tipo = dto.tipo })

        val response = service.atualizarTipoProduto(idTipoProduto, dto)

        assertEquals(200, response.statusCodeValue)
        assertNotNull(response.body)
        assertEquals(dto.tipo, response.body?.tipo)
    }

    @Test
    fun `deve retornar 404 ao atualizar tipo de produto inexistente`() {
        val idTipoProduto = 999
        val dto = AtualizarTipoProdutoDTO(tipo = "Metal")

        `when`(repositorio.findById(idTipoProduto)).thenReturn(Optional.empty())

        val response = service.atualizarTipoProduto(idTipoProduto, dto)

        assertEquals(404, response.statusCodeValue)
        assertNull(response.body)
    }

    @Test
    fun `deve deletar tipo de produto com sucesso`() {
        val idTipoProduto = 1

        `when`(repositorio.existsById(idTipoProduto)).thenReturn(true)
        doNothing().`when`(repositorio).deleteById(idTipoProduto)

        val response = service.deletarTipoProduto(idTipoProduto)

        assertEquals(204, response.statusCodeValue)
    }

    @Test
    fun `deve retornar 404 ao deletar tipo de produto inexistente`() {
        val idTipoProduto = 999

        `when`(repositorio.existsById(idTipoProduto)).thenReturn(false)

        val response = service.deletarTipoProduto(idTipoProduto)

        assertEquals(404, response.statusCodeValue)
    }
}