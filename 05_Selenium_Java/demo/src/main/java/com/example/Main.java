package com.example;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main{
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String pesquisa = s.nextLine();

        // Configurar o caminho do driver (substitua pelo seu caminho real)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\FUJIOKA\\Desktop\\07_Repositórios\\chromedriver-win64\\chromedriver.exe");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();

        // Abrir o IMDb
        driver.get("https://www.imdb.com");

        // Aguardar alguns segundos para visualização (opcional)
        try {
            Thread.sleep(5000); // Espera por 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exemplo de interação: encontrar a barra de pesquisa e inserir uma consulta
        // WebElement barraPesquisa = driver.findElement(By.name("q"));
        WebElement barraPesquisa = driver.findElement(By.xpath("//*[@id=\"suggestion-search\"]"));
        barraPesquisa.sendKeys(pesquisa);

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

        // Imprimir as informações        
        System.out.println("Nome do Filme: " + nomeDoFilme.getText());
        System.out.println("Duração do Filme: " + duracaoDoFilme.getText());
        System.out.println("Ano do Filme: " + anoDoFilme.getText());
        System.out.println("Nota do Filme: " + notaDoFilme.getText());

        // Fechar o navegador
        driver.quit();
    }
}


