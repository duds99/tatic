# tatic

--------------------------------------------------------------------------------------
A ideia:

A ideia usada para deixar o sistema melhor foi processar o arquivo de entrada e dividi-lo em varios
arquivos pequenos separados por yyyyMMddhh e apos a separação foi feito a compactacao de todos para
ocupar menos espaço no disco.

Como os arquivos foram criados com suas respectivas dadas, ficou facil encontrar o dado desejado,
o programa buscador sabe exatamente quais arquivos se deve varrer, ao encontrar o mesmo ele é
descompactado para a leitura.

--------------------------------------------------------------------------------------
Compilar e executar o projeto passo a passo

1 - git clone https://github.com/duds99/tatic.git
2 - Pelo terminal navegue pelo terminal com comando cd até a pasta tatic/src/main/java
3 - Compile o projeto com o comando: javac tatictest/Main.java
4 - Para executar o programa dê o comando: java tatictest/Main

----------------------------------------------------------------------------------------------------

Execução

Comandos existentes:
armazenador
buscador
sair

Para que o programa funcione, o primeiro comando que deve ser dado é o armazenador, pois o buscador
depende dele para o funcionamento, caso tente usar o comando buscador antes, o programa ira dizer
que primeiro é necessário usar o armazenador (caso ele nunca tenha realizado essa função anteriormente).

Exemplos de execução armazenador:

armazenador caminhoCompletoDoArquivo
armazenador /Users/eduardofigueiredo/Downloads/sample.txt

Exemplos de execução buscador:
Sem filtro de id:
buscador 20170206175709744 20170219124557428

Com filtro de id:
buscador 20170206175709744 20170219124557428 BFD99205 B5079387 0A23AFD0

----------------------------------------------------------------------------------------------------

Rodar tests unitários

1 - git clone https://github.com/duds99/tatic.git
2 - Pelo eclipse importe o projeto como um projeto Maven existente
3 - Clique com o botao direito no package de test e clique: run as -> jUnit
