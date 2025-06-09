package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica.Clima
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica.ClimaRepository
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica.ClimaService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ClimaServiceTest {

    @Mock
    lateinit var repository: ClimaRepository

    @InjectMocks
    lateinit var service: ClimaService

    @Test
    fun `deve salvar clima`() {
        val clima = Clima(0, "Campinas", "nublado", 20.0, 80, 3.5)
        val salvo = clima.copy(id = 1L)

        `when`(repository.save(clima)).thenReturn(salvo)

        val resultado = service.salvar(clima)

        assertEquals(1L, resultado.id)
        assertEquals("Campinas", resultado.cidade)
    }

    @Test
    fun `deve retornar lista de climas`() {
        val lista = listOf(
            Clima(1L, "Campinas", "nublado", 20.0, 80, 3.5),
            Clima(2L, "São Paulo", "ensolarado", 25.0, 60, 2.1)
        )

        `when`(repository.findAll()).thenReturn(lista)

        val resultado = service.listar()

        assertEquals(2, resultado.size)
        assertEquals("São Paulo", resultado[1].cidade)
    }

    @Test
    fun `deve buscar clima por ID existente`() {
        val clima = Clima(1L, "Campinas", "chuva", 19.0, 90, 3.0)

        `when`(repository.findById(1L)).thenReturn(Optional.of(clima))

        val resultado = service.buscarPorId(1L)

        assertNotNull(resultado)
        assertEquals("Campinas", resultado?.cidade)
    }

    @Test
    fun `deve retornar null ao buscar clima por ID inexistente`() {
        `when`(repository.findById(99L)).thenReturn(Optional.empty())

        val resultado = service.buscarPorId(99L)

        assertNull(resultado)
    }

    @Test
    fun `deve atualizar clima existente`() {
        val existente = Clima(1L, "Campinas", "chuva", 18.0, 88, 2.5)
        val novo = Clima(1L, "Campinas", "nublado", 21.0, 70, 3.0)

        `when`(repository.findById(1L)).thenReturn(Optional.of(existente))
        `when`(repository.save(any())).thenReturn(novo)

        val atualizado = service.atualizar(1L, novo)

        assertNotNull(atualizado)
        assertEquals("nublado", atualizado?.descricao)
        assertEquals(21.0, atualizado?.temperatura)
    }

    @Test
    fun `deve retornar null ao atualizar clima inexistente`() {
        val novo = Clima(1L, "Campinas", "nublado", 21.0, 70, 3.0)

        `when`(repository.findById(1L)).thenReturn(Optional.empty())

        val atualizado = service.atualizar(1L, novo)

        assertNull(atualizado)
    }

    @Test
    fun `deve deletar clima`() {
        doNothing().`when`(repository).deleteById(1L)

        service.deletar(1L)

        verify(repository, times(1)).deleteById(1L)
    }
}