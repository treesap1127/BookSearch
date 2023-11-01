import React from 'react';
import { createRoot } from 'react-dom/client'; // Import from "react-dom/client" instead of "react-dom"
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Mybatis from './components/Mybatis';
import Search from './components/Search';
import Indexing from './components/indexing';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <Router>
    <React.StrictMode>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/Mybatis" element={<Mybatis />} />
        <Route path="/search" element={<Search />} />
        <Route path="/Indexing" element={<Indexing />} />
      </Routes>
    </React.StrictMode>
  </Router>
);

reportWebVitals();
