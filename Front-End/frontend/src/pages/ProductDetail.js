import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function ProductDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8082/public/product/${id}`)
      .then(response => {
        setProduct(response.data);
      })
      .catch(error => {
        console.error("Error fetching product:", error);
      });
  }, [id]);

  if (!product) {
    return <div>Loading product details...</div>;
  }

  return (
    <div style={{ textAlign: 'center', padding: '20px' }}>
      <img
        src={product.imageUrls[0]}
        alt={product.name}
        style={{ width: '300px', height: 'auto', borderRadius: '10px' }}
      />
      <h2>{product.name}</h2>
      <p><strong>Price:</strong> ₹{product.price}</p>
      <p><strong>Type:</strong> {product.type}</p>
      <p><strong>Available:</strong> {product.fromDate} to {product.toDate}</p>

      <button onClick={() => navigate('/')} style={{
        padding: '10px 20px',
        marginTop: '20px',
        borderRadius: '5px',
        backgroundColor: '#007bff',
        color: '#fff',
        border: 'none',
        cursor: 'pointer'
      }}>
        ← Back to Home
      </button>
    </div>
  );
}

export default ProductDetail;
