import React, { useEffect, useState } from 'react';
import { Outlet, Link } from "react-router-dom";
import axios from 'axios';

const MongoDB = () => {
    const [bookData, setBookData] = useState({
        mongoId: '',
        bookId: '',
        bookName: '',
        publisher: '',
      });

  useEffect(() => {
    fetchTestData();
  }, []);

  const fetchTestData = async () => {
    try {
      const response = await axios.get('/api/mongoTest');
      console.log(response);
      setBookData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>MyComponent파일</h1>
      <p>/api/mongoTest Link Data: {bookData.mongoId}</p>
      <p>/api/mongoTest Link Data: {bookData.bookId}</p>
      <p>/api/mongoTest Link Data: {bookData.bookName}</p>
      <p>/api/mongoTest Link Data: {bookData.publisher}</p>
      <Link to="/">list</Link>
      <Outlet />
    </div>
  );
};

export default MongoDB;
