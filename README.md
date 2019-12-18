# OSEletronicosWeb
Projeto para estudo de caso de livro sobre Java EE. Usa o Hibernate 5.3.6, Payara 5, Primefaces 7.

Este projeto é a camada web para a camada de modelo do projeto https://github.com/jlbavaresco/OSEletronicosModel

O projeto utiliza o banco de dados PostgreSQL versão 11. 

Para utilizar o projeto crie um banco de dados chamado "oseletronicos", e restaure o backup existente no repositório (https://github.com/jlbavaresco/OSEletronicosModel/blob/master/src/backup_completo.backup). O usuário e senha de PostgreSQL configurados no arquivo persistence.xml são 'postgres' e 'postgres'.

Após, realize um clone do projeto https://github.com/jlbavaresco/OSEletronicosModel, pois ele é uma dependência para este projeto. 

O projeto utiliza o servidor Payara Server 5, mas pode ser utilizado com o servidor GlassFish 5 sem problemas. Na IDE NetBeans para usar o servidor Payara Server, é necessário primeiro instalar um plugin específico dele, e depois configurar o servidor no Netbeans. No endereço https://blog.payara.fish/adding-payara-server-to-netbeans pode-se visualizar como realizar esta tarefa. 

Antes de se realizar as configurações no Payara Server, deve-se copiar o driver JDBC do PostgreSQL (https://github.com/jlbavaresco/OSEletronicosWeb/blob/master/lib/postgresql-42.2.5.jar) para a pasta lib do Payara (payara5\glassfish\lib) , para ele reconheça o driver no momento da criação do recurso JDBC.

Antes de rodar o projeto WEB, execute o servidor Payara e acesse o console de administração, que por padrão funciona no endereço local http://localhost:4848/.

Clique no menu resources no lado esquedo da página, e clique no botão add resources. Clique no botão escolher arquivo e envie o arquivo glassfish-resources.xml (https://github.com/jlbavaresco/OSEletronicosWeb/blob/master/web/WEB-INF/glassfish-resources.xml). Serão criados um JDBC Connection Pool (post-gre-sql_oseletronicos_postgresPool) e um JDBC resource (jdbc/oseletronicos). Pode-se testar o pool de conexões acessando o recurso chamado post-gre-sql_oseletronicos_postgresPool no menu reources -> JDBC -> JDBC Connection Poll. Para testar clique no botão Ping.

Após é necessário configurar a segurança da aplicação no servidor. Para isso acesse o menu configurations -> server-config -> Security -> Realms -> new Realms

Informe os seguintes campos:

	name: OSEletronicos-Realm
	class: com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm
	JAAS Context: jdbcRealm
	JNDI: jdbc/oseletronicos
	User Table: usuario (nome da tabela que tem os dados do usuário)
	User Name Column: nome_usuario (campo da tabela que tem o nome do usuário)
	Password Column: senha (campo da tabela que tem a senha)
	Group Table: permissoes (nome da tabela associativa que tem as permissões do usuario)
	Group Table User Name Column: nome_usuario (campo da tabela que guarda a chave do usuário)
	Group Name Column: permissao (campo da tabela que guarda a chave da permissão)
	Password Encryption Algorithm: none
	Database User: postgres
	Database Password: postgres
	Digest Algorithm: none

Depois pode-se executar a aplicação. Para acessar caso tenha restaurado o backup do banco de dados use o usuário 'jorgebavaresco' e senha '123456'.







