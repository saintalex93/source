## Academia Java
Este projeto tem como objetivo exercitar a escrita de algoritmos e introduzir as ferramentas de b�sicas de desenvolvimento:
* Eclipse
* Maven (gerenciamento de depend�ncias)
* JUnit + AssertJ (testes unit�rios)

Requisitos:
* [JDK 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)

## Exemplo
O pacote _br.com.neolog.academy.example_ � um exemplo de exerc�cio resolvido para demostrar algumas t�cnicas de codifica��o e teste:
#### src/main/java
Nesta pasta est�o os enunciados na forma de interface: _MinFinder_
Observe que o javadoc do m�todo _findMinimum_ possui as seguintes informa��es:
* Comportamento: descri��o funcional do m�todo
* Premissas: neste caso n�o h� premissas espec�ficas sobre os dados de entrada.  
Tipicamente, as premissas poderiam incluir caracter�sticas ou pr�-requisitos dos inputs do m�todo ou estado do objeto como, por exemplo: valores estritamente positivos, array ordenado, valores entre um range delimitado, etc.
* Casos de borda: especifica��o do comportamento em cen�rios extremos (exce��es)

Ainda nesta pasta, a classe _MinFinderImpl_ exemplifica a resolu��o deste exerc�cio.
Observe que a implementa��o segue a ordem:
* Tratamento dos casos de borda
* Implementa��o do caso geral

#### src/test/java
Nesta pasta, s�o armazenados os testes unit�rios, �teis para validar a implementa��o do comportamento especificado pelo enunciado.
O arquivo _MinFinderTest_ possui diversos casos de teste, cada um com objetivo espec�fico, com a finalidade de verificar se os comportamentos previstos no contrato do m�todo est�o sendo cumpridos.
  
Para executar os testes definidos na classe, basta utilizar a hot-key _F11_ (debug).
Note que a implementa��o concreta sob teste � declarada no in�cio do corpo da classe de teste:
>	/**  
>	 \* Atribua ao subject a implementa��o a ser testada.  
>	 */  
>	private final MinFinder subject = new MinFinderImpl();

Nos exerc�cios, a inst�ncia sob teste (tamb�m chamada de subject) deve ser atribu�da antes de executar os testes.
Nos m�todos de teste, observe que os nomes s�o bastante extensos, descrevendo qual o comportamento deve ser observado dadas as condi��es do teste. Por exemplo: _shouldReturnZeroWhenArrayHasOnlyZeroAsElement_

Ademais, observe o teste _shouldDetectMinPossibleValueShortCircuiting_. Este teste al�m de verificar o resultado do m�todo, tamb�m tem a responsabilidade de medir o tempo de execu��o. Na classe _MinFinderImpl_, substitua o conte�do da linha 16 de
> for (int i = 1; i < values.length && min > Integer.MIN_VALUE; i++) {

por
> for (int i = 1; i < values.length; i++) {
 
Ao executar os testes, observe que todos os valores de resultados esperados s�o obtidos com sucesso. Entretanto, o teste deve apresentar falha por estourar o tempo de execu��o esperado para o cen�rio.