	package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


	@SpringBootApplication
class Application

	fun main(args: Array<String>) {
		// Carrega o .env
		val dotenv = Dotenv.load()

		// Define as variáveis como propriedades do sistema para o Spring ler
		System.setProperty("DB_HOST", dotenv["DB_HOST"])
		System.setProperty("DB_PORT", dotenv["DB_PORT"])
		System.setProperty("DB_NAME", dotenv["DB_NAME"])
		System.setProperty("DB_USER", dotenv["DB_USER"])
		System.setProperty("DB_PASSWORD", dotenv["DB_PASSWORD"])

		runApplication<Application>(*args)
		println("Aplicação iniciada com sucesso! Acessando o banco: ${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]} " +
				"com o user " +
				dotenv["DB_USER"]
		)
	}
