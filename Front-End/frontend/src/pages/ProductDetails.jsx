import React, { useEffect, useState, useRef } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import Slider from "react-slick";

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "./ProductDetails.css";


const ProductDetails = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const sliderRef = useRef();
  const API_URL = process.env.REACT_APP_BACKEND_URL;


  useEffect(() => {
    axios
      .get(`${API_URL}/public/product/${id}`)
      .then((res) => setProduct(res.data))
      .catch((err) => console.error("Failed to fetch product:", err));
  }, [id]);

  const settings = {
    dots: true,
    infinite: true,
    arrows: false,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };

  if (!product) return <p>Loading product...</p>;

  return (
    <div className="product-container">
      <div className="product-card">
        <div className="slider-wrapper">
          {/* Arrows */}
          <button className="arrow left" onClick={() => sliderRef.current.slickPrev()}>
            &#8592;
          </button>
          <button className="arrow right" onClick={() => sliderRef.current.slickNext()}>
            &#8594;
          </button>

          {/* Image Slider */}
          <Slider ref={sliderRef} {...settings}>
            {product.imageUrls?.map((url, index) => (
              <div key={index}>
                <img className="product-image" src={url} alt={`product-${index}`} />
              </div>
            ))}
          </Slider>
        </div>

        {/* Product Info */}
        <div className="product-info">
          <h2>{product.productName}</h2>
          <p><strong>Price:</strong> ₹{product.price}</p>
          <p><strong>Type:</strong> {product.type}</p>
          <p><strong>Start Date:</strong> {new Date(product.startDate).toLocaleDateString()}</p>
          <p><strong>End Date:</strong> {new Date(product.endDate).toLocaleDateString()}</p>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
