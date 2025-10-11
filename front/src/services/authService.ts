// Importanto biblioteca responsável por requisições HTTP
import axios from 'axios';

// Definindo a base do url para os endpoints
const apiUrl = "http://localhost:8080";

// Definindo o objeto do serviço
const authService = {

    // Definindo a função de login
    async authenticate(login: {username: string, password: string}) {
        const endpoint = `${apiUrl}/user/login`
        return axios.post(endpoint, login);
    },

    // Função para salvar o usuário logado no local storage
    setLoggedToken(token : string){
        let parsedToken = JSON.stringify(token)
        sessionStorage.setItem("token",parsedToken)
    },

    // Função responsável por recuperar o usuário logado do local storage
    getLoggedToken(){
        let token = localStorage.getItem("token");
        if(!token) return null;
        try {
            let parsedToken = JSON.parse(token)
            return parsedToken
        } catch (error) {
            console.log(error)
            return null
        }
    }
}

export default authService;