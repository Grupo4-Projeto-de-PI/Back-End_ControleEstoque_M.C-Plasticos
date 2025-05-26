package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogPrecoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.LogPrecoProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDateTime

@RestController
@RequestMapping("/log-preco-produto")
class LogPrecoProdutoController(val repositorio: LogPrecoProdutoRepositorio,
val produtoRepositorio: ProdutoRepositorio
) {

    @PostMapping
    fun criar(@RequestBody produto: Produto): ResponseEntity<Produto> {
        val salvo = produtoRepositorio.save(produto)

        if (salvo.precoMedio != null) {
            val log = LogPrecoProduto(
                fkProduto = salvo,
                precoAntigo = null,
                precoNovo = salvo.precoMedio!!.toBigDecimal(),
                dataAlteracao = LocalDateTime.now()
            )
            repositorio.save(log)
        }

        return ResponseEntity.ok(salvo)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody produto: Produto): ResponseEntity<Produto> {
        val existente = produtoRepositorio.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        val precoAntigo = existente.precoMedio
        val precoNovo = produto.precoMedio

        existente.nome = produto.nome
        existente.tipo = produto.tipo
        existente.fkUsuario = produto.fkUsuario
        existente.precoMedio = precoNovo

        val atualizado = produtoRepositorio.save(existente)

        if (precoAntigo != null && precoNovo != null && precoAntigo != precoNovo) {
            val log = LogPrecoProduto(
                fkProduto = atualizado,
                precoAntigo = BigDecimal.valueOf(precoAntigo),
                precoNovo = BigDecimal.valueOf(precoNovo),
                dataAlteracao = LocalDateTime.now()
            )
            repositorio.save(log)
        }

        return ResponseEntity.ok(atualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        val produto = produtoRepositorio.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        if (produto.precoMedio != null) {
            val log = LogPrecoProduto(
                fkProduto = produto,
                precoAntigo = produto.precoMedio!!.toBigDecimal(),
                precoNovo = null,
                dataAlteracao = LocalDateTime.now()
            )
            repositorio.save(log)
        }

        produtoRepositorio.delete(produto)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun listar(): List<Produto> = produtoRepositorio.findAll()

}