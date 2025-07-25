import React, { useState } from "react";
import axios from "axios";

const AddProduct = () => {
  const [productData, setProductData] = useState({
    productName: "",
    price: 0,
    startDate: "",
    endDate: "",
    type: "",
  });

  const [images, setImages] = useState([]);

  const handleChange = (e) => {
    setProductData({ ...productData, [e.target.name]: e.target.value });
  };

  const handleFileChange = (e) => {
    setImages([...e.target.files]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("product", JSON.stringify(productData)); // this must be string
      images.forEach((file) => formData.append("image", file)); // this must match backend

      const response = await axios.post("http://localhost:8082/public/create", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      alert("Upload successful!");
      console.log(response.data);
    } catch (error) {
      console.error("Upload failed:", error);
      if (error.response) {
        alert(`Upload failed: ${error.response.data}`);
      } else {
        alert(`Upload failed: ${error.message}`);
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} encType="multipart/form-data">
      <input type="text" name="productName" placeholder="Product Name" onChange={handleChange} required />
      <input type="number" name="price" placeholder="Price" onChange={handleChange} required />
      <input type="datetime-local" name="startDate" onChange={handleChange} required />
      <input type="datetime-local" name="endDate" onChange={handleChange} required />
      <select name="type" onChange={handleChange} required>
        <option value="">Select Type</option>
        <option value="rent">Rent</option>
        <option value="sell">Sell</option>
      </select>
      <input type="file" multiple accept="image/*" onChange={handleFileChange} required />
      <button type="submit">Upload</button>
    </form>
  );
};

export default AddProduct;
