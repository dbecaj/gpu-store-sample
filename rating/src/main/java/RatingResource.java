import me.dbecaj.gpu_store.models.Article;
import me.dbecaj.gpu_store.models.SimpleError;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/article")
public class RatingResource {

    @PersistenceContext
    private EntityManager em;

    @PUT
    @Path("/{articleId}/rating")
    public Response setRating(@PathParam("articleId") Integer articleId, Article updatedArticle) {
        Article article = em.find(Article.class, articleId);
        if (article == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleError(String.format("Article with id %d doesn't exist", articleId)))
                    .build();
        }

        em.getTransaction().begin();
        article.setRating(updatedArticle.getRating());
        em.getTransaction().commit();

        return Response.status(Response.Status.ACCEPTED).entity(article).build();
    }
}
