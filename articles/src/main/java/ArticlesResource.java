import me.dbecaj.gpu_store.models.Article;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

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
        // Clear cache so we get current data (other services can update data)
        em.getEntityManagerFactory().getCache().evictAll();

        List<Article> articles = em
                .createNamedQuery("Article.findAll", Article.class)
                .setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache)
                .getResultList();

        return Response.ok(articles).header("Access-Control-Allow-Origin", "*").build();
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
