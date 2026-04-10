# Sistema de Cadastro de Clientes em Java + MySQL

## 📌 Descrição

Este projeto consiste em um **sistema de cadastro de clientes desenvolvido em Java utilizando Swing para a interface gráfica e MySQL para persistência de dados**.

O sistema permite registrar informações de clientes como:

* Nome
* Email
* Telefone
* CPF

Os dados são armazenados em um **banco de dados MySQL**, permitindo gerenciamento estruturado e consulta posterior.

Este projeto foi desenvolvido com fins **educacionais**, demonstrando conceitos importantes de:

* Interface gráfica com **Java Swing**
* Conexão com banco de dados utilizando **JDBC**
* Validação de CPF
* Máscara de entrada para telefone
* Estrutura básica de um sistema CRUD

---

# 🖥 Tecnologias Utilizadas

* **Java**
* **Java Swing**
* **MySQL**
* **JDBC (Java Database Connectivity)**
* **IntelliJ IDEA**

---

# 📂 Estrutura do Projeto

```
ProjetoCadastroClientes
│
├── Cadastro.java
├── mysql-connector-j.jar
└── clientes.sql
```

Descrição dos arquivos:

* **Cadastro.java** → Código principal do sistema com interface gráfica e conexão ao banco
* **mysql-connector-j.jar** → Driver JDBC necessário para conectar Java ao MySQL
* **clientes.sql** → Script SQL para criação do banco e da tabela

---

# 🗄 Estrutura do Banco de Dados

O banco de dados utilizado é:

```
cadastro_clientes
```

Tabela principal:

```
clientes
```

Estrutura da tabela:

| Campo    | Tipo              |
| -------- | ----------------- |
| id       | INT (Primary Key) |
| nome     | VARCHAR(100)      |
| email    | VARCHAR(100)      |
| telefone | VARCHAR(20)       |
| cpf      | VARCHAR(14)       |

Exemplo de registro salvo:

| id | nome | email                                             | telefone        | cpf         |
| -- | ---- | ------------------------------------------------- | --------------- | ----------- |
| 1  | Vini | [ffvini686@gmail.com](mailto:ffvini686@gmail.com) | (11) 45385-8385 | 43166285857 |

---

# ⚙ Como Configurar o Projeto

## 1️⃣ Instalar MySQL

Instale o MySQL Server e o MySQL Workbench.

---

## 2️⃣ Criar o banco de dados

Execute o script SQL:

```
clientes.sql
```

Ele irá criar:

* Banco `cadastro_clientes`
* Tabela `clientes`

---

## 3️⃣ Configurar conexão no código

No arquivo `Cadastro.java`, configure seu usuário e senha do MySQL:

```java
private static final String URL = "jdbc:mysql://localhost:3306/cadastro_clientes";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
```

---

## 4️⃣ Adicionar driver JDBC

Baixe o driver:

```
mysql-connector-j
```

No IntelliJ:

```
Project Structure
Libraries
+
Add JAR
```

Selecione:

```
mysql-connector-j-x.x.x.jar
```

---

# ▶ Como Executar o Projeto

1. Abra o projeto no **IntelliJ IDEA**
2. Verifique se o driver JDBC foi adicionado
3. Execute a classe:

```
Cadastro.java
```

A interface do sistema será aberta permitindo cadastrar clientes.

---

# 🧩 Funcionalidades do Sistema

✔ Cadastro de clientes
✔ Validação automática de CPF
✔ Máscara de telefone
✔ Conexão com banco de dados MySQL
✔ Inserção de registros na tabela `clientes`

---

# 📷 Interface do Sistema

Tela principal contém:

* Campo **Nome**
* Campo **Email**
* Campo **Telefone**
* Campo **CPF**
* Botão **Salvar**
* Botão **Limpar**

---

# 🚀 Possíveis Melhorias

Algumas evoluções que podem ser implementadas no sistema:

* Listar clientes em **JTable**
* Buscar cliente por **CPF**
* Editar cadastro
* Excluir cliente
* Criar **CRUD completo**
* Implementar arquitetura **MVC**
* Criar versão usando **JavaFX**

---

# 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos na disciplina de **Programação em Java**, demonstrando integração entre **Java e banco de dados MySQL**.
