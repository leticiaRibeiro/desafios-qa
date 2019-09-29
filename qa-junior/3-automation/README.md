# Teste Analista de QA Júnior - IDWALL

## Requisitos para executar a automação

- Maven
- JDK 1.8+
- Google Chrome versão 76+

## Como executar o projeto

- Clonar o projeto
- Importar o projeto em sua IDE preferida (Eclipse, IntelliJ IDEA...)
- Alterar na classe "StepDefs" o driver do Google Chrome para o Sistema Operacional desejado:

*É só deixar descomentado o driver do sistema operacional a ser utilizado*.
![Método que contém os drivers para os SO](https://cdn.discordapp.com/attachments/265649360832823297/627677423156199473/unknown.png)
- Executar a classe "Runner" que se encontra em java/src/test/com/idwall

## Observações

O teste "Testar link dos autores dos posts" falhará devido ao erro encontrado para a página de autores. Maiores detalhes podem ser encontrados no documento de testes (2 - execution).

