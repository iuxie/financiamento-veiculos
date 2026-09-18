# Financiamento de Veículos — Java Swing e Maven

Projeto acadêmico em Java 17, organizado na estrutura do `maven-archetype-quickstart`, com classes separadas por responsabilidade e herança para os tipos de veículo e formulários.

## Executar

Requisitos: **JDK 17 ou superior** e **Maven 3.6.3 ou superior**. Na pasta que contém o `pom.xml`:

```sh
mvn clean package
java -jar target/financiamento-veiculos-1.0-SNAPSHOT.jar
```

Para executar somente os testes:

```sh
mvn test
```

A primeira execução do Maven precisa de internet para baixar os plugins e o JUnit. O programa não precisa de dependências externas em execução. A janela Swing exige ambiente gráfico. Em uma IDE, importe o `pom.xml` como projeto Maven e execute `br.com.guilherme.financiamento.main`.

## Classes e responsabilidades

| Pacote / classe | Responsabilidade |
| --- | --- |
| `main.java` | Importa e instancia o serviço e a janela; inicia o Swing na thread correta |
| `model/Veiculo` | Superclasse abstrata com marca, modelo, ano, valor e validação comum |
| `model/VeiculoNovo` | Subclasse para veículos novos |
| `model/VeiculoUsado` | Subclasse que acrescenta quilometragem e proprietários e sobrescreve a validação |
| `model/CondicoesFinanciamento` | Dados e validação da entrada e das parcelas |
| `model/ResultadoFinanciamento` | Dados imutáveis do resultado |
| `service/FinanciamentoService` | Valida os modelos e calcula o financiamento sem depender de Swing |
| `util/ConversorNumerico` | Converte os campos numéricos no formato brasileiro |
| `view/JanelaFinanciamento` | Configura o JFrame e a rolagem |
| `view/PainelSimulacao` | Organiza os painéis e conecta os botões ao serviço |
| `view/PainelFormulario` | Superclasse abstrata com layout de formulário e observação de alterações |
| `view/PainelVeiculo` | Formulário do veículo e seleção novo/usado |
| `view/PainelVeiculoUsado` | Campos exclusivos do veículo usado |
| `view/PainelFinanciamento` | Entrada e quantidade de parcelas |
| `view/PainelResultado` | Formatação e exibição dos resultados |

`PainelVeiculo`, `PainelVeiculoUsado` e `PainelFinanciamento` herdam de `PainelFormulario`. `VeiculoNovo` e `VeiculoUsado` herdam de `Veiculo`; a chamada de `validar()` no serviço usa polimorfismo. As outras classes colaboram por composição. A lógica de cálculo fica no serviço, e não em `main.java`.

O nome `main.java` foi mantido em minúsculas conforme solicitado; a convenção usual de Java seria `Main.java`.

## Estrutura Maven e archetype

Fontes: `src/main/java`. Testes: `src/test/java`. Configuração: `pom.xml`. Saídas: `target` (ignorado pelo Git).

O archetype é um gerador de estrutura inicial, não uma dependência da aplicação. Para gerar um esqueleto equivalente **em outra pasta vazia**, use:

```sh
mvn archetype:generate -DgroupId=br.com.guilherme -DartifactId=financiamento-veiculos -Dpackage=br.com.guilherme.financiamento -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

Não execute esse comando dentro deste projeto pronto. Neste ambiente, o Maven não estava instalado e o download não foi concluído; a estrutura equivalente foi criada manualmente, sem alegar execução do gerador. O POM configura compilação para Java 17, testes JUnit e JAR com a classe principal no manifesto.

## Regras da atividade

- Marca em lista e modelo em texto, interpretando o primeiro “Modelo” do enunciado como “Marca”, conforme a imagem.
- Anos de 2026 até 2000 em ordem decrescente; parcelas 12, 24, 36, 48 e 60.
- Seleção exclusiva novo/usado; dados de usado ocultos quando novo.
- Entrada exibida somente quando marcada; campos ocultos não interferem no cálculo.
- Limpar restaura textos vazios, marca sem seleção válida, novo, ano 2026, sem entrada e 12 parcelas.
- Resultado aparece apenas após cálculo válido e é ocultado quando os dados mudam.
- LayoutManagers: BorderLayout, BoxLayout, GridBagLayout, FlowLayout e GridLayout.
- Números brasileiros: `50000`, `50000,00` e `50.000,00`; ponto é separador de milhar.
- Valor do veículo positivo; entrada positiva e inferior ao valor quando marcada; quilometragem não negativa e proprietários inteiros positivos para usados.

## Cálculo

A taxa adotada é **1,5% ao mês**, aplicada ao saldo devedor com **parcelas fixas pela Tabela Price**. Cada parcela representa um mês. Altere `FinanciamentoService.TAXA_MENSAL` para mudar a taxa; o rótulo na tela acompanha a constante. Esta é uma simulação didática sem tarifas, impostos ou seguros adicionais.

```text
financiado = valor do veículo - entrada
fator = (1 + taxa mensal) ^ quantidade de parcelas
parcela = financiado × taxa mensal × fator ÷ (fator − 1)
total a pagar = parcela × quantidade de parcelas
```

Em cada mês, os juros de 1,5% incidem sobre o saldo devedor daquele mês; o restante da parcela amortiza a dívida. São usados BigDecimal e arredondamento HALF_UP para centavos na parcela. O total exibido multiplica a parcela arredondada pelo prazo. Não inclui a entrada, conforme a definição de “total a pagar” da atividade.

Exemplo: R$ 50.000,00 de veículo, R$ 5.000,00 de entrada e 36 parcelas → R$ 45.000,00 financiados, **36 parcelas de R$ 1.626,86** e **R$ 58.566,96** nas parcelas. Somando a entrada, o desembolso total é R$ 63.566,96.

## Verificação realizada

Fontes compiladas com Java 17; verificações de cálculo, validação, herança, campos condicionais, invalidação de resultado e limpeza executadas sem janela gráfica. O mesmo conjunto é chamado por `FinanciamentoTest` no Maven. O ciclo `mvn package` e a aparência da janela não foram verificados neste ambiente.

## GitHub

Envie o conteúdo desta pasta para a raiz do repositório. Esta versão substitui a anterior: não mantenha `src/FinanciamentoVeiculos.java` e a pasta antiga `test` junto com a nova estrutura Maven.
