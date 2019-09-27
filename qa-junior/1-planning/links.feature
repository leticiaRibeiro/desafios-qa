Feature: Cenários de teste da homepage do blog da Idwall

Scenario: Testar link dos menus
    Given Acessar homepage do blog
    When Acessar os links do header
    Then Todos devem retornar 200

Scenario: Testar link do título dos posts
    Given Acessar homepage do blog
    When Acessar os links dos posts da primeira pagina
    Then Todos devem retornar 200

Scenario: Testar link do logo
    Given Acessar homepage do blog
    When Acessar o link do logo
    Then O link do logo deve possuir a mesma URL da homepage

Scenario Outline: Pesquisar um post no blog da Idwall
    Given Acessar homepage do blog
    When Eu procuro pelo título "<titulo>" de um post
    Then Deve exibir o post pesquisado em uma pagina de resultados

    Examples:
        |titulo     |
        |mastercard |
        |PEP        |

Scenario: Testar link dos autores dos posts
    Given Acessar homepage do blog
    When Acessar os links de cada autor
    Then Todos os links devem ser diferentes da URL da homepage

Scenario: Acessar todos os links das imagens dos posts apresentados na pagina inicial
	Given Acessar homepage do blog
	When Eu acesso os links das imagens dos posts da primeira pagina
	Then Haha