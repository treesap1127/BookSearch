import React, { useState } from 'react';
import { Outlet, Link } from "react-router-dom";
import axios from 'axios';

const SearchPage = () => {
const [searchData, setSearchData] = useState('');

  //useEffect(() => {}, []);

  const searchBookIndex = async () => {
    const keyword = document.getElementById("keyword").value;
    console.log(keyword);
    if(keyword){
      try {
          const formData = new FormData();
          formData.append('keyword', keyword);
          const response = await axios.post('/api/book/search', formData, {
              headers: {},
          });
          
          if (response.status === 200) {
            setSearchData(response.data);
          }
      } catch (error) {
          console.error(error);
      }
      
    }
    else{
      alert('검색어를 입력해주세요.')
      
    }
};

  return (
    <div>
      <h1>MyComponent파일</h1>
      <input type="text" id="keyword" name="keyword" />
      <button onClick={searchBookIndex}>검색</button>
      <p>-- 검색 데이터 -- </p>
      <p>{searchData} </p>
      
      <Link to="/">list</Link>
      <Outlet />
    </div>
  );
};

export default SearchPage;
