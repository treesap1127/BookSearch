import React, { useState } from 'react';
import { Outlet, Link } from "react-router-dom";
function App() {
  return (
    <div>
      <h1>JpaTest</h1>
      <Link to="/JpaTest">JpaTest</Link>
      <h1>Search</h1> 
      <Link to="/Search">Search</Link>
      <h1>indexing</h1> 
      <Link to="/Indexing">indexing</Link>
      <Outlet />
    </div>
  );
};

export default App;
