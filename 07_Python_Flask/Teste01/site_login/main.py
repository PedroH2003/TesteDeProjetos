from flask import Flask, render_template, request, redirect

app = Flask(__name__)

@app.route('/')
def inicio():
    return render_template('inicio.html')

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/perfil', methods=['GET','POST'])
def perfil():
    nome = request.form.get('nome')
    senha = request.form.get('senha')
    print(nome)
    print(senha)

    return render_template('perfil.html', nome=nome) # aqui eu passei só o nome para meu template perfil.html mas e se
    # eu usar o nome e a senha pra procurar esse usuário em uma tabela do banco de dados, ai nessa tabela eu pego mais
    # um monte de informações sobre o usuário e mando para o meu template todas essas informações em um formato de lista
    # ou algo do tipo.

if __name__ in "__main__":
    app.run(debug=True)