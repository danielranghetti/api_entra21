package br.com.proway.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import br.com.proway.api.dao.UsuarioDAO;

import br.com.proway.api.model.Usuario;
import br.com.proway.api.model.util.Status;

@Path("usuarios")
public class UsuarioController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Usuario> listChamados() {
		try {
			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			return UsuarioDAO.listar();
		} catch (Exception ex) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Usuario getChamado(@PathParam("id") long id) {
		try {
			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			return UsuarioDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Usuario Usuario) {
		try {
			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			UsuarioDAO.inserir(Usuario);
			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Usuario Usuario) {
		try {
			Usuario.setStatus(Status.PENDENTE);

			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			UsuarioDAO.alterar(Usuario);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			UsuarioDAO UsuarioDAO = new UsuarioDAO();
			UsuarioDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("{id}/")
	public Response concluir(@PathParam("id") long id) {
		try {
			UsuarioDAO UsuarioDAO = new UsuarioDAO();

			Usuario c = UsuarioDAO.selecionar(id);
			c.setStatus(Status.FECHADO);

			UsuarioDAO.alterar(c);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ChamadoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
