package com.idwall.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Stepdefs {

    WebDriver driver;
    String homePage = "https://blog.idwall.co/";
    HttpURLConnection huc = null;
    int respCode;
    List<String> urls = new ArrayList<>();
    Set<String> urlAutor = new HashSet<>();
    String linkLogo;

    @Given("^Acessar homepage do blog$")
    public void acessar_home_page() {
//        System.setProperty("webdriver.chrome.driver", "./driver/linux/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "./driver/windows/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "./driver/mac/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        System.out.println("Acessando " + homePage);
        driver.get(homePage);
    }

    @When("^Acessar os links do header$")
    public void acessar_links_do_header() {
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@id='menu-menu-principal-1']//li//a"));
        elements.forEach(elemento -> urls.add(elemento.getAttribute("href")));
        driver.quit();
    }

    @When("^Acessar os links dos posts da primeira pagina$")
    public void acessar_links_dos_posts() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='penci-wrapper-posts-content']//li//h2//a"));
        elements.forEach(elemento -> urls.add(elemento.getAttribute("href")));
        driver.quit();
    }

    @Then("^Todos devem retornar 200$")
    public void todos_devem_funcionar() {
        for (String url : urls) {
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();
                System.out.println(respCode);

                if (respCode != 200) {
                    throw new Exception("URL quebrada!");
                } else {
                    System.out.println(url + " está OK!");
                }
            } catch (Exception e) {
                Assert.fail(url + " está quebrada! Status code de retorno: "+respCode);
                e.printStackTrace();
            }
        }
    }

    @When("^Acessar o link do logo$")
    public void acessar_link_do_logo() {
        WebElement logo = driver.findElement(By.xpath("//div[@id='logo']//a"));
        linkLogo = logo.getAttribute("href");
        driver.quit();
    }

    @Then("^O link do logo deve possuir a mesma URL da homepage$")
    public void deve_enviar_para_homepage() {
        if(!homePage.equals(linkLogo))
            Assert.fail("URL do logo ["+linkLogo+"] não é igual a URL da homepage ["+homePage+"]!");
    }

    @When("^Eu procuro pelo título \"(.*)\" de um post$")
    public void realizar_busca(String titulo) {
        driver.findElement(By.xpath("//a[@class='search-click']")).click();
        driver.findElement(By.xpath("//input[@id='s']")).sendKeys(titulo);
        driver.findElement(By.xpath("//input[@id='s']")).sendKeys(Keys.RETURN);
    }

    @Then("^Deve exibir o post pesquisado em uma pagina de resultados$")
    public void deve_exibir_o_post_pesquisado_em_uma_pagina_de_resultados() throws Throwable {
        driver.quit();
    }

    @When("^Acessar os links de cada autor$")
    public void acessar_os_links_de_cada_autor() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='grid-post-box-meta']//a[@class='url fn n']"));
        elements.forEach(elemento -> urlAutor.add(elemento.getAttribute("href")));
        driver.quit();
    }

    @Then("^Todos os links devem ser diferentes da URL da homepage$")
    public void nao_devem_enviar_para_homepage() {
        String urlFinal = null;
        for (String url : urlAutor) {
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();
                urlFinal = huc.getURL().toString();

                if (respCode != 200 || !url.equals(urlFinal)) {
                    throw new Exception("URL quebrada!");
                } else {
                    System.out.println(url + " está OK!");
                }
            } catch (Exception e) {
                Assert.fail("A url " + url + " redirecionou para " + urlFinal + "; - Status code de retorno: " + respCode);
                e.printStackTrace();
            }
        }
    }

	@When("^Eu seleciono uma categoria \"(.*)\"$")
	public void eu_seleciono_uma_categoria_e_volto_para_a_homepage(String codigo) throws Throwable {
    	driver.findElement(By.xpath("//select[@id='cat']")).click();
    	driver.findElement(By.xpath("//option[@value=11]")).click();
	}

    @Then("^Deve listar os posts da categoria selecionada$")
    public void deve_listar_os_posts_da_categoria_selecionada() throws Throwable {
        driver.quit();
    }
}
