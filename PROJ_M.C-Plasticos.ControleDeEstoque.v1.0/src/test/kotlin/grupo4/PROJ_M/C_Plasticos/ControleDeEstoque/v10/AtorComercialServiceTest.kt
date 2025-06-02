package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.AtorComercialService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class AtorComercialServiceTest {

    val service = AtorComercialService()

    @Test
    fun `getTelefone deve retornar telefone válido quando tiver 11 dígitos`() {
        val telefone = "11987654321"
        val resultado = service.getTelefone(telefone)
        assertEquals(telefone, resultado)
    }

    @Test
    fun `getTelefone deve lançar exceção quando telefone tiver menos de 11 dígitos`() {
        val telefoneInvalido = "1198765432"
        val excecao = assertThrows<IllegalArgumentException> {
            service.getTelefone(telefoneInvalido)
        }
        assertEquals("O telefone deve ter 11 algarismos", excecao.message)
    }

    @Test
    fun `getTelefone deve lançar exceção quando telefone tiver mais de 11 dígitos`() {
        val telefoneInvalido = "119876543210"
        val excecao = assertThrows<IllegalArgumentException> {
            service.getTelefone(telefoneInvalido)
        }
        assertEquals("O telefone deve ter 11 algarismos", excecao.message)
    }

    @Test
    fun `getNome deve retornar nome quando nome for válido`() {
        val nome = "João da Silva"
        val resultado = service.getNome(nome)
        assertEquals(nome, resultado)
    }

    @Test
    fun `getNome deve lançar exceção quando nome for vazio`() {
        val nomeInvalido = ""
        val excecao = assertThrows<IllegalArgumentException> {
            service.getNome(nomeInvalido)
        }
        assertEquals("O nome não pode ser vazio", excecao.message)
    }

    @Test
    fun `getNome deve lançar exceção quando nome for apenas espaços em branco`() {
        val nomeInvalido = "    "
        val excecao = assertThrows<IllegalArgumentException> {
            service.getNome(nomeInvalido)
        }
        assertEquals("O nome não pode ser vazio", excecao.message)
    }


}