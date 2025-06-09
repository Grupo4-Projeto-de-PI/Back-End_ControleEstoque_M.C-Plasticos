package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.ApisIndividuais.monica
import jakarta.persistence.*

@Entity
data class Clima(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val cidade: String,
    val descricao: String,
    val temperatura: Double,
    val umidade: Int,
    val vento: Double
)