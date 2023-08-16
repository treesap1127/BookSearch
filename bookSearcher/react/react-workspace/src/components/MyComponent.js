import React, { useState } from 'react';
import { Outlet, Link } from "react-router-dom";
function App() {
  return (
    <div>
      <h1>JpaTest</h1>
      <Link to="/JpaTest">JpaTest</Link>
      <h1>MongoDB</h1> 
      <Link to="/MongoDB">MongoDB</Link>
      <h1>Mybatis</h1> 
      <Link to="/Mybatis">Mybatis</Link>
      <Outlet />
    </div>
  );
  };

export default App;
