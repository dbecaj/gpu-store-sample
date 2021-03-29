import { useState } from 'react';
import './StarRating.css'

const Star = ({ index, rating, onMouseEnter, onMouseLeave, onClick }) => {
  
  return (
    <div
      className="star"
      onMouseEnter={onMouseEnter}
      onMouseLeave={onMouseLeave}
      onClick={onClick}
    >
      <svg
        height="55px"
        width="53px"
        class={rating && rating >= index ? "starFilled" : "starBlank"}
        viewBox="0 0 25 23"
        data-rating="1"
      >
        <polygon
          stroke-width="0"
          points="9.9, 1.1, 3.3, 21.78, 19.8, 8.58, 0, 8.58, 16.5, 21.78"
        />
      </svg>
    </div>
  );
};

export default function StarRating(props) {
  const [rating, setRating] = useState(props.rating);
  const [hoverRating, setHoverRating] = useState(0);

  return (
    <div class="starRating">
      { /* Generate stars */ }
      {Array.from([1, 2, 3, 4, 5]).map(i => {
        return (
          <Star 
            key={i}
            index={i}
            rating={hoverRating || rating}
            onMouseEnter={() => setHoverRating(i)}
            onMouseLeave={() => setHoverRating(0)}
            onClick={() => {
              setRating(i);

              // Update article rating
              const requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ rating: i })
              };
              fetch(`http://localhost:8081/v1/article/${props.articleId}/rating`, requestOptions)
                .then(response => response.json())
                .then(data => {
                  console.log(data);
                  if (!data || !data.id) {
                    console.log("Error with star rating update");
                    console.error(data?.message);
                  }
                })
                .catch(error => {
                  console.log("There was an error!", error);
                });
            }}
          />
        )
      })}
    </div>
  )
}
