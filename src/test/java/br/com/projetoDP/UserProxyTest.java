package br.com.projetoDP;

import br.com.projetoDP.dto.LoginRequest;
import br.com.projetoDP.entity.User;
import br.com.projetoDP.repository.UserRepository;
import br.com.projetoDP.service.UserProxy;
import br.com.projetoDP.utils.Role;
import br.com.projetoDP.utils.Type;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserProxyTest {

    UserProxy userProxy;
    UserRepository userRepository;

    @BeforeEach
    void setup(){
        userRepository = mock();
        userProxy = new UserProxy(userRepository);
    }

    @Test
    void deveRetornar201QuandoUsuarioValidoForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.CREATED.getStatusCode());

    }

    @Test
    void deveRetornar500QuandoUsuarioSemNomeForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = null;
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());


    }

    @Test
    void deveRetornar500QuandoUsuarioSemMatriculaForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula =  null;
        user.tipo = Type.SERVIDOR;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    }

    @Test
    void deveRetornar500QuandoUsuarioSemTipoForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João" ;
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = null;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    }

    @Test
    void deveRetornar500QuandoUsuarioSemEmailForCriado(){
        User user = new User();
        user.email = null;
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    }

    @Test
    void deveRetornar500QuandoUsuarioSemSenhaForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = null;
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    }

    @Test
    void deveRetornar500QuandoUsuarioSemRoleForCriado(){
        User user = new User();
        user.email = "example@gmail.com";
        user.password = "passoword";
        user.nome = "Dom João";
        user.role = null;
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        doThrow(PersistenceException.class).when(userRepository).persist(user);
        Response response = userProxy.createUser(user);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

    }

    @Test
    void deveRetornar403QuandoTentarDeletarUsuarioAdmin(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        when(userRepository.findById(1L)).thenReturn(user);
        Response response = userProxy.deleteUser(1L);
        assertEquals(response.getStatus(),Response.Status.FORBIDDEN.getStatusCode());
    }


    @Test
    void deveRetornar400QuandoTentarDeletarUsuarioInexistente(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.ADMIN.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        when(userRepository.findById(1L)).thenReturn(null);
        Response response = userProxy.deleteUser(1L);
        assertEquals(response.getStatus(),Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    void deveRetornar200QuandoUsuarioForDeletadoComSucesso(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.COMUM.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        when(userRepository.findById(1L)).thenReturn(user);
        Response response = userProxy.deleteUser(1L);
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
    }

    @Test
    void deveRetornar500QuandoHouverErroDePersistencia(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.COMUM.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        when(userRepository.findById(1L)).thenReturn(user);
        doThrow(PersistenceException.class).when(userRepository).delete(user);
        Response response = userProxy.deleteUser(1L);
        assertEquals(response.getStatus(),Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    @Test
    void deveRetornar200QuandoUsuarioValidoLogar(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.COMUM.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        LoginRequest request = new LoginRequest();
        request.email = "example@gmail.com";
        request.senha = "password";
        when(userRepository.findByEmail(request.email)).thenReturn(user);
        Response response = userProxy.login(request);
        assertEquals(response.getStatus(),Response.Status.OK.getStatusCode());
    }

    @Test
    void deveRetornar401QuandoUsuarioErrarSenha(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.COMUM.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        LoginRequest request = new LoginRequest();
        request.email = "example@gmail.com";
        request.senha = "wrong-password";
        when(userRepository.findByEmail(request.email)).thenReturn(user);
        Response response = userProxy.login(request);
        assertEquals(response.getStatus(),Response.Status.UNAUTHORIZED.getStatusCode());
    }

    @Test
    void deveRetornar401QuandoUsuarioTentarLogarComEmailInexistente(){
        User user = new User();
        user.id = 1L;
        user.email = "example@gmail.com";
        user.password = "password";
        user.nome = "Dom João";
        user.role = Role.COMUM.name();
        user.matricula = "121";
        user.tipo = Type.SERVIDOR;
        LoginRequest request = new LoginRequest();
        request.email = "example@gmail.com";
        request.senha = "password";
        when(userRepository.findByEmail(request.email)).thenReturn(null);
        Response response = userProxy.login(request);
        assertEquals(response.getStatus(),Response.Status.UNAUTHORIZED.getStatusCode());
    }

}
