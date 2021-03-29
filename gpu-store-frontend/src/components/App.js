import { useEffect, useState } from 'react';
import './App.css';
import ArticleItem from './ArticleItem';
import Layout from './Layout';

function App() {
  const [articleList, setArticleList] = useState([])

  useEffect(() => {
    // Load all articles
    fetch('http://localhost:8080/v1/articles')
      .then(response => response.json())
      .then(data => setArticleList(data))
  }, [])

  return (
    <div className="App">
      <header className="appHeader">
        <h1>GPU Store</h1>
      </header>

      <Layout>
        {articleList.map(article => {
          console.log(article);
          return (
            <div class="articleContainer">
              <ArticleItem
                articleId={article.id}
                name={article.name}
                imageURL={article.imageURL}
                price={article.price}
                rating={article.rating}
              />

              <hr />
            </div>
          )
        })}
      </Layout>
    </div>
  );
}

export default App;
