import me.dbecaj.gpu_store.models.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/articles")
public class ArticlesResource {

    @PersistenceContext
    private EntityManager em;

    @GET
    public Response getArticles() {
        List<Article> articles = em
                .createNamedQuery("Article.findAll", Article.class)
                .getResultList();

        return Response.ok(articles).build();
    }

    @POST
    public Response createArticle(Article article) {
        article.setId(null);

        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(article).build();
    }
}
