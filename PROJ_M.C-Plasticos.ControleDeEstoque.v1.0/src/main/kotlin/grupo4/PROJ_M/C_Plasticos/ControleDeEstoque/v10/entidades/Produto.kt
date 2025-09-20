package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.produtoEnum.ProdutoNivelSaude
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.produtoEnum.ProdutoPrioridade
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDateTime

@Entity
data class Produto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field: NotBlank
    var nome: String? = null,

    @JsonIgnore
    @field: PositiveOrZero
    var preco: Double? = null,

    var prioridade: ProdutoPrioridade = ProdutoPrioridade.BAIXA, // Valor padrão

    var nivelSaude: ProdutoNivelSaude? = null,

    @field:PositiveOrZero
    var precoMaximo: Double? = null,

    @field:PositiveOrZero
    var precoMinimo: Double? = null,

    @field:PositiveOrZero
    var precoMedio: Double? = null,

    @ManyToOne
    @JoinColumn(name = "tipo_produto", referencedColumnName = "id")
    var tipo: TipoProduto? = null,

    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "codigo_funcionario")
    var fkUsuario: Usuario? = null,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var dataCadastro: LocalDateTime = LocalDateTime.now(),

    @Column(length = 100*1024*1024)
    var fotoProduto: ByteArray? = null
)

