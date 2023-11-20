# Projeto - Programação Orientada a Objeto Grupo 3
## Participantes
- Bruna Zimbrão Silva
- Luan Cordoeira Terra da Silva
- Manoel Vitor Laque Costa
- Bernardo Bonifácio Duarte da Silva
- Eduardo Pacheco Carvalho
- Fabiana Audi Von Haehling Lima
- Gustavo Monerat Rosa

### Abstract 
Este projeto tem como objetivo exercitar a criação de classes em Java e integrar com os conhecimentos obtidos em Banco de Dados. Essa integração é feita a partir do acesso, criação e armazenamento de dados através da conexão do Java com o PostgreSQL.

### E-commerce ✔️
Criar um banco de dados que contenha:

* Cliente
* Produto
* Pedido

### DER - Diagrama Entidade Relacionamento
![DER](/ProjetoPooGrupo3/DER%20BD.png)



### As features necessárias:
1. Cada tabela: ✔️
   - 1. Deve permitir cadastrar, alterar e excluir (CRUD): clientes, produtos e pedidos.
   - 2. Deve possuir classes para cada entidade.
   - 3. Deve possuir uma classe de conexão.
   - 4. Deve possuir classes DAO.
   - 5. Deve possuir método para selecionar um registro (localizar).
   - 6. Deve possuir método para selecionar mais de um registro (relatório).

2. O sistema deve possuir um menu para cada situação: ✔️

 - ``Produto``
 - ``Cliente``
 - ``Pedido``

3. Cada item deve possuir menu para permitir o CRUD (persistência de dados no banco Postgres) a localização e o relatório. ✔️
   - 1. Deve permitir selecionar um cliente (por código e/ou nome) e mostrar todos os pedidos que possui.
   - 2. Deve permitir selecionar um produto (por código e/ou nome) e mostrar todos os pedidos que possui.
   - 3. Deve permitir selecionar um pedido (por código e/ou data) e mostrar o seu cliente e todos os produtos que possui.
   - 4. Deve possuir os dados da empresa que vende o produto para o cliente.

4. Deve ser entregue o SQL de criação das tabelas e o projeto Java completo. ✔️
5. Deve ser entregue o Diagrama de Classes (UML) ✔️
   
### UML - Linguagem de modelagem unificada
![DER](/ProjetoPooGrupo3/UML.png)
