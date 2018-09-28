## Academia Java
Este projeto tem como objetivo exercitar a escrita de algoritmos e introduzir as ferramentas de básicas de desenvolvimento:
* Eclipse
* Maven (gerenciamento de dependências)
* JUnit + AssertJ (testes unitários)

Requisitos:
* [JDK 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)

## Exemplo
O pacote _br.com.neolog.academy.example_ é um exemplo de exercício resolvido para demostrar algumas técnicas de codificação e teste:
#### src/main/java
Nesta pasta estão os enunciados na forma de interface: _MinFinder_
Observe que o javadoc do método _findMinimum_ possui as seguintes informações:
* Comportamento: descrição funcional do método
* Premissas: neste caso não há premissas específicas sobre os dados de entrada.  
Tipicamente, as premissas poderiam incluir características ou pré-requisitos dos inputs do método ou estado do objeto como, por exemplo: valores estritamente positivos, array ordenado, valores entre um range delimitado, etc.
* Casos de borda: especificação do comportamento em cenários extremos (exceções)

Ainda nesta pasta, a classe _MinFinderImpl_ exemplifica a resolução deste exercício.
Observe que a implementação segue a ordem:
* Tratamento dos casos de borda
* Implementação do caso geral

#### src/test/java
Nesta pasta, são armazenados os testes unitários, úteis para validar a implementação do comportamento especificado pelo enunciado.
O arquivo _MinFinderTest_ possui diversos casos de teste, cada um com objetivo específico, com a finalidade de verificar se os comportamentos previstos no contrato do método estão sendo cumpridos.
  
Para executar os testes definidos na classe, basta utilizar a hot-key _F11_ (debug).
Note que a implementação concreta sob teste é declarada no início do corpo da classe de teste:
>	/**  
>	 \* Atribua ao subject a implementação a ser testada.  
>	 */  
>	private final MinFinder subject = new MinFinderImpl();

Nos exercícios, a instância sob teste (também chamada de subject) deve ser atribuída antes de executar os testes.
Nos métodos de teste, observe que os nomes são bastante extensos, descrevendo qual o comportamento deve ser observado dadas as condições do teste. Por exemplo: _shouldReturnZeroWhenArrayHasOnlyZeroAsElement_

Ademais, observe o teste _shouldDetectMinPossibleValueShortCircuiting_. Este teste além de verificar o resultado do método, também tem a responsabilidade de medir o tempo de execução. Na classe _MinFinderImpl_, substitua o conteúdo da linha 16 de
> for (int i = 1; i < values.length && min > Integer.MIN_VALUE; i++) {

por
> for (int i = 1; i < values.length; i++) {
 
Ao executar os testes, observe que todos os valores de resultados esperados são obtidos com sucesso. Entretanto, o teste deve apresentar falha por estourar o tempo de execução esperado para o cenário.