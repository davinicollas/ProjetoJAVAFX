# ProjetoJAVAfx
Projeto criado no curso Java COMPLETO Programação Orientada a Objetos +Projetos

Construindo arquivo JAR
Lista de controle:
Gerando o arquvio jar...
**click project name -> Export -> Java -> Runnable JAR file -> Next 
**Select a classe principal "Main"
**Select o destino da pasta 
**Selecione a terceira opção
Levar os seguintes arquivos
**JAR file 
**db.properties 
**MySQL Connector 
**JavaFX SDK 
**Java SDK Instalation 
 
Lista de controle:

Install Java: https://www.oracle.com/technetwork/java/javase/downloads/index.html
Setup JAVA_HOME (ex: C:\Program Files\Java\jdk-11.0.3) 
Copy JavaFX 
Setup PATH_TO_FX (ex: C:\java-libs\javafx-sdk\lib) 
Coloque o conector MySQL na pasta lib
coloque os arquivos JAR & db.properties na mesma pasta 
Execute o aplicativo:
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp "Substituir pelo nome do app" 
application.Main 
Arquivo Bat (opcional)
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp "Substituir pelo nome do app" 
application.Main 
Atalho do Windows (opcional)
Alvo:
"C:\Program Files\Java\jdk-11.0.3\bin\java.exe" --module-path %PATH_TO_FX% --add-modules 
javafx.controls,javafx.fxml -cp "Substituir pelo nome do app" application.Main 
iniciar: 
C:\appfolde
