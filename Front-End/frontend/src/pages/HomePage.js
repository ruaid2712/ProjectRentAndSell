import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import './HomePage.css'; // 👈 Import the CSS

const API_URL = process.env.REACT_APP_BACKEND_URL;
function HomePage() {
  const [products, setProducts] = useState([]);
  


  useEffect(() => {
    axios.get(`${API_URL}/public/home-page`)
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error("Error fetching products:", error);
      });
  }, []);

  return (
    <div>
      <h2 style={{ textAlign: 'center' }}>Available Products</h2>
      <div className="product-list">
        {products.map(product => (
  <Link to={`/product/${product.id}`} className="product-card" key={product.id}>
    <img src={product.imageUrl} alt={product.productName} />
    <h3>{product.productName}</h3>
    <p>₹{product.price}</p>
  </Link>
))}


      </div>
    </div>
  );
}

export default HomePage;
