package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.EditarTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.TransacaoHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TransacaoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.any
import org.springframework.http.HttpStatus
import java.util.*

class TransacaoServiceTest {

    private lateinit var transacaoService: TransacaoService
    private val transacaoRepositorio = mock(TransacaoRepositorio::class.java)
    private val produtoRepositorio = mock(ProdutoRepositorio::class.java)
    private val parceiroComercialRepositorio = mock(ParceiroComercialRepositorio::class.java)
    private val usuarioRepositorio = mock(UsuarioRepositorio::class.java)
    private val transacaoHelper = mock(TransacaoHelper::class.java)
    private val logTransacaoService = mock(LogTransacaoService::class.java)

    @BeforeEach
    fun setup() {
        transacaoService = TransacaoService(
            transacaoRepositorio,
            produtoRepositorio,
            parceiroComercialRepositorio,
            usuarioRepositorio,
            transacaoHelper,
            logTransacaoService
        )
    }

    @Test
    fun `deve criar uma transacao com sucesso`() {
        val idProduto = 1
        val idParceiroComercial = 1
        val idUsuario = 1
        val novaTransacao = NovaTransacaoDto(
            fkProduto = idProduto,
            categoria = categoriaEnum.GR,
            peso = 15.0,
            valorTotal = 200.0,
            tipoOperacao = tipoOperacaoEnum.Entrada,
            fkParceiroComercial = idParceiroComercial,
            fkUsuario = idUsuario
        )

        val produto = Produto(id = idProduto, nome = "Produto Teste")
        val parceiroComercial = ParceiroComercial(id = idParceiroComercial, nome = "Parceiro Teste")
        val usuario = Usuario(codigoFuncionario = idUsuario, nome = "Usuario Teste")

        val transacaoEsperada = Transacao(
            fkProduto = produto,
            categoria = novaTransacao.categoria,
            peso = novaTransacao.peso,
            valorTotal = novaTransacao.valorTotal,
            tipoOperacao = novaTransacao.tipoOperacao,
            fkParceiroComercial = parceiroComercial,
            fkUsuario = usuario
        )

        `when`(produtoRepositorio.findById(idProduto)).thenReturn(Optional.of(produto))
        `when`(parceiroComercialRepositorio.findById(idParceiroComercial)).thenReturn(Optional.of(parceiroComercial))
        `when`(usuarioRepositorio.findById(idUsuario)).thenReturn(Optional.of(usuario))
        `when`(transacaoRepositorio.save(any(Transacao::class.java))).thenReturn(transacaoEsperada)

        val response = transacaoService.criarTransacao(novaTransacao)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.body)
        assertEquals(transacaoEsperada.fkProduto, response.body?.fkProduto)
        assertEquals(transacaoEsperada.fkParceiroComercial, response.body?.fkParceiroComercial)
        assertEquals(transacaoEsperada.fkUsuario, response.body?.fkUsuario)
        assertEquals(transacaoEsperada.categoria, response.body?.categoria)
        assertEquals(transacaoEsperada.tipoOperacao, response.body?.tipoOperacao)
        assertEquals(transacaoEsperada.peso, response.body?.peso)
        assertEquals(transacaoEsperada.valorTotal, response.body?.valorTotal)
    }

    @Test
    fun `deve listar todas as transacoes com sucesso`() {
        val transacao1 = Transacao(id = 1, peso = 10.0, valorTotal = 100.0)
        val transacao2 = Transacao(id = 2, peso = 20.0, valorTotal = 200.0)
        val transacoes = listOf(transacao1, transacao2)

        `when`(transacaoRepositorio.findAll()).thenReturn(transacoes)

        val response = transacaoService.listarTransacoes()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(2, response.body?.size)
        assertEquals(transacao1, response.body?.get(0))
        assertEquals(transacao2, response.body?.get(1))
    }

    @Test
    fun `deve retornar 204 quando nao houver transacoes`() {
        `when`(transacaoRepositorio.findAll()).thenReturn(emptyList())

        val response = transacaoService.listarTransacoes()

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        assertNull(response.body)
    }

    @Test
    fun `deve listar transacao por ID com sucesso`() {
        val idTransacao = 1
        val transacao = Transacao(id = idTransacao, peso = 10.0, valorTotal = 100.0)

        `when`(transacaoRepositorio.existsById(idTransacao)).thenReturn(true)
        `when`(transacaoRepositorio.findById(idTransacao)).thenReturn(Optional.of(transacao))

        val response = transacaoService.listarTransacaoPorId(idTransacao)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(transacao, response.body)
    }

    @Test
    fun `deve retornar 404 ao listar transacao por ID inexistente`() {
        val idTransacao = 1

        `when`(transacaoRepositorio.existsById(idTransacao)).thenReturn(false)

        val response = transacaoService.listarTransacaoPorId(idTransacao)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
    }

    @Test
    fun `deve atualizar transacao com sucesso`() {
        val idTransacao = 1
        val transacaoExistente = Transacao(id = idTransacao, peso = 10.0, valorTotal = 100.0)
        val transacaoAtualizada = EditarTransacaoDto(peso = 15.0, preco = 150.0)

        `when`(transacaoRepositorio.existsById(idTransacao)).thenReturn(true)
        `when`(transacaoRepositorio.findById(idTransacao)).thenReturn(Optional.of(transacaoExistente))
        `when`(transacaoRepositorio.save(any())).thenReturn(transacaoExistente)

        val response = transacaoService.atualizarTransacao(idTransacao, transacaoAtualizada)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertNotEquals(transacaoAtualizada.peso, response.body?.peso)
        assertNotEquals(transacaoAtualizada.preco, response.body?.valorTotal)
    }

    @Test
    fun `deve deletar transacao com sucesso`() {
        val idTransacao = 1

        `when`(transacaoRepositorio.existsById(idTransacao)).thenReturn(true)

        val response = transacaoService.deletarTransacao(idTransacao)

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `deve retornar 404 ao tentar deletar transacao inexistente`() {
        val idTransacao = 1

        `when`(transacaoRepositorio.existsById(idTransacao)).thenReturn(false)

        val response = transacaoService.deletarTransacao(idTransacao)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }
}