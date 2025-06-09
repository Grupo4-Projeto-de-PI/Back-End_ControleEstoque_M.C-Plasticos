package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.ParceiroComercialHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ParceiroComercialServiceTest {


    private lateinit var repositorio: ParceiroComercialRepositorio
    private lateinit var service: ParceiroComercialService
    private lateinit var helper: ParceiroComercialHelper


    @BeforeEach
    fun setup() {
        repositorio = mock(ParceiroComercialRepositorio::class.java)
        helper = mock(ParceiroComercialHelper::class.java)
        service = ParceiroComercialService(repositorio, helper)
    }

    @Test
    fun `listarTodosParceiros deve retornar 204 quando lista estiver vazia`() {
        `when`(repositorio.findAll()).thenReturn(emptyList())

        val response = service.listarTodosParceiros()

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `listarTodosParceiros deve retornar 200 com lista de parceiros`() {
        val parceiro = ParceiroComercial(1, "Fornecedor X", "11999999999", tipoComercialEnum.PJ, papelComercialEnum.FN)
        `when`(repositorio.findAll()).thenReturn(listOf(parceiro))

        val response = service.listarTodosParceiros()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `criarParceiro deve retornar 201 com parceiro salvo`() {
        val parceiro = ParceiroComercial(1, "Cliente A", "11888888888", tipoComercialEnum.PF, papelComercialEnum.CL)
        `when`(repositorio.save(parceiro)).thenReturn(parceiro)

        val response = service.criarParceiro(parceiro)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(parceiro, response.body)
    }

    @Test
    fun `deletarParceiro deve retornar 204 quando parceiro existir`() {
        val id = 1
        `when`(repositorio.existsById(id)).thenReturn(true)

        val response = service.deletarParceiro(id)

        verify(repositorio).deleteById(id)
        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `deletarParceiro deve retornar 404 quando parceiro não existir`() {
        val id = 1
        `when`(repositorio.existsById(id)).thenReturn(false)

        val response = service.deletarParceiro(id)

        verify(repositorio, never()).deleteById(id)
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }


    @Test
    fun `atualizarParceiro deve atualizar campos corretamente quando parceiro existir`() {
        val id = 1
        val parceiroExistente = ParceiroComercial(id, "Antigo Nome", "11666666666", tipoComercialEnum.PF, papelComercialEnum.CL)
        val parceiroAtualizado = ParceiroComercial(id, "Novo Nome", "11999999999", tipoComercialEnum.PJ, papelComercialEnum.CLFN)

        `when`(repositorio.existsById(id)).thenReturn(true)
        `when`(repositorio.findById(id)).thenReturn(Optional.of(parceiroExistente))
        `when`(repositorio.save(any())).thenAnswer { invocation ->
            val parceiro = invocation.arguments[0] as ParceiroComercial
            parceiro
        }

        val response = service.atualizarParceiro(id, parceiroAtualizado)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotEquals(parceiroAtualizado.nome, response.body?.nome)
        assertNotEquals(parceiroAtualizado.telefone, response.body?.telefone)
        assertNotEquals(parceiroAtualizado.tipoComercial, response.body?.tipoComercial)
        assertNotEquals(parceiroAtualizado.papelComercial, response.body?.papelComercial)
    }
}