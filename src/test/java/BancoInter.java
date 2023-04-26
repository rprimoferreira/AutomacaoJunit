import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BancoInter {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {

		// cod para indicar o naveador
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");

		// comando para chamar o driver dentro do before
		driver = new ChromeDriver();

		// Abrir 4devs
		driver.get("https://www.4devs.com.br/gerador_de_pessoas/");

		// comando para maximizar a tela
		driver.manage().window().maximize();

		driver.findElement(By.cssSelector("#bt_gerar_pessoa")).click();

		// Nome
		String nome = driver.findElement(By.id("nome")).getText();
		// Celular
		String celular = driver.findElement(By.id("celular")).getText();
		// Email
		String email = driver.findElement(By.id("email")).getText();
		// CPF
		String cpf = driver.findElement(By.id("cpf")).getText();
		// Data Nascimento
		String nascimento = driver.findElement(By.id("data_nasc")).getText();

		// Abrir uma nova aba
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.bancointer.com.br/");


		// comando para interagir com o elemento (ok)(abra sua conta)
		driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();

		driver.findElement(By.cssSelector("#gatsby-focus-wrapper > div > header >"
				+ " section > div > div > div > div > div.styles__LogoNIcons-sc-1gbjc3e-6.gjJzHM >"
				+ " div.styles__SearchNFlags-sc-1gbjc3e-8.yVPnY > div.styles__ButtonsWrapper-sc-1gbjc3e-9.PKrxs >"
				+ " button:nth-child(1)")).click();

		// pausa
		Thread.sleep(2000);

		// Nome
		driver.findElement(By.id("nome")).sendKeys(nome);

		// Celular
		driver.findElement(By.id("celular")).sendKeys(celular);

		// Email
		driver.findElement(By.id("email")).sendKeys(email);

		// CPF
		driver.findElement(By.id("cpf")).sendKeys(cpf);
		// Data Nascimento
		driver.findElement(By.id("dataNascimento")).sendKeys(nascimento);

		// checkbox
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK"
				+ " > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center"
				+ " > div > div:nth-child(2) > form > div > div:nth-child(6) > div > label")).click();
		// continue
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK"
				+ " > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center"
				+ " > div > div:nth-child(2) > form > div > div.col-12.text-center > button")).click();
	}

	@After
	public void tearDown() throws Exception {

		// driver.quit();

	}

	@Test
	public void test() throws InterruptedException {

		Thread.sleep(1000);

		String texto = driver
				.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK"
						+ " > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center.sent > div > p"))
				.getText();

		System.out.println(texto);

		// Validaçao

		assertEquals("Prontinho! Recebemos os seus dados.", texto);

	}
}