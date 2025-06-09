package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica

import org.springframework.data.jpa.repository.JpaRepository

interface ClimaRepository : JpaRepository<Clima, Long> {
    fun findByCidade(cidade: String): Clima?
}