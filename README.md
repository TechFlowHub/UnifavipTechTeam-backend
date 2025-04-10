# UnifavipTechTeam  

## Descrição  

O **UnifavipTechTeam** conecta alunos da Unifavip a estágios e empregos por meio de uma plataforma gamificada, promovendo o desenvolvimento de habilidades e ajudando empregadores a encontrar o perfil ideal para suas vagas.  

## Pré-requisitos  

- **Git**  
- **Java - Spring Boot**  
- **MySQL**  

## API  

### Register User  

Endpoint para registrar um usuário com o papel de "Student" ou "Teacher".  
**Nota:** Admins não podem ser registrados por esta rota. Não é necessário token de autenticação.

**Nota2:** Usuarios do com a role "student" so poderão se cadastrar com e-mail do tipo "@alunos.unifavip.edu.br" ja com a role "teacher" apenas com o:
@professores.unifavip.edu.br

**Endpoint: /auth/register**  


**Exemplo de Request (JSON) (POST):**  
```json  
{  
    "email": "exemploAluno@alunos.unifavip.edu.br",  
    "password": "123",  
    "role": "student"  
}
```
### Register Admin

Para registrar o admin você pode criar com qualquer tipo de email ex: gmail ou ate o institucional da favip.

**Endpoint: /auth/registerAdmin**

**Nota:** so será possivel criar este usuario caso você esteja usando um token de admin.

**Exemplo de Request (JSON) (POST):**
```json
{
	"email": "exemplogmail.com",
	"password": "123",
	"role": "admin"
}
```

### Login User

**Endpoint: /auth/login**

**Exemplo de Request (JSON) (POST):**

```json

{
	"email": "exemploAluno@alunos.unifavip.edu.br",
	"password": "123"
}
```

### RecoveryPassword

Para usar o RecoveryPassword você precisa ter a recovery key gerada na criação da conta do usuario.

**NOTA:** a recovery key e unica por usuario e ao usar para recoperar a senha ela fica invalida e será necessario solicitar uma nova recovery key.

**EndPoint: /user/recoveryPassword/{id}**

**Exemplo de Request (JSON) (PUT):**

```json
{
  "newPassword": "123456",
  "recoveryKey": "RECOVERY-KEY-AQUI"
}
```

### DeleteUser

**NOTA:** O DeleteUser so podera ser usado apenas com um token de admin

**EndPoint: /user/deleteUser/{id}**

**Exemplo de Request (DELETE): /user/deleteUser/1** 

### GetAllUser

**NOTA:** Para usar o getAllUser tera que ser passado um token admin.

**Endpoint: /user/getAllUser**

**Exemplo de Request (GET): /user/getAllUser**

### GetUser

**NOTA:** Para usar o getUser tera que ser passado um token admin.

**Endpoint: /user/getUser/{id}**

**Exemplo de Request (GET): /user/getUser/1**

### SendFirstAcessCode

**NOTA:** Para usar este Endpoint você so poderá usar em um e-mail que esteja cadastrado no banco de dados.

**Endpoint: /email/sendFirstAcessCode**

**Exemplo de Request (JSON) (POST):

```json
{
	"to": "exemplo@alunos.unifavip.edu.br"
}
```

### GetValid

Esta request serve para verificar o status da recovery key no caso se ela e valida ou não

**EndPoint: /recovery/getValid/{email}**

**Exemplo de Request (GET):** /recovery/getValid/exemplo@gmail.com 

### SendEmail

**Endpoint: /email/send**

**Exemplo de Request (JSON) (POST):**

```json
{
	"to": "exemplo@alunos.unifavip.edu.br",
	"subject": "exemplo de aviso importante",
	"text": "texto de exemplo"
}
```

### Post course

Esta request serve para adicionar um curso

**EndPoint: /courses/post**

**Exemplo de Request (JSON) (POST)**

```json
{
  "name": "Computer Science"
}
```

### Get all course

Esta request serve para puxar todos os cursos cadastrados

**EndPoint: /courses/get-all**

### Post star

Esta request serve para adicionar estrelas ao personal data

**EndPoint: /start/give**

**Exemplo de Request (JSON) (POST)**

```json
{
  "giverId": 2,
  "receiverId": 3
}
```

### Get star

**EndPoint: /star/get/{id}**

**Exemplo de Request (GET): /star/get/1**








