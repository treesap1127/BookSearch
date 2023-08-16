import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Mybatis from './components/Mybatis';
import MongoDB from './components/MongoDB';
import JpaTest from './components/JpaTest';

ReactDOM.render(
  <Router>
    <React.StrictMode>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/Mybatis" element={<Mybatis />} />
        <Route path="/MongoDB" element={<MongoDB />} />
        <Route path="/JpaTest" element={<JpaTest />} />
      </Routes>
    </React.StrictMode>
  </Router>,
  document.getElementById('root')
);

reportWebVitals();
