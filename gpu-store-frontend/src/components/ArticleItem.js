import './ArticleItem.css'
import StarRating from './StarRating'

export default function ArticleItem(props) {
  return (
    <div class="articleItem">
      <img src={props.imageURL} class="image" alt={`${props.name}`} />
      <div class="info">
        <h2 class="name">{props.name}</h2>
        <p class="price">{`${props.price}$`}</p>
        <div class="rating">
          <StarRating articleId={props.articleId} rating={props.rating} />
        </div>
      </div>
    </div>
  )
}