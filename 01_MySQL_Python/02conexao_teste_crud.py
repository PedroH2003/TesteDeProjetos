import mysql.connector

conexao = mysql.connector.connect(
    host='localhost',
    user='pedro',
    password='sjaphmaa',
    database='teste_crud',
)

cursor = conexao.cursor()

print("Olá, bem vindp ao banco de dados teste_crudo")

while True:
    print("O que você deseja fazer no banco de dados? (Digite o número da opção desejada)")
    print("(1) Mostrar alguma tabela")
    print("(2) Sair do banco de dados")
    opc = int(input("Opcao escolhida: "))

    if opc != 1 and opc != 2:
        print("O número deve ser 1 ou 2...")
    elif opc == 1:
        tabela = input("Qual o nome da tabela que você deseja acessar? ")
        comando = f'SELECT * FROM {tabela}'
        cursor.execute(comando)
        resultado = cursor.fetchall()

        print()
        for i in resultado:
            for j in i:
                print(j, end=" ")
            print()
        print("\n\n")

    else:
        break



cursor.close()
conexao.close()