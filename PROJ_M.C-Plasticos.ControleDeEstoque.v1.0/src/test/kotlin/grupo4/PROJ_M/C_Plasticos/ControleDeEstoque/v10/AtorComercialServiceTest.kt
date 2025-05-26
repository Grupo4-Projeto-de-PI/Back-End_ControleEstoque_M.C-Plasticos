package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.AtorComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Service.AtorComercialService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AtorComercialServiceTest {

    val service = AtorComercialService()

    @ParameterizedTest
    @CsvSource(
        "11111111111",
        "22222222222",
        "33333333333"
    )

//    @Test
//    fun `deve passar quando todos os dados forem válidos`() {
//
//
//
//    }

    @Test
    fun `telefones que não tem 11 algarismos não devem ser aceitos`() {

        val telefoneTeste = "11940028922"

        assertThrows(IllegalArgumentException::class.java) {
            service.getTelefone(telefoneTeste)
        }

    }

    @Test
    fun `nomes e tipo de pessoa em branco não devem ser aceitos`() {

        val nomeTeste = "José"

        assertThrows(IllegalArgumentException::class.java) {
            service.getNome(nomeTeste)
        }
    }



}