package poov.projeto01cadastro.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import poov.projeto01cadastro.models.Filme;

public class FilmeIMDB {

    public Filme f = new Filme();

    public FilmeIMDB(String nomeFilme){

        // Configurar o caminho do driver (substitua pelo seu caminho real)

        // Chamar o caminho do chromedriver que eu baixei:
        // System.setProperty("webdriver.chrome.driver", "C:\\Users\\FUJIOKA\\Desktop\\07_Repositórios\\chromedriver-win64\\chromedriver.exe");

        // Chamar o chromedriver que foi baixado automaticamente por uma dependencia no pom:
        WebDriverManager.chromedriver().setup();

        // // Configurar o ChromeDriver para modo headless
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver(); // new ChromeDriver(options); pra não mostrar o chrome
        
        // Abrir o IMDb
        driver.get("https://www.imdb.com");

        // Aguardar alguns segundos para visualização (opcional)
        try {
            Thread.sleep(5000); // Espera por 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exemplo de interação: encontrar a barra de pesquisa e inserir uma consulta
        WebElement barraPesquisa = driver.findElement(By.xpath("//*[@id=\"suggestion-search\"]"));
        barraPesquisa.sendKeys(nomeFilme);

        // Enviar a consulta
        barraPesquisa.submit();

        // Encontrar o primeiro resultado da pesquisa (titulo)
        WebElement primeiroResultado = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div[3]/section/div/div[1]/section[2]/div[2]/ul/li[1]"));
        primeiroResultado.click();

        // Aguardar alguns segundos para visualização (opcional)
        try {
            Thread.sleep(5000); // Espera por 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Pegar a informações da página do filme
        WebElement nomeDoFilme = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/div"));
        WebElement duracaoDoFilme = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[3]"));
        WebElement anoDoFilme = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/ul/li[1]"));
        WebElement notaDoFilme = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/section[1]/section/div[3]/section/section/div[2]/div[2]/div/div[1]/a/span/div/div[2]/div[1]/span[1]"));

        // System.out.println(nomeDoFilme.getText());
        // System.out.println(anoDoFilme.getText());
        // System.out.println(duracaoDoFilme.getText());
        // System.out.println(notaDoFilme.getText());

        f.setNome(nomeDoFilme.getText());
        f.setAno(anoDoFilme.getText());
        f.setDuracao(duracaoDoFilme.getText());
        f.setNota(notaDoFilme.getText());

        System.out.println(f.toString());

        driver.quit();
    }

}
