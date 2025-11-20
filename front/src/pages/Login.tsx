import { Card, Field, Stack, Input, Button, Flex } from "@chakra-ui/react";
import authService from "../services/authService"
import { useState } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  async function sendLogin(e) {
    e.preventDefault();
    let login = { username, password };

    try {
      let res = await authService.authenticate(login);
      authService.setLoggedToken(res.data.token);
    } catch (error) {
      console.log(error);
      alert("Erro ao efetuar login.");
    }
  }
  return (
    <Flex width="100vw" height="100vh" align="center" justify="center">
      <Card.Root maxW="500px" w="90vw">
        <Card.Header>
          <Card.Title>Login</Card.Title>
        </Card.Header>
        <Card.Body>
          <Stack gap="4" w="full">
            <Field.Root>
              <Field.Label>Usuário</Field.Label>
              <Input />
            </Field.Root>
            <Field.Root>
              <Field.Label>Senha</Field.Label>
              <Input />
            </Field.Root>
          </Stack>
        </Card.Body>
        <Card.Footer justifyContent="flex-end">
          <Button variant="outline">Registrar</Button>
          <Button variant="solid">Entrar</Button>
        </Card.Footer>
      </Card.Root>
    </Flex>
  );
}

export default Login;
