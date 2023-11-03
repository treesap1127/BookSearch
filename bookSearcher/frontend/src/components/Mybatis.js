import React, { useEffect, useState } from 'react';
import { Outlet, Link } from "react-router-dom";
import axios from 'axios';

const Mybatis = () => {
const [testData, setTestData] = useState('');

  useEffect(() => {
    fetchTestData();
  }, []);

  const fetchTestData = async () => {
    try {
      const response = await axios.get('/api/test/mysql');
      setTestData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>MyComponent파일</h1>
      <p>api/test/mysql Link Data: {testData}</p>
      <Link to="/">list</Link>
      <Outlet />
    </div>
  );
};

export default Mybatis;
