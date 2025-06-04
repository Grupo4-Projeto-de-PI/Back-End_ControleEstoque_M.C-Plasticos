package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Usuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.TransacaoHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TransacaoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.TransacaoService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock


class TransacaoServiceTest {

    private lateinit var transacaoService: TransacaoService
    private val transacaoRepositorio = mock(TransacaoRepositorio::class.java)
    private val produtoRepositorio = mock(ProdutoRepositorio::class.java)
    private val parceiroComercialRepositorio = mock(ParceiroComercialRepositorio::class.java)
    private val usuarioRepositorio = mock(UsuarioRepositorio::class.java)
    private val transacaoHelper = mock(TransacaoHelper::class.java)

    @BeforeEach
    fun setup() {
        transacaoService = TransacaoService(
            transacaoRepositorio,
            produtoRepositorio,
            parceiroComercialRepositorio,
            usuarioRepositorio,
            transacaoHelper
        )
    }

    @Test
    fun `deve criar uma transacao com sucesso`() {
        // Arrange
        val novaTransacao = NovaTransacaoDto(
            fkProduto = 1,
            fkParceiroComercial = 2,
            fkUsuario = 3,
            categoria = 0,  // GR
            tipoOperacao = 1, // Saida
            peso = 10.0,
            valorTotal = 100.0
        )

        val produto = Produto(id = 1, nome = "Produto Teste")
        val parceiro = ParceiroComercial(id = 2, nome = "Parceiro Teste")
        val usuario = Usuario(id = 3, nome = "Usuario Teste")

        `when`(produtoRepositorio.findById(1)).thenReturn(Optional.of(produto))
        `when`(parceiroComercialRepositorio.findById(2)).thenReturn(Optional.of(parceiro))
        `when`(usuarioRepositorio.findById(3)).thenReturn(Optional.of(usuario))
        `when`(transacaoRepositorio.save(any(Transacao::class.java))).thenAnswer { it.arguments[0] }

        // Act
        val response = transacaoService.criarTransacao(novaTransacao)

        // Assert
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertNotNull(response.body)
        assertEquals(produto, response.body?.fkProduto)
        assertEquals(parceiro, response.body?.fkParceiroComercial)
        assertEquals(usuario, response.body?.fkUsuario)
        assertEquals(categoriaEnum.GR, response.body?.categoria)
        assertEquals(tipoOperacaoEnum.Saida, response.body?.tipoOperacao)
        assertEquals(10.0, response.body?.peso)
        assertEquals(100.0, response.body?.valorTotal)
    }

}