# EM CASO DE DÚVIDA ACESSE O SEGUINTE VÍDEO: "CRUD em Python - Python e MySQL" do canal "Hashtag Programação"

import mysql.connector

conexao = mysql.connector.connect(
    host='localhost',
    user='pedro',
    password='sjaphmaa',
    database='teste_crud',
)

cursor = conexao.cursor()

"""
#CREATE
descricao = "chinelo"
valor = 10.23
tabela = "vendas"

comando = f'INSERT INTO {tabela} (descricao, valor) VALUES ("{descricao}", {valor})'
cursor.execute(comando)
conexao.commit() # edita o banco de dados
"""

"""
#READ
comando = 'SELECT * FROM vendas'
cursor.execute(comando)
resultado = cursor.fetchall() # ler o banco de dados
print(resultado)

for i in resultado:
    for j in i:
        print(j)
"""

"""
#UPDATE
descricao = "chinelo"
valor = 666
tabela = "vendas"

comando = f'UPDATE {tabela} SET valor = {valor} WHERE descricao = "{descricao}"'
cursor.execute(comando)
conexao.commit() # edita o banco de dados
"""

"""
#DELETE
id = 1
tabela = "vendas"

comando = f'DELETE FROM {tabela} WHERE id = {id}'
cursor.execute(comando)
conexao.commit() # edita o banco de dados
"""

cursor.close()
conexao.close()