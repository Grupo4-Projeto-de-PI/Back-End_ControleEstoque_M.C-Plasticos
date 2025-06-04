package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.monica

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.json.JSONObject

@RestController
@RequestMapping("/clima")
class ClimaController {

    private val apiKey = "3cc2ca5b51ee68307eecb53d90bb1e01"

    @GetMapping("/{cidade}")
    fun obterClima(@PathVariable cidade: String): ResponseEntity<ClimaDto> {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${cidade}&appid=${apiKey}&units=metric&lang=pt_br"

        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject(url, String::class.java)

        return try {
            val json = JSONObject(response)

            val nomeCidade = json.getString("name")
            val descricao = json.getJSONArray("weather").getJSONObject(0).getString("description")
            val temperatura = json.getJSONObject("main").getDouble("temp")
            val umidade = json.getJSONObject("main").getInt("humidity")
            val vento = json.getJSONObject("wind").getDouble("speed")

            val clima = ClimaDto(nomeCidade, descricao, temperatura, umidade, vento)

            ResponseEntity.ok(clima)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }
}